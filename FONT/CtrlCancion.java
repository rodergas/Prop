/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.GestorDeDatos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 *
 */

public class CtrlCancion {
    private CjtCancion cjtc;
    private Generos gen;
    
    public CtrlCancion(){
        cjtc = new CjtCancion();
        gen = new Generos();
    }
    
    public void CtrlanadirCancion (String title, String author, Integer minutes,
        Integer seconds, Integer num_reproductions, String style) throws IllegalArgumentException {
        if (!gen.existeGenero(style)) throw new IllegalArgumentException("El genero introducido no ha sido creado previamente");
        Cancion c = new Cancion(title, author, minutes, seconds, num_reproductions, style);
        cjtc.anadirCancion(c);
    }
    
    public void CtrleliminarCancion(String titulo) {
        cjtc.eliminarCancion(titulo);
    }
    
    public Cancion CtrlconsultarCancion(String nombre) {
        return cjtc.consultarCancion(nombre);
    }
    
    public void CtrlmodTitulo (String titulo, String titulo_nuevo){
        cjtc.modTitulo(titulo, titulo_nuevo);
    }
    
    public void CtrlmodAutor (String titulo, String autor_nuevo){
        cjtc.modAutor(titulo, autor_nuevo);
    }
    
    public void CtrlmodMinutos (String titulo, Integer duracion_nueva){
        cjtc.modMinutos(titulo, duracion_nueva);
    }
    
    public void CtrlmodSegundos (String titulo, Integer duracion_nueva){
        cjtc.modSegundos(titulo, duracion_nueva);
    }
    
    public void CtrlmodReproducciones (String titulo, Integer num_reproducciones_nuevo){
        cjtc.modReproducciones(titulo, num_reproducciones_nuevo);
    }
    
    public ArrayList<Cancion> CtrlobtenerCanciones(){
        return cjtc.ObtenerCanciones();
    }
    
    public CjtCancion CtrlobtenerConjunto(){
        return cjtc;
    }
    
    /*
    public boolean CtrlexisteCancion(String nombre){
        return cjtc.existeCancion(nombre);
    }
    */
    
    public boolean CtrlexisteGenero(String style) {
        return gen.existeGenero(style);
    }
    
    public void CtrlanadirGenero(String style) {
        gen.anadirGenero(style);
    }
    
    public ArrayList<String> CtrlobtenerGeneros(){
        return gen.obtenerGeneros();
    }
    
    public LinkedList<String> CtrlNombres() {
        return cjtc.nombres();
    }
     
    public LinkedList<String> CtrlNombres(String s) {
        return cjtc.nombres(s);
    }
     
    public LinkedList<String> CtrlNombresGeneros(String s) {
        return gen.Nombres(s);
    }

    public void guardar(String path) throws FileNotFoundException, IOException, Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirEscritura();
        Iterator<String> itg = gen.obtenerGeneros().iterator();
        if (!itg.hasNext()) GD.escribir("..");
        while (itg.hasNext()) {
            String s = ".";
            int i = 0;
            while (itg.hasNext() && i < 100) {
                String c = itg.next();
                s +=  c + ".";
                ++i;
            }
            GD.escribir(s);
        }
        Iterator<Cancion> it = cjtc.ObtenerCanciones().iterator();
        while (it.hasNext()) {
            String s = "/";
            int i = 0;
            while (it.hasNext() && i < 100) {
                Cancion c = it.next();
                s += "*" + c.consultarTitulo() + "*" + c.consultarAutor() + "*" + c.consultarDuracion().toString() + "*" +
                c.consultarReproducciones().toString() + "*" + c.consultarGenero() + "*" + "/";
                ++i;
            }
            GD.escribir(s);
        }
        GD.cerrar();
    }
    
    public void cargar(String path) throws FileNotFoundException, Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirLectura();
        String s = GD.leer();
        while (s != null && s.charAt(0) == '.') { 
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != '.') {
                    int j = i;
                    while (i < s.length() && s.charAt(i) != '.') ++i;
                    try {
                        gen.anadirGenero(s.substring(j, i));
                    }
                    catch(IllegalArgumentException IAE) {}
                }
            }
            s = GD.leer();
        } 
        while (s != null) { 
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != '/') {
                    int j = i;
                    while (i < s.length() && s.charAt(i) != '/') ++i;
                    interprete(s.substring(j, i));
                }
            }
            s = GD.leer();
        }
        GD.cerrar();
    }
    
    private void interprete(String c) {
        ArrayList<String> s = new ArrayList<String>();
        for (int i = 0; i < c.length(); ++i) {
            if (c.charAt(i) != '*') {
                int j = i;
                while (i < c.length() && c.charAt(i) != '*') ++i;
                s.add(c.substring(j, i));
            }
        }
        Integer aux = Integer.parseInt(s.get(2));
        Integer min = Integer.parseInt(s.get(2))/60;
        Integer seg = Integer.parseInt(s.get(2))%60;
        try {
            CtrlanadirCancion(s.get(0), s.get(1), min, seg, Integer.parseInt(s.get(3)), s.get(4));    
        }
        catch (IllegalArgumentException IAE) {}
    }
}