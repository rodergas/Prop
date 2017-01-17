/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.Cancion;
import Dominio.CliquePercolation;
import Dominio.Comunidad;
import Dominio.CtrlAlgoritmo;
import Dominio.CtrlCancion;
import Dominio.CtrlListado;
import Dominio.CtrlRelacion;
import Dominio.CtrlSolucion;
import Dominio.CtrlUsuario;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Dimension;
import javax.swing.JFrame;
import Dominio.Grafo;
import Dominio.Newman;
import Dominio.Solucion;
import Dominio.SolucionParcialLouvain;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;
//import javax.xml.transform.Transformer;
import org.apache.commons.collections15.Transformer;


/**
 *
 * @author rober_000
 */
public class SimpleGraphView extends JPanel {
    private ArrayList<Comunidad> ComL;
    private Solucion soluL; 
    
    private Graph<Cancion, String> GNew;
    private Graph<Cancion, String> GLou;
    private Graph<Cancion, String> GCliq;
    
    private Boolean copiaNew = false;
    private Boolean copiaLou = false;
    private Boolean copiaCliq = false;
    
    
    private Layout<Cancion,String> LNew;
    private Layout<Cancion,String> LLou;
    private Layout<Cancion,String> LCliq;
    
    private BasicVisualizationServer<Cancion,String> vvNew;
    private BasicVisualizationServer<Cancion,String> vvLow;
    private BasicVisualizationServer<Cancion,String> vvCliq;
        private Graph<Cancion, String> gg;
        private Grafo<Cancion,Float> Gr;
        private Grafo<Cancion,Float> Copia;
        private BasicVisualizationServer<Cancion,String> vv;
        private Solucion solu;
        private CtrlAlgoritmo a;
        private ArrayList<ArrayList<Cancion>> CliqueParcial;
        private Solucion LouvainParcial;
        private Boolean init = false;
        private Boolean ejecutarfinalNewman = false;
        private Boolean deboHacerfinalNewman = false;
        private Boolean deboHacerfinalClique = false;
        private Boolean deboHacerfinalLouvain = false;

    public SimpleGraphView(){
        gg = new SparseMultigraph<Cancion, String>();
        GNew = new SparseMultigraph<Cancion, String>();
        GLou = new SparseMultigraph<Cancion, String>();
        GCliq = new SparseMultigraph<Cancion, String>();
        ComL = new ArrayList<>();
        
        Layout<Cancion,String> layout = new SpringLayout<Cancion,String>(gg);
        //layout.setSize(new Dimension(350,350));
        vv = new BasicVisualizationServer<Cancion,String>(layout);
        //vv.setPreferredSize(new Dimension(350,350));
        
    }
    
    
    
    public void crearGrafo(Grafo<Cancion,Float> G){
        
        //gg = new SparseMultigraph<Cancion, String>();
        ArrayList<Cancion> Nodos = G.ObtenerNodos();
        for (int i = 0; i < Nodos.size(); ++i){
            gg.addVertex(Nodos.get(i));
        }
        
        for (int h = 0; h < Nodos.size(); ++h){
            //System.out.print(Nodos.get(h).consultarTitulo() + " ");
            for (int q = 0; q < G.consultarSucesores(Nodos.get(h)).size(); ++q){ 
                //System.out.print(G.consultarSucesores(Nodos.get(h)).get(q).consultarTitulo() + " ");
                Cancion Origen = Nodos.get(h);
                Cancion Destino = G.consultarSucesores(Nodos.get(h)).get(q);
                if(!gg.containsEdge(Origen.consultarTitulo() + Destino.consultarTitulo()) && !gg.containsEdge(Destino.consultarTitulo() + Origen.consultarTitulo()))gg.addEdge(Origen.consultarTitulo()+Destino.consultarTitulo(), Origen, Destino,EdgeType.UNDIRECTED);
            }
            //System.out.println("");
        }
        //System.out.println("---------");
    }
    
    public void init(Grafo<Cancion,Float> G){
        ArrayList<Cancion> Nodos = G.ObtenerNodos();
        for (int i = 0; i < Nodos.size(); ++i){
            gg.removeVertex(Nodos.get(i));
        }
        
        for (int h = 0; h < Nodos.size(); ++h){
            for (int q = 0; q < G.consultarSucesores(Nodos.get(h)).size(); ++q){  
                Cancion Origen = Nodos.get(h);
                Cancion Destino = G.consultarSucesores(Nodos.get(h)).get(q);
                if(gg.containsEdge(Origen.consultarTitulo() + Destino.consultarTitulo())) gg.removeEdge(Origen.consultarTitulo()+Destino.consultarTitulo());
                if(gg.containsEdge(Destino.consultarTitulo() + Origen.consultarTitulo())) gg.removeEdge(Destino.consultarTitulo()+Origen.consultarTitulo());
            }
        } 
    }
    
    public void printarNewmanParcial(Grafo<Cancion,Float> G){
        init(G);
        crearGrafo(G);
            Transformer<Cancion,Paint> vertexPaint = new Transformer<Cancion,Paint>() {
                public Paint transform(Cancion can) {
                    return Color.RED;
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
            add(vv);
            revalidate();
            
    }
    
    public void printarNewmanFinal(Grafo<Cancion,Float> G, Solucion s){
        init(G);
        crearGrafo(G);
        solu = s;
            Transformer<Cancion,Paint> vertexPaint = new Transformer<Cancion,Paint>() {
                Integer n = solu.obtenerComunidades().size();
                Color[] cols = new Color[n];
                public Paint transform(Cancion can) {
                for(Integer i = 0; i < n; i++) cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
                    Integer color = solu.consultarComunidad(can);
                    Color c = cols[color];
                    return c;
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
            add(vv);
            revalidate();
            
    }
    
    public void printarLouvainParcial(Grafo<Cancion,Float> G){
        init(G);
        crearGrafo(G);
            Transformer<Cancion,Paint> vertexPaint = new Transformer<Cancion,Paint>() {
                Integer n = soluL.obtenerComunidades().size();
                Color[] cols = new Color[n];
                public Paint transform(Cancion can) {
                for(Integer i = 0; i < n; i++) cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
                    Integer color = soluL.consultarComunidad(can);
                    Color c = cols[color];
                    return c;
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
            add(vv);
            revalidate();
    }
        public void printarLouvainIteracion0(Grafo<Cancion,Float> G, ArrayList<Comunidad> Com){
        ComL = Com;
        soluL = new Solucion("",ComL);
        crearGrafo(G);
        Gr = G;
            Transformer<Cancion,Paint> vertexPaint = new Transformer<Cancion,Paint>() {
                Integer n = Gr.ObtenerNodos().size();
                Color[] cols = new Color[n];
                public Paint transform(Cancion can) {
                for(Integer i = 0; i < n; i++) cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
                    Integer color = Gr.consultarIndice(can);
                    Color c = cols[color];
                    return c;
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
            add(vv);
            revalidate();
    }
    
        public void printarLouvainFinal(Grafo<Cancion,Float> G, Solucion s){
        solu = s;
        init(G);
        crearGrafo(G);
            Transformer<Cancion,Paint> vertexPaint = new Transformer<Cancion,Paint>() {
                Integer n = solu.obtenerComunidades().size();
                Color[] cols = new Color[n];
                public Paint transform(Cancion can) {
                for(Integer i = 0; i < n; i++) cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
                    Integer color = solu.consultarComunidad(can);
                    Color c = cols[color];
                    return c;
                }
            };
            ;
            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
            add(vv);
            revalidate();
    }
    
    
    public void printarCliqueParcial(Grafo<Cancion,Float> G, ArrayList<ArrayList<Cancion>> sol){
        init(G);
        crearGrafo(G);
        CliqueParcial = sol;
            Transformer<Cancion,Paint> vertexPaint = new Transformer<Cancion,Paint>() {
                Integer n = CliqueParcial.size();
                Color[] cols = new Color[n];
                public Paint transform(Cancion can) {
                for(Integer i = 0; i < n; i++) cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
                    Integer color = consultarComunidadCliqueParcial(can);
                    Color c = cols[color];
                    return c;
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
            add(vv);
            revalidate();
    }
    
 public void printarCliqueFinal(Grafo<Cancion,Float> G,Solucion s){
        solu = s;
        init(G);
        crearGrafo(G);
            Transformer<Cancion,Paint> vertexPaint = new Transformer<Cancion,Paint>() {
                Integer n = solu.obtenerComunidades().size();
                Color[] cols = new Color[n];
                public Paint transform(Cancion can) {
                for(Integer i = 0; i < n; i++) cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
                    Integer color = solu.consultarComunidad(can);
                    Color c = cols[color];
                    return c;
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
            add(vv);
            revalidate();
            deboHacerfinalClique = false;
    }
    
    public Integer consultarComunidadCliqueParcial(Cancion c){
        boolean trobat = false;
        int i = 0;
        int aux = 0;
        while(!trobat && i < CliqueParcial.size()){
            int j = 0;
            while(!trobat && j < CliqueParcial.get(i).size()){
                if(CliqueParcial.get(i).get(j).equals(c)) {aux = i; trobat = true;}
                ++j;
            }
            ++i;
        }
        return aux;
    } 
}
