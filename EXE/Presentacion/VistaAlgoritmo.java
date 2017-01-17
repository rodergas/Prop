/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.Cancion;
import Dominio.Solucion;
import Dominio.SolucionParcialLouvain;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author MonSB
 */
public class VistaAlgoritmo extends JPanel {
    
    CtrlVistaAlgoritmo CVA;
    
    private String[] alg = {"Clique Percolation", "Girvan Newman", "Louvain"};
    
    private JLabel tituloAlgoritmo;
    private JPanel centrarTitulo;
    private JComboBox Algoritmo;
    private JPanel seleccionAlgoritmo;
    
    private CardLayout DepenAlgotimo;
    private JPanel DA;
    
    private JLabel Nsolucion;
    private JPanel centrarNsolucion;
    private JTextField TnombreSolucion;
    private JPanel BSoluciones;
    private JList soluciones;   
    private JPanel PanelSoluciones;
    
    private JPanel I;
    
    //NEWMAN
    private JLabel numcomNewman;
    private JTextField TnumcomNewman;
    private JButton ejecutarNewman;
    private JButton solParcialNewman;
    private JButton soltotalNewman;
    private JPanel solNewman;
    private JLabel nombresolNewman;
    private JTextField TnombresolNewman;
    private JPanel PanelNewman;
    
    //CLIQUE
    private JLabel numcomClique;
    private JTextField TnumcomClique;
    private JButton ejecutarClique;
    private JButton solParcialClique;
    private JButton soltotalClique;
    private JPanel solClique;
    private JLabel nombresolClique;
    private JTextField TnombresolClique;
    private JPanel PanelClique;
    
    //LOUVAIN
    private JButton ejecutarLouvain;
    private JButton solParcialLouvain;
    private JButton soltotalLouvain;
    private JPanel solLouvain;
    private JLabel nombresolLouvain;
    private JTextField TnombresolLouvain;
    private JPanel PanelLouvain;
    
    private SimpleGraphView colorines;
    
    //PANEL PRINCIPAL
    private JPanel panelPrincipal;
    
    
    public VistaAlgoritmo(CtrlVistaAlgoritmo cva) {
        CVA = cva;
        panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setLayout(new GridBagLayout());
        
        tituloAlgoritmo = new JLabel("Algortimos");
        centrarTitulo = new JPanel();
        centrarTitulo.setOpaque(false);
        centrarTitulo.add(tituloAlgoritmo);
        Algoritmo = new JComboBox(alg);
        seleccionAlgoritmo = new JPanel(new BorderLayout());
        seleccionAlgoritmo.setOpaque(false);
        seleccionAlgoritmo.add(centrarTitulo, BorderLayout.NORTH);
        seleccionAlgoritmo.add(Algoritmo);

        DepenAlgotimo = new CardLayout();
        DA = new JPanel(DepenAlgotimo);

        Nsolucion = new JLabel("Soluciones");
        centrarNsolucion = new JPanel();
        centrarNsolucion.add(Nsolucion);
        TnombreSolucion = new JTextField("", 20);
        TnombreSolucion.getDocument().addDocumentListener(ACTUALIZAR);
        BSoluciones = new JPanel(new BorderLayout());
        BSoluciones.add(centrarNsolucion, BorderLayout.NORTH);
        BSoluciones.add(TnombreSolucion, BorderLayout.CENTER);
        soluciones = new JList();   
        PanelSoluciones = new JPanel(new BorderLayout());
        PanelSoluciones.add(BSoluciones, BorderLayout.NORTH);
        PanelSoluciones.add(new JScrollPane(soluciones), BorderLayout.CENTER);
        PanelSoluciones.setBorder(BorderFactory.createLineBorder(Color.black));
        
        I = new JPanel(new BorderLayout());
        I.setOpaque(false);
        I.add(seleccionAlgoritmo, BorderLayout.NORTH);
        I.add(DA, BorderLayout.CENTER);
        I.add(PanelSoluciones, BorderLayout.SOUTH);
        panelPrincipal.add(I, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        colorines = new SimpleGraphView();
        panelPrincipal.add(colorines, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        //NEWMAN
        numcomNewman = new JLabel("Número Comunidades");
        TnumcomNewman = new JTextField("", 20);
        TnumcomNewman.addKeyListener(solo_numeros);
        ejecutarNewman = new JButton("Ejecutar");
        solParcialNewman = new JButton("Solución Parcial");
        soltotalNewman = new JButton("Solucion Total");
        solNewman = new JPanel();
        solNewman.add(solParcialNewman);
        solNewman.add(soltotalNewman);
        nombresolNewman = new JLabel("Nombre Solución");
        TnombresolNewman = new JTextField("", 20);
        PanelNewman = new JPanel(new GridBagLayout());
        PanelNewman.add(nombresolNewman, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelNewman.add(TnombresolNewman, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelNewman.add(TnumcomNewman, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelNewman.add(numcomNewman, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelNewman.add(TnumcomNewman, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelNewman.add(ejecutarNewman, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelNewman.add(solNewman, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelNewman.setBorder(BorderFactory.createLineBorder(Color.black));
        DA.add(PanelNewman, "NEWMAN");
        //CLIQUE
        numcomClique = new JLabel("K");
        TnumcomClique = new JTextField("", 20);
        TnumcomClique.addKeyListener(solo_numeros);
        ejecutarClique = new JButton("Ejecutar");
        solParcialClique = new JButton("Solución Parcial");
        soltotalClique = new JButton("Solucion Total");
        solClique = new JPanel();
        solClique.add(solParcialClique);
        solClique.add(soltotalClique);
        nombresolClique = new JLabel("Nombre Solución");
        TnombresolClique = new JTextField("", 20);
        PanelClique = new JPanel(new GridBagLayout());
        PanelClique.add(nombresolClique, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelClique.add(TnombresolClique, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelClique.add(TnumcomClique, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelClique.add(numcomClique, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelClique.add(TnumcomClique, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelClique.add(ejecutarClique, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelClique.add(solClique, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelClique.setBorder(BorderFactory.createLineBorder(Color.black));
        DA.add(PanelClique, "CLIQUE");
        
        //LOUVAIN
        ejecutarLouvain = new JButton("Ejecutar");
        solParcialLouvain = new JButton("Solución Parcial");
        soltotalLouvain = new JButton("Solucion Total");
        solLouvain = new JPanel();
        solLouvain.add(solParcialLouvain);
        solLouvain.add(soltotalLouvain);
        nombresolLouvain = new JLabel("Nombre Solución");
        TnombresolLouvain = new JTextField("", 20);
        PanelLouvain = new JPanel(new GridBagLayout());
        PanelLouvain.add(nombresolLouvain, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelLouvain.add(TnombresolLouvain, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelLouvain.add(ejecutarLouvain, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelLouvain.add(solLouvain, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelLouvain.setBorder(BorderFactory.createLineBorder(Color.black));
        DA.add(PanelLouvain, "LOUVAIN");
        
        //PANEL PRINCIPAL
        setLayout(new FlowLayout());
        panelPrincipal.setOpaque(false);
        add(panelPrincipal);
        
        //BOTOTNES
        Algoritmo.addActionListener(CAMBIAR);
        ejecutarNewman.addActionListener(EJECUTAR_NEWMAN);
        ejecutarClique.addActionListener(EJECUTAR_CLIQUE);
        ejecutarLouvain.addActionListener(EJECUTAR_LOUVAIN);
        solParcialNewman.addActionListener(NEWMAN_PARCIAL);
        soltotalNewman.addActionListener(NEWMAN_FINAL);
        solParcialClique.addActionListener(CLIQUE_PARCIAL);
        soltotalClique.addActionListener(CLIQUE_FINAL);
        solParcialLouvain.addActionListener(LOUVAIN_PARCIAL);
        soltotalLouvain.addActionListener(LOUVAIN_FINAL);
        
        actualizarLista();
        visible(0);
        DepenAlgotimo.show(DA, "CLIQUE");
        colorines.setVisible(false);
    }
    
    ActionListener CAMBIAR = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(Algoritmo.getSelectedIndex()) {
                case 0:
                    DepenAlgotimo.show(DA, "CLIQUE");
                    break;
                case 1:
                    DepenAlgotimo.show(DA, "NEWMAN");
                    break;
                case 2:
                    DepenAlgotimo.show(DA, "LOUVAIN");
                    break;
            }
            visible(0);
            disponibles(0);
            disponibles(1);
            colorines.setVisible(false);
        }
    };
    
    ActionListener EJECUTAR_NEWMAN = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CVA.ConsultarCtrlAlgoritmo().reiniciarItNewman();
            String parametro = TnumcomNewman.getText();
            String nombreSolucion = TnombresolNewman.getText();
            if (!parametro.equals("") && !nombreSolucion.equals("")) {
                
                ////////////////////////////////
                try {
                    visible(1);
                    disponibles(2);
                    remove(colorines);
                     colorines = new SimpleGraphView();
                     colorines.setVisible(false);
                     add(colorines, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
                    CVA.ConsultarCtrlAlgoritmo().ejecutarAlgoritmo(2, nombreSolucion, Integer.parseInt(parametro));
                    actualizarLista();
                }
                catch(IllegalArgumentException IAE) {
                    CVA.GestionError(IAE.getMessage(), 0);
                    visible(0);
                }

                ////////////////////////////////
                
            }
            else CVA.GestionError("No se han llenado todos los campos", 0);
        }
    };
    
    ActionListener EJECUTAR_CLIQUE = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CVA.ConsultarCtrlAlgoritmo().reiniciarItCliques();
            String parametro = TnumcomClique.getText();
            String nombreSolucion = TnombresolClique.getText();
            if (!parametro.equals("") && !nombreSolucion.equals("")) {
                
                ////////////////////////////////
                try {
                    visible(1);
                    disponibles(2);
                    remove(colorines);
                     colorines = new SimpleGraphView();
                     colorines.setVisible(false);
                     add(colorines, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
                    CVA.ConsultarCtrlAlgoritmo().ejecutarAlgoritmo(1, nombreSolucion, Integer.parseInt(parametro));
                    actualizarLista();
                }
                catch(IllegalArgumentException IAE) {
                    CVA.GestionError(IAE.getMessage(), 0);
                    visible(0);
                }
                
                ////////////////////////////////
                
            }
            else CVA.GestionError("No se han llenado todos los campos", 0);
        }
    };
    
    ActionListener EJECUTAR_LOUVAIN = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombreSolucion = TnombresolLouvain.getText();
            CVA.ConsultarCtrlAlgoritmo().reiniciarLouvain();
            if (!nombreSolucion.equals("")) {
                
                ////////////////////////////////
                try {
                    visible(1);
                    disponibles(2);
                    remove(colorines);
                    colorines = new SimpleGraphView();
                    colorines.setVisible(false);
                    add(colorines, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
                    CVA.ConsultarCtrlAlgoritmo().ejecutarAlgoritmo(3, nombreSolucion, 0);
                    actualizarLista();
                }
                catch(IllegalArgumentException IAE) {
                    CVA.GestionError(IAE.getMessage(), 0);
                    visible(0);
                }
 
                ////////////////////////////////
                
            }
            else CVA.GestionError("No se han llenado todos los campos", 0);
        }
    };
    
    ActionListener NEWMAN_PARCIAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            colorines.setVisible(true);
            String parametro = TnumcomNewman.getText();
            String nombreSolucion = TnombresolNewman.getText();
            Solucion<Cancion> solu = new Solucion();
            solu = CVA.ConsultarCtrlAlgoritmo().CtrlConsultarCtrlSolucion().CtrlConsultarSolucion(nombreSolucion);
            Boolean ejecutarfinalNewman = false;
            ejecutarfinalNewman = CVA.ConsultarCtrlAlgoritmo().ejecutarSolucionParcialNewman();
            if(ejecutarfinalNewman)colorines.printarNewmanFinal(CVA.ConsultarCtrlAlgoritmo().retornarGrafo(), solu);
            else colorines.printarNewmanParcial(CVA.ConsultarCtrlAlgoritmo().retornarGrafo());
        }
    };
    
    ActionListener NEWMAN_FINAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            colorines.setVisible(true);
            String parametro = TnumcomNewman.getText();
            String nombreSolucion = TnombresolNewman.getText();
            Solucion<Cancion> solu = new Solucion();
            solu = CVA.ConsultarCtrlAlgoritmo().CtrlConsultarCtrlSolucion().CtrlConsultarSolucion(nombreSolucion);
            CVA.ConsultarCtrlAlgoritmo().ejecutarSolucionFinalNewman();
            colorines.printarNewmanFinal(CVA.ConsultarCtrlAlgoritmo().retornarGrafo(), solu);
        }
    };
    
    ActionListener CLIQUE_PARCIAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            colorines.setVisible(true);
            String parametro = TnumcomClique.getText();
            String nombreSolucion = TnombresolClique.getText();
            Solucion<Cancion> solu = new Solucion();
            solu = CVA.ConsultarCtrlAlgoritmo().CtrlConsultarCtrlSolucion().CtrlConsultarSolucion(nombreSolucion);
            ArrayList<ArrayList<Cancion>> sol = CVA.ConsultarCtrlAlgoritmo().ejecutarSolucionParcialClique();
            colorines.printarCliqueParcial(CVA.ConsultarCtrlAlgoritmo().retornarGrafo(), sol);
        }
    };
    
    ActionListener CLIQUE_FINAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            colorines.setVisible(true);
            String parametro = TnumcomClique.getText();
            String nombreSolucion = TnombresolClique.getText();
            Solucion<Cancion> solu = new Solucion();
            solu = CVA.ConsultarCtrlAlgoritmo().CtrlConsultarCtrlSolucion().CtrlConsultarSolucion(nombreSolucion);
            CVA.ConsultarCtrlAlgoritmo().ejecutarSolucionFinalClique();
            colorines.printarCliqueFinal(CVA.ConsultarCtrlAlgoritmo().retornarGrafo(), solu);
        }
    };
    
    ActionListener LOUVAIN_PARCIAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            colorines.setVisible(true);
            String nombreSolucion = TnombresolLouvain.getText();
            Boolean b;
            b = CVA.ConsultarCtrlAlgoritmo().ejecutarSolucionParcialLouvain();
            if(b.equals(false)) colorines.printarLouvainIteracion0(CVA.ConsultarCtrlAlgoritmo().retornarGrafo(),CVA.ConsultarCtrlAlgoritmo().LouvainComIni());
            else colorines.printarLouvainParcial(CVA.ConsultarCtrlAlgoritmo().retornarGrafo());
        }
    };
    
    ActionListener LOUVAIN_FINAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            colorines.setVisible(true);
            String nombreSolucion = TnombresolLouvain.getText();
            Solucion<Cancion> solu = new Solucion();
            solu = CVA.ConsultarCtrlAlgoritmo().CtrlConsultarCtrlSolucion().CtrlConsultarSolucion(nombreSolucion);
            CVA.ConsultarCtrlAlgoritmo().ejecutarSolucionFinalLouvain();
            colorines.printarLouvainFinal(CVA.ConsultarCtrlAlgoritmo().retornarGrafo(),solu);
        }
    };
    
    public void actualizarLista() {
        String s = TnombreSolucion.getText();
        if(!s.equals("")) {
            LinkedList<String> L = CVA.ConsultarCtrlAlgoritmo().CtrlConsultarCtrlSolucion().CtrlNombres(s);
            String[] nombres = L.toArray(new String[L.size()]);
            soluciones.setListData(nombres);
        }
        else {
            LinkedList<String> L = CVA.ConsultarCtrlAlgoritmo().CtrlConsultarCtrlSolucion().CtrlNombres();
            String[] nombres = L.toArray(new String[L.size()]);
            soluciones.setListData(nombres);
        }
    }
    
    private void visible(int x) {
        if (x == 0) {
            solParcialNewman.setVisible(false);
            soltotalNewman.setVisible(false);
            solParcialClique.setVisible(false);
            soltotalClique.setVisible(false);
            solParcialLouvain.setVisible(false);
            soltotalLouvain.setVisible(false);
        }
        else {
            solParcialNewman.setVisible(true);
            soltotalNewman.setVisible(true);
            solParcialClique.setVisible(true);
            soltotalClique.setVisible(true);
            solParcialLouvain.setVisible(true);
            soltotalLouvain.setVisible(true);
        }
    }
    
    private void disponibles(int x) {
        switch(x) {
            case 0:
                TnumcomNewman.setText("");
                TnombresolNewman.setText("");
                TnumcomClique.setText("");
                TnombresolClique.setText("");
                TnombresolLouvain.setText("");
                break;
            case 1: 
                TnumcomNewman.setEditable(true);
                TnombresolNewman.setEditable(true);
                TnumcomClique.setEditable(true);
                TnombresolClique.setEditable(true);
                TnombresolLouvain.setEditable(true);
                break;
            case 2:
                TnumcomNewman.setEditable(false);
                TnombresolNewman.setEditable(false);
                TnumcomClique.setEditable(false);
                TnombresolClique.setEditable(false);
                TnombresolLouvain.setEditable(false);
                break;
        }
    }
    
    DocumentListener ACTUALIZAR = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            actualizarLista();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actualizarLista();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actualizarLista();
        }
    };
    
    KeyAdapter solo_numeros = new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                e.consume();
            }
        }
    };
}
