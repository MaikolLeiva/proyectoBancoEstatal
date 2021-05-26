package com.wings.designs.ProyectoFraude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name= "Users")
public class Users {
    @Id
    @Column(
            name="RUT",
            updatable = false

    )
    private String rut;
    @Column(
            name="contraseña",
            nullable = false

    )
    private String password;

    public Users(String rut, String password) {
        this.rut = rut;
        this.password = password;
    }

    public Users() {

    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "rut='" + rut + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

