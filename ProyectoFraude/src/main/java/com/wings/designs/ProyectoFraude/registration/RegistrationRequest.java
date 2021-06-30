/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.registration;

import jdk.nashorn.internal.objects.annotations.Getter;


public class RegistrationRequest {
    private String rut;
    private String password;
    private String fullName;
    private String address;
    private String email;
    private Long account;
    private Long phoneNumber;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String rut, String password, String fullName, String address, String email, Long account,
                               Long phoneNumber) {
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

    public Long getAccount() {
        return account;
    }

    public Long getPhoneNumber() {
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
