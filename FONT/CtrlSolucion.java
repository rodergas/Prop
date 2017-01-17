/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.GestorDeDatos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author 1172257
 */
public class CtrlSolucion {
    private CjtSolucion cjts;
    private CtrlCancion ctrlc;
    public CtrlSolucion(){}
    
    public CtrlSolucion( CtrlCancion c){
        ctrlc = c;
        cjts = new CjtSolucion();
    }
    
    public CtrlCancion CtrlconsultarCtrlCanciones() {
        return ctrlc;
    }
    
    public void CtrlanadirSolucion (String nombreSol,Solucion<Cancion> resultado){
        cjts.anadirSolucion(nombreSol, resultado);
    }
    
    public void CtrleliminarSolucion(String nombre) {
        cjts.eliminarSolucion(nombre);
    }
    
    public Solucion<Cancion> CtrlConsultarSolucion(String nombre) {
        return cjts.consultarSolucion(nombre);
    }
    
    public ArrayList<Solucion<Cancion>> CtrlobtenerSoluciones(){
        return cjts.ObtenerSoluciones();
    }
    
    public LinkedList<String> CtrlNombres() {
        return cjts.Nombres();
    } 
    
    public LinkedList<String> CtrlNombres(String s) {
        return cjts.Nombres(s);
    } 
    
    public void guardar(String path) throws Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirEscritura();
        Iterator<Solucion<Cancion>> its = cjts.ObtenerSoluciones().iterator();
        while (its.hasNext()) {
            Solucion<Cancion> SC = its.next();
            String s = SC.consultarNombre();
            GD.escribir(s);
            Iterator<Comunidad<Cancion>> itc = SC.obtenerComunidades().iterator();
            while (itc.hasNext()) {
                Comunidad<Cancion> C = itc.next();
                s = "/" + C.consultarIdentificador();
                GD.escribir(s);
                Iterator<Cancion> it = C.consultarComunidad().iterator();
                while (it.hasNext()) {
                    int i = 0;
                    s = "*";
                    while (it.hasNext() && i < 100) {
                        Cancion can = it.next();
                        s += can.consultarTitulo() + "*";
                        ++i;
                    }
                    GD.escribir(s);
                }
            }
        }
        GD.cerrar();
    }
    
    public void cargar(String path) throws Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirLectura();
        String s = GD.leer();
        while (s != null) {
            String nombre = s;
            ArrayList<Comunidad<Cancion>> comunidades = new ArrayList<Comunidad<Cancion>>();      
            s = GD.leer();
            while (s != null && s.charAt(0) == '/') {
                Comunidad<Cancion> com = new Comunidad<Cancion>(Integer.parseInt(s.substring(1)));
                s = GD.leer();
                while (s != null && s.charAt(0) == '*') {
                    for (int i = 0; i < s.length(); ++i) {
                        if (s.charAt(i) != '*') {
                            int j = i;
                            while (i < s.length() && s.charAt(i) != '*') ++i;
                            try {
                                com.anadirNodo(ctrlc.CtrlconsultarCancion(s.substring(j, i)));
                            }
                            catch(IllegalArgumentException IAE) {
                                //System.out.println("no he podido cargar una canción");
                            }
                        }
                    }
                    s = GD.leer();
                }
                comunidades.add(com);
            }
            try {
                cjts.anadirSolucion(nombre,new Solucion<Cancion>(nombre, comunidades));
            }
            catch(IllegalArgumentException IAE) {
                //System.out.println(IAE.getMessage());
            }
        }
    }
    
    
   
}
