package com.oscargu.tiendamaterialfotografia.gui;

import com.oscargu.tiendamaterialfotografia.base.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<ProductoFotografico> buscarProductosFotograficos(String opcionesDesplegable, String texto) {
        List<ProductoFotografico> resultado = new ArrayList<>();
        String buscado = texto.trim().toLowerCase();

        for (ProductoFotografico producto : obtenerProductosFotograficos()) {
            String valor;
            switch (opcionesDesplegable) {
                case "Nombre":
                    valor = producto.getNombre();
                    break;
                case "Marca":
                    valor = producto.getMarca();
                    break;
                case "Tipo":
                    valor = producto.getClass().getSimpleName(); // Accesorio, Camara, Iluminacion, Objetivo
                    break;
                default:
                    valor = "";
            }
            if (valor.toLowerCase().contains(buscado)) {
                resultado.add(producto);
            }
        }
        return resultado;
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
                nodoDatos = documento.createElement("TipoLuz");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Iluminacion) unProductoFotografico).getTipoLuz());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("Watios");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Iluminacion) unProductoFotografico).getWatios()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TemperaturaColor");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Iluminacion) unProductoFotografico).getTemperaturaColor());
                nodoDatos.appendChild(texto);
            }
            else {
                nodoDatos = documento.createElement("AlcanceLente");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Objetivo) unProductoFotografico).getAlcanceLente());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("AperturaMax");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Objetivo) unProductoFotografico).getAperturaMax());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TipoMontura");
                nodoProductosFotograficos.appendChild(nodoDatos);
                texto = documento.createTextNode(((Objetivo) unProductoFotografico).getTipoMontura());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("TieneEstabilizador");
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

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaProductosFotograficos = new ArrayList<ProductoFotografico>();
        Accesorio nuevoAccesorio = null;
        Camara nuevaCamara = null;
        Iluminacion nuevaIluminacion = null;
        Objetivo nuevoObjetivo = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero); //Parseamos, necesitamos pasar el fichero a DOM

        //Leera etiqueta por etiqueta, * --> Cualquier etiqueta
        NodeList listaElementos = documento.getElementsByTagName("*");
        for (int i = 0; i<listaElementos.getLength(); i++) {
            Element nodoProductoFotografico = (Element) listaElementos.item(i); //Castear a elemento

            if (nodoProductoFotografico.getTagName().equals("Accesorio")) {
                nuevoAccesorio = new Accesorio();
                nuevoAccesorio.setNombre(nodoProductoFotografico.getChildNodes().item(0).getTextContent());
                nuevoAccesorio.setMarca(nodoProductoFotografico.getChildNodes().item(1).getTextContent());
                nuevoAccesorio.setPrecio(Float.parseFloat(nodoProductoFotografico.getChildNodes().item(2).getTextContent()));
                nuevoAccesorio.setStock(Integer.parseInt(nodoProductoFotografico.getChildNodes().item(3).getTextContent()));
                nuevoAccesorio.setFechaCreacion(LocalDate.parse(nodoProductoFotografico.getChildNodes().item(4).getTextContent()));
                nuevoAccesorio.setCategoria(nodoProductoFotografico.getChildNodes().item(5).getTextContent());
                nuevoAccesorio.setMaterialPrincipal(nodoProductoFotografico.getChildNodes().item(6).getTextContent());
                nuevoAccesorio.setPesoSoportadoKg(Float.parseFloat(nodoProductoFotografico.getChildNodes().item(7).getTextContent()));

                listaProductosFotograficos.add(nuevoAccesorio);
            }
            else if (nodoProductoFotografico.getTagName().equals("Camara")) {
                nuevaCamara = new Camara();
                nuevaCamara.setNombre(nodoProductoFotografico.getChildNodes().item(0).getTextContent());
                nuevaCamara.setMarca(nodoProductoFotografico.getChildNodes().item(1).getTextContent());
                nuevaCamara.setPrecio(Float.parseFloat(nodoProductoFotografico.getChildNodes().item(2).getTextContent()));
                nuevaCamara.setStock(Integer.parseInt(nodoProductoFotografico.getChildNodes().item(3).getTextContent()));
                nuevaCamara.setFechaCreacion(LocalDate.parse(nodoProductoFotografico.getChildNodes().item(4).getTextContent()));
                nuevaCamara.setTipoSensor(nodoProductoFotografico.getChildNodes().item(5).getTextContent());
                nuevaCamara.setResolucionMpx(Float.parseFloat(nodoProductoFotografico.getChildNodes().item(6).getTextContent()));
                nuevaCamara.setEsSinEspejo(Boolean.parseBoolean(nodoProductoFotografico.getChildNodes().item(7).getTextContent()));
                nuevaCamara.setResolucionMaxVideo(nodoProductoFotografico.getChildNodes().item(8).getTextContent());

                listaProductosFotograficos.add(nuevaCamara);
            }
            else if (nodoProductoFotografico.getTagName().equals("Iluminacion")) {
                nuevaIluminacion = new Iluminacion();
                nuevaIluminacion.setNombre(nodoProductoFotografico.getChildNodes().item(0).getTextContent());
                nuevaIluminacion.setMarca(nodoProductoFotografico.getChildNodes().item(1).getTextContent());
                nuevaIluminacion.setPrecio(Float.parseFloat(nodoProductoFotografico.getChildNodes().item(2).getTextContent()));
                nuevaIluminacion.setStock(Integer.parseInt(nodoProductoFotografico.getChildNodes().item(3).getTextContent()));
                nuevaIluminacion.setFechaCreacion(LocalDate.parse(nodoProductoFotografico.getChildNodes().item(4).getTextContent()));
                nuevaIluminacion.setTipoLuz(nodoProductoFotografico.getChildNodes().item(5).getTextContent());
                nuevaIluminacion.setWatios(Integer.parseInt(nodoProductoFotografico.getChildNodes().item(6).getTextContent()));
                nuevaIluminacion.setTemperaturaColor(nodoProductoFotografico.getChildNodes().item(7).getTextContent());

                listaProductosFotograficos.add(nuevaIluminacion);
            }
            else if (nodoProductoFotografico.getTagName().equals("Objetivo")){
                nuevoObjetivo = new Objetivo();
                nuevoObjetivo.setNombre(nodoProductoFotografico.getChildNodes().item(0).getTextContent());
                nuevoObjetivo.setMarca(nodoProductoFotografico.getChildNodes().item(1).getTextContent());
                nuevoObjetivo.setPrecio(Float.parseFloat(nodoProductoFotografico.getChildNodes().item(2).getTextContent()));
                nuevoObjetivo.setStock(Integer.parseInt(nodoProductoFotografico.getChildNodes().item(3).getTextContent()));
                nuevoObjetivo.setFechaCreacion(LocalDate.parse(nodoProductoFotografico.getChildNodes().item(4).getTextContent()));
                nuevoObjetivo.setAlcanceLente(nodoProductoFotografico.getChildNodes().item(5).getTextContent());
                nuevoObjetivo.setAperturaMax(nodoProductoFotografico.getChildNodes().item(6).getTextContent());
                nuevoObjetivo.setTipoMontura(nodoProductoFotografico.getChildNodes().item(7).getTextContent());
                nuevoObjetivo.setTieneEstabilizador(Boolean.parseBoolean(nodoProductoFotografico.getChildNodes().item(8).getTextContent()));

                listaProductosFotograficos.add(nuevoObjetivo);
            }

        }

    }

    public void eliminarProductoFotografico(ProductoFotografico seleccionado) {
        listaProductosFotograficos.remove(seleccionado);

    }

}
