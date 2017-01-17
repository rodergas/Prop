/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.*;

/**
 *
 *
 */
public class CjtCancion {
    //Atributos
    private TST<Cancion> canciones;
    
    //Constructoras
    public CjtCancion(){
        canciones = new TST<Cancion>();
    }
    
    //Consultoras
    public Integer consultarTamano() {
        return canciones.consultarTamano(); 
    }
    
    public Cancion consultarCancion(String titulo) throws IllegalArgumentException {
        Cancion c = canciones.consultarElemento(titulo);
        if (c == null) throw new IllegalArgumentException("No existe una cancion con ese titulo");
        else return c;
    }  
    
    /*
    public Boolean existeCancion(String titulo) {
        boolean trobat = false;
        Cancion c = canciones.consultarElemento(titulo);
        if (c != null) trobat = true;
        return trobat;
    }
    */
    
    //Modificadoras
    public void anadirCancion(Cancion c) throws IllegalArgumentException {
        try { canciones.anadirNodo(c.consultarTitulo(), c); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe una cancion con ese titulo"); 
        }
    }

    public void eliminarCancion(String titulo) throws IllegalArgumentException {
        try { canciones.eliminarElemento(titulo); }
        catch (IllegalArgumentException IAE) { 
            throw new IllegalArgumentException("No existe una cancion con ese titulo"); 
        }
    }
    
    public void modTitulo (String titulo, String titulo_nuevo){
        Cancion c = canciones.consultarElemento(titulo_nuevo);
        if (c != null) throw new IllegalArgumentException("Ya existe una cancion con el titulo nuevo introducido");
        else {
            c = canciones.consultarElemento(titulo);
            if (c == null) throw new IllegalArgumentException("No existe la cancion que se desea modificar");
            else c.modificarTitulo(titulo_nuevo);
        }
    }
    
    public void modAutor (String titulo, String autor_nuevo){
        Cancion c = canciones.consultarElemento(titulo);
        if (c == null) throw new IllegalArgumentException("No existe la cancion que se desea modificar");
        else c.modificarAutor(autor_nuevo);
    }
    
    public void modMinutos (String titulo, Integer duracion_nueva){
        Cancion c = canciones.consultarElemento(titulo);
        if (c == null) throw new IllegalArgumentException("No existe la cancion que se desea modificar");
        else c.modificarMinutos(duracion_nueva);
    }
    
    public void modSegundos (String titulo, Integer duracion_nueva){
        Cancion c = canciones.consultarElemento(titulo);
        if (c == null) throw new IllegalArgumentException("No existe la cancion que se desea modificar");
        else c.modificarSegundos(duracion_nueva);
    }
    
    public void modReproducciones (String titulo, Integer num_reproducciones_nuevo){
        Cancion c = canciones.consultarElemento(titulo);
        if (c == null) throw new IllegalArgumentException("No existe la cancion que se desea modificar");
        else c.modificarReproducciones(num_reproducciones_nuevo);
    }
    
    public ArrayList<Cancion> ObtenerCanciones(){
        LinkedList<String> cancion =  (LinkedList<String>) canciones.ObtenerClaves();
        ArrayList<Cancion> resultado = new ArrayList<>();
        for(int i = 0; i < cancion.size(); ++i){
            Cancion c = canciones.consultarElemento(cancion.get(i));
            resultado.add(c);
        }
        return resultado;
    }
    
    public LinkedList<String> nombres() {
        return (LinkedList<String>) canciones.ObtenerClaves();
    }
    
    public LinkedList<String> nombres(String s) {
        return (LinkedList<String>) canciones.Prefijo(s);
    }
}
