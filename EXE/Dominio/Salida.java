/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author rober_000
 */
public class Salida {
    
    ArrayList<Arista> Newman;
    ArrayList<ArrayList<ArrayList<Cancion>>> Cliques;
    ArrayList<SolucionParcialLouvain> Louvain;
    ArrayList<Comunidad> LouvainComunidadIniciales;
    
    public Salida(){
        Newman = new ArrayList<>();
        Cliques = new ArrayList<>();
        Louvain = new ArrayList<>();
        LouvainComunidadIniciales = new ArrayList<>();
    }
    
    public void modificarNewman(ArrayList<Arista> n){
        Newman = n;
    }
   public ArrayList<Arista> consultarNewman(){
        return Newman;
    }
    public void modificarCliques(ArrayList<ArrayList<ArrayList<Cancion>>> c){
        Cliques = c;
    }
   public ArrayList<ArrayList<ArrayList<Cancion>>> consultarCliques(){
        return Cliques;
    }
    public void modificarLouvain(ArrayList<SolucionParcialLouvain> l, ArrayList<Comunidad> ComI){
        Louvain = l;
        LouvainComunidadIniciales = ComI;
    }
    public ArrayList<SolucionParcialLouvain> consultarLouvain(){
        return Louvain;
    }
    
    public ArrayList<Comunidad> consultarLouvainInicial(){
        return LouvainComunidadIniciales;
    }
   
}
