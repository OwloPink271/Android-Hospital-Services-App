package com.example.testare_proiect_licenta.old;

import java.io.Serializable;

public class test_item_class implements Serializable {

    private String nume;
    private long telefon;

    private String URL_imagine;
    private String spec;

    public test_item_class(String nume, long telefon, String URL_imagine, String spec) {
        this.nume = nume;
        this.telefon = telefon;
        this.URL_imagine = URL_imagine;
        this.spec=spec;

    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public long getTelefon() {
        return telefon;
    }

    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }

    public String getURL_imagine() {
        return URL_imagine;
    }

    public void setURL_imagine(String URL_imagine) {
        this.URL_imagine = URL_imagine;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "test_item_class{" +
                "nume='" + nume + '\'' +
                ", telefon=" + telefon +
                ", URL_imagine='" + URL_imagine + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }
}
