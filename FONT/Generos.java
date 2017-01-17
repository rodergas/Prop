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
public class Generos {
    
    private TST<String> generos;
    
    public Generos() {
        generos = new TST<String>();
    }
    
    public boolean existeGenero(String style) {
        return generos.contiene(style);
    }
    
    public void anadirGenero(String style) throws IllegalArgumentException {
        if (!string_valido(style)) throw new IllegalArgumentException("El genero introducido no es valido");
        try { generos.anadirNodo(style,style); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe ese genero");
        }
    }
    
     public ArrayList<String> obtenerGeneros(){
        LinkedList<String> genero =  (LinkedList<String>) generos.ObtenerClaves();
        ArrayList<String> resultado = new ArrayList<>();
        for(int i = 0; i < genero.size(); ++i){
            String s = generos.consultarElemento(genero.get(i));
            resultado.add(s);
        }
        return resultado;
    }
     
    public LinkedList<String> Nombres(String s) {
        return (LinkedList<String>)generos.Prefijo(s);
    }
    
    private boolean string_valido (String s) {
        boolean valido = true;
        int i = 0;
        while (valido && i < s.length()) {
            if (!Character.isLetter(s.charAt(i))) valido = false;
            ++i;
        }
        return valido;
    }
 
}
