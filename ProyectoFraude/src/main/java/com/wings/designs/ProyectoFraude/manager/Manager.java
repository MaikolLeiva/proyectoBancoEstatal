package com.wings.designs.ProyectoFraude.manager;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity(name= "Manager")
public class Manager {
    @Id
    @Column(
            name="RUT",
            updatable = false
    )
    private String rut;
    @Column(
            name="nombre_completo",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"


    )
    private String fullName;

    public Manager( String rut, String fullName) {
        this.rut = rut;
        this.fullName = fullName;
    }

    public Manager() {

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

    @Override
    public String toString() {
        return "Manager{" +
                ", rut='" + rut + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
