package com.example.testare_proiect_licenta.claseObiecte;

import java.io.Serializable;
import java.util.List;

public class Departament implements Serializable {

    private String nume;
    private List<Doctor>lista_doctori;
    private List<Servicii>lista_servicii;


    public Departament(String nume, List<Doctor> lista_doctori, List<Servicii> lista_servicii) {
        this.nume = nume;
        this.lista_doctori = lista_doctori;
        this.lista_servicii = lista_servicii;
    }

    public Departament(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Doctor> getLista_doctori() {
        return lista_doctori;
    }

    public void setLista_doctori(List<Doctor> lista_doctori) {
        this.lista_doctori = lista_doctori;
    }

    public List<Servicii> getLista_servicii() {
        return lista_servicii;
    }

    public void setLista_servicii(List<Servicii> lista_servicii) {
        this.lista_servicii = lista_servicii;
    }
}
