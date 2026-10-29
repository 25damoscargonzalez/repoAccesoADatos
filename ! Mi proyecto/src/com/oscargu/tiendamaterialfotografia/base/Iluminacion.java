package com.oscargu.tiendamaterialfotografia.base;

import java.time.LocalDate;

public class Iluminacion extends ProductoFotografico {
    private String tipoLuz;
    private int watios;
    private String temperaturaColor; //Ej: "Blanco Neutro", "RGB"...

    public Iluminacion() {

    }

    public Iluminacion(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion, String tipoLuz, int watios, String temperaturaColor) {
        super(nombre, marca, precio, stock, fechaCreacion);
        this.tipoLuz = tipoLuz;
        this.watios = watios;
        this.temperaturaColor = temperaturaColor;
    }

    public String getTipoLuz() {
        return tipoLuz;
    }

    public void setTipoLuz(String tipoLuz) {
        this.tipoLuz = tipoLuz;
    }

    public int getWatios() {
        return watios;
    }

    public void setWatios(int watios) {
        this.watios = watios;
    }

    public String getTemperaturaColor() {
        return temperaturaColor;
    }

    public void setTemperaturaColor(String temperaturaColor) {
        this.temperaturaColor = temperaturaColor;
    }

    @Override
    public String toString() {
        return "Iluminacion=\n" +
                "nombre='" + getNombre() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", precio='" + getPrecio() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", fechaCreacion='" + getFechaCreacion() + "\'" +
                ", tipoLuz='" + tipoLuz + '\'' +
                ", watios='" + watios + '\'' +
                ", temperaturaColor='" + temperaturaColor + '\'';
    }

}
