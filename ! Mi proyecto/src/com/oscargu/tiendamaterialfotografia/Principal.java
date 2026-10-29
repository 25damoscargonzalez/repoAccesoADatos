package com.oscargu.tiendamaterialfotografia;

import com.oscargu.tiendamaterialfotografia.gui.ProductoFotoControlador;
import com.oscargu.tiendamaterialfotografia.gui.ProductoFotoModelo;
import com.oscargu.tiendamaterialfotografia.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        ProductoFotoModelo modelo = new ProductoFotoModelo();
        Ventana vista = new Ventana();
        ProductoFotoControlador controlador = new ProductoFotoControlador(modelo, vista);
    }

}
