/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 *
 */
public class Arista<T> {
    private Integer origen;
    private Integer destino;
    private T peso;
    
    public Arista(){}
    
    public Arista(Integer v,Integer w, T weight) {
        origen = v;
        destino = w;
        peso = weight;
    }
    
    public Integer consultarOrigen() {
        return origen;
    }
    
    public Integer consultarDestino() {
        return destino;
    }
    
    public T consultarPeso() {
        return peso;
    }
    
   public void  modificarOrigen(Integer v) {
        origen = v;
    }
    
    public void  modificarDestino(Integer w) {
        destino = w;
    }
    
    public void  modificarPeso(T weight) {
        peso = weight;
    }
}
