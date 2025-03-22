package com.example.testare_proiect_licenta.claseObiecte;

import java.io.Serializable;
import java.util.List;

public class Servicii implements Serializable {

    private String nume;
    private String nume_Spital;
    private String nume_Doctor;
    private float pret;
    private boolean acoperit_casa;
    private String departament;
    private List<Doctor>doctori;

    public Servicii(String nume, String nume_Spital, String nume_Doctor, float pret, boolean acoperit_casa, String departament) {
        this.nume = nume;
        this.nume_Spital = nume_Spital;
        this.nume_Doctor = nume_Doctor;
        this.pret = pret;
        this.acoperit_casa = acoperit_casa;
        this.departament = departament;

    }

    public Servicii(String nume, String nume_Spital, String nume_Doctor, float pret, boolean acoperit_casa, String departament, List<Doctor> doctori) {
        this.nume = nume;
        this.nume_Spital = nume_Spital;
        this.nume_Doctor = nume_Doctor;
        this.pret = pret;
        this.acoperit_casa = acoperit_casa;
        this.departament = departament;
        this.doctori = doctori;
    }

    public List<Doctor> getDoctori() {
        return doctori;
    }

    public void setDoctori(List<Doctor> doctori) {
        this.doctori = doctori;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume_Spital() {
        return nume_Spital;
    }

    public void setNume_Spital(String nume_Spital) {
        this.nume_Spital = nume_Spital;
    }

    public String getNume_Doctor() {
        return nume_Doctor;
    }

    public void setNume_Doctor(String nume_Doctor) {
        this.nume_Doctor = nume_Doctor;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public boolean isAcoperit_casa() {
        return acoperit_casa;
    }

    public void setAcoperit_casa(boolean acoperit_casa) {
        this.acoperit_casa = acoperit_casa;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }
}
