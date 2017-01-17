/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.*;

//VERSION ADAPTANDOLO A COMUNIDAD Y SOLUCION
//DIFERENCIAS:
// - INICIALIZACION GRAFO
// - CLASES DE PUBLIC A PRIVATE
public class CliquePercolation<T> extends Algoritmo<T> {
	//Cliques encontrados
	private ArrayList<ArrayList<T>> CjtCliques;
        private Integer K;
        private ArrayList<ArrayList<ArrayList<T>>> CjtCliquesParcial;
	
        
	public CliquePercolation(Integer num) {
            CjtCliques = new ArrayList<ArrayList<T>>();
            CjtCliquesParcial = new ArrayList<ArrayList<ArrayList<T>>>();
            K = num;
	}
        
        public void modificarNumeroK(Integer k) {
            K = k;
        }
    
     
        private ArrayList<ArrayList<T>> consultarCliques() {
            return CjtCliques;
        }
	private ArrayList<ArrayList<T>> obtenerCliques(Grafo G) {
		// si funciona esto en la constructura
		ArrayList<T> Posibles = new ArrayList<T> ();
		ArrayList<T> Nodos  = new ArrayList<T> (G.ObtenerNodos());
		ArrayList<T> Encontrados = new ArrayList<T> ();
                
		// En CjtCliques tendremos todos los Kcliqes posibles del grafo
		CliquesRecursivos(Nodos, Posibles, Encontrados, G);
                
                return CjtCliques;
	}
        
        private void InsertSort(ArrayList<T> I) {
            boolean insertat = false;
            for (int i = 0; i < CjtCliques.size() && !insertat; ++i) {
                if (CjtCliques.get(i).size() < I.size()) {
                    CjtCliques.add(i, I);
                    insertat = true;
                }
            }
            if (!insertat) CjtCliques.add(I);
        }
        
        //Si queremos la solucion de manera parcial, quitar el while, el fet false, y el boolean inicializarlo a false;
        private void fusionCliques(){ 
            boolean fet = true;
            while (fet){
                fet = false;
                for (int i = 0; i < CjtCliques.size() && !fet; ++i) {
                    for (int j = 0; j < CjtCliques.size() && !fet; ++j){
                        if (i != j){
                            if (comparten(CjtCliques.get(i), CjtCliques.get(j))){
                                fusiona(CjtCliques.get(i), CjtCliques.get(j));
                                CjtCliques.remove(j);
                                fet = true;
                            }
                        }
                    }
                }
            }
        }
        
        private void fusionCliquesParcial(){
            boolean fet = true;
            while (fet){               
                ArrayList<ArrayList<T>> F = new ArrayList<ArrayList<T>>();
                for (ArrayList<T> x : CjtCliques) {
                    ArrayList<T> SOL = new ArrayList<T>(x);
                    F.add(SOL);
                }
                CjtCliquesParcial.add(F);
                //System.out.println(CjtCliquesParcial);
                fet = false;
                for (int i = 0; i < CjtCliques.size() && !fet; ++i) {
                    for (int j = 0; j < CjtCliques.size() && !fet; ++j){
                        if (i != j){
                            if (comparten(CjtCliques.get(i), CjtCliques.get(j))){
                                fusiona(CjtCliques.get(i), CjtCliques.get(j));
                                CjtCliques.remove(j);
                                fet = true;
                            }
                        }
                    }
                }
            }
        }
        	
        //Función que nos devuelve true si dos cliques, A y B, comparten k-1 nodos
        private boolean comparten(ArrayList<T> A, ArrayList<T> B){
            int cont = 0;
            for (int i = 0; i < A.size(); ++i){
                boolean trobat = false;
                for (int j = 0; j < B.size() && !trobat; ++j){
                    if (A.get(i) == B.get(j)){
                        ++cont;
                        trobat = true;
                    }
                }
                if (cont == K - 1) return true;
            }
            return false;
        }
        
        //Función que nos fusiona dos cliques, A y B, y lo guarda en A
        private void fusiona(ArrayList<T> A, ArrayList<T> B){
            for(int i = 0; i < A.size();++i){
                for(int j = 0; j < B.size();++j){
                    if(A.get(i).equals(B.get(j))){
                        B.remove(A.get(i));
                    }
                }
            }
            A.addAll(B);
        }
        
        @Override
        public Solucion<T> ejecutar(Grafo G, String nombre) throws IllegalArgumentException{
            ArrayList<Comunidad> resultado = new ArrayList<Comunidad>();
            obtenerCliques(G);
            fusionCliques();
            for (int i = 0; i < CjtCliques.size(); ++i){
               Comunidad c = new Comunidad(i);
               for (int j = 0; j < CjtCliques.get(i).size(); ++j){
                   c.anadirNodo(CjtCliques.get(i).get(j));
               }
               resultado.add(c);
            }
            Solucion sol = new Solucion(nombre, resultado);
            return sol;
        }
        
        public Solucion<T> ejecutarParcial(Grafo G, String nombre) throws IllegalArgumentException{
            if (G.ObtenerNodos().size() < K) throw new IllegalArgumentException("El numero K debe ser menor o igual que el numero de nodos");
            ArrayList<Comunidad> resultado = new ArrayList<Comunidad>();
            obtenerCliques(G);
            if (CjtCliques.isEmpty()) throw new IllegalArgumentException("No existe ningun clique de tamaño K insertado por el usuario");
            fusionCliquesParcial();
            for (int i = 0; i < CjtCliques.size(); ++i){
               Comunidad c = new Comunidad(i);
               for (int j = 0; j < CjtCliques.get(i).size(); ++j){
                   c.anadirNodo(CjtCliques.get(i).get(j));
               }
               resultado.add(c);
            }
            Solucion sol = new Solucion(nombre, resultado);
            return sol;
        }
        
        public ArrayList<ArrayList<ArrayList<T>>> devolverSolucionParcial(){
            return CjtCliquesParcial;
        }
        
         // Algoritmo de Bron-Kerbosch Para la deteccion de todos los cliques de un grafo
        
	private void CliquesRecursivos(ArrayList<T> CjtNodos, ArrayList<T> CjtPosibles, ArrayList<T> CjtEncontrados, Grafo G) {
		ArrayList<T> Cjt = new ArrayList<T> (CjtNodos);
                if (!existeNodoConexo(CjtNodos, CjtEncontrados, G)) {
                    for (T nodo : Cjt) {
                        // TODOS LOS NODOS PUEDEN PERTENECER A UN
                        CjtPosibles.add(nodo);
                        CjtNodos.remove(nodo);
                        
                        ArrayList<T> SN = new ArrayList<T>();
                        
                        for (T nodoV : CjtNodos) {
                            try {
                                G.consultarPeso(nodo, nodoV);
                                SN.add(nodoV);
                                //System.out.println(SN);
                                //System.out.println("He entrado");
                            }
                            catch (IllegalArgumentException e) {
                                //System.out.println("No he entrado");
                            }
                        }
                        
                        ArrayList<T> SE = new ArrayList<T>();
                        
                        for (T nodoV : CjtEncontrados) {
                            try {
                                G.consultarPeso(nodo, nodoV);
                                SE.add(nodoV);
                                //System.out.println("He entrado");
                                //System.out.println(SE);
                            }
                            catch (IllegalArgumentException e) {
                                //System.out.println("No he entrado");
                            }
                        }
                        
                        if (SN.isEmpty() && SE.isEmpty() && CjtPosibles.size() >= K) InsertSort(new ArrayList<T> (CjtPosibles));
                            //CjtCliques.add(new ArrayList<T> (CjtPosibles));//InsertSort(new ArrayList<T> (CjtPosibles));
                        else CliquesRecursivos(SN, CjtPosibles, SE, G);
                        
                        CjtEncontrados.add(nodo);
                        CjtPosibles.remove(nodo);
                    }
                }
	}
        
        
        private boolean existeNodoConexo(ArrayList<T> N, ArrayList<T> E, Grafo G) {
            Integer conectados;
            for (T nodoE: E) {
                conectados = 0;
                for (T nodoN: N) {
                    try {
                        G.consultarPeso(nodoE, nodoN);
                        ++conectados;
                    }
                    catch (IllegalArgumentException e) {}
                }
                if (conectados == N.size()) return true;
            }
            return false;
        }
            
}
