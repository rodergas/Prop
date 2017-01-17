/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author 1171535
 */
public class Comunidad<N> {
    private ArrayList<N> comunidad;
    private Integer id;
   
    public Comunidad(){}
   
    public Comunidad(Integer identificador){
       comunidad = new ArrayList<N>();
       id = identificador;
    }
    
    public ArrayList<N> consultarComunidad(){
       return comunidad;
    }
  
    public Integer consultarTamano(){
       return comunidad.size();
    }
   
    public N consultarNodo(Integer index){
       if(index >= 0 && index < comunidad.size()) return comunidad.get(index);
       return null;
    }
    public Integer consultarIdentificador(){
       return id;
    }
   
    public void anadirNodo(N nodo){
       comunidad.add(nodo);
    }
    /*
    public void borrarNodo(N nodo){
       for(int i = 0; i < comunidad.size();++i){
           if(comunidad.get(i).equals(nodo)) comunidad.remove(i);
       }
    }
    */
    public void borrarNodo(N nodo) {
        boolean borrado = false;
        int i = 0;
        while (!borrado && i < comunidad.size()) {
            if (comunidad.get(i).equals(nodo)) {
                comunidad.remove(i);
                borrado = true;
            }
            ++i;
        }
    }
    public boolean existeNodo (N nodo) {
        boolean trobat = false;
        int i = 0;
        while (!trobat && i < comunidad.size()) {
            if (comunidad.get(i).equals(nodo)) trobat = true;
            ++i;
        }
        return trobat;
    }
}
