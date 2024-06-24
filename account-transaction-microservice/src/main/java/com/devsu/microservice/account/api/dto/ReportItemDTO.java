package com.devsu.microservice.account.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ReportItemDTO {

    @JsonProperty("Fecha")
    @NotNull
    private Date date;

    @JsonProperty("Cliente")
    @NotBlank
    private String clientName;

    @JsonProperty("Numero Cuenta")
    @NotBlank
    private String accountNumber;

    @JsonProperty("Tipo")
    @NotBlank
    private String accountType;

    @JsonProperty("Saldo Inicial")
    @NotNull
    private Double initialBalance;

    @JsonProperty("Estado")
    private boolean status;

    @JsonProperty("Movimiento")
    private Double amount;

    @JsonProperty("Saldo Disponible")
    private Double balanceAfterTransaction;

    public @NotNull Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    public @NotBlank String getClientName() {
        return clientName;
    }

    public void setClientName(@NotBlank String clientName) {
        this.clientName = clientName;
    }

    public @NotBlank String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotBlank String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotBlank String getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotBlank String accountType) {
        this.accountType = accountType;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

}
