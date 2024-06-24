package com.devsu.microservice.account.api.dto;

import com.devsu.microservice.account.api.entity.Account;
import com.devsu.microservice.account.api.model.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountResponse {
    @JsonProperty("Numero Cuenta")
    @NotBlank
    private String accountNumber;

    @JsonProperty("Tipo")
    @NotNull
    private AccountType accountType;

    @JsonProperty("Saldo Inicial")
    @NotNull
    private Double balance;

    @JsonProperty("Estado")
    private boolean status;

    @JsonProperty("Cliente")
    @NotNull
    private String clientName;

    public @NotBlank String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotBlank String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotNull AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotNull AccountType accountType) {
        this.accountType = accountType;
    }

    public @NotNull Double getBalance() {
        return balance;
    }

    public void setBalance(@NotNull Double balance) {
        this.balance = balance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public @NotNull String getClientName() {
        return clientName;
    }

    public void setClientName(@NotNull String clientName) {
        this.clientName = clientName;
    }

    public static AccountResponse fromAccountAndClientName(Account account, String clientName) {
        AccountResponse response = new AccountResponse();
        response.setAccountType(account.getAccountType());
        response.setClientName(clientName);
        response.setAccountNumber(account.getAccountNumber());
        response.setStatus(account.getStatus());
        response.setBalance(account.getBalance());

        return response;
    }
}
