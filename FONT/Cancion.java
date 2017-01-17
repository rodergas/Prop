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
public class Cancion {
    //Atributos
    private String titulo;
    private String autor;
    private Integer minutos;
    private Integer segundos;
    private Integer duracion;
    private Integer num_reproducciones;
    private String genero;
    
    //Constructoras
    public Cancion(String title, String author, Integer minutes, Integer seconds, 
                   Integer num_reproductions, String style) throws IllegalArgumentException {
        if (!string_valido(title)) throw new IllegalArgumentException("El titulo introducido no es valido");
        if (!string_valido(author)) throw new IllegalArgumentException("El autor introducido no es valido");
        if (!duracion_valida(minutes)) throw new IllegalArgumentException("El numero de minutos introducido no es valido");
        if (!duracion_valida(seconds)) throw new IllegalArgumentException("El numero de segundos introducido no es valido");
        if (!reproducciones_validas(num_reproductions)) throw new IllegalArgumentException("El numero de reproducciones introducido no es valido");
        titulo = title;
        autor = author;
        minutos = minutes;
        segundos = seconds;
        num_reproducciones = num_reproductions;
        genero = style;
        duracion = minutos*60 + segundos;
    }
    
    public Cancion() {}
    
    // Consultoras
    public String consultarTitulo() {
        return titulo;
    }
    
    public String consultarAutor() {
        return autor;
    }
    
    public Integer consultarMinutos() {
        return minutos;
    }
    
    public Integer consultarSegundos() {
        return segundos;
    }
    
    public Integer consultarDuracion() {
        return duracion;
    }
    
    public Integer consultarReproducciones() {
        return num_reproducciones;
    }
    
    public String consultarGenero() {
        return genero;
    }
    
    //Modificadoras
    public void modificarTitulo(String title) throws IllegalArgumentException {
        if (!string_valido(title)) throw new IllegalArgumentException("El titulo introducido no es valido");
        titulo = title;
    }
    
    public void modificarAutor(String author) throws IllegalArgumentException {
        if (!string_valido(author)) throw new IllegalArgumentException("El autor introducido no es valido");
        autor = author;
    }
    
    public void modificarMinutos(Integer minutes) throws IllegalArgumentException {
        if (!duracion_valida(minutes)) throw new IllegalArgumentException("El numero de minutos introducido no es valido");
        minutos = minutes;
        duracion = minutos*60 + segundos;
    }
    
    public void modificarSegundos(Integer seconds) throws IllegalArgumentException {
        if (!duracion_valida(seconds)) throw new IllegalArgumentException("El numero de segundos introducido no es valido");
        segundos = seconds;
        duracion = minutos*60 + segundos;
    }
        
    public void modificarReproducciones(Integer num_reproductions) throws IllegalArgumentException {
        if (!reproducciones_validas(num_reproductions)) throw new IllegalArgumentException("El numero de reproducciones introducido no es valido");
        num_reproducciones = num_reproductions;
    }
    
    public void CancionEscuchada() {
        ++num_reproducciones;
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
    
    private boolean reproducciones_validas (Integer i) {   
        return (i >= 0);
    }
    
   private boolean duracion_valida (Integer i) {
       return (i >= 0 && i <= 59);
   }
    
}