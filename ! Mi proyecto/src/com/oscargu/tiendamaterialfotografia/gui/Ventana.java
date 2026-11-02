package com.oscargu.tiendamaterialfotografia.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.oscargu.tiendamaterialfotografia.base.ProductoFotografico;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


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
    public JButton limpiarButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JButton buscarButton;

    public JList list1;

    public JLabel cambiarPrimeraLbl;
    public JLabel cambiarSegundaLbl;
    public JLabel cambiarTerceraLbl;
    public JLabel cambiarCuartaLbl;

    public JLabel imagenLbl;

    public JTextField primeraTxt;
    public JTextField segundaTxt;
    public JTextField terceraTxt;
    public JTextField cuartaTxt;

    public JTextField buscarTxt;

    public DatePicker fechaCreacionDPiker;
    public JComboBox buscarComboBox;

    //Atributos creados por mi
    public JFrame frame;
    public DefaultListModel<ProductoFotografico> dlmProductoFotografico;

    public Ventana() {
        panel1.setBackground(Color.LIGHT_GRAY);
        panel1.setOpaque(true);

        frame = new JFrame("ProductosFotograficosMVC");
        frame.setContentPane(panel1);
        crearMenu();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmProductoFotografico = new DefaultListModel<ProductoFotografico>();
        list1.setModel(dlmProductoFotografico);
    }

    //Menu
    public JMenuItem itemExportar;
    public JMenuItem itemImportar;

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemExportar = new JMenuItem("Exportar XML");
        itemImportar = new JMenuItem("Importar XML");

        menu.add(itemExportar);
        menu.add(itemImportar);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }

    public void cambiarImagen(String rutaImagen) {
        try {
            // Carga la imagen usando el ClassLoader desde la ruta exacta del paquete
            URL url = getClass().getResource(rutaImagen);

            if (url != null) {
                ImageIcon iconoOriginal = new ImageIcon(url);

                // Escalamos la imagen para que encaje perfectamente dentro del JLabel sin deformarse
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                        imagenLbl.getWidth() > 0 ? imagenLbl.getWidth() : 150,
                        imagenLbl.getHeight() > 0 ? imagenLbl.getHeight() : 150,
                        java.awt.Image.SCALE_SMOOTH //alta calidad
                );

                imagenLbl.setIcon(new ImageIcon(imagenEscalada));
            } else {
                System.out.println("No se ha encontrado la imagen: " + rutaImagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
