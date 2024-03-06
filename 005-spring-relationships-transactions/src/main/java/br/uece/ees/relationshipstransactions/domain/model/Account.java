package br.uece.ees.relationshipstransactions.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Generated(event = EventType.INSERT)
    @Column(name = "number", nullable = false, insertable = false, updatable = false)
    private Long number;

    @Enumerated
    @Column(name = "type", nullable = false)
    private AccountType type = AccountType.CHECKING;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Customer owner;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();

    public boolean hasEnoughMoney(BigDecimal amount) {
        return getBalance().compareTo(amount) >= 0;
    }

    public BigDecimal getBalance() {
        var credits = transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.CREDIT)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var debits = transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.DEBIT)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return credits.subtract(debits);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
