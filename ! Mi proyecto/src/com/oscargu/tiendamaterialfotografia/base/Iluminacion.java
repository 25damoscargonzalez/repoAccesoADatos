package com.oscargu.tiendamaterialfotografia.base;

public class Iluminacion extends ProductoFotografico {
    private String tipoLuz;
    private int watios;
    private String temperaturaColor; //Ej: "Blanco Neutro", "RGB"...

    public Iluminacion() {

    }

    public Iluminacion(String nombre, String marca, float precio, int stock, String tipoLuz, int watios, String temperaturaColor) {
        super(nombre, marca, precio, stock);
        this.tipoLuz = tipoLuz;
        this.watios = watios;
        this.temperaturaColor = temperaturaColor;
    }


}
