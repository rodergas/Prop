/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author MonSB
 */
abstract public class Algoritmo<T> {
    public Algoritmo() {}
    abstract public Solucion<T> ejecutar(Grafo G, String nombre);
}
