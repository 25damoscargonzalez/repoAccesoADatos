package com.oscargu.tiendamaterialfotografia.base;

public class ProductoFotografico {
    private String nombre;
    private String marca;
    private float precio;
    private int stock;

    public ProductoFotografico() {

    }

    public ProductoFotografico(String nombre, String marca, float precio, int stock) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }
}
