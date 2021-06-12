/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
/**
 *Represents a user of the system with his attributes and it's representation in the Database.
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 * */
@Entity(name= "Users")
public class Users {
    @Id
    @SequenceGenerator(name="users-sequence",sequenceName="users_sequence", allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "users_sequence")
    @Column(name="id",updatable = false)
    private Long id;
    @Column(name="rut",updatable = false,nullable =false)
    private String rut;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="fullname",nullable = false)
    @JsonProperty("fullname")
    private String fullName;
    @Column(name="address",nullable = false,columnDefinition = "TEXT")
    private String address;
    @Column(name="email",updatable = false,unique = true)
    private String email;
    @Column(name="account_number",updatable = false)
    private Long account;
    @Column(name="phone_number",updatable = false)
    @JsonProperty("phone_number")
    private Long phoneNumber;
    @Column(name="user_type")
    private enumUserType userType;

    /**
     * Constructor that receives all the attributes as parameters, except for the id.
     * @param rut Represent the rut of the person. A unique number assigned for every citizen that lives in Chile.
     * @param password Is the password of  the user, it has to be encrypted.
     * @param fullName Is the complete name of the user.
     * @param address Is the address of the user.
     * @param email Is the email of the user.
     * @param account Is the account number of the user.
     * @param phoneNumber Is the phone number of the user.
     * @param userType Is the type of rol that the user have.
     */
    public Users(String rut, String password, String fullName,String address, String email, Long account,
                 Long phoneNumber,enumUserType userType) {
        this.rut = rut;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.account = account;
        this.phoneNumber= phoneNumber;
        this.userType=userType;
    }

    /**
     * An Empty Constructor
     */
    public Users() {
    }
    public Long getId() {
        return id;
    }
    public String getRut() {
        return rut;
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
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public Long getAccount() {
        return account;
    }
    public enumUserType getUserType() {
        return userType;
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
                ", phoneNumber=" + phoneNumber +
                ", userType=" + userType +
                '}';
    }

    /**
     * Enum that defines all the possibles roles that the user can have.
     * <code>CLIENT<code/> allow users to create tickets.
     * <code>MANAGER<code/> allow users to take and close tickets.
     */
    enum enumUserType{
        CLIENT,
        MANAGER
    }
}

