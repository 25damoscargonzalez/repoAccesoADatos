package com.oscargu.tiendamaterialfotografia.base;

import java.time.LocalDate;

public class Objetivo extends ProductoFotografico {
    private String alcanceLente; //Distancia focal de un objetivo, medida en mm, indica cuanto zoom tiene una lente
    private String aperturaMax; //Indica cuanta luz deja pasar el objetivo hacia el sensor de la cámara
    private String tipoMontura;
    private boolean tieneEstabilizador;

    public Objetivo() {

    }

    public Objetivo(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion, String alcanceLente, String aperturaMax, String tipoMontura, boolean tieneEstabilizador) {
        super(nombre, marca, precio, stock, fechaCreacion);
        this.alcanceLente = alcanceLente;
        this.aperturaMax = aperturaMax;
        this.tipoMontura = tipoMontura;
        this.tieneEstabilizador = tieneEstabilizador;
    }

    public String getAlcanceLente() {
        return alcanceLente;
    }

    public void setAlcanceLente(String alcanceLente) {
        this.alcanceLente = alcanceLente;
    }

    public String getAperturaMax() {
        return aperturaMax;
    }

    public void setAperturaMax(String aperturaMax) {
        this.aperturaMax = aperturaMax;
    }

    public String getTipoMontura() {
        return tipoMontura;
    }

    public void setTipoMontura(String tipoMontura) {
        this.tipoMontura = tipoMontura;
    }

    public boolean getTieneEstabilizador() {
        return tieneEstabilizador;
    }

    public void setTieneEstabilizador(boolean tieneEstabilizador) {
        this.tieneEstabilizador = tieneEstabilizador;
    }

    @Override
    public String toString() {
        return "Objetivo=\n" +
                "nombre='" + getNombre() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", precio='" + getPrecio() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", fechaCreacion='" + getFechaCreacion() + "\'" +
                ", alcanceLente='" + alcanceLente + '\'' +
                ", aperturaMax='" + aperturaMax + '\'' +
                ", tipoMontura='" + tipoMontura + '\'' +
                ", tieneEstabilizador='" + tieneEstabilizador + '\'';
    }

}
