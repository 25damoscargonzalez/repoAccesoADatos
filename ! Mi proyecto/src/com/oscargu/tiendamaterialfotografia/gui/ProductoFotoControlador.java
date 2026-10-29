package com.oscargu.tiendamaterialfotografia.gui;

import com.oscargu.tiendamaterialfotografia.base.ProductoFotografico;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.time.LocalDate;
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
        addListSelectionListener(this);
        addWindowListener(this);

        //Marcamos por defecto el radioButton "Accesorio"
        vista.accesorioRadioButton.doClick();

    }

    private String hayCamposVacios() {
        String mensaje = "";

        if (vista.nombreTxt.getText().isEmpty()) {
            mensaje += "Nombre\n";
        }
        if (vista.marcaTxt.getText().isEmpty()) {
            mensaje += "Marca\n";
        }
        if (vista.precioTxt.getText().isEmpty()) {
            mensaje += "Precio\n";
        }
        if (vista.stockTxt.getText().isEmpty()) {
            mensaje += "Stock\n";
        }
        if (vista.fechaCreacionDPiker.getText().isEmpty()) {
            mensaje += "Fecha Creacion\n";
        }

        if (vista.primeraTxt.getText().isEmpty()) {
            mensaje += vista.cambiarPrimeraLbl.getText() + "\n";
        }
        if (vista.segundaTxt.getText().isEmpty()) {
            mensaje += vista.cambiarSegundaLbl.getText() + "\n";
        }
        if (vista.terceraTxt.getText().isEmpty()) {
            mensaje += vista.cambiarTerceraLbl.getText() + "\n";
        }
        if (vista.cuartaTxt.isVisible() && vista.cuartaTxt.getText().isEmpty()) {
            mensaje += vista.cambiarCuartaLbl.getText() + "\n";
        }

        return mensaje;
    }

    private void limpiarCampos() {
        vista.primeraTxt.setText(null);
        vista.segundaTxt.setText(null);
        vista.terceraTxt.setText(null);
        vista.cuartaTxt.setText(null);

        vista.nombreTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.precioTxt.setText(null);
        vista.stockTxt.setText(null);
        vista.fechaCreacionDPiker.setDate(null);

        //Despues de limpiar los campos el cursor quedaria parpadeando en el TextField "matriculaTxt"
        vista.nombreTxt.requestFocus(true);

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
        vista.frame.addWindowListener(listener); //frame, el boton de cerrar esta en la ventanita de fuera de la ventana
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
                String vacios = hayCamposVacios();
                if (!vacios.isEmpty()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios = " + vacios);
                    break;
                }

                if (modelo.existeNombreProducto(vista.nombreTxt.getText())) {
                    Util.mensajeError("Ya existe un producto fotografico con ese nombre");
                    break;
                }

                if (vista.accesorioRadioButton.isSelected()) {
                    modelo.altaAccesorio(
                            vista.nombreTxt.getText(),
                            vista.marcaTxt.getText(),
                            Float.parseFloat(vista.precioTxt.getText()),
                            Integer.parseInt(vista.stockTxt.getText()),
                            vista.fechaCreacionDPiker.getDate(),
                            vista.primeraTxt.getText(),
                            vista.segundaTxt.getText(),
                            Float.parseFloat(vista.terceraTxt.getText()));
                }
                else if (vista.camaraRadioButton.isSelected()) {
                    modelo.altaCamara(
                            vista.nombreTxt.getText(),
                            vista.marcaTxt.getText(),
                            Float.parseFloat(vista.precioTxt.getText()),
                            Integer.parseInt(vista.stockTxt.getText()),
                            vista.fechaCreacionDPiker.getDate(),
                            vista.primeraTxt.getText(),
                            Float.parseFloat(vista.segundaTxt.getText()),
                            Boolean.parseBoolean(vista.terceraTxt.getText()),
                            vista.cuartaTxt.getText());
                }
                else if (vista.iluminacionRadioButton.isSelected()) {
                    modelo.altaIluminacion(
                            vista.nombreTxt.getText(),
                            vista.marcaTxt.getText(),
                            Float.parseFloat(vista.precioTxt.getText()),
                            Integer.parseInt(vista.stockTxt.getText()),
                            vista.fechaCreacionDPiker.getDate(),
                            vista.primeraTxt.getText(),
                            Integer.parseInt(vista.segundaTxt.getText()),
                            vista.terceraTxt.getText());
                }
                else {
                    modelo.altaObjetivo(
                            vista.nombreTxt.getText(),
                            vista.marcaTxt.getText(),
                            Float.parseFloat(vista.precioTxt.getText()),
                            Integer.parseInt(vista.stockTxt.getText()),
                            vista.fechaCreacionDPiker.getDate(),
                            vista.primeraTxt.getText(),
                            vista.segundaTxt.getText(),
                            vista.terceraTxt.getText(),
                            Boolean.parseBoolean(vista.cuartaTxt.getText()));
                }
                limpiarCampos();
                refrescar();
                break;

            case "Exportar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int option = selectorFichero.showSaveDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                    actualizarDatosConfiguracion(selectorFichero.getSelectedFile());
                }

                break;

            case "Importar":
                break;

            case "Actualizar":
                break;

            case "Buscar":
                break;

            //RadioButtons
            case "Accesorio":
                vista.cambiarPrimeraLbl.setText("Categoria");
                vista.cambiarSegundaLbl.setText("Meterial Principal");
                vista.cambiarTerceraLbl.setText("Peso Soportado Kg.");
                vista.cambiarCuartaLbl.setText(null);
                vista.cuartaTxt.setVisible(false);
                vista.cambiarCuartaLbl.setVisible(false);
                break;

            case "Camara":
                vista.cambiarPrimeraLbl.setText("Tipo Sensor");
                vista.cambiarSegundaLbl.setText("Resolucion Mpx");
                vista.cambiarTerceraLbl.setText("Es Sin Espejo");
                vista.cambiarCuartaLbl.setText("Resolucion Max Video");
                vista.cuartaTxt.setVisible(true);
                vista.cambiarCuartaLbl.setVisible(true);
                break;

            case "Iluminacion":
                vista.cambiarPrimeraLbl.setText("Tipo Luz");
                vista.cambiarSegundaLbl.setText("Watios");
                vista.cambiarTerceraLbl.setText("Temperatura Color");
                vista.cuartaTxt.setVisible(false);
                vista.cambiarCuartaLbl.setVisible(false);
                break;

            case "Objetivo":
                vista.cambiarPrimeraLbl.setText("Alcance Lente");
                vista.cambiarSegundaLbl.setText("Apertura Max");
                vista.cambiarTerceraLbl.setText("Tipo Montura");
                vista.cambiarCuartaLbl.setText("Tiene Estabilizador");
                vista.cuartaTxt.setVisible(true);
                vista.cambiarCuartaLbl.setVisible(true);
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
