package com.oscargu.tiendamaterialfotografia.gui;

import com.oscargu.tiendamaterialfotografia.base.ProductoFotografico;

import javax.swing.*;

public class Ventana {

    //Atributos creados en el .form
    public JPanel panel1;

    public JRadioButton accesorioRadioButton;
    public JRadioButton camaraRadioButton;
    public JRadioButton iluminacionRadioButton;
    public JRadioButton objetivoRadioButton;

    public JTextField nombreTextField;
    public JTextField marcaTextField;
    public JTextField precioTextField;
    public JTextField stockTextField;

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

    public JTextField primeraTextField;
    public JTextField segundaTextField;
    public JTextField terceraTextField;
    public JTextField cuartaTextField;

    //Atributos creados por mi
    public JFrame frame;
    public DefaultListModel<ProductoFotografico> dlmProductoFotografico;

    public Ventana() {
        frame = new JFrame("ProductosFotograficosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmProductoFotografico = new DefaultListModel<ProductoFotografico>();
        list1.setModel(dlmProductoFotografico);
    }

}
