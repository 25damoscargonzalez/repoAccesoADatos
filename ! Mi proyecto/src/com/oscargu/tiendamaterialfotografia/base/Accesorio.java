package com.oscargu.tiendamaterialfotografia.base;

import java.time.LocalDate;

public class Accesorio extends ProductoFotografico {
    private String categoria; //Soporte, Transporte, Filtro Optico...
    private String materialPrincipal;
    private float pesoSoportadoKg;

    public Accesorio() {

    }

    public Accesorio(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion, String categoria, String materialPrincipal, float pesoSoportadoKg) {
        super(nombre, marca, precio, stock, fechaCreacion);
        this.categoria = categoria;
        this.materialPrincipal = materialPrincipal;
        this.pesoSoportadoKg = pesoSoportadoKg;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMaterialPrincipal() {
        return materialPrincipal;
    }

    public void setMaterialPrincipal(String materialPrincipal) {
        this.materialPrincipal = materialPrincipal;
    }

    public float getPesoSoportadoKg() {
        return pesoSoportadoKg;
    }

    public void setPesoSoportadoKg(float pesoSoportadoKg) {
        this.pesoSoportadoKg = pesoSoportadoKg;
    }

    @Override
    public String toString() {
        return "Accesorio=\n" +
                "nombre='" + getNombre() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", precio='" + getPrecio() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", fechaCreacion='" + getFechaCreacion() + "\'" +
                ", categoria='" + categoria + '\'' +
                ", materialPrincipal='" + materialPrincipal + '\'' +
                ", pesoSoportadoKg=" + pesoSoportadoKg;
    }

}
