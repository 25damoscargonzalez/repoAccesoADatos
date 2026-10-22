package com.oscargu.tiendamaterialfotografia.base;

public class Objetivo extends ProductoFotografico {
    private String alcanceLente; //Distancia focal de un objetivo, medida en mm, indica cuanto zoom tiene una lente
    private String aperturaMax; //Indica cuanta luz deja pasar el objetivo hacia el sensor de la cámara
    private String tipoMontura;
    private boolean tieneEstabilizador;

    public Objetivo() {

    }

    public Objetivo(String nombre, String marca, float precio, int stock, String alcanceLente, String aperturaMax, String tipoMontura, boolean tieneEstabilizador) {
        super(nombre, marca, precio, stock);
        this.alcanceLente = alcanceLente;
        this.aperturaMax = aperturaMax;
        this.tipoMontura = tipoMontura;
        this.tieneEstabilizador = tieneEstabilizador;
    }


}
