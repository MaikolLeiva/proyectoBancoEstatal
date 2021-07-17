/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegistrationRequest {
    @NotBlank
    private final String rut;
    @NotBlank
    @Pattern(message = "not a valid account number",
            regexp = "^[0-9]{4}$")
    private final String password;
    @NotBlank
    @JsonProperty("fullname")
    private final String fullName;
    @NotBlank
    private final String address;
    @NotBlank
    private final String email;
    @NotBlank
    private final String account;
    @NotBlank
    @JsonProperty("phone_number")
    private final String phoneNumber;

    public RegistrationRequest(String rut, String password, String fullName, String address, String email, String account,
                               String phoneNumber) {
        this.rut = rut;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.account = account;
        this.phoneNumber = phoneNumber;
    }

    public String getRut() {
        return rut;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getAccount() {
        return account;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "rut='" + rut + '\'' +
                ", password=" + password +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
