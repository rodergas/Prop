/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.*;


public class Grafo<V, E> {
    Integer nc = 0;
    ArrayList<ConnectedNodoGrafo> v;
    class ConnectedNodoGrafo {
        V nodo;
        Boolean visitado;
        Integer num_cc;
        
        public ConnectedNodoGrafo(V node) {
            nodo = node;
            visitado = false;
            num_cc = 0;
        }
        
        public Integer consultarNum(){
            return num_cc;
        }
        
        public V consultarNodo(){
            return nodo;
        }
        
        public Boolean consultarVisitado(){
            return visitado;
        }
    }
    class Pair{
        ArrayList<Arista<E>> sucesores;
        ArrayList<Arista<E>> predecesores;
        public Pair() {
            sucesores = new ArrayList<Arista<E>>();
            predecesores = new ArrayList<Arista<E>>();
        }
    }
    
    ArrayList<Pair> Graf;
    ArrayList<V> Nodos;
    
    public Grafo(){
        Nodos = new ArrayList<V>();
        Graf = new ArrayList<Pair>();
    }
    
    public ArrayList<V> dameNodos () {
        return Nodos;
    }
    
    public ArrayList<ConnectedNodoGrafo> consultarComposConexas() {
        return v;
    }

    public Integer compoConexas() {
        ArrayList<V> N = ObtenerNodos();
        v = new ArrayList<ConnectedNodoGrafo>();
        for (V nodo : N) v.add(new ConnectedNodoGrafo(nodo));
        for (ConnectedNodoGrafo cn : v) {
            if (!cn.visitado) {
                ++nc;
                conex1(cn,v);    
            }
            
        }
        return nc;
    }
    
    private void conex1(ConnectedNodoGrafo cn, ArrayList<ConnectedNodoGrafo> v) {
    cn.visitado = true;
    cn.num_cc = nc;
    ArrayList<V> Sucesores = consultarSucesores(cn.nodo);
        for(V nodo : Sucesores){
            ConnectedNodoGrafo Sucesor = v.get(consultarIndice(nodo));
            if(!Sucesor.visitado) conex1(Sucesor,v);
        }
    }
    /*public ArrayList<Pair> dameGraf() {
        return Graf;
    }*/
    
    public Grafo(Grafo GOriginal) {
        /*
        Nodos = new ArrayList<V>();
        Nodos = Goriginal.dameNodos();
        Graf = new ArrayList<Pair>();
        for(int i = 0; i < Goriginal.dameGraf().size(); ++i) {
            Pair p = new Pair();
            p.sucesores = new ArrayList<Arista<E>>();
            p.predecesores = new ArrayList<Arista<E>>();
            Graf.add(p);
        }
        Graf = Goriginal.dameGraf();
        */
        Nodos = new ArrayList<V>();
        Graf = new ArrayList<Pair>();
        ArrayList<V> NodosOriginal = GOriginal.dameNodos();
        
        for (V node : NodosOriginal) anadirNodo(node);
           
        ArrayList<Arista<E>> AristasOriginal = GOriginal.ObtenerAristas();
        for (Arista<E> aresta : AristasOriginal) {
            V source = Nodos.get(aresta.consultarOrigen());
            //System.out.println(source);
            V destination= Nodos.get(aresta.consultarDestino());
            E weight = aresta.consultarPeso();
            anadirArista(source, destination, weight);
        }
        
        
    }
    
    public void anadirNodo(V nodo) throws IllegalArgumentException{
        if(!Nodos.contains(nodo)){
            Integer i = Nodos.indexOf(nodo);
            Graf.add(new Pair());
            //Graf.get(i).predecesores = new ArrayList<>();
            //Graf.get(i).sucesores = new ArrayList<>();
            Nodos.add(nodo);
        }
        else throw new IllegalArgumentException("Ya existe este nodo");
    }
    
    public boolean existeNodo (V nodo){
        return(Nodos.contains(nodo));
    }
    
    public E consultarPeso (V nodoO, V nodoD)throws IllegalArgumentException {
        if (Nodos.contains(nodoO) && Nodos.contains(nodoD)){
            Integer in = Nodos.indexOf(nodoO);
            Integer mida = Graf.get(in).sucesores.size();          
            for (int i = 0; i < mida; ++i) {    
                Integer nodeD = Graf.get(in).sucesores.get(i).consultarDestino();
                if (Nodos.get(nodeD).equals(nodoD)) {
                    return Graf.get(in).sucesores.get(i).consultarPeso();
                }
            }
            throw new IllegalArgumentException("No existe arista");
        }
        else throw new IllegalArgumentException("No existe algun nodo");
    } 
    
    public void borrarArista(V Origen, V Destino) throws IllegalArgumentException{
        //BORRAR ARISTAS INEXISTENTES?¿¿?¿?¿?
        if(Nodos.contains(Origen) && Nodos.contains(Destino)){
            Integer indiceOrigen = Nodos.indexOf(Origen);
            Integer indiceDestino = Nodos.indexOf(Destino);
            Integer sizeOrigen = Graf.get(indiceOrigen).sucesores.size();
            int i = 0;
            boolean trobat = false;
            while(!trobat && i < sizeOrigen){
                if(Graf.get(indiceOrigen).sucesores.get(i).consultarDestino() == indiceDestino){
                    Graf.get(indiceOrigen).sucesores.remove(i);
                    trobat = true;
                }
                ++i;
            }
            i = 0;
            trobat = false;
            Integer sizeDestino = Graf.get(indiceDestino).predecesores.size();
                while(!trobat && i < sizeDestino){
                    if(Graf.get(indiceDestino).predecesores.get(i).consultarOrigen() == indiceOrigen){
                        Graf.get(indiceDestino).predecesores.remove(i);
                        trobat = true;
                    }
                    ++i;
                }
            }
        else throw new IllegalArgumentException("No existe algun nodo");
    }
    
    public void anadirArista(V Origen, V Destino, E peso){
        //MIRAR ARISTAS REPETIDAS
        if(Nodos.contains(Origen) && Nodos.contains(Destino)){
           Integer indiceOrigen = Nodos.indexOf(Origen);
           Integer indiceDestino = Nodos.indexOf(Destino);
           Arista<E> nueva = new Arista<E>(indiceOrigen, indiceDestino,peso);
           Graf.get(indiceOrigen).sucesores.add(nueva);
           Graf.get(indiceDestino).predecesores.add(nueva/*2*/);
        }
        else throw new IllegalArgumentException("No existe algun nodo");
    }
    
    public void borrarNodo(V nodo) throws IllegalArgumentException{
        //EN CASO DE USAR, CUIDADO! FORS POR WHILES CON TROBAT
        if(Nodos.contains(nodo)){
            boolean trobat = false;
            Integer in = Nodos.indexOf(nodo);
            Integer sizeOrigen = Graf.get(in).sucesores.size();
            //For que borra las aristas sucesoras del nodo a borrar
            int i = 0;
            int j = 0;
            while(i < sizeOrigen && !trobat){
                Integer indiceDestino = Graf.get(in).sucesores.get(i).consultarDestino();
                Integer sizeDestino = Graf.get(indiceDestino).predecesores.size();
                while(j < sizeDestino && !trobat){
                    if (Graf.get(indiceDestino).predecesores.get(j).consultarOrigen().equals(in)){
                        Graf.get(indiceDestino).predecesores.remove(j);
                        trobat =true;
                    }
                    ++j;
                }
                ++i;
            }
            //For que borra las aristas predecesoras del nodo a borrar
            Integer sizeDestino = Graf.get(in).predecesores.size();
            i = 0;
            j = 0;
            trobat = false;
            while(i < sizeDestino && !trobat){
                Integer indiceOrigen = Graf.get(in).predecesores.get(i).consultarOrigen();
                Integer sizeOrigen2 = Graf.get(indiceOrigen).sucesores.size();
                while(j < sizeOrigen2 && !trobat ){
                    if (Graf.get(indiceOrigen).sucesores.get(j).consultarDestino() == in){
                        Graf.get(indiceOrigen).sucesores.remove(j);
                        trobat = true;
                    }
                    ++j;
                }
                ++i;
            }
            Graf.remove(Nodos.indexOf(nodo));
            Nodos.remove(nodo);
        }
        else throw new IllegalArgumentException("No existe este nodo");
    }
    
    public void oly(){
        System.out.println("ENTRAMOS EN OLY");
        for(int i = 0; i < Graf.size();++i){
            System.out.println(i);
            System.out.println("Lista Sucesores");
            for(int j = 0; j < Graf.get(i).sucesores.size(); ++j){
                Arista<E> A = Graf.get(i).sucesores.get(j);
                System.out.println(A.consultarOrigen() + " " + A.consultarDestino() + " " + A.consultarPeso());
                System.out.print(" ");
            }
            
            System.out.println("Lista Predecesores");
            for(int k = 0; k < Graf.get(i).predecesores.size();++k){
                Arista<E> A = Graf.get(i).predecesores.get(k);
                System.out.println(A.consultarOrigen() + " " + A.consultarDestino() + " " + A.consultarPeso());
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        System.out.println("---------------------");
        System.out.println("ARRAY DE ARISTAS");
        ArrayList<Arista<E>> jeje = new ArrayList<Arista<E>>();
        jeje = ObtenerAristas();
        for(int i = 0; i < jeje.size(); ++i){
            System.out.println(jeje.get(i).consultarOrigen() + " " + jeje.get(i).consultarDestino() + " " + jeje.get(i).consultarPeso());
        }
    }
    
    public boolean conectado(V nodo, V nodo2){
        if(Nodos.contains(nodo) && Nodos.contains(nodo2)){
            Integer in = Nodos.indexOf(nodo);
            Integer in2 = Nodos.indexOf(nodo2);
            for(int i = 0; i < Graf.get(in).sucesores.size(); ++i){
                if(Graf.get(in).sucesores.get(i).consultarDestino() == in2) 
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<V> consultarPredecesores(V nodo) {
        if(Nodos.contains(nodo)){
            ArrayList<V> nodosPred = new ArrayList<V>();
            Integer in = Nodos.indexOf(nodo);
            Integer midaPred = Graf.get(in).predecesores.size();
            for (int i = 0; i < midaPred; ++i) {
                V node = Nodos.get(Graf.get(in).predecesores.get(i).consultarOrigen());
                nodosPred.add(node);
            }
            return nodosPred;
        }
        else throw new IllegalArgumentException("No existe este nodo");
    }
    
    public ArrayList<V> consultarSucesores(V nodo) {
        if(Nodos.contains(nodo)){
            ArrayList<V> nodosSuc = new ArrayList<V>();
            Integer in = Nodos.indexOf(nodo);
            Integer midaPred = Graf.get(in).sucesores.size();
            for (int i = 0; i < midaPred; ++i) {
                V node = Nodos.get(Graf.get(in).sucesores.get(i).consultarDestino());
                nodosSuc.add(node);
            }
            return nodosSuc;
        }
        else throw new IllegalArgumentException("No existe este nodo");
    }
    public void BorrarAristas(){
        for(int i = 0; i < Graf.size(); ++i){
           Graf.get(i).sucesores = new ArrayList<Arista<E>>();
           Graf.get(i).predecesores = new ArrayList<Arista<E>>();
        }
    }
    
    public void ConstruirGrafo(ArrayList<V> A){
        for(int i = 0; i < A.size();++i) anadirNodo(A.get(i));
    }
    
    public ArrayList<V> ObtenerNodos(){
        return Nodos;
    }
    
   public ArrayList<Arista<E>> ObtenerAristas(){
       ArrayList<Arista<E>> Aristas = new ArrayList<>(); 
       for(int i = 0; i < Nodos.size(); ++i){
            int size = Graf.get(i).sucesores.size();
            for(int j = 0; j < size; ++j){
                Aristas.add(Graf.get(i).sucesores.get(j));
            }
       }
       return Aristas;
    }
    
   public V consultarNodo(Integer indice) {
       return Nodos.get(indice);
   }
   
   public Integer consultarIndice(V nodo) {
       return Nodos.indexOf(nodo);
   }
    
}






