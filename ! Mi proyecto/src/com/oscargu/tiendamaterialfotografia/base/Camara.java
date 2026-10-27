package com.oscargu.tiendamaterialfotografia.base;

public class Camara extends ProductoFotografico{
    private String tipoSensor;
    private float resolucionMpx;
    private boolean esSinEspejo; // true = Moderna sin espejo, false = Réflex tradicional
    private String resolucionMaxVideo; //4K, 1080p, 8K 60fps

    public Camara() {

    }

    public Camara(String nombre, String marca, float precio, int stock, String tipoSensor, float resolucionMpx, boolean esSinEspejo, String resolucionMaxVideo) {
        super(nombre, marca, precio, stock);
        this.tipoSensor = tipoSensor;
        this.resolucionMpx = resolucionMpx;
        this.esSinEspejo = esSinEspejo;
        this.resolucionMaxVideo = resolucionMaxVideo;
    }

    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public float getResolucionMpx() {
        return resolucionMpx;
    }

    public void setResolucionMpx(float resolucionMpx) {
        this.resolucionMpx = resolucionMpx;
    }

    public boolean isEsSinEspejo() {
        return esSinEspejo;
    }

    public void setEsSinEspejo(boolean esSinEspejo) {
        this.esSinEspejo = esSinEspejo;
    }

    public String getResolucionMaxVideo() {
        return resolucionMaxVideo;
    }

    public void setResolucionMaxVideo(String resolucionMaxVideo) {
        this.resolucionMaxVideo = resolucionMaxVideo;
    }

    @Override
    public String toString() {
        return "Camara=\n" +
                "nombre='" + getNombre() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", precio='" + getPrecio() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", tipoSensor='" + tipoSensor + '\'' +
                ", resolucionMpx='" + resolucionMpx + '\'' +
                ", esSinEspejo='" + esSinEspejo + '\'' +
                ", resolucionMaxVideo='" + resolucionMaxVideo + '\'';
    }
}
