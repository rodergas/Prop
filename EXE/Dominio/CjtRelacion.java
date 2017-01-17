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
 * @author MonSB
 */
public class CjtRelacion {
    
    private Grafo<Cancion, Float> G;
    private TST<Relacion> relaciones;
    
    
    public CjtRelacion() {
        relaciones = new TST<Relacion>();
    }
    
    public Grafo<Cancion, Float> obtenerGrafo(){
        return G;
    }
    
    public Relacion CrearRelacionSimple(String nombre,String criterio,Object contenido){
        boolean existe = false;
        existe = existeRelacion(nombre);
        if (!existe){
            RelacionS<Object> S = new RelacionS<Object>(nombre,criterio, contenido);
            relaciones.anadirNodo(nombre, S);
            return S;
        }
        else throw new IllegalArgumentException("Ya existe una relacion con ese nombre");
    }
    
    public Boolean existeRelacion(String nombre) {
        boolean trobat = false;
        Relacion r = relaciones.consultarElemento(nombre);
        if (r != null) trobat = true;
        return trobat;
    }
    
    public Relacion CrearRelacionAND(String nombre,String izq, String der){
        Relacion R = relaciones.consultarElemento(izq);
        Relacion R1 = relaciones.consultarElemento(der);
        boolean existe = false;
        existe = existeRelacion(nombre);
        if (!existe){
            RelacionAND AND = new RelacionAND(nombre,R,R1);
            relaciones.anadirNodo(nombre, AND);
            return AND;
        }
        else throw new IllegalArgumentException("Ya existe una relacion con ese nombre");
    }
    
    public Relacion CrearRelacionOR(String nombre,String izq, String der){
        Relacion R = relaciones.consultarElemento(izq);
        Relacion R1 = relaciones.consultarElemento(der);
        boolean existe = false;
        existe = existeRelacion(nombre);
        if (!existe){
            RelacionOR OR = new RelacionOR(nombre,R,R1);
            relaciones.anadirNodo(nombre, OR);
            return OR;
        }
        else throw new IllegalArgumentException("Ya existe una relacion con ese nombre");
    }
    
    public Relacion CrearRelacionNOT(String nombre,String relacion){
        boolean existe = false;
        existe = existeRelacion(nombre);
        Relacion R = relaciones.consultarElemento(relacion);
        if (!existe){
            RelacionNOT NOT = new RelacionNOT(nombre,R);
            relaciones.anadirNodo(nombre, NOT);
            return NOT;
        }
        else throw new IllegalArgumentException("Ya existe una relacion con ese nombre");
    }
    
    public Grafo<Cancion,Float> CrearGrafo(String nombreRelacion, CjtCancion Cjtc, CjtUsuario Cjtu) {
        G = new Grafo<Cancion, Float>();
        Relacion R = relaciones.consultarElemento(nombreRelacion);
        ArrayList<Cancion> canciones = R.obtenerConjunto(Cjtc,Cjtu);
        G.ConstruirGrafo(canciones);
        
        for (Cancion c : canciones){
            for (Cancion c1 : canciones){
                if (c != c1){
                    float peso;
                    //Autor
                    float autor;
                    if (c.consultarAutor().equalsIgnoreCase(c1.consultarAutor())) autor = 1;
                    else autor = 0;

                    //Duracion
                    float duracion;
                    if (c.consultarDuracion().equals(c1.consultarDuracion())) duracion = 1;
                    else duracion = 0;

                    //Titulo
                    float titulo;
                    if (c.consultarTitulo().equalsIgnoreCase(c1.consultarTitulo())) titulo = 1;
                    else titulo = 0;

                    //Numero de reproduciones
                    float num_rep;
                    if (c.consultarReproducciones().equals(c1.consultarReproducciones())) num_rep = 1;
                    else num_rep = 0;

                    //Genero
                    float genero = 0;
                    if (c.consultarGenero().equals(c1.consultarGenero())) genero = 1;
                    else genero = 0;

                    peso = autor + duracion + titulo + num_rep + genero;

                    if (peso != 0)G.anadirArista(c,c1,peso);

                }
            }
        }
        return G;
    }
    
    public LinkedList<String> Nombres() {
        return (LinkedList<String>) relaciones.ObtenerClaves();
    }
    
    public LinkedList<String> Nombres(String s) {
        return (LinkedList<String>) relaciones.Prefijo(s);
    }
    
    /*
    public void escribir(){
        G.oly();
    }
    */
    
}
