/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 
 */
public class Newman<T> extends Algoritmo<T> {
    class ConnectedNodo {
        T nodo;
        Boolean visitado;
        Integer num_cc;
        
        public ConnectedNodo(T node) {
            nodo = node;
            visitado = false;
            num_cc = 0;
        }
        
        public ConnectedNodo(T no, Boolean v, Integer n){
            nodo = no;
            visitado = v;
            num_cc = n;
        }
                
        public Integer consultarNum(){
            return num_cc;
        }
        
        public T consultarNodo(){
            return nodo;
        }
        
    }
    class TriPair {
        Integer indiceO;
        Integer indiceD;
        Integer CaminosMinimos;
        
        public TriPair(Integer iO, Integer iD) {
            indiceO = iO;
            indiceD = iD;
            CaminosMinimos = 0;
        }
    }
    class nodoDijkstra<T> {
        T nodo;
        Float peso;
        public nodoDijkstra(T nodoV, Float p) {
            nodo = nodoV;
            peso = p;
        }
    }
    private Integer num_min_comunidades = 1;
    private ArrayList<TriPair> AC;
    private Integer nc = 1;
    private Integer maxBetweenness;
    private ArrayList<Arista> AristasBorradas;
    public Newman(){}
    
    public Newman(Integer n){
        num_min_comunidades = n;
    }
    
    public void modificarNumeroComunidades(int c) {
        num_min_comunidades = c;
    }
    
    public Integer consultar(){
        return num_min_comunidades;
    }
    
    public ArrayList<Arista> consultarAristasBorradas(){
        return AristasBorradas;
    }
    
    @Override
    public Solucion<T> ejecutar(Grafo G, String nombre) throws IllegalArgumentException{
        Integer compoconexas = G.compoConexas();
        if(compoconexas > num_min_comunidades) throw new IllegalArgumentException("El grafo ya tiene más o igual comunidades que las deseadas por el usuario");
        else if( G.ObtenerNodos().size() >= num_min_comunidades){
            ArrayList<ConnectedNodo> NodosConexos = new ArrayList<>();
            AristasBorradas = new ArrayList<>();
            while(nc < num_min_comunidades){
                nc = 0;
                ArrayList<Arista> Aristas = G.ObtenerAristas();
                Integer size = Aristas.size();
                AC = new ArrayList<TriPair>();
                for (int i = 0; i < size; ++i) {
                    Integer origen = Aristas.get(i).consultarOrigen();
                    Integer destino = Aristas.get(i).consultarDestino();
                    TriPair tri = new TriPair(destino, origen); 
                    boolean trobat = false;
                    int j = 0;
                    while(!trobat && j < AC.size()){
                        if(AC.get(j).indiceO.equals(tri.indiceO) && AC.get(j).indiceD.equals(tri.indiceD) && AC.get(j).CaminosMinimos.equals(tri.CaminosMinimos)){
                            trobat = true;
                        }
                        ++j;
                    }
                    if(!trobat) AC.add(new TriPair(origen, destino));
                }
                actualizarCaminosMinimos(G);
                Integer indiceO = AC.get(maxBetweenness).indiceO;
                Integer indiceD = AC.get(maxBetweenness).indiceD;
                Float peso = (Float) G.consultarPeso(G.consultarNodo(indiceO), G.consultarNodo(indiceD));
                Arista a = new Arista(indiceO,indiceD,peso);
                AristasBorradas.add(a);
                G.borrarArista(G.consultarNodo(indiceO), G.consultarNodo(indiceD));
                G.borrarArista(G.consultarNodo(indiceD), G.consultarNodo(indiceO));
                NodosConexos = compoConexas(G);

               /* for(int i = 0; i < NodosConexos.size();++i) {
                    Cancion c = (Cancion) G.consultarNodo(G.consultarIndice(NodosConexos.get(i).nodo)); 
                    System.out.println("NUMERO COMPONENTES CONEXAS TOTALES:" + nc);
                    System.out.println(c.consultarTitulo() + " " + NodosConexos.get(i).num_cc);
                    System.out.println(" ");
                }*/
                maxBetweenness = null;
            }  
        ArrayList<Comunidad> resultado = new ArrayList<Comunidad>();
        for(int i = 0; i < nc ;++i){ 
            resultado.add(new Comunidad(i));
        }

        for(int i = 0; i < NodosConexos.size(); ++i){
            Integer indice = (NodosConexos.get(i).num_cc)-1;
            resultado.get(indice).anadirNodo(NodosConexos.get(i).nodo);
        }
        Solucion solucion = new Solucion(nombre,resultado);
        for(int i = 0; i < AristasBorradas.size();++i){
            G.anadirArista(G.consultarNodo(AristasBorradas.get(i).consultarOrigen()), G.consultarNodo(AristasBorradas.get(i).consultarDestino()), AristasBorradas.get(i).consultarPeso());
            G.anadirArista(G.consultarNodo(AristasBorradas.get(i).consultarDestino()), G.consultarNodo(AristasBorradas.get(i).consultarOrigen()), AristasBorradas.get(i).consultarPeso());
        }
        return solucion;
        }
        else throw new IllegalArgumentException("El numero de comunidades deseadas debe de ser menor que el numero de nodos");
    }
    
    private ArrayList<ConnectedNodo> compoConexas(Grafo G) {
        ArrayList<T> N = G.ObtenerNodos();
        ArrayList<ConnectedNodo> v = new ArrayList<ConnectedNodo>();
        for (T nodo : N) v.add(new ConnectedNodo(nodo));
        for (ConnectedNodo cn : v) {
            if (!cn.visitado) {
                ++nc;
                conex1(G,cn,v);    
            }
            
        }
        return v;
    }
    
    private void conex1(Grafo G, ConnectedNodo cn, ArrayList<ConnectedNodo> v) {
    cn.visitado = true;
    cn.num_cc = nc;
    ArrayList<T> Sucesores = G.consultarSucesores(cn.nodo);
        for(T nodo : Sucesores){
            ConnectedNodo Sucesor = v.get(G.consultarIndice(nodo));
            if(!Sucesor.visitado) conex1(G,Sucesor,v);
        }
    }
    
    private void actualizarCaminosMinimos(Grafo G) {
        ArrayList<T> N = G.ObtenerNodos();
        for (T nodo : N) {
            for (T nodoD : N) {
                if (nodo != nodoD) {
                    Integer size = G.ObtenerNodos().size();
                    ArrayList<T> p = new ArrayList<T>((Collection<? extends T>) Collections.nCopies(size, null));
                    ArrayList<Float> d = new ArrayList<Float>(Collections.nCopies(size, Float.MAX_VALUE));
                    dijkstra(G, nodo, nodoD, p, d);
                    Integer indiceD = G.consultarIndice(nodoD);
                    if (d.get(indiceD) != Float.MAX_VALUE) {
                        ArrayList<T> v = new ArrayList<T>();
                        v.add(nodoD);
                        while (nodoD != nodo) {
                            indiceD = G.consultarIndice(nodoD);
                            T aux = p.get(indiceD);
                            v.add(aux);
                            nodoD = p.get(indiceD);
                        }       
                        
                        // En v tenemos el shortest path empezando por el destino
                        
                        for (int i = 0; i < v.size(); ++i) {    
                            Integer indiceA = G.consultarIndice(v.get(i));                        
                            Integer indiceB;                     
                            if ((i+1) < v.size()) {                          
                                indiceB = G.consultarIndice(v.get(i+1));                      
                                int j = 0;
                                Boolean trobat = false;
                                while (!trobat && j < AC.size()) {
                                    if ((indiceA == AC.get(j).indiceO) && (indiceB == AC.get(j).indiceD)) {
                                        ++AC.get(j).CaminosMinimos; 
                                        trobat = true;
                                        if(maxBetweenness == null)  maxBetweenness = j;  
                                        else if(AC.get(maxBetweenness).CaminosMinimos < AC.get(j).CaminosMinimos){
                                            maxBetweenness = j;
                                        }
                                    }   
                                    else if ((indiceA == AC.get(j).indiceD) && (indiceB == AC.get(j).indiceO)) {
                                        ++AC.get(j).CaminosMinimos;   
                                        trobat = true;
                                        if(maxBetweenness == null) maxBetweenness = j;  
                                        else if(AC.get(maxBetweenness).CaminosMinimos < AC.get(j).CaminosMinimos){
                                            maxBetweenness = j;
                                        }
                                    } 
                                    ++j;
                                }  
                            }
                        }
                    }         
                }
            }
        }
        /*
        for(int i = 0; i < AC.size(); ++i){
            Cancion c = (Cancion) G.consultarNodo(AC.get(i).indiceO);
            Cancion d = (Cancion) G.consultarNodo(AC.get(i).indiceD);
            System.out.print(c.consultarTitulo() + " " + d.consultarTitulo() + " " + AC.get(i).CaminosMinimos);
            System.out.println("");
        }
                */
    }
    
    private void dijkstra (Grafo G, T nodo, T nodoD, ArrayList<T> p, ArrayList<Float> d) {
        
        Integer size = G.ObtenerNodos().size();
        Integer indice = G.consultarIndice(nodo);
        d.set(indice,0.0f);
        ArrayList<Boolean> b = new ArrayList<Boolean>(Collections.nCopies(size, false));
        PriorityQueue<nodoDijkstra> Q = new PriorityQueue(1,
            new Comparator<nodoDijkstra>() {
                public int compare(nodoDijkstra a1, nodoDijkstra a2) {
                    return a1.peso.compareTo(a2.peso);
                }
            }
        );
        nodoDijkstra<T> n = new nodoDijkstra<T>(nodo, 0.0f);
        Q.add(n);
        while (!Q.isEmpty()) {
            T u = (T) Q.peek().nodo;
            Q.poll();
            Integer indiceU = G.consultarIndice(u);
            if (!b.get(indiceU)) {
                b.set(indiceU,true);
                ArrayList<T> SucesoresU = G.consultarSucesores(u);
                for (T nodoS : SucesoresU) {
                    Float coste = (Float) G.consultarPeso(u, nodoS);
                    Integer indiceS = G.consultarIndice(nodoS);
                    if (d.get(indiceS) > d.get(indiceU) + coste){
                        p.set(indiceS,u);
                        Float result = d.get(indiceU) + coste;
                        d.set(indiceS,result);
                        nodoDijkstra<T> n2 = new nodoDijkstra<T>(nodoS, result);
                        Q.add(n2);
                    }
                }
            }
        }
    }     
}
