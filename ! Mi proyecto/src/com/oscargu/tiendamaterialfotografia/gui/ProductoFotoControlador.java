package com.oscargu.tiendamaterialfotografia.gui;

import com.oscargu.tiendamaterialfotografia.base.ProductoFotografico;
import sun.invoke.empty.Empty;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class ProductoFotoControlador implements ActionListener, ListSelectionListener, WindowListener {
    private ProductoFotoModelo modelo;
    private Ventana vista;
    private File ultimaRutaExportada;

    public ProductoFotoControlador(ProductoFotoModelo modelo, Ventana vista) {
        this.modelo = modelo;
        this.vista = vista;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No se encontro el archivo" + e.getMessage());
        }

        addActionListener(this);

    }

    private boolean hayCamposVacios() {
        return false;
    }

    private void limpiarCampos() {
        vista.primeraTextField.setText(null);
        vista.segundaTextField.setText(null);
        vista.terceraTextField.setText(null);
        vista.cuartaTextField.setText(null);

        vista.nombreTextField.setText(null);
        vista.marcaTextField.setText(null);
        vista.precioTextField.setText(null);
        vista.stockTextField.setText(null);
    }

    public void refrescar() {
        vista.dlmProductoFotografico.clear();
        for (ProductoFotografico unProductoFotografico : modelo.obtenerProductosFotograficos()) {
            vista.dlmProductoFotografico.addElement(unProductoFotografico);
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.actualizarButton.addActionListener(listener);
        vista.buscarButton.addActionListener(listener);
        vista.accesorioRadioButton.addActionListener(listener);
        vista.camaraRadioButton.addActionListener(listener);
        vista.iluminacionRadioButton.addActionListener(listener);
        vista.objetivoRadioButton.addActionListener(listener);
    }

    private void addListSelectionListener (ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void addWindowListener (WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("vehiculos.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion (File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion () throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("vehiculos.conf"), "Datos configuracion vehiculos");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionComand = e.getActionCommand();
        switch (actionComand) {
            case "Crear Nuevo":
                if (hayCamposVacios()) {

                }

                if (vista.accesorioRadioButton.isSelected()) {
                    modelo.altaAccesorio(
                            vista.nombreTextField.getText(),
                            vista.marcaTextField.getText(),
                            vista.precioTextField.getText(),
                            vista.stockTextField.getText(),
                            vista.primeraTextField.getText(),
                            vista.segundaTextField.getText(),
                            vista.terceraTextField.getText()));
                }

                limpiarCampos();
                refrescar();
                break;
            case "Accesorio":
                vista.cambiarPrimeraLbl.setText("Categoria");
                vista.cambiarSegundaLbl.setText("Meterial Principal");
                vista.cambiarTerceraLbl.setText("Peso Soportado Kg.");
                vista.cambiarCuartaLbl.setText(null);
                vista.cuartaTextField = null;
                break;

            case "Camara":
                vista.cambiarPrimeraLbl.setText("Tipo Sensor");
                vista.cambiarSegundaLbl.setText("Resolucion Mpx");
                vista.cambiarTerceraLbl.setText("Es Sin Espejo");
                vista.cambiarCuartaLbl.setText("Resolucion Max Video");
                break;

            case "Iiluminacion":
                vista.cambiarPrimeraLbl.setText("Tipo Luz");
                vista.cambiarSegundaLbl.setText("Watios");
                vista.cambiarTerceraLbl.setText("Temperatura Color");
                vista.cambiarCuartaLbl.setText(null);
                vista.cuartaTextField = null;
                break;

            case "Objetivo":
                vista.cambiarPrimeraLbl.setText("Alcance Lente");
                vista.cambiarSegundaLbl.setText("Apertura Max");
                vista.cambiarTerceraLbl.setText("Tipo Montura");
                vista.cambiarCuartaLbl.setText("Tiene Estabilizador");
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
