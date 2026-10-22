package com.oscargu.tiendamaterialfotografia.base;

public class Camara extends ProductoFotografico{
    private String tipoSensor;
    private float resolucionMpx;
    private boolean esSinEspejo; // true = Moderna sin espejo, false = Réflex tradicional
    private String resolucionMaxVideo;

    public Camara() {

    }

    public Camara(String nombre, String marca, float precio, int stock, String tipoSensor) {
        super(nombre, marca, precio, stock);
        this.tipoSensor = tipoSensor;
    }
}
