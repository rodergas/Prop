/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.GestorDeDatos;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * 
 */
public class CtrlListado {
    private CjtListado cjtl;
    private CtrlCancion ctrlc;
    
    public CtrlListado(){
        cjtl = new CjtListado();
        ctrlc = new CtrlCancion();
    }
    
    public CtrlListado(CtrlCancion c){
        cjtl = new CjtListado();
        ctrlc = c;
    }
       
    public void CtrlCrearListadoRandom(String nombre, int n){
        if(n > ctrlc.CtrlobtenerCanciones().size() || n == 0) throw new IllegalArgumentException("No se puede producir el listado");
        cjtl.anadirListadoRandom(nombre,n,ctrlc.CtrlobtenerCanciones());
    }
    
    public void CtrlCrearListadoManual(String nombre){
        cjtl.anadirListadoManual(nombre);
    }
    
    public void CtrlanadirListado(Listado l){
        cjtl.anadirListado(l);
    }
    
    public void CtrleliminarListado(String nombre) {
        cjtl.eliminarListado(nombre);
    }
    
    public Boolean CtrlexisteListado(String nombre) {
        return cjtl.existeListado(nombre);
    }
    
    public Listado CtrlconsultarListado(String nombre) {
        return cjtl.consultarListado(nombre);
    }
    
    public void CtrlanadirCancionListado(String nombre, String nombreC) {
        Cancion c = ctrlc.CtrlconsultarCancion(nombreC);
        cjtl.anadirCancionListado(nombre,c);
    }
    
    public void CtrleliminarCancionListado(String nombre, String nombreC){
        cjtl.eliminarCancionListado(nombre, nombreC);
    }
    
    public ArrayList<Cancion> consultarTodasCanciones(String nombre){
        return cjtl.consultarListado(nombre).consultarCanciones();
    }
    
    public ArrayList<Listado> consultarListados() {
        return cjtl.ObtenerListados();
    }
    
    public  LinkedList<String> CtrlNombres() {
        return cjtl.Nombres();
    }
    
    public  LinkedList<String> CtrlNombres(String s) {
        return cjtl.Nombres(s);
    }
    
    public void guardar(String path) throws Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirEscritura();
        Iterator<Listado> itl = cjtl.ObtenerListados().iterator();
        while (itl.hasNext()) {
            Listado l = itl.next();
            String s = l.consultarNombre();
            GD.escribir(s);
            Iterator<Reproduccion> itr = l.consultarReproducciones().iterator();
            if (!itr.hasNext()) GD.escribir("**");
            while (itr.hasNext()) {
                int i = 0;
                s = "*";
                while (itr.hasNext() && i < 100) {
                    Reproduccion r = itr.next();
                    s += '.' + r.consultarFecha() + "." + r.consultarHora() + "." + r.consutlarNombre() + '.' + "*";
                }
                GD.escribir(s);
            }
            Iterator<Cancion> itc = l.consultarCanciones().iterator();
            if (!itc.hasNext()) GD.escribir("//");
            while (itc.hasNext()) {
                int i = 0;
                s = "/";
                while (itc.hasNext() && i < 100) {
                    Cancion c = itc.next();
                    s += c.consultarTitulo() + "/";
                }
                GD.escribir(s);
            }
        }
        GD.cerrar();
    }
    
    public void cargar(String path) throws Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirLectura();
        String s = GD.leer();
        while (s != null) {
            Listado l = new Listado(s);
            s = GD.leer();
            ArrayList<Reproduccion> R = new ArrayList<Reproduccion>();
            while (s != null && s.charAt(0) == '*') {
                for (int i = 0; i < s.length(); ++i) {
                    if(s.charAt(i) != '*') {
                        int j = i;
                        while (i < s.length() && s.charAt(i) != '*') ++i;
                        R.add(leerRegistro(s.substring(j, i)));
                    }
                }
                s = GD.leer();
            }
            l.cargarRegistro(R);
            while (s != null && s.charAt(0) == '/') {
                for (int i = 0; i < s.length(); ++i) {
                    if(s.charAt(i) != '/') {
                        int j = i;
                        while (i < s.length() && s.charAt(i) != '/') ++i;
                        try {
                            l.anadirCancion(ctrlc.CtrlconsultarCancion(s.substring(j, i)));
                        }
                        catch(IllegalArgumentException IAE) {
                        }
                    }
                }
                s = GD.leer();
            }
            try  {
                cjtl.anadirListado(l);
            }
            catch(IllegalArgumentException IEA) {
                for (Cancion c : l.consultarCanciones()) {
                    try {
                        cjtl.anadirCancionListado(l.consultarNombre(), c);
                    }
                    catch(IllegalArgumentException IAE) {
                    }
                }
            }
        }
        GD.cerrar();
    }
    
    private Reproduccion leerRegistro(String s) {
        ArrayList<String> a = new ArrayList<String>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '.') {
                int j = i;
                while (i < s.length() && s.charAt(i) != '.') ++i;
                a.add(s.substring(j, i));
            }
        }
        return new Reproduccion(a.get(0), a.get(1), a.get(2));
    }
}
