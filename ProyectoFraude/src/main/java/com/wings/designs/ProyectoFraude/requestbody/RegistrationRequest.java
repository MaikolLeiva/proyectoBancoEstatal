/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wings.designs.ProyectoFraude.persistence.validation.ValidRut;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegistrationRequest {
    @NotBlank
    @ValidRut
    private final String rut;
    @NotBlank
    @Pattern(message = "not a valid account number",
            regexp = "^[0-9]{4}$")
    private final String password;
    @NotBlank
    @Pattern(message = "Not valid name: ${validatedValue}",
            regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?$")
    @JsonProperty("fullname")
    private final String fullName;
    @NotBlank
    private final String address;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    @Pattern(message = "not a valid account number",
            regexp = "^[0-9]{8,12}$")
    private final String account;
    @NotBlank
    @Pattern(message = "not a valid phone number: ${validatedValue}",
            regexp = "^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$")
    @JsonProperty("phone_number")
    private final String phoneNumber;

    public RegistrationRequest(final String rut, final String password,
                               final String fullName, final String address,
                               final String email, final String account,
                               final String phoneNumber) {
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
