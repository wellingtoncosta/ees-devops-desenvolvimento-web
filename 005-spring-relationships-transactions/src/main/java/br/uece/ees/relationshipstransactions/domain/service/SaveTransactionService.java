package br.uece.ees.relationshipstransactions.domain.service;

import br.uece.ees.relationshipstransactions.domain.model.CustomerNotFoundException;
import br.uece.ees.relationshipstransactions.domain.model.NoEnoughBalanceException;
import br.uece.ees.relationshipstransactions.domain.model.Transaction;
import br.uece.ees.relationshipstransactions.domain.model.TransactionType;
import br.uece.ees.relationshipstransactions.domain.repository.CustomerRepository;
import br.uece.ees.relationshipstransactions.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveTransactionService {

    /// region dependencies

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public SaveTransactionService(
            CustomerRepository customerRepository,
            TransactionRepository transactionRepository
    ) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    /// endregion

    public Transaction execute(Long customerId, Transaction transaction) {
        var customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }

        var account = customer.get().getAccount();
        transaction.setAccount(account);

        if (transaction.getType() == TransactionType.DEBIT && !account.hasEnoughMoney(transaction.getAmount())) {
            throw new NoEnoughBalanceException(account.getId());
        }

        return transactionRepository.save(transaction);
    }

}
