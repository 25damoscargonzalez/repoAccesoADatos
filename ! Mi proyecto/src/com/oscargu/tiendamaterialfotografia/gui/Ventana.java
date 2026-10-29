package com.oscargu.tiendamaterialfotografia.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.oscargu.tiendamaterialfotografia.base.ProductoFotografico;

import javax.swing.*;

public class Ventana {

    //Atributos creados en el .form
    public JPanel panel1;

    public JRadioButton accesorioRadioButton;
    public JRadioButton camaraRadioButton;
    public JRadioButton iluminacionRadioButton;
    public JRadioButton objetivoRadioButton;

    public JTextField nombreTxt;
    public JTextField marcaTxt;
    public JTextField precioTxt;
    public JTextField stockTxt;

    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JButton actualizarButton;
    public JButton buscarButton;

    public JList list1;

    public JLabel cambiarPrimeraLbl;
    public JLabel cambiarSegundaLbl;
    public JLabel cambiarTerceraLbl;
    public JLabel cambiarCuartaLbl;

    public JTextField primeraTxt;
    public JTextField segundaTxt;
    public JTextField terceraTxt;
    public JTextField cuartaTxt;
    public DatePicker fechaCreacionDPiker;

    //Atributos creados por mi
    public JFrame frame;
    public DefaultListModel<ProductoFotografico> dlmProductoFotografico;

    public Ventana() {
        frame = new JFrame("ProductosFotograficosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmProductoFotografico = new DefaultListModel<ProductoFotografico>();
        list1.setModel(dlmProductoFotografico);
    }

}
