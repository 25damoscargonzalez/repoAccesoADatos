package com.oscargu.tiendamaterialfotografia.base;

public class Accesorio extends ProductoFotografico {
    private String categoria; //Soporte, Transporte, Filtro Optico...
    private String materialPrincipal;
    private float pesoSoportadoKg;

    public Accesorio() {

    }

    public Accesorio(String nombre, String marca, float precio, int stock, String categoria, String materialPrincipal, float pesoSoportadoKg) {
        super(nombre, marca, precio, stock);
        this.categoria = categoria;
        this.materialPrincipal = materialPrincipal;
        this.pesoSoportadoKg = pesoSoportadoKg;
    }
}
