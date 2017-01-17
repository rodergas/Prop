/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 *
 */
public abstract class Relacion {
   
    //Constructora
    
    //protected Relacion relacionIzquierda;
    //protected Relacion relacionDerecha;
    protected String nombre;
    public Relacion() {}
    
    public abstract String consultarNombre();
    /*
    public Relacion RelacionIzquierda() {
        return relacionIzquierda;
    }*/
    
    public abstract ArrayList<Cancion> obtenerConjunto(CjtCancion Cc, CjtUsuario Cu);
    
    /*
    public Relacion RelacionDerecha() {
        return relacionDerecha;
    }
    */
}




