package com.devsu.microservice.account.api.entity;

import com.devsu.microservice.account.api.model.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

import static com.devsu.microservice.account.api.model.TransactionType.DEPOSITO;
import static com.devsu.microservice.account.api.model.TransactionType.RETIRO;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType type;

    @NotNull
    private Double amount;

    @NotNull
    private Double balanceAfterTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    public @NotNull TransactionType getType() {
        return type;
    }

    public void setType(@NotNull TransactionType type) {
        this.type = type;
    }

    public void inferType() {
        this.type = this.amount < 0 ? RETIRO : DEPOSITO;
    }

    public @NotNull Double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull Double amount) {
        this.amount = amount;
    }

    public @NotNull Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(@NotNull Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void copy(Transaction source) {
        if (source.getDate() != null) {
            this.date = source.getDate();
        }
        if (source.getType() != null) {
            this.type = source.getType();
        }
        if (source.getAmount() != null) {
            this.amount = source.getAmount();
        }
        if (source.getBalanceAfterTransaction() != null) {
            this.balanceAfterTransaction = source.getBalanceAfterTransaction();
        }
        if (source.getAccount() != null) {
            this.account = source.getAccount();
        }
    }
}
