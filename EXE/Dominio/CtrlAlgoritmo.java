/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 1172070
 */
public class CtrlAlgoritmo {
    private CtrlRelacion r;
    private CtrlSolucion s;
    private Salida salida;
    private Grafo<Cancion,Float> G;
    private ArrayList<Comunidad> ComL;
    //private Algoritmo<Cancion> A;
    private Boolean hechoNewman = false;
    private Integer itNewman = 0;
    private Integer itCliques = 0;
    private Integer itLouvain = 0;
    private Boolean init = false;
    private Boolean initNew = false;
    
    public CtrlAlgoritmo() {}
    
    public CtrlAlgoritmo(CtrlRelacion grafo,CtrlSolucion s1) {
        r = grafo;
        s = s1;
        salida = new Salida(); 
        G = new Grafo<>();
    }
    
    public CtrlSolucion CtrlConsultarCtrlSolucion() {
        return s;
    }
    
    public CtrlRelacion CtrlConsultarCtrlRelacion() {
        return r;
    }
    
    public void ejecutarAlgoritmo(int id, String nombreSolucion,Integer n)throws IllegalArgumentException{
        if(hechoNewman) {reconstruirGrafo(G,salida.consultarNewman()); hechoNewman = !hechoNewman;}
        if(id == 1){
            CliquePercolation CP = new CliquePercolation(n);
            Solucion<Cancion> sol = new Solucion<>();
            //try{
                G = new Grafo(r.consultarGrafo());
                sol = CP.ejecutarParcial(G,nombreSolucion);
                //System.out.println(sol.obtenerComunidades().size());
                salida.modificarCliques(CP.devolverSolucionParcial());
                s.CtrlanadirSolucion(nombreSolucion, sol);
            //}catch(IllegalArgumentException e){
                //System.out.println(e.getMessage());
            //}
        }
        else if(id == 2){
            Newman newman = new Newman(n);
            Solucion<Cancion> sol = new Solucion<>();
            //try{
             G = new Grafo(r.consultarGrafo());
             sol =  newman.ejecutar(G, nombreSolucion);
             salida.modificarNewman(newman.consultarAristasBorradas());
             s.CtrlanadirSolucion(nombreSolucion, sol);
            //}catch (IllegalArgumentException e){
                //System.out.println(e.getMessage());
            //}

        }
        else if(id == 3){
            init = false;
            Louvain L = new Louvain();
            Solucion<Cancion> sol = new Solucion<>();
            //try{
            G = new Grafo(r.consultarGrafo());
            sol =  L.ejecutar(G, nombreSolucion);
            salida.modificarLouvain(L.devolverSolucionParcial(),L.devolverComunidadesIniciales());
            s.CtrlanadirSolucion(nombreSolucion, sol);
            //}catch(IllegalArgumentException e){
                //System.out.println(e.getMessage());
            //}
        }
        else{
            throw new IllegalArgumentException("No hay asignado un algortimo para ese numero");
        }
    }
    
    public boolean ejecutarSolucionFinalLouvain() {
        return true;
    }
    
   public Boolean ejecutarSolucionParcialLouvain() {
        if(!init) {init = true; ComL = salida.consultarLouvainInicial(); return false;}
        else {
            actualizar();
            return true;
        }
    }
    
    public boolean ejecutarSolucionFinalClique() {
        return true;
    }
    
    public ArrayList<ArrayList<Cancion>> ejecutarSolucionParcialClique() {
        if(itCliques < salida.consultarCliques().size()) ++itCliques;
        return salida.consultarCliques().get(itCliques-1);
    }
    
    public boolean ejecutarSolucionFinalNewman() {
        int i;
        for(i = itNewman; i < salida.consultarNewman().size(); ++i){
            Arista a = salida.consultarNewman().get(i);
            G.borrarArista(G.consultarNodo(a.consultarOrigen()), G.consultarNodo(a.consultarDestino()));
            G.borrarArista(G.consultarNodo(a.consultarDestino()), G.consultarNodo(a.consultarOrigen()));
        }
        itNewman = i;
        if(!hechoNewman){
            hechoNewman = true;
            itNewman = 0;
        }
        return hechoNewman;
    }
    
    public boolean ejecutarSolucionParcialNewman() {
        if(!initNew) initNew = true;
        else if(itNewman < salida.consultarNewman().size()){
            Arista a = salida.consultarNewman().get(itNewman);
            G.borrarArista(G.consultarNodo(a.consultarOrigen()), G.consultarNodo(a.consultarDestino()));
            G.borrarArista(G.consultarNodo(a.consultarDestino()), G.consultarNodo(a.consultarOrigen()));
            ++itNewman;
        }
        if(itNewman >= salida.consultarNewman().size()){
            itNewman = 0;
            hechoNewman = true;
            initNew = false;
        } else hechoNewman = false;
        return hechoNewman;
    }
    
    public void reconstruirGrafo(Grafo<Cancion,Float> G, ArrayList<Arista> AristasBorradas){
         for(int i = 0; i < AristasBorradas.size();++i){
            G.anadirArista(G.consultarNodo(AristasBorradas.get(i).consultarOrigen()), G.consultarNodo(AristasBorradas.get(i).consultarDestino()), (Float) AristasBorradas.get(i).consultarPeso());
            G.anadirArista(G.consultarNodo(AristasBorradas.get(i).consultarDestino()), G.consultarNodo(AristasBorradas.get(i).consultarOrigen()), (Float) AristasBorradas.get(i).consultarPeso());
        }
    }
    
    public Grafo<Cancion,Float> retornarGrafo(){
        return G;
    }
    
    public void reiniciarItCliques(){
        itCliques = 0;
    }
    
    public void reiniciarItNewman(){
        itNewman = 0;
        initNew = false;
    }
    
    public ArrayList<Comunidad> LouvainComIni(){
        return salida.consultarLouvainInicial();
    }
    
    public void actualizar(){
        if(itLouvain < salida.consultarLouvain().size()) ++itLouvain;
        SolucionParcialLouvain SPL = salida.consultarLouvain().get(itLouvain-1);
        ComL.get(SPL.consultarIdComunidadOrigen()).borrarNodo(SPL.consultarCancion());
        ComL.get(SPL.consultarIdComunidadDestino()).anadirNodo(SPL.consultarCancion());
    }
    
    public void reiniciarLouvain(){
        init = false;
        itLouvain = 0;
    }
}
