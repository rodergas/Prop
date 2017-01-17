/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.Cancion;
import Dominio.Comunidad;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author MonSB
 */
public class VistaSolucion extends VistaComun {
    
    private CtrlVistaSolucion CVS;
    private JLabel titulo;
    
    //COMUNIDADES
    private JLabel titulo_comunidades;
    private JList comunidades;
    private JButton consCom;
    
    private JPanel D;
    
    
    //CANCIONES
    private JLabel Ncanciones;
    private JPanel centrarNcanciones;
    private JButton consultarCancion;
    private JList canciones;
    
    private JPanel panelCanciones;
    
    //CONSULTA CANCIONES
    private JLabel tituloFormulario;
    private JPanel centrarTitulo;
    private JLabel tituloCancion;
    private JTextField TtituloCancion;
    private JLabel autorCancion;
    private JTextField TautoroCancion;
    private JLabel generoCancion;
    private JTextField TgeneroCancion;
    private JLabel duracionCancion;
    private JLabel minutos;
    private JLabel segundos;
    private JTextField TduracionCancionMin;
    private JTextField TduracionCancionSeg;
    private JPanel DURACION;
    private JLabel numRCancion;
    private JTextField TnumRCancion;
    private JPanel formulario;
    private JPanel formtitle;
    private JPanel C;
    
    //PANEL PRINCIPAL
    private JPanel Centro;
    private JPanel CentroRelativo;
    
    
    public VistaSolucion(CtrlVistaSolucion cvs) {
        
        CVS = cvs;
        
        titulo = new JLabel("Soluciones");
        add(titulo, BorderLayout.NORTH);
        
        //BLOQUE COMUN
        JTF.getDocument().addDocumentListener(BUSQUEDA);
        JL.addListSelectionListener(SELECCION_SOLUCION);
        
        Anadir.setVisible(false);
        Borrar.setVisible(false);
        Modificar.setVisible(false); 
        
        Cargar.addActionListener(LOAD);
        Guardar.addActionListener(SAVE);
        Consultar.addActionListener(GET);
        
        //COMUNIDADES
        titulo_comunidades = new JLabel("Comunidades");
        comunidades = new JList();
        comunidades.addListSelectionListener(SELECCION_COMUNIDAD);
        consCom = new JButton("Consultar Comunidad");
        D = new JPanel(new BorderLayout());
        D.setOpaque(false);
        D.add(titulo_comunidades, BorderLayout.NORTH);
        D.add(new JScrollPane(comunidades), BorderLayout.CENTER);
        D.add(consCom, BorderLayout.SOUTH);
        D.setVisible(false);
        
        consCom.addActionListener(CONSULTAR_COMUNIDAD);
        
        add(D, BorderLayout.EAST);
        
        //CANCIONES
        Ncanciones = new JLabel("Canciones Comunidad");
        centrarNcanciones = new JPanel();
        centrarNcanciones.add(Ncanciones);
        consultarCancion = new JButton("Consultar");
        canciones = new JList();
        panelCanciones = new JPanel(new BorderLayout());
        panelCanciones.add(centrarNcanciones, BorderLayout.NORTH);
        panelCanciones.add(new JScrollPane(canciones), BorderLayout.CENTER);
        panelCanciones.add(consultarCancion, BorderLayout.SOUTH);
        panelCanciones.setBorder(BorderFactory.createLineBorder(Color.black));
        
        consultarCancion.addActionListener(CONSULTAR_CANCION);
        
        //CONSULTA CANCIONES
        tituloFormulario = new JLabel("Canción");
        centrarTitulo = new JPanel();
        centrarTitulo.setOpaque(false);
        centrarTitulo.add(tituloFormulario);
        tituloCancion = new JLabel("Título ");
        TtituloCancion = new JTextField("", 20);
        TtituloCancion.setEditable(false);
        autorCancion = new JLabel("Autor ");
        TautoroCancion = new JTextField("", 20);
        TautoroCancion.setEditable(false);
        generoCancion = new JLabel("Género ");
        TgeneroCancion = new JTextField("", 20);
        TgeneroCancion.setEditable(false);
        duracionCancion = new JLabel("Duración ");
        minutos = new JLabel("Minutos");
        TduracionCancionMin = new JTextField("", 3);
        TduracionCancionMin.setEditable(false);
        segundos = new JLabel("Segundos");
        TduracionCancionSeg = new JTextField("", 3);
        TduracionCancionSeg.setEditable(false);
        DURACION = new JPanel();
        DURACION.setOpaque(false);
        DURACION.add(minutos);
        DURACION.add(TduracionCancionMin);
        DURACION.add(segundos);
        DURACION.add(TduracionCancionSeg);
        numRCancion = new JLabel("Número Reproduciones ");;
        TnumRCancion = new JTextField("", 20);
        TnumRCancion.setEditable(false);
        formulario = new JPanel(new GridBagLayout());
        formulario.setOpaque(false);
        formulario.add(tituloCancion, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TtituloCancion, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(autorCancion, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TautoroCancion, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(generoCancion, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TgeneroCancion, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(duracionCancion, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(DURACION, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(numRCancion, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TnumRCancion, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        formtitle = new JPanel(new BorderLayout());
        //formtitle.setOpaque(false);
        formtitle.setBorder(BorderFactory.createLineBorder(Color.black));
        formtitle.add(centrarTitulo, BorderLayout.NORTH);
        formtitle.add(formulario, BorderLayout.CENTER);
        
        C = new JPanel(new BorderLayout());
        C.setOpaque(false);
        C.add(formtitle, BorderLayout.CENTER);
        
        C.setVisible(false);
        
        //PANEL PRINCIPAL
        Centro = new JPanel(new GridBagLayout());
        Centro.setOpaque(false);
        Centro.add(panelCanciones, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        Centro.add(C, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        Centro.setVisible(false);
        
        CentroRelativo = new JPanel();
        CentroRelativo.setOpaque(false);
        CentroRelativo.add(Centro);
        
        add(CentroRelativo, BorderLayout.CENTER);
    }
    
    ActionListener LOAD = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser F = new JFileChooser();
            F.setDialogTitle("Selecionar Base de Datos de Soluciones");
            F.setCurrentDirectory(new java.io.File("."));                
            int p = F.showOpenDialog(Cargar);
            if (p == JFileChooser.APPROVE_OPTION) {
                String path = F.getSelectedFile().getPath();
                try {
                    CVS.ConsultarCtrlSolucion().cargar(path);
                    //System.out.println(path);
                    LinkedList<String> L = CVS.ConsultarCtrlSolucion().CtrlNombres();
                    String[] nombres = L.toArray(new String[L.size()]);
                    //System.out.println(nombres);
                    JL.setListData(nombres);
                } catch (Exception ex) {
                    Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                    CVS.GestionError("No se ha podido cargar la base de datos de soluciones", 0);
                }
            }
        }
    };
    
    ActionListener SAVE = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser F = new JFileChooser();
            F.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            F.setDialogTitle("Guardar Base de Datos de Soluciónes");
            F.setCurrentDirectory(new java.io.File("."));  
            F.setAcceptAllFileFilterUsed(false);
            int p = F.showOpenDialog(Guardar);
            if (p == JFileChooser.APPROVE_OPTION) {
                String path = F.getSelectedFile().getPath();
                try {
                    CVS.ConsultarCtrlSolucion().guardar(path);
                } catch (Exception ex) {
                    Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                    CVS.GestionError("No se ha podido guardar la base de datos de soluciones", 0);
                }
            }
        }
    };
    
    ActionListener GET = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (JL.getSelectedValue() !=  null) {
                D.setVisible(true);
                Centro.setVisible(false);
                int n = CVS.ConsultarCtrlSolucion().CtrlConsultarSolucion((String)JL.getSelectedValue()).obtenerComunidades().size();
                String[] indices = new String[n];
                for (Integer i = 0; i < n; ++i) indices[i] = i.toString();
                comunidades.setListData(indices);
            }
            else CVS.GestionError("No se ha seleccionado ninguna solución", 0);
        }
    };
    
    ActionListener CONSULTAR_CANCION = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (canciones.getSelectedValue() != null) {
                 C.setVisible(true);
                TtituloCancion.setText(CVS.ConsultarCtrlSolucion().CtrlconsultarCtrlCanciones().CtrlconsultarCancion((String)canciones.getSelectedValue()).consultarTitulo());
                TautoroCancion.setText(CVS.ConsultarCtrlSolucion().CtrlconsultarCtrlCanciones().CtrlconsultarCancion((String)canciones.getSelectedValue()).consultarAutor());
                TgeneroCancion.setText(CVS.ConsultarCtrlSolucion().CtrlconsultarCtrlCanciones().CtrlconsultarCancion((String)canciones.getSelectedValue()).consultarGenero());
                TduracionCancionMin.setText(CVS.ConsultarCtrlSolucion().CtrlconsultarCtrlCanciones().CtrlconsultarCancion((String)canciones.getSelectedValue()).consultarMinutos().toString());
                TduracionCancionSeg.setText(CVS.ConsultarCtrlSolucion().CtrlconsultarCtrlCanciones().CtrlconsultarCancion((String)canciones.getSelectedValue()).consultarSegundos().toString());
                TnumRCancion.setText(CVS.ConsultarCtrlSolucion().CtrlconsultarCtrlCanciones().CtrlconsultarCancion((String)canciones.getSelectedValue()).consultarReproducciones().toString());
            }
            else CVS.GestionError("No se ha seleccionado ninguna canción", 0);
            
        }
    };
    
    ActionListener CONSULTAR_COMUNIDAD = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (comunidades.getSelectedIndex() != -1 && JL.getSelectedValue() != null) {
                Centro.setVisible(true);
                C.setVisible(false);
                ArrayList<Cancion> C = CVS.ConsultarCtrlSolucion().CtrlConsultarSolucion((String)JL.getSelectedValue()).obtenerComunidad(comunidades.getSelectedIndex()).consultarComunidad();
                String[] nombre = new String[C.size()];
                for (int i = 0; i < C.size(); ++i) nombre[i] = C.get(i).consultarTitulo();
                canciones.setListData(nombre);
            }
            else {
                if (comunidades.getSelectedIndex() != -1) CVS.GestionError("No se ha seleccionado ninguna comunidad", 0);
                else CVS.GestionError("No se ha seleccionado ningun listado", 0);
            }
            
        }
    };
    
    DocumentListener BUSQUEDA = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            actualizar();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actualizar();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actualizar();
        }
    };
    
    public void actualizar() {
        String s = JTF.getText();
        if(!s.equals("")) {
            LinkedList<String> L = CVS.ConsultarCtrlSolucion().CtrlNombres(s);
            String[] nombres = L.toArray(new String[L.size()]);
            JL.setListData(nombres);
        }
        else {
            LinkedList<String> L = CVS.ConsultarCtrlSolucion().CtrlNombres();
            String[] nombres = L.toArray(new String[L.size()]);
            JL.setListData(nombres);
        }
    }
    
    ListSelectionListener SELECCION_SOLUCION = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            D.setVisible(false);
            Centro.setVisible(false);
        }
    };
    
    ListSelectionListener SELECCION_COMUNIDAD = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            Centro.setVisible(false);
        }
    };
}
