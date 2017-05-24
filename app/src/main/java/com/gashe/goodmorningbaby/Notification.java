package com.gashe.goodmorningbaby;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gashe on 24/5/17.
 */

public class Notification implements Serializable {

    private String foto;
    private int anio;
    private int dia;
    private int mes;

    public Notification(String foto, int anio, int dia, int mes) {
        this.foto = foto;
        this.anio = anio;
        this.dia = dia;
        this.mes = mes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
