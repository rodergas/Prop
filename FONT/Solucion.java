/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.GestorDeDatos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author 1171535
 */
public class Solucion<N> {
    
    private ArrayList<Comunidad<N>> comunidades; // componentes conexas
    private String nombre;
    
    public Solucion(){}
    
    public Solucion(String name, ArrayList<Comunidad<N>> communities){
        nombre = name;
        comunidades = communities;
    }
    public String consultarNombre(){
        return nombre;
    }
    
    public ArrayList<Comunidad<N>> obtenerComunidades(){
        return comunidades;
    }
    
    public Comunidad obtenerComunidad(Integer i){
        return comunidades.get(i);
    }
    
    public Integer consultarComunidad(N nodo){
        Integer result = 0;
        for(int i = 0; i < comunidades.size(); ++i){
            if(comunidades.get(i).existeNodo(nodo)) result = i;
        }
        return result;
    }
}
