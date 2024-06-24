package com.devsu.microservice.account.api.dto;

import com.devsu.microservice.account.api.entity.Transaction;
import com.devsu.microservice.account.api.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionResponse {

    @JsonProperty("Numero Cuenta")
    @NotBlank
    private String accountNumber;

    @JsonProperty("Tipo")
    @NotBlank
    private String type;

    @JsonProperty("Saldo Inicial")
    @NotNull
    private Double initialBalance;

    @JsonProperty("Estado")
    private boolean status;

    @JsonProperty("Movimiento")
    @NotBlank
    private String transaction;


    public @NotBlank String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotBlank String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotBlank String getType() {
        return type;
    }

    public void setType(@NotBlank String type) {
        this.type = type;
    }

    public @NotNull Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(@NotNull Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public @NotBlank String getTransaction() {
        return transaction;
    }

    public void setTransaction(@NotBlank String transaction) {
        this.transaction = transaction;
    }

    public static TransactionResponse fromTransactionEntity(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setAccountNumber(transaction.getAccount().getAccountNumber());
        response.setType(transaction.getType().name());
        response.setInitialBalance(transaction.getBalanceAfterTransaction() - transaction.getAmount());
        response.setStatus(transaction.getAccount().getStatus());

        StringBuilder stringBuilder = new StringBuilder();
        if(transaction.getType().equals(TransactionType.DEPOSITO)) {
            stringBuilder.append("Depósito de ");
        } else {
            stringBuilder.append("Retiro de ");
        }

        stringBuilder.append(transaction.getAmount());

        response.setTransaction(stringBuilder.toString());

        return response;
    }
}
