package com.oscargu.tiendamaterialfotografia.gui;

import com.oscargu.tiendamaterialfotografia.base.Accesorio;
import com.oscargu.tiendamaterialfotografia.base.ProductoFotografico;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductoFotoModelo {
    private ArrayList<ProductoFotografico> listaProductosFotograficos;

    public ProductoFotoModelo() {
        listaProductosFotograficos = new ArrayList<>();
    }

    public ArrayList<ProductoFotografico> obtenerProductosFotograficos() {
        return listaProductosFotograficos;
    }

    public void altaAccesorio(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion, String categoria, String materialPrincipal, float pesoSoportado) {
        Accesorio nuevoAccesorio = new Accesorio(nombre, marca, precio, stock, fechaCreacion, categoria, materialPrincipal, pesoSoportado);
        listaProductosFotograficos.add(nuevoAccesorio);
    }


}
