/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.ArrayList;
/**
 *
 *
 */
public class Louvain<T> {
        class NodoLouvain {
        T nodo;
        Float mod;
        boolean borrado;
        
        public NodoLouvain(T node) {
            nodo = node;
            mod = 0.0f;
            borrado = false;
        }
    }
    private ArrayList<Comunidad> comunidadesIniciales;
    private ArrayList<Comunidad> comunidades;
    private Grafo GL;
    private Grafo GE;
    private boolean ganancia_iter;
    private ArrayList<SolucionParcialLouvain> parcial;
    
    private Integer iteracion = 0;
    
    public void Louvain(){}
    
    public ArrayList<Comunidad> devolverComunidadesIniciales() {
        return comunidadesIniciales;
    }
    
    public ArrayList<SolucionParcialLouvain> devolverSolucionParcial() {
        return parcial;
    }
    
    private void ejecutarLouvain() {       
        ArrayList<T> Nodos = GL.ObtenerNodos();
        ArrayList<NodoLouvain> NodosL = new ArrayList<NodoLouvain>();
        for(T nodo : Nodos){
            NodoLouvain nl = new NodoLouvain(nodo);
            NodosL.add(nl);
        }
        boolean primera_vez = true;
        Integer iter_sin_ganancia = 0;
        while (comunidades.size() > 1 && iter_sin_ganancia < 2) {
            boolean ganancia = true;
            if (primera_vez) {
                ganancia_iter = false;
                while(ganancia) {
                    ganancia = false;
                    for(NodoLouvain nodoL : NodosL){
                        boolean ganancia_actual = false;
                        Comunidad micom = obtenerComunidad(nodoL.nodo);
                        Comunidad Max = micom;
                        Float maxGanancia = nodoL.mod;
                        ArrayList<T> nodosAdyacentes = GL.consultarSucesores(nodoL.nodo);
                        for (T node : nodosAdyacentes) {
                            Comunidad comady = obtenerComunidad(node);
                            if (micom != comady) {
                                Float guany = calcularGanancia(GL,nodoL.nodo,micom,comady);
                                if (guany > maxGanancia){
                                    maxGanancia = guany;
                                    Max = comady;
                                    ganancia_actual = true;   
                                }
                            }
                        }
                        if (ganancia_actual) {
                            nodoL.mod = maxGanancia;
                            ganancia = true;
                            ganancia_iter = true;
                            micom.borrarNodo(nodoL.nodo);
                            Max.anadirNodo(nodoL.nodo);
                            Cancion c = (Cancion) nodoL.nodo;
                            Integer idCOri = micom.consultarIdentificador();
                            Integer idCDes = Max.consultarIdentificador();
                            SolucionParcialLouvain aux = new SolucionParcialLouvain(c, idCOri, idCDes);
                            parcial.add(aux);
                            if (micom.consultarTamano().equals(0)) comunidades.remove(micom);
                        }
                    }
                }
            }
            else {
                Nodos = GL.ObtenerNodos();
                NodosL = new ArrayList<NodoLouvain>();
                for(T nodo : Nodos){
                    NodoLouvain nl = new NodoLouvain(nodo);
                    NodosL.add(nl);
                }  
                ganancia_iter = false;
                while(ganancia) {
                    ganancia = false;
                    for (NodoLouvain nodoL : NodosL){
                        boolean ganancia_actual = false;
                        Comunidad micom = (Comunidad) nodoL.nodo;
                        Comunidad Max = micom;
                        Float maxGanancia = nodoL.mod;
                        ArrayList<T> nodosAdyacentes = GL.consultarSucesores(nodoL.nodo);
                        for (T node : nodosAdyacentes) {
                            Comunidad comady = (Comunidad) node;
                            boolean deleted = false;
                            for (NodoLouvain n : NodosL) {
                               if (n.nodo == comady) if (n.borrado) deleted = true;
                            }
                            if (micom != comady && !deleted) {
                                Float guany = calcularGanancia(GL,nodoL.nodo,micom,comady);
                                if (guany > maxGanancia) {
                                    maxGanancia = guany;
                                    Max = comady;
                                    ganancia_actual = true;
                                }
                            }
                        }
                        if (ganancia_actual) {
                            nodoL.mod = maxGanancia;
                            ganancia = true;
                            ganancia_iter = true;
                            for (int i = 0; i < micom.consultarTamano(); ++i) {
                                T t = (T) micom.consultarNodo(i);
                                Max.anadirNodo(t);
                                // parcial
                                Cancion c = (Cancion) t;
                                Integer idCOri = micom.consultarIdentificador();
                                Integer idCDes = Max.consultarIdentificador();
                                SolucionParcialLouvain aux = new SolucionParcialLouvain(c, idCOri, idCDes);
                                parcial.add(aux);
                                if (micom.consultarTamano().equals(0)) comunidades.remove(micom);
                                // parcial
                            }
                            comunidades.remove(micom);
                            for (NodoLouvain x : NodosL) {
                                if (x.nodo == micom) x.borrado = true;
                            }
                        }
                    }
                }
            }
            ++iteracion;
            if (!ganancia_iter) ++iter_sin_ganancia;
            else iter_sin_ganancia = 0;
            if (primera_vez) primera_vez = false;
            actualizarGrafo();
        }
    }
    
    private Float calcularGanancia (Grafo G, T nodo, Comunidad micom, Comunidad comady) {
        // m = suma de los pesos de todas las aristas del grafo
        Float m = 0.0f;
        // self_loops = suma de los pesos de aquellas aristas que conecten un nodo consigo mismo
        Float self_loops = 0.0f;
        ArrayList<T> Nodos = G.ObtenerNodos();
        for (T node : Nodos) {
            ArrayList<T> nodosAdyacentes = G.consultarSucesores(node);
            for (T nodeady : nodosAdyacentes) {
                if (node == nodeady) self_loops += (Float) G.consultarPeso(node, nodeady);
                else m += (Float) G.consultarPeso(node, nodeady);
            }
        }
        m = (m/2) + self_loops; // entre 2 para no contabilizar aristas repetidas
        
        // ki = suma de los pesos de las aristas adyacentes al nodo
        Float ki = 0.0f;
        ArrayList<T> nodosAdyacentes = G.consultarSucesores(nodo);
        for (T nodeady : nodosAdyacentes) ki += (Float) G.consultarPeso(nodo, nodeady);
        
        // Ein = suma de los pesos de las aristas internas de la comunidad a la que voy
            Float Ein = 0.0f;
            
        // Etot = suma de los pesos de las aristas adyacentes a nodos de la comunidad a la que voy
            Float Etot = 0.0f;
            
        // kiin = suma de los pesos de las aristas entre el nodo y los nodos de la comunidad a la que voy
            Float kiin = 0.0f;
            
        if (iteracion == 0) {
            ArrayList<T> Nodos_comady = comady.consultarComunidad();
            for (T node : Nodos_comady){
                for (T node2 : Nodos_comady){
                    if (G.conectado(node, node2)) {
                        Ein += (Float) G.consultarPeso(node, node2);
                    }
                }
            }
            Ein = (Ein/2); // entre 2 para no contabilizar aristas repetidas
            
            // dif_comunidad = suma de los pesos de las aristas que vienen de otra comunidad
            Float dif_comunidad = 0.0f;
            for (T node : Nodos_comady){
                ArrayList<T> nodosadyacentes = G.consultarSucesores(node);
                for (T nodeady : nodosadyacentes){
                    if (!comady.existeNodo(nodeady)) dif_comunidad += (Float) G.consultarPeso(node, nodeady);
                    else Etot += (Float) G.consultarPeso(node, nodeady);
                }
            }
            Etot = (Etot/2) + dif_comunidad; // entre 2 para no contabilizar aristas repetidas

            for (T node : Nodos_comady) if (G.conectado(nodo, node)) kiin += (Float) G.consultarPeso(nodo, node);
            
        }
        
        else {
          
            nodosAdyacentes = G.consultarSucesores(comady);
            for (T nodeady : nodosAdyacentes) {
                if (nodeady == comady) Ein = (Float) G.consultarPeso(comady, nodeady);
                Etot += (Float) G.consultarPeso(comady, nodeady);
            }
            
            nodosAdyacentes = G.consultarSucesores(nodo);
            for (T nodeady : nodosAdyacentes) if (nodeady == comady) kiin = (Float) G.consultarPeso(nodo, nodeady);

        }
        
        Float guany = (Ein + kiin) / (2*m);
        guany -= ((Etot + ki) / (2*m)) * ((Etot + ki) / (2*m));
        guany -= Ein / (2*m);
        guany += (Etot / (2*m)) * (Etot / (2*m));
        guany += (ki / (2*m)) * (ki / (2*m));
       
        return guany;
    }
    
    private Comunidad obtenerComunidad(T Nodo) throws IllegalArgumentException{
        Comunidad com = new Comunidad();
        boolean trobada = false;
        int i = 0;
        while (!trobada && i < comunidades.size()){ 
            int j = 0;
            while (!trobada && j < comunidades.get(i).consultarTamano()) {
                if(comunidades.get(i).consultarNodo(j).equals(Nodo)){
                    trobada = true;
                    com = comunidades.get(i);
                 }
            ++j;
            }
        ++i;
        }  
        if (!trobada) throw new IllegalArgumentException("No te comunitat");
        else return com;
    }
    
    private void actualizarGrafo(){
        Grafo aux = new Grafo();
        for (int i = 0; i < comunidades.size(); ++i) {
            aux.anadirNodo(comunidades.get(i));
        }
        ArrayList<Comunidad> comunities = aux.ObtenerNodos();
        for (Comunidad micom : comunities){
            // Ein2 = suma de los pesos de las aristas internas de mi comunidad
            Float Ein2 = 0.0f;
            ArrayList<T> Nodos_micom = micom.consultarComunidad();
            for (T node : Nodos_micom){
                for (T node2 : Nodos_micom){
                    if (GE.conectado(node, node2)) {
                        Ein2 += (Float) GE.consultarPeso(node, node2);
                    }
                }
            }
            aux.anadirArista(micom, micom, Ein2);
            for (Comunidad comady : comunities){     
                if (micom != comady) {       
                    Float peso = 0.0f;
                    ArrayList<T> Nodosmicom = micom.consultarComunidad();
                    for (T node : Nodosmicom) {
                        ArrayList<T> Nodoscomady = comady.consultarComunidad();
                        for (T node2 : Nodoscomady){ 
                            if (GE.conectado(node, node2)) peso += (Float) GE.consultarPeso(node, node2);
                        }
                    }
                    if (peso > 0) aux.anadirArista(micom, comady, peso);
                }
            }
        }
        GL = aux;
    }
    
    public Solucion ejecutar(Grafo G, String nombre) throws IllegalArgumentException {
        GL = new Grafo(G);
        GE = G;
        parcial = new ArrayList<SolucionParcialLouvain>();
        comunidades = new ArrayList<Comunidad>();
        comunidadesIniciales = new ArrayList<Comunidad>(); // parcial
        ArrayList<T> Nodos = GL.ObtenerNodos();
        for (int i = 0; i < Nodos.size(); ++i) {
            comunidades.add(new Comunidad(i));   
            comunidades.get(i).anadirNodo(Nodos.get(i));
            // parcial
            comunidadesIniciales.add(new Comunidad(i)); 
            comunidadesIniciales.get(i).anadirNodo(Nodos.get(i));
            // parcial
        }
        ejecutarLouvain();
        Solucion sol = new Solucion(nombre,comunidades);
        return sol;
    }
}