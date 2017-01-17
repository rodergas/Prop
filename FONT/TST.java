/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.*;
/**
 *
 * @author MonSB
 */
public class TST<T> {
    
    class Nodo<T> {
        private char id;
        private Nodo<T> Izquierdo, Central, Derecho;
        private T info;
        
        public Nodo(char identificador) {
            id = identificador;
            Izquierdo = null;
            Central = null;
            Derecho = null;
            info = null;
        }
        
        public T consultarInfo(){
            return info;
        }
    }
    
    private Integer tamano;
    private Nodo<T> Raiz;
    private boolean aux;
    
    public TST(){
        tamano = 0;
    }
    
    public Integer consultarTamano(){
        return tamano;
    }
    
    public void anadirNodo(String id, T datos) throws IllegalArgumentException {
        aux = false;
        if (id.isEmpty()) throw new IllegalArgumentException("Identificador invalido");
        if (datos == null) throw new IllegalArgumentException("Sin datos");
        Raiz = Anadir(Raiz, id, datos, 0);
        if (!aux) throw new IllegalArgumentException("Ya existe un elemento con ese id");
    }
    
    private Nodo<T> Anadir(Nodo<T> N, String id2, T datos, Integer indice) {
        char actual = id2.charAt(indice);
        if (N == null) {
            N = new Nodo<T>(actual);
            Anadir(N, id2, datos, indice);
        }
        else if (actual < N.id) N.Izquierdo = Anadir(N.Izquierdo, id2, datos, indice);
        else if (actual > N.id) N.Derecho = Anadir(N.Derecho, id2, datos, indice);
        else if (indice < (id2.length() - 1)) N.Central = Anadir(N.Central, id2, datos, indice + 1);
        else {
            if (N.info == null) {
                ++tamano;
                N.info = datos;
                aux = true;
            }
        }
        return N;
    }
    
    public T consultarElemento(String id)throws IllegalArgumentException {
        if (id.isEmpty()) throw new IllegalArgumentException("Identificador vacio");
        Nodo<T> R = consultarNodo(Raiz, id, 0);
        if (R == null) return null;
        else return R.consultarInfo();
    }  
    
    private Nodo<T> consultarNodo(Nodo<T> N, String id2, Integer indice) {
        if (N == null) return null;
        char actual = id2.charAt(indice);
        if (actual < N.id) return consultarNodo(N.Izquierdo, id2, indice);
        else if (actual > N.id) return consultarNodo(N.Derecho, id2, indice);
        else if (indice < (id2.length() - 1)) return consultarNodo(N.Central, id2, indice + 1);
        else return N;
    }
    
    public void eliminarElemento(String id)throws NullPointerException, IllegalArgumentException {
        aux = false;
        if (id.isEmpty()) throw new IllegalArgumentException("Identificador vacio");
        eliminarNodo(Raiz, id, 0);
        if (!aux) throw new IllegalArgumentException("No existe un elemento con ese id");
    }
    
    private void eliminarNodo(Nodo<T> N, String id2, Integer indice) {
        if (N == null) return;
        char actual = id2.charAt(indice);
        if (actual < N.id) eliminarNodo(N.Izquierdo, id2, indice);
        else if (actual > N.id) eliminarNodo(N.Derecho, id2, indice);
        else if (indice < (id2.length() - 1)) eliminarNodo(N.Central, id2, indice + 1);
        else {
            --tamano;
            N.info = null;
            N = null;
            aux = true;
        }
    }
    
    public boolean contiene(String id) throws IllegalArgumentException {
        if (id.isEmpty()) throw new IllegalArgumentException("Identificador vacio");
        Nodo nodo = consultarNodo(Raiz, id, 0);
        if (nodo == null) return false;
        else {
            if (nodo.info != null) return true;
            else return false;
        }
    }
    
    public T obtener(String id) throws IllegalArgumentException {
        if (id.isEmpty()) throw new IllegalArgumentException("Identificador vacio");
        Nodo nodo = consultarNodo(Raiz, id, 0);
        if (nodo == null) return null;
        else return (T) nodo.info;
    }
    
    //Devuelve todas las keys del tst introducidas
    public Iterable<String> ObtenerClaves(){
        LinkedList<String> claves = new LinkedList<>();
        Obtener(Raiz, "", claves);
        return claves;
    }
    
    //Devuelve todas las keys con el prefijo introducido
    public Iterable<String> Prefijo(String prefijo){
        LinkedList<String> claves = new LinkedList<>();
        Nodo x = consultarNodo(Raiz,prefijo,0);
        if(x == null) return claves;
        if(x.info != null) claves.add(prefijo);
        Obtener(x.Central, prefijo, claves);
        return claves;
    }
    
    //Dado un prefijo autocompleta palabras
    private void Obtener(Nodo x, String prefijo, LinkedList<String> claves){
        if(x != null){
            Obtener(x.Izquierdo, prefijo, claves);
            if(x.info != null) claves.add(prefijo + x.id);
            Obtener(x.Central, prefijo + x.id, claves);
            Obtener(x.Derecho, prefijo, claves);
        }
    }
}