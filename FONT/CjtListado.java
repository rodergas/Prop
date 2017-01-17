/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 *
 *
 */
public class CjtListado {
    private TST<Listado> listados;
    
    public CjtListado() {
        listados = new TST<Listado>();
    }
    
    public void anadirListadoRandom(String nombre, int n, ArrayList<Cancion> c) { 
        Listado l = new Listado(nombre);
        Random randomGenerator = new Random();
        Set<Integer> generated = new HashSet<Integer>();
        while (generated.size() < n){
            int randomInt = randomGenerator.nextInt(c.size());
            generated.add(randomInt);
        }
        for (Integer i : generated){
            l.anadirCancion(c.get(i));
        }       
        try { listados.anadirNodo(l.consultarNombre(), l); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe un listado con ese nombre"); 
        }
    }
    
    public void anadirCancionListado(String nombre, Cancion c){
        boolean existe = false;
        existe = existeListado(nombre);
        if (existe) {
            Listado l = listados.consultarElemento(nombre);
            l.anadirCancion(c);
        }
        else throw new IllegalArgumentException("No existe un listado con ese nombre");
    }
    
    public void eliminarCancionListado(String nombre, String nombreC){
        boolean existe = false;
        existe = existeListado(nombre);
        if (existe) {
            Listado l = listados.consultarElemento(nombre);
            //Cancion c = l.consultarCancion(nombreC);
            //if(c != null) l.eliminarCancion(nombreC);
            //else throw new IllegalArgumentException("No existe una cancion con ese nombre en ese listado");
            l.eliminarCancion(nombreC);
        }
        else throw new IllegalArgumentException("No existe un listado con ese nombre");
    }
    
    public void anadirListadoManual(String nombre) throws IllegalArgumentException {
        Listado l = new Listado(nombre);
        try { listados.anadirNodo(l.consultarNombre(), l); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe un listado con ese nombre"); 
        }
    }
    
    public void anadirListado(Listado l) throws IllegalArgumentException {
        try { listados.anadirNodo(l.consultarNombre(), l); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe un listado con ese nombre"); 
        }
    }
   
    public Listado consultarListado (String nombre) throws IllegalArgumentException {
        Listado l = listados.consultarElemento(nombre);
        if (l == null) throw new IllegalArgumentException("No existe un listado con ese nombre");
        else return l;
    }  
    
    public void eliminarListado(String nombre) throws IllegalArgumentException {
        try { listados.eliminarElemento(nombre); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("No existe un listado con ese nombre"); 
        }
    }
    
    public Boolean existeListado(String nombre) {
        boolean trobat = false;
        Listado l = listados.consultarElemento(nombre);
        if(l != null) trobat = true;
        return trobat;
    }
    
    public ArrayList<Listado> ObtenerListados(){
        LinkedList<String> usuario =  (LinkedList<String>) listados.ObtenerClaves();
        ArrayList<Listado> resultado = new ArrayList<Listado>();
        for(int i = 0; i < usuario.size(); ++i){
            Listado l = listados.consultarElemento(usuario.get(i));
            resultado.add(l);
        }
        return resultado;
    }
    
    public LinkedList<String> Nombres() {
        return (LinkedList<String>) listados.ObtenerClaves();
    }
    
    public LinkedList<String> Nombres(String s) {
        return (LinkedList<String>) listados.Prefijo(s);
    }
    
}
