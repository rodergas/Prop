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
public class Usuario {
    //Atributos
    
    private String nombre;
    private String nacionalidad;
    private Integer edad;
    private CjtListado listasUsuario;
    
    //Contructuras
    
    public Usuario() {}
    
    public Usuario(String name, String nationality, Integer age) {
        if (!string_valido(name)) throw new IllegalArgumentException("El nombre introducido no es valido");
        if (!string_valido(nationality)) throw new IllegalArgumentException("La nacionalidad introducida no es valida");
        if (!entero_valido(age)) throw new IllegalArgumentException("La edad introducida no es valida");
        nombre = name;
        nacionalidad = nationality;
        edad = age;
        listasUsuario = new CjtListado();
    }
    
    //Consultoras
    
    public String consultarNombre() {
        return nombre;
    }
    
    public String consultarNacionalidad() {
        return nacionalidad;
    }
    
    public Integer consultarEdad() {
        return edad;
    }
    
    //Modificadoras
    
    public void modificarNombre(String name) {
        if (!string_valido(name)) throw new IllegalArgumentException("El nombre introducido no es valido");
        nombre = name;
    }
    
    public void modificarNacionalidad(String nationality) {
        if (!string_valido(nationality)) throw new IllegalArgumentException("La nacionalidad introducida no es valida");
        nacionalidad = nationality;
    }
    
    public void modificarEdad(Integer age) {
        if (!entero_valido(age)) throw new IllegalArgumentException("La edad introducida no es valida");
        edad = age;
    }
    
    public void anadirListado(Listado L) {
        listasUsuario.anadirListado(L);
    }
    
    public Listado consultaListado(String nombreListado){
       return listasUsuario.consultarListado(nombreListado);
    }
    
    public CjtListado consultaListados(){
       return listasUsuario;
    }
    
    public void borrarListado(String nombreListado){
        Listado l = consultaListado(nombreListado);
        if(l == null) throw new IllegalArgumentException("No existe Listado");
        else listasUsuario.eliminarListado(nombreListado);
    }
    
    public void eliminarCancionListado(String nombreListado, String nombre){
        Listado l = consultaListado(nombreListado);
        if(l == null) throw new IllegalArgumentException("No existe Listado");
        else l.eliminarCancion(nombre);
    }
    
    public void anadirCancionListado(String nombreListado, Cancion c){
        Listado l = consultaListado(nombreListado);
        if(l == null) throw new IllegalArgumentException("No existe Listado");
        else l.anadirCancion(c);
    }
    
    public ArrayList<Listado> consultarListados() {
        return listasUsuario.ObtenerListados();
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
    
    private boolean entero_valido (Integer i) {   
        return (i > 0);
    }
}
