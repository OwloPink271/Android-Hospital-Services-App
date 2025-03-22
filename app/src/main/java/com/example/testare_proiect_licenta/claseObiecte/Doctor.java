package com.example.testare_proiect_licenta.claseObiecte;

import java.io.Serializable;
import java.util.List;

public class Doctor implements Serializable {

    private String nume;
    private String loc_de_munca;
    private String specializari;
    private String poza;
    private List<Servicii> listaServicii;
    private boolean lucreaza_casa_de_asigurari;


    public Doctor(String nume, String loc_de_munca, String specializari, String poza, List<Servicii> listaServicii, boolean lucreaza_casa_de_asigurari) {
        this.nume = nume;
        this.loc_de_munca = loc_de_munca;
        this.specializari = specializari;
        this.poza = poza;
        this.listaServicii = listaServicii;
        this.lucreaza_casa_de_asigurari = lucreaza_casa_de_asigurari;
    }

    public boolean isLucreaza_casa_de_asigurari() {
        return lucreaza_casa_de_asigurari;
    }

    public void setLucreaza_casa_de_asigurari(boolean lucreaza_casa_de_asigurari) {
        this.lucreaza_casa_de_asigurari = lucreaza_casa_de_asigurari;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getLoc_de_munca() {
        return loc_de_munca;
    }

    public void setLoc_de_munca(String loc_de_munca) {
        this.loc_de_munca = loc_de_munca;
    }

    public String getSpecializari() {
        return specializari;
    }

    public void setSpecializari(String specializari) {
        this.specializari = specializari;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public List<Servicii> getListaServicii() {
        return listaServicii;
    }

    public void setListaServicii(List<Servicii> listaServicii) {
        this.listaServicii = listaServicii;
    }
}
