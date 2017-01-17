/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 *
 */
public class Listado {
    //Atributos
    private String nombre;
    
    private TST<Cancion> canciones;
    private ArrayList<Reproduccion> Registro;
   
    //Constructoras
    public Listado(String name) {
        nombre = name;
        canciones = new TST<Cancion>();
        Registro = new ArrayList<Reproduccion>();
    }
    

    public Listado() {
        canciones = new TST<Cancion>();
        Registro = new ArrayList<Reproduccion>();
    }
    
    //Consultoras
    public String consultarNombre() {
        return nombre;
    }
    
    public Cancion consultarCancion(String titulo) throws IllegalArgumentException {
        Cancion c = canciones.consultarElemento(titulo);
        if (c == null) throw new IllegalArgumentException("No existe una cancion con ese titulo");
        else return  c;
    }
    
    public Boolean existeCancion(String titulo) {
        return canciones.contiene(titulo);
    }
    
    public ArrayList<Cancion> consultarCanciones(){
        LinkedList<String> c =  (LinkedList<String>) canciones.ObtenerClaves();
        ArrayList<Cancion> resultado = new ArrayList<Cancion>();
        for (int i = 0; i < c.size(); ++i){
            Cancion can = canciones.consultarElemento(c.get(i));
            resultado.add(can);
        }
        return resultado;
    }
    
    //Modificadoras
    public void modificarTST(TST<Cancion> t){
        canciones = t;
    }
    
    public void modificarNombre(String name) {
        nombre = name;
    }
    
    public void anadirCancion(Cancion c) {
        try { canciones.anadirNodo(c.consultarTitulo(), c); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe una cancion en el listado con ese titulo"); 
        }
    }
    
    public void eliminarCancion(String titulo) throws IllegalArgumentException {
        try { canciones.eliminarElemento(titulo); }
        catch (IllegalArgumentException IAE) { 
            throw new IllegalArgumentException("No existe una cancion en el listado con ese titulo"); 
        }
    }
    
   /* 
    public void printListado() {
        for(int i = 0; i < canciones.size(); ++i){
            System.out.println(canciones.get(i).consultarTitulo());
        }
    }
    
    */
    public void escucharCancion(String titulo) throws IllegalArgumentException {
        Cancion c = canciones.consultarElemento(titulo);
        if (c == null) throw new IllegalArgumentException("No existe una cancion en el listado con ese titulo");
        else {
            c.CancionEscuchada();
            Registro.add(new Reproduccion(nombre));
        }
    }
    
    public ArrayList<Reproduccion> consultarReproducciones() {
        return Registro;
    }
    
    public void cargarRegistro(ArrayList<Reproduccion> R) {
        Registro = R;
    }
    
    public void crearTST(ArrayList<Cancion> a){
        for (int i = 0; i < a.size();++i){
            canciones.anadirNodo(a.get(i).consultarTitulo(), a.get(i));
        }
    }
    
    public LinkedList<String> consultarNombres() {
        return (LinkedList<String>) canciones.ObtenerClaves();
    }
    
    public LinkedList<String> consultarNombres(String s) {
        return (LinkedList<String>) canciones.Prefijo(s);
    }
}
