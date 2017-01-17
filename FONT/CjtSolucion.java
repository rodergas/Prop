/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 *
 */
public class CjtSolucion {
    private TST<Solucion<Cancion>> soluciones;
    
    public CjtSolucion() {
        soluciones = new TST<Solucion<Cancion>>();
    }
    
    public Solucion<Cancion> consultarSolucion(String id) {
        Solucion<Cancion> C = soluciones.consultarElemento(id);
        return C;
    }
    
    /*
    public Boolean existeSolucion(String titulo) {
        boolean trobat = false;
        Solucion<Cancion> c = soluciones.consultarElemento(titulo);
        if(c != null) trobat = true;
        return trobat;
    }
    */
    
    //Modificadoras   
    public void anadirSolucion(String nombreSol, Solucion<Cancion> c) throws IllegalArgumentException {
        try { soluciones.anadirNodo(nombreSol, c); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe una solucion con ese nombre"); 
        }
    }
    
    public void eliminarSolucion(String nombre) throws IllegalArgumentException {
        try { soluciones.eliminarElemento(nombre); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("No existe una solucion con ese nombre"); 
        }
    }
    
    public ArrayList<Solucion<Cancion>> ObtenerSoluciones(){
         LinkedList<String> S =  (LinkedList<String>) soluciones.ObtenerClaves();
         ArrayList<Solucion<Cancion>> resultado = new ArrayList<Solucion<Cancion>>();
         for(int i = 0; i < S.size(); ++i){
             Solucion<Cancion> u = soluciones.consultarElemento(S.get(i));
             resultado.add(u);
         }
         return resultado;
     }
    
    public LinkedList<String> Nombres() {
        return (LinkedList<String>) soluciones.ObtenerClaves();
    }
    
    public LinkedList<String> Nombres(String s) {
        return (LinkedList<String>) soluciones.Prefijo(s);
    }
}
