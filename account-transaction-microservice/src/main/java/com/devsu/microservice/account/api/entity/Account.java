package com.devsu.microservice.account.api.entity;

import com.devsu.microservice.account.api.model.AccountType;
import com.devsu.microservice.account.api.validation.UniqueAccountNumber;
import com.devsu.microservice.account.api.validation.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @UniqueAccountNumber(groups = OnCreate.class)
    private String accountNumber;

    @NotNull
    private Double balance;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType accountType;

    @NotNull
    private Boolean status;

    @NotNull
    private Long clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotBlank String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotNull Double getBalance() {
        return balance;
    }

    public void setBalance(@NotNull Double balance) {
        this.balance = balance;
    }

    public @NotNull AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotNull AccountType accountType) {
        this.accountType = accountType;
    }

    public @NotNull Boolean getStatus() {
        return status;
    }

    public void setStatus(@NotNull Boolean status) {
        this.status = status;
    }

    public @NotNull Long getClientId() {
        return clientId;
    }

    public void setClientId(@NotNull Long clientId) {
        this.clientId = clientId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void copy(Account source) {
        if (source.getAccountNumber() != null) {
            this.accountNumber = source.getAccountNumber();
        }
        if (source.getBalance() != null) {
            this.balance = source.getBalance();
        }
        if (source.getAccountType() != null) {
            this.accountType = source.getAccountType();
        }
        if (source.getStatus() != null) {
            this.status = source.getStatus();
        }
        if (source.getClientId() != null) {
            this.clientId = source.getClientId();
        }
        if (source.getTransactions() != null) {
            this.transactions = source.getTransactions();
        }
    }
}
