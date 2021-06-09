/*
* Copyright
* */
package com.wings.designs.ProyectoFraude.users;

import javax.persistence.*;

@Entity(name= "Users")
public class Users {
    @Id
    @SequenceGenerator(
            name="users-sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"

    )
    @Column(
            name="id",
            updatable = false

    )
    private Long id;
    @Column(
            name="rut",
            updatable = false,
            nullable =false

    )
    private String rut;
    @Column(
            name="password",
            nullable = false

    )
    private String password;
    @Column(
            name="full_name",
            nullable = false

    )

    private String fullName;
    @Column(
            name="address",
            nullable = false,
            columnDefinition = "TEXT"

    )
    private String address;
    @Column(
            name="email",
            updatable = false,
            unique = true

    )
    private String email;
    @Column(
            name="account",
            updatable = false

    )
    private Long account;
    @Column(
            name="userType"


    )
    private String userType;



    public Users(String rut, String password, String fullName,
                 String address, String email, Long account) {
        this.rut = rut;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.account = account;
        this.userType="CLIENTE";
    }

    public Users() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rutUser) {
        this.rut = rutUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordUser) {
        this.password = passwordUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullNameUser) {
        this.fullName = fullNameUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressUser) {
        this.address = addressUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailUser) {
        this.email = emailUser;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long accountNumber) {
        this.account = accountNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", rut='" + rut + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                ", userType='" + userType + '\'' +
                '}';
    }
}

