package br.uece.ees.relationshipstransactions.application.service;

import br.uece.ees.relationshipstransactions.application.model.NewTransactionDto;
import br.uece.ees.relationshipstransactions.application.model.TransactionDto;
import br.uece.ees.relationshipstransactions.application.model.MoneyTransferDto;
import br.uece.ees.relationshipstransactions.domain.model.Account;
import br.uece.ees.relationshipstransactions.domain.model.CustomerNotFoundException;
import br.uece.ees.relationshipstransactions.domain.repository.CustomerRepository;
import br.uece.ees.relationshipstransactions.domain.repository.TransactionRepository;
import br.uece.ees.relationshipstransactions.domain.service.SaveTransactionService;
import br.uece.ees.relationshipstransactions.domain.service.TransferMoneyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionApplicationService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final SaveTransactionService saveTransactionService;
    private final TransferMoneyService transferMoneyService;

    public TransactionApplicationService(
            CustomerRepository customerRepository,
            TransactionRepository transactionRepository,
            SaveTransactionService saveTransactionService,
            TransferMoneyService transferMoneyService
    ) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.saveTransactionService = saveTransactionService;
        this.transferMoneyService = transferMoneyService;
    }

    public List<TransactionDto> findAll(Long customerId) {
        var account = getAccountFromCustomerOrThrow(customerId);
        return TransactionDto.from(transactionRepository.findAllByAccountId(account.getId()));
    }

    public TransactionDto save(Long customerId, NewTransactionDto newTransactionDto) {
        var transactionSave = saveTransactionService.execute(customerId, newTransactionDto.toTransaction());
        return TransactionDto.from(transactionSave);
    }

    public void transfer(MoneyTransferDto moneyTransferDto) {
        transferMoneyService.execute(moneyTransferDto.toMoneyTransfer());
    }

    private Account getAccountFromCustomerOrThrow(Long customerId) {
        var customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }

        return customer.get().getAccount();
    }
}
