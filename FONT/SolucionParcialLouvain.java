/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 *
 */
public class SolucionParcialLouvain {
    
    private Cancion c;
    private Integer idComunidadOrigen;
    private Integer idComunidadDestino;
    
    public SolucionParcialLouvain(){}
   
    public SolucionParcialLouvain(Cancion ca, Integer idCO, Integer idCD){
        c = ca;
        idComunidadOrigen = idCO;
        idComunidadDestino = idCD;
    }
    
    public Cancion consultarCancion(){
       return c;
    }
    
    public Integer consultarIdComunidadOrigen(){
        return idComunidadOrigen;
    }
    
    public Integer consultarIdComunidadDestino(){
        return idComunidadDestino;
    }
    
}
