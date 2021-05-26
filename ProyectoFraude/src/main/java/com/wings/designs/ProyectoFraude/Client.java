package com.wings.designs.ProyectoFraude;

import javax.persistence.*;
import javax.sound.midi.Sequence;

@Entity (name="Client")
public class Client {



    @Id
    @Column(
            name="RUT",
            updatable = false

    )
    private String rut;
    @Column(
            name="nombre_completo",
            nullable = false

    )
    private String fullName;
    @Column(
            name="Direccion",
            nullable = false,
            columnDefinition = "TEXT"

    )
    private String address;
    @Column(
            name="correo",
            updatable = false,
            nullable = false,
            unique = true

    )
    private String mail;
    @Column(
            name="numero_cuenta",
            updatable = false,
            nullable = false,
            unique = true

    )
    private Long accountNumber;

    public Client( String rut, String fullName, String address, String mail, Long accountNumber) {
        this.rut = rut;
        this.fullName = fullName;
        this.address = address;
        this.mail = mail;
        this.accountNumber = accountNumber;
    }

    public Client() {

    }


    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }


    @Override
    public String toString() {
        return "Client{" +
                ", rut='" + rut + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
