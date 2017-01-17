/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author MonSB
 */
public class VistaRelacion extends JPanel {
    CtrlVistaRelacion CVR;
    JLabel titulo;
    
    private String[] atributos = {"Autor", "Duración", "Número Reproducciones", "Género", "Usuario", "Nacionalidad", "Edad"};
    private String[] display_atributos = {"AUTOR", "DURACION", "NUMERO_REPRODUCCIONES", "GENERO", "USUARIO", "NACIONALIDAD", "EDAD"};
    //Panel Izquierdo///

    private JLabel tituloRelacionesCreadas;
    private JPanel centrarTituloBuscador;
    private JList relacionesCreadas;  
    private JPanel listaRelaciones;
    private JTextField buscarRelacion;
    private JPanel I;
    
    //PANEL CENTRAL
    private JLabel TrelacionPrincipal;
    private JPanel centrarTitulo;
    private JTextField relacionPrincipal;
    private JButton botonRelacionPrincipal;
    private JButton crearConjunto;
    private JPanel relPrincipal;
    private JPanel Conjunto;
    ////
    
    //Panel Central
    //SIMPLE
    private JPanel simple;
    private JLabel Tsimple;
    private JComboBox atributos_relacion;
    private JTextField valorSimpleString;
    private JTextField valorSimpleInt;
    private JTextField valorSimpleMin;
    private JTextField valorSimpleSeg;
    private JLabel minutos;
    private JLabel segundos;
    private JPanel SIMPLE;
    private JLabel TvalorSimple;
    private JPanel selecionesSimples;
    private JTextField NombreSIMPLE;
    private JLabel TnombreSIMPLE;
    private JButton crearSIMPLE;
    private JPanel formularioSIMPLE;
    //AND
    private JPanel and;
    private JLabel Tand;
    private JPanel CentrarTand;
    private JTextField andIzquierdo;
    private JTextField andDerecho;
    private JLabel separadorAnd;
    private JButton seleccionandIzquierdo;
    private JButton selecionarandDerecho;
    private JPanel selecionesAND;
    private JTextField NombreAND;
    private JLabel TnombreAND;
    private JButton crearAND;
    private JPanel formularioAND;
    //OR
    private JPanel or;
    private JLabel Tor;
    private JPanel CentrarTor;
    private JTextField orIzquierdo;
    private JTextField orDerecho;
    private JLabel separadorOr;
    private JButton seleccionorIzquierdo;
    private JButton selecionarorDerecho;
    private JPanel selecionesOR;
    private JTextField NombreOR;
    private JLabel TnombreOR;
    private JButton crearOR;
    private JPanel formularioOR;
    //NOT
    private JPanel not;
    private JLabel Tnot;
    private JPanel CentrarTnot;
    private JTextField notUnico;
    private JLabel separadorNot;
    private JButton seleccionnot;
    private JPanel selecionesNOT;
    private JTextField NombreNOT;
    private JLabel TnombreNOT;
    private JButton crearNOT;
    private JPanel formularioNOT;
    
    private JPanel panelSimpleNot;
    private JPanel panelAndOR;
    
    private JPanel C;
    private JPanel RELACIONES;
    
    
    public VistaRelacion(CtrlVistaRelacion cvr) {
        
        CVR = cvr;
        
        setLayout(new BorderLayout());
        titulo = new JLabel("Relaciones");
        add(titulo, BorderLayout.NORTH);
        
        //Panel Izquierdo
        relacionesCreadas = new JList();
        tituloRelacionesCreadas = new JLabel("Relaciones Creadas");
        centrarTituloBuscador = new JPanel();
        centrarTituloBuscador.setOpaque(false);
        centrarTituloBuscador.add(tituloRelacionesCreadas);
        buscarRelacion = new JTextField("", 18);
        buscarRelacion.getDocument().addDocumentListener(CAMBIO);
        I = new JPanel(new BorderLayout());
        I.setOpaque(false);
        listaRelaciones = new JPanel(new BorderLayout());
        listaRelaciones.setOpaque(false);
        listaRelaciones.add(centrarTituloBuscador, BorderLayout.NORTH);
        listaRelaciones.add(new JScrollPane(relacionesCreadas), BorderLayout.CENTER);
        listaRelaciones.add(buscarRelacion, BorderLayout.SOUTH);
        
         //RELACION PRINCIPAL
        TrelacionPrincipal = new JLabel("Relación Principal");
        centrarTitulo = new JPanel();
        centrarTitulo.add(TrelacionPrincipal);
        relacionPrincipal = new JTextField("", 15);
        relacionPrincipal.setEditable(false);
        botonRelacionPrincipal = new JButton("Seleccionar");
        crearConjunto = new JButton("Crear Conjunto");
        
        relPrincipal = new JPanel(new BorderLayout());
        relPrincipal.setOpaque(false);
        relPrincipal.add(relacionPrincipal, BorderLayout.NORTH);
        relPrincipal.add(botonRelacionPrincipal, BorderLayout.CENTER);
        relPrincipal.add(crearConjunto, BorderLayout.SOUTH);
        
        Conjunto = new JPanel(new BorderLayout());
        Conjunto.setOpaque(false);
        
        Conjunto.add(centrarTitulo, BorderLayout.NORTH);
        Conjunto.add(relPrincipal, BorderLayout.CENTER);
        Conjunto.setBorder(BorderFactory.createLineBorder(Color.black));
 
        //add(RELACION_PRINCIPAL, BorderLayout.CENTER);
        I.add(Conjunto, BorderLayout.NORTH);
        I.add(listaRelaciones, BorderLayout.CENTER);
        I.setBorder(BorderFactory.createLineBorder(Color.black));
        add(I, BorderLayout.WEST);
        
        //SIMPLE
        //combo.getSelectedItem().toString()
        Tsimple = new JLabel("Crear Relación SIMPLE");
        atributos_relacion = new JComboBox(atributos);
        atributos_relacion.addActionListener(CAMBIAR);
        valorSimpleString = new JTextField("", 10);
        valorSimpleInt = new JTextField("", 10);
        valorSimpleInt.addKeyListener(solo_numeros);
        valorSimpleMin = new JTextField("", 3);
        valorSimpleMin.addKeyListener(solo_numeros);
        valorSimpleSeg = new JTextField("", 3);
        valorSimpleSeg.addKeyListener(solo_numeros);
        minutos = new JLabel("Min");
        segundos = new JLabel("Seg");
        SIMPLE = new JPanel();
        SIMPLE.add(valorSimpleString);
        SIMPLE.add(valorSimpleInt);
        SIMPLE.add(minutos);
        SIMPLE.add(valorSimpleMin);
        SIMPLE.add(segundos);
        SIMPLE.add(valorSimpleSeg);
        valorSimpleString.setVisible(true);
        valorSimpleInt.setVisible(false);
        valorSimpleMin.setVisible(false);
        valorSimpleSeg.setVisible(false);
        minutos.setVisible(false);
        segundos.setVisible(false);
        
        TvalorSimple = new JLabel("Valor ");
        selecionesSimples = new JPanel();
        selecionesSimples.add(TvalorSimple);
        selecionesSimples.add(SIMPLE);
        NombreSIMPLE = new JTextField("", 10);
        TnombreSIMPLE = new JLabel("Nombre ");
        crearSIMPLE = new JButton("Crear");
        formularioSIMPLE = new JPanel();
        formularioSIMPLE.add(TnombreSIMPLE);
        formularioSIMPLE.add(NombreSIMPLE);
        formularioSIMPLE.add(crearSIMPLE);
        simple = new JPanel(new GridBagLayout());
        simple.add(Tsimple, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        simple.add(atributos_relacion, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        simple.add(selecionesSimples, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        simple.add(formularioSIMPLE, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        simple.setBorder(BorderFactory.createLineBorder(Color.black));
        //AND
        Tand = new JLabel("Crear Relación AND");
        CentrarTand = new JPanel();
        CentrarTand.add(Tand);
        andIzquierdo = new JTextField("", 10);
        andIzquierdo.setEditable(false);
        andDerecho = new JTextField("", 10);
        andDerecho.setEditable(false);
        separadorAnd = new JLabel("AND");
        seleccionandIzquierdo = new JButton("Seleccionar");
        selecionarandDerecho = new JButton("Seleccionar");
        selecionesAND = new JPanel(new GridBagLayout());
        selecionesAND.add(andIzquierdo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesAND.add(separadorAnd, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesAND.add(andDerecho, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesAND.add(seleccionandIzquierdo, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesAND.add(selecionarandDerecho, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        NombreAND = new JTextField("", 10);
        TnombreAND = new JLabel("Nombre ");
        crearAND = new JButton("Crear");
        formularioAND = new JPanel();
        formularioAND.add(TnombreAND);
        formularioAND.add(NombreAND);
        formularioAND.add(crearAND);
        and = new JPanel(new BorderLayout());
        and.add(CentrarTand, BorderLayout.NORTH);
        and.add(selecionesAND, BorderLayout.CENTER);
        and.add(formularioAND, BorderLayout.SOUTH);
        and.setBorder(BorderFactory.createLineBorder(Color.black));
        //OR
        Tor = new JLabel("Crear Relación OR");
        CentrarTor = new JPanel();
        CentrarTor.add(Tor);
        orIzquierdo = new JTextField("", 10);
        orIzquierdo.setEditable(false);
        orDerecho = new JTextField("", 10);
        orDerecho.setEditable(false);
        separadorOr = new JLabel("OR");
        seleccionorIzquierdo = new JButton("Seleccionar");
        selecionarorDerecho = new JButton("Seleccionar");
        selecionesOR = new JPanel(new GridBagLayout());
        selecionesOR.add(orIzquierdo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesOR.add(separadorOr, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesOR.add(orDerecho, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesOR.add(seleccionorIzquierdo, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesOR.add(selecionarorDerecho, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        NombreOR = new JTextField("", 10);
        TnombreOR = new JLabel("Nombre ");
        crearOR = new JButton("Crear");
        formularioOR = new JPanel();
        formularioOR.add(TnombreOR);
        formularioOR.add(NombreOR);
        formularioOR.add(crearOR);
        or = new JPanel(new BorderLayout());
        or.add(CentrarTor, BorderLayout.NORTH);
        or.add(selecionesOR, BorderLayout.CENTER);
        or.add(formularioOR, BorderLayout.SOUTH);
        or.setBorder(BorderFactory.createLineBorder(Color.black));
        //NOT
        Tnot = new JLabel("Crear Relación NOT");
        CentrarTnot = new JPanel();
        CentrarTnot.add(Tnot);
        notUnico = new JTextField("", 10);
        notUnico.setEditable(false);
        separadorNot = new JLabel("NOT");
        seleccionnot = new JButton("Seleccionar");
        selecionesNOT = new JPanel(new GridBagLayout());
        selecionesNOT.add(separadorNot, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesNOT.add(notUnico, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        selecionesNOT.add(seleccionnot, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        NombreNOT = new JTextField("", 10);
        TnombreNOT = new JLabel("Nombre ");
        crearNOT = new JButton("Crear");
        formularioNOT = new JPanel();
        formularioNOT.add(TnombreNOT);
        formularioNOT.add(NombreNOT);
        formularioNOT.add(crearNOT);
        not = new JPanel(new BorderLayout());
        not.add(CentrarTnot, BorderLayout.NORTH);
        not.add(selecionesNOT, BorderLayout.CENTER);
        not.add(formularioNOT, BorderLayout.SOUTH);
        not.setBorder(BorderFactory.createLineBorder(Color.black));
        /*
        C = new JPanel(new GridBagLayout());
        C.setOpaque(false);
        C.add(simple, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        C.add(and, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        C.add(or, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        C.add(not, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        */
        panelSimpleNot = new JPanel(new BorderLayout());
        panelSimpleNot.add(simple, BorderLayout.NORTH);
        panelSimpleNot.add(not, BorderLayout.SOUTH);
        
        panelAndOR = new JPanel(new BorderLayout());
        panelAndOR.add(and, BorderLayout.NORTH);
        panelAndOR.add(or, BorderLayout.SOUTH);
        
        C = new JPanel(new BorderLayout());
        C.setOpaque(false);
        C.add(panelSimpleNot, BorderLayout.NORTH);
        C.add(panelAndOR, BorderLayout.CENTER);
        
        RELACIONES = new JPanel(new FlowLayout());
        RELACIONES.setOpaque(false);
        RELACIONES.add(C);
        add(RELACIONES, BorderLayout.CENTER);        
        
        /////////////BOTONES//////////////
        //SIMPLE
        crearSIMPLE.addActionListener(CREAR_SIMPLE);
        //AND
        seleccionandIzquierdo.addActionListener(SELECCION_AND_I);
        selecionarandDerecho.addActionListener(SELECCION_AND_D);
        crearAND.addActionListener(CREAR_AND);
        //OR
        seleccionorIzquierdo.addActionListener(SELECCION_OR_I);
        selecionarorDerecho.addActionListener(SELECCION_OR_D);
        crearOR.addActionListener(CREAR_OR);
        //NOT
        seleccionnot.addActionListener(SELECCION_NOT);
        crearNOT.addActionListener(CREAR_NOT);
        //CONJUNTO
        botonRelacionPrincipal.addActionListener(SELECCIONAR_PRINCIPAL);
        crearConjunto.addActionListener(CREAR_CONJUNTO);
        
        actualizarLista();
    }
    
    ActionListener CREAR_SIMPLE = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = NombreSIMPLE.getText();
            if (!nombre.equals("")) {
                //private String[] difsplay_atributos = {"AUTOR", "DURACION", "NUMERO_REPRODUCCIONES", "GENERO", "USUARIO", "NACIONALIDAD", "EDAD"};
                try {
                    switch (atributos_relacion.getSelectedIndex()) {
                        case 0:
                            String valor0 = valorSimpleString.getText();
                            if (!valor0.equals("")) {
                                CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionS(nombre, display_atributos[atributos_relacion.getSelectedIndex()], valor0);
                                valorSimpleString.setText("");
                            }
                            else CVR.GestionError("No se ha introducido un valor", 0);
                            break;
                        case 1:
                            String valor1a = valorSimpleMin.getText();
                            String valor1b = valorSimpleSeg.getText();
                            if (!valor1a.equals("") && !valor1b.equals("")) {
                                Integer valor1 = Integer.parseInt(valor1a)*60 + Integer.parseInt(valor1b);
                                CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionS(nombre, display_atributos[atributos_relacion.getSelectedIndex()], valor1);
                                valorSimpleMin.setText("");
                                valorSimpleSeg.setText("");
                            }
                            else CVR.GestionError("No se ha introducido un valor", 0);
                            break;
                        case 2:
                            String valor2 = valorSimpleInt.getText();
                            if (!valor2.equals("")) {
                                CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionS(nombre, display_atributos[atributos_relacion.getSelectedIndex()], Integer.parseInt(valor2));
                                valorSimpleInt.setText("");
                            }
                            else CVR.GestionError("No se ha introducido un valor", 0);
                            break;
                        case 3:
                            String valor3 = valorSimpleString.getText();
                            if (!valor3.equals("")) {
                                CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionS(nombre, display_atributos[atributos_relacion.getSelectedIndex()], valor3);
                                valorSimpleString.setText("");
                            }
                            else CVR.GestionError("No se ha introducido un valor", 0);
                            break;
                        case 4:
                            String valor4 = valorSimpleString.getText();
                            if (!valor4.equals("")) {
                                CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionS(nombre, display_atributos[atributos_relacion.getSelectedIndex()], valor4);
                                valorSimpleString.setText("");
                            }
                            else  CVR.GestionError("No se ha introducido un valor", 0);
                            break;
                        case 5:
                            String valor5 = valorSimpleString.getText();
                            if (!valor5.equals("")) {
                                CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionS(nombre, display_atributos[atributos_relacion.getSelectedIndex()], valor5);
                                valorSimpleString.setText("");
                            }
                            else CVR.GestionError("No se ha introducido un valor", 0);
                            break;
                        case 6:
                            String valor6 = valorSimpleInt.getText();
                            if (!valor6.equals("")) {
                                CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionS(nombre, display_atributos[atributos_relacion.getSelectedIndex()], Integer.parseInt(valor6));
                                valorSimpleInt.setText("");
                            }
                            else CVR.GestionError("No se ha introducido un valor", 0);
                            break;
                    }
                    actualizarLista();
                    //valorSimple.setText("");
                    NombreSIMPLE.setText("");
                }
                catch(IllegalArgumentException IAE) {
                    CVR.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVR.GestionError("No se ha introducido un nombre", 0);
             
        }
    };
    
    ActionListener SELECCION_AND_I = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (relacionesCreadas.getSelectedValue() != null) {
                andIzquierdo.setText((String)relacionesCreadas.getSelectedValue());
            }
            else CVR.GestionError("No se ha seleccionado ninguna relación", 0);
        }
    };
    
    ActionListener SELECCION_AND_D = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (relacionesCreadas.getSelectedValue() != null) {
                andDerecho.setText((String)relacionesCreadas.getSelectedValue());
            }
            else CVR.GestionError("No se ha seleccionado ninguna relación", 0);
        }
    };
    
    ActionListener CREAR_AND = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = NombreAND.getText();
            String Izquierda = andIzquierdo.getText();
            String Derecha = andDerecho.getText();
            if (!nombre.equals("") && !Izquierda.equals("") && !Derecha.equals("")) {
                try {
                    CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionAND(nombre, Izquierda, Derecha);
                    actualizarLista();
                    NombreAND.setText("");
                    andIzquierdo.setText("");
                    andDerecho.setText("");
                }
                catch(IllegalArgumentException IAE) {
                    CVR.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVR.GestionError("No se han llenado todos los campos", 0);
        }
    };
    
    ActionListener SELECCION_OR_I = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (relacionesCreadas.getSelectedValue() != null) {
                orIzquierdo.setText((String)relacionesCreadas.getSelectedValue());
            }
            else CVR.GestionError("No se ha seleccionado ninguna relación", 0);
        }
    };
    
    ActionListener SELECCION_OR_D = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (relacionesCreadas.getSelectedValue() != null) {
                orDerecho.setText((String)relacionesCreadas.getSelectedValue());
            }
            else CVR.GestionError("No se ha seleccionado ninguna relación", 0);
        }
    };
    
    ActionListener CREAR_OR = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = NombreOR.getText();
            String Izquierda = orIzquierdo.getText();
            String Derecha = orDerecho.getText();
            if (!nombre.equals("") && !Izquierda.equals("") && !Derecha.equals("")) {
                try {
                    CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionOR(nombre, Izquierda, Derecha);
                    actualizarLista();
                    NombreOR.setText("");
                    orIzquierdo.setText("");
                    orDerecho.setText("");
                }
                catch(IllegalArgumentException IAE) {
                    CVR.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVR.GestionError("No se han llenado todos los campos", 0);
        }
    };
    
    ActionListener SELECCION_NOT = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (relacionesCreadas.getSelectedValue() != null) {
                notUnico.setText((String)relacionesCreadas.getSelectedValue());
            }
            else CVR.GestionError("No se ha seleccionado ninguna relación", 0);
        }
    };
    
    ActionListener CREAR_NOT = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = NombreNOT.getText();
            String Unica = notUnico.getText();
            if (!nombre.equals("") && !Unica.equals("")) {
                try {
                    CVR.ConsultarCtrlRelacion().CtrlRelacionRelacionNOT(nombre, Unica);
                    actualizarLista();
                    NombreNOT.setText("");
                    notUnico.setText("");
                }
                catch(IllegalArgumentException IAE) {
                    CVR.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVR.GestionError("No se han llenado todos los campos", 0);
        }
    };  
    
    ActionListener SELECCIONAR_PRINCIPAL = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (relacionesCreadas.getSelectedValue() != null) {
                relacionPrincipal.setText((String)relacionesCreadas.getSelectedValue());
            }
            else CVR.GestionError("No se ha seleccionado ninguna relación", 0);     
        }
    };
    
    ActionListener CREAR_CONJUNTO = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = relacionPrincipal.getText();
            if (!s.equals("")) {
                CVR.ConsultarCtrlRelacion().CrearGrafo(s);
                relacionPrincipal.setText("");
                CVR.GestionError("", 1);
            }
            else CVR.GestionError("No se ha seleccionado ninguna relación", 0);
        }
    };
    
     DocumentListener CAMBIO = new DocumentListener() {
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
    
    public void actualizarLista() {
        String s = buscarRelacion.getText();
        if (!s.equals("")) {
            LinkedList<String> L = CVR.ConsultarCtrlRelacion().CtrlNombres(s);
            String[] nombres = L.toArray(new String[L.size()]);
            relacionesCreadas.setListData(nombres);
        }
        else {
            LinkedList<String> L = CVR.ConsultarCtrlRelacion().CtrlNombres();
            String[] nombres = L.toArray(new String[L.size()]);
            relacionesCreadas.setListData(nombres);
        }
    }
    
    ActionListener CAMBIAR = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (atributos_relacion.getSelectedIndex()) {
                case 0:
                    valorSimpleString.setVisible(true);
                    valorSimpleInt.setVisible(false);
                    valorSimpleMin.setVisible(false);
                    valorSimpleSeg.setVisible(false);
                    minutos.setVisible(false);
                    segundos.setVisible(false);
                    break;
                case 1:
                    valorSimpleString.setVisible(false);
                    valorSimpleInt.setVisible(false);
                    valorSimpleMin.setVisible(true);
                    valorSimpleSeg.setVisible(true);
                    minutos.setVisible(true);
                    segundos.setVisible(true);
                    break;
                case 2:
                    valorSimpleString.setVisible(false);
                    valorSimpleInt.setVisible(true);
                    valorSimpleMin.setVisible(false);
                    valorSimpleSeg.setVisible(false);
                    minutos.setVisible(false);
                    segundos.setVisible(false);
                    break;
                case 3:
                    valorSimpleString.setVisible(true);
                    valorSimpleInt.setVisible(false);
                    valorSimpleMin.setVisible(false);
                    valorSimpleSeg.setVisible(false);
                    minutos.setVisible(false);
                    segundos.setVisible(false);
                    break;
                case 4:
                    valorSimpleString.setVisible(true);
                    valorSimpleInt.setVisible(false);
                    valorSimpleMin.setVisible(false);
                    valorSimpleSeg.setVisible(false);
                    minutos.setVisible(false);
                    segundos.setVisible(false);
                    break;
                case 5:
                    valorSimpleString.setVisible(true);
                    valorSimpleInt.setVisible(false);
                    valorSimpleMin.setVisible(false);
                    valorSimpleSeg.setVisible(false);
                    minutos.setVisible(false);
                    segundos.setVisible(false);
                    break;
                case 6:
                    valorSimpleString.setVisible(false);
                    valorSimpleInt.setVisible(true);
                    valorSimpleMin.setVisible(false);
                    valorSimpleSeg.setVisible(false);
                    minutos.setVisible(false);
                    segundos.setVisible(false);
                    break;
            }
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
