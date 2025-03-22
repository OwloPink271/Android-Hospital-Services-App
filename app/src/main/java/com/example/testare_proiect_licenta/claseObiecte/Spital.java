package com.example.testare_proiect_licenta.claseObiecte;

import java.io.Serializable;
import java.util.List;

public class Spital implements Serializable  {


    private String id;
    private String nume;
    private String telefon;
    private String adresa;
    private Double distanta;
    private List<Departament>lista_departamente;
    private String email;
    private boolean asigurare;
    private boolean public_privat;
    private String poza;

    public Spital(String nume, String telefon, String adresa, Double distanta, List<Departament> lista_departamente, String email, boolean asigurare, boolean public_privat, String poza) {
        this.nume = nume;
        this.telefon = telefon;
        this.adresa = adresa;
        this.distanta = distanta;
        this.lista_departamente = lista_departamente;
        this.email = email;
        this.asigurare = asigurare;
        this.public_privat = public_privat;
        this.poza = poza;
    }

    public Spital(String id,String nume, String telefon, String adresa, Double distanta, String email, boolean asigurare, boolean public_privat, String poza) {
        this.id=id;
        this.nume = nume;
        this.telefon = telefon;
        this.adresa = adresa;
        this.distanta = distanta;
        this.email = email;
        this.asigurare = asigurare;
        this.public_privat = public_privat;
        this.poza = poza;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Double getDistanta() {
        return distanta;
    }

    public void setDistanta(Double distanta) {
        this.distanta = distanta;
    }

    public List<Departament> getLista_departamente() {
        return lista_departamente;
    }

    public void setLista_departamente(List<Departament> lista_departamente) {
        this.lista_departamente = lista_departamente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAsigurare() {
        return asigurare;
    }

    public void setAsigurare(boolean asigurare) {
        this.asigurare = asigurare;
    }

    public boolean isPublic_privat() {
        return public_privat;
    }

    public void setPublic_privat(boolean public_privat) {
        this.public_privat = public_privat;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }
}
