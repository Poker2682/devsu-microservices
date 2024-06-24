package com.devsu.microservice.client.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "clientId")
public class Client extends Person {

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    private Boolean status;

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotNull Boolean getStatus() {
        return status;
    }

    public void setStatus(@NotNull Boolean status) {
        this.status = status;
    }

    public void copy(Client source) {
        if (source.getPassword() != null) {
            this.password = source.getPassword();
        }
        if (source.getStatus() != null) {
            this.status = source.getStatus();
        }
        if (source.getName() != null) {
            this.setName(source.getName());
        }
        if (source.getGender() != null) {
            this.setGender(source.getGender());
        }
        if (source.getAge() != null) {
            this.setAge(source.getAge());
        }
        if (source.getIdentification() != null) {
            this.setIdentification(source.getIdentification());
        }
        if (source.getAddress() != null) {
            this.setAddress(source.getAddress());
        }
        if (source.getPhone() != null) {
            this.setPhone(source.getPhone());
        }
    }
}
