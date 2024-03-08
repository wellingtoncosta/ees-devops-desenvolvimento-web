package br.uece.ees.springtesting.domain.service;

import br.uece.ees.springtesting.domain.repository.AccountRepository;
import br.uece.ees.springtesting.domain.repository.TransactionRepository;
import br.uece.ees.springtesting.domain.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferMoneyService {

    /// region dependencies

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransferMoneyService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    /// endregion

    @Transactional
    public void execute(MoneyTransfer moneyTransfer) {
        var sender = getAccountOrThrow(moneyTransfer.accountSenderId());

        if (!sender.hasEnoughMoney(moneyTransfer.amount())) {
            throw new NoEnoughBalanceException(sender.getId());
        }

        var debit = new Transaction();
        debit.setAccount(sender);
        debit.setAmount(moneyTransfer.amount());
        debit.setType(TransactionType.DEBIT);

        transactionRepository.save(debit);

        var recipient = getAccountOrThrow(moneyTransfer.accountRecipientId());

        var credit = new Transaction();
        credit.setAccount(recipient);
        credit.setAmount(moneyTransfer.amount());
        credit.setType(TransactionType.CREDIT);

        transactionRepository.save(credit);
    }

    private Account getAccountOrThrow(Long id) {
        var account = accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new AccountNotFoundException(id);
        }

        return account.get();
    }
}
