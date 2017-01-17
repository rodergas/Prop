/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.LinkedList;

/**
 *
 * @author 1172257
 */
public class CtrlRelacion {
    private CjtRelacion cjtr;
    private CtrlUsuario u;
    private CtrlCancion c;
    
    public CtrlRelacion(CtrlCancion c2, CtrlUsuario u2){
        cjtr = new CjtRelacion();
        u = u2;
        c = c2;
    }
    
    public Relacion CtrlRelacionRelacionS(String nombre, String criterio, Object valor){
        return cjtr.CrearRelacionSimple(nombre, criterio,valor);
    }
    
    
    public Relacion CtrlRelacionRelacionAND(String nombre,String Izq, String Der){
        return cjtr.CrearRelacionAND(nombre, Izq, Der);
    }
    
    public Relacion CtrlRelacionRelacionOR(String nombre,String Izq, String Der){
        return cjtr.CrearRelacionOR(nombre,Izq, Der);
    }
    
    public Relacion CtrlRelacionRelacionNOT(String nombre,String R){
        return cjtr.CrearRelacionNOT(nombre, R);
    }
    
    public Grafo<Cancion,Float> CrearGrafo(String nombre){
        CjtCancion Cjtc = c.CtrlobtenerConjunto();
        CjtUsuario Cjtu = u.CtrlObtenerConjunto();
        return cjtr.CrearGrafo(nombre,Cjtc,Cjtu);
    }
    /*
    public CtrlRelacion(Relacion R, CjtCancion Cjtc) {
        cjtr = new CjtRelacion(R, Cjtc);
    }
    */
    public CjtRelacion consultarConjuntoDeRelaciones() {
        return cjtr;
    }
    
    public Grafo<Cancion, Float> consultarGrafo() {
        return cjtr.obtenerGrafo();
    }
    
    public LinkedList<String> CtrlNombres() {
        return cjtr.Nombres();
    }
    
    public LinkedList<String> CtrlNombres(String s) {
        return cjtr.Nombres(s);
    }
}
