package com.oscargu.tiendamaterialfotografia;

import com.oscargu.tiendamaterialfotografia.gui.ProductoFotoControlador;
import com.oscargu.tiendamaterialfotografia.gui.ProductoFotoModelo;
import com.oscargu.tiendamaterialfotografia.gui.Ventana;

public class Principal {
    ProductoFotoModelo modelo = new ProductoFotoModelo();
    Ventana vista = new Ventana();
    ProductoFotoControlador controlador = new ProductoFotoControlador(modelo, vista);

}
