package com.oscargu.tiendamaterialfotografia.gui;

import com.oscargu.tiendamaterialfotografia.base.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductoFotoModelo {
    private ArrayList<ProductoFotografico> listaProductosFotograficos;

    public ProductoFotoModelo() {
        listaProductosFotograficos = new ArrayList<>();
    }

    public ArrayList<ProductoFotografico> obtenerProductosFotograficos() {
        return listaProductosFotograficos;
    }

    public void altaAccesorio(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion,
                              String categoria, String materialPrincipal, float pesoSoportado) {
        Accesorio nuevoAccesorio = new Accesorio(nombre, marca, precio, stock, fechaCreacion, categoria, materialPrincipal, pesoSoportado);
        listaProductosFotograficos.add(nuevoAccesorio);
    }

    public void altaCamara(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion,
                           String tipoSensor, float resolucionMpx, boolean esSinEspejo, String resolucionMaxVideo) {
        Camara nuevaCamara = new Camara(nombre, marca, precio, stock, fechaCreacion, tipoSensor, resolucionMpx, esSinEspejo, resolucionMaxVideo);
        listaProductosFotograficos.add(nuevaCamara);
    }

    public void altaIluminacion(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion,
                                String tipoLuz, int watios, String temperaturaColor) {
        Iluminacion nuevaIluminacion = new Iluminacion(nombre, marca, precio, stock, fechaCreacion, tipoLuz, watios, temperaturaColor);
        listaProductosFotograficos.add(nuevaIluminacion);
    }

    public void altaObjetivo(String nombre, String marca, float precio, int stock, LocalDate fechaCreacion,
                             String alcanceLente, String aperturaMax, String tipoMontura, boolean tieneEstabilizador) {
        Objetivo nuevoObjetivo = new Objetivo(nombre, marca, precio, stock, fechaCreacion, alcanceLente, aperturaMax, tipoMontura, tieneEstabilizador);
        listaProductosFotograficos.add(nuevoObjetivo);
    }


    public boolean existeNombreProducto(String nombre) {
        for (ProductoFotografico unProductoFotografico : listaProductosFotograficos) {
            if (unProductoFotografico.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "TiendaMaterialFotografico", null);

        Element raiz = documento.createElement("ProductosFotograficos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoProductosFotograficos = null;
        Element nodoDatos = null;
        Text texto = null;

        for (ProductoFotografico unProductoFotografico : listaProductosFotograficos) {
            if (unProductoFotografico instanceof Accesorio) {
                nodoProductosFotograficos = documento.createElement("Accesorio");
            }
            else if (unProductoFotografico instanceof Camara) {
                nodoProductosFotograficos = documento.createElement("Camara");
            }
            else if (unProductoFotografico instanceof Iluminacion) {
                nodoProductosFotograficos = documento.createElement("Iluminacion");
            }
            else {
                nodoProductosFotograficos = documento.createElement("Objetivo");
            }
            raiz.appendChild(nodoProductosFotograficos);

            //Nodos comunes
            nodoDatos = documento.createElement("Nombre");
            nodoProductosFotograficos.appendChild(nodoDatos);
            texto = documento.createTextNode(unProductoFotografico.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("Marca");
            nodoProductosFotograficos.appendChild(nodoDatos);
            texto = documento.createTextNode(unProductoFotografico.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("Precio");
            nodoProductosFotograficos.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unProductoFotografico.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("Stock");
            nodoProductosFotograficos.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unProductoFotografico.getStock()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("FechaCreacion");
            nodoProductosFotograficos.appendChild(nodoDatos);
            texto = documento.createTextNode(unProductoFotografico.getFechaCreacion().toString());
            nodoDatos.appendChild(texto);

            //Campos diferentes
            if (unProductoFotografico instanceof Accesorio) {
                nodoDatos = documento.createElement("Categoria");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Accesorio) unProductoFotografico).getCategoria());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("MaterialPrincipal");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Accesorio) unProductoFotografico).getMaterialPrincipal());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("PesoSoportadoKg");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Accesorio) unProductoFotografico).getPesoSoportadoKg()));
                nodoDatos.appendChild(texto);
            }
            else if (unProductoFotografico instanceof Camara) {
                nodoDatos = documento.createElement("TipoSensor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Camara) unProductoFotografico).getTipoSensor());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("ResolucionMpx");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Camara) unProductoFotografico).getResolucionMpx()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("EsSinEspejo");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Camara) unProductoFotografico).getEsSinEspejo()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("ResolucionMaxVideo");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Camara) unProductoFotografico).getResolucionMaxVideo());
                nodoDatos.appendChild(texto);
            }
            else if (unProductoFotografico instanceof Iluminacion) {
                nodoDatos = documento.createElement("TipoSensor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Iluminacion) unProductoFotografico).getTipoLuz());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TipoSensor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Iluminacion) unProductoFotografico).getWatios()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TipoSensor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Iluminacion) unProductoFotografico).getTemperaturaColor());
                nodoDatos.appendChild(texto);
            }
            else {
                nodoDatos = documento.createElement("AlcanceLente");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Objetivo) unProductoFotografico).getAlcanceLente());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TipoSensor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Objetivo) unProductoFotografico).getAperturaMax());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TipoSensor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Objetivo) unProductoFotografico).getTipoMontura());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TipoSensor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Objetivo) unProductoFotografico).getTieneEstabilizador()));
                nodoDatos.appendChild(texto);
            }

        }

        //Guardamos el fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);


    }
}
