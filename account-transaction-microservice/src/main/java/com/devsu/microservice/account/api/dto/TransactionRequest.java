package com.devsu.microservice.account.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TransactionRequest {

    @NotNull
    private Double amount;

    @NotNull
    private String accountNumber;

    private Date date;

    public @NotNull Double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull Double amount) {
        this.amount = amount;
    }

    public @NotNull String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotNull String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
