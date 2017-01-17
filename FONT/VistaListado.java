/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
public class VistaListado extends VistaComun {
    
    private CtrlVistaListado CVL;
    private JLabel titulo;
    
    //BLOQUE COMUN
    private JButton ConsultarCancion;
    private JButton Escuchar;
    
    //PANEL DERECHO CANCIONES
    private JLabel Tcanciones;
    private JTextField TbuscadorCanciones;
    private JList canciones; 
    private JPanel BloqueCanciones;
    
    private JPanel D;
    
    //CANCION
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
    private JPanel Duracion;
    private JLabel numRCancion;
    private JTextField TnumRCancion;
    private JPanel formulario;
    private JPanel formtitle;
    
    //PANEL REPRODDUCION
    private JLabel display;
    private JPanel reproduccion;
    
    //PANEL PRINCIPAL
    private JPanel C;   
            
    public VistaListado(CtrlVistaListado cvl) {
        
        CVL = cvl;
        titulo = new JLabel("Listados");
        add(titulo, BorderLayout.NORTH);
        
        //BLOQUE COMUN      
        JTF.getDocument().addDocumentListener(CAMBIO);
        JL.addListSelectionListener(SELECCION_LISTADO);

        Cargar.addActionListener(LOAD);
        Guardar.addActionListener(SAVE);
        Consultar.addActionListener(GET);
        Anadir.setVisible(false);
        Borrar.setVisible(false);
        Modificar.setVisible(false);
        
        //PANEL DERECHO CANCIONES      
        Tcanciones = new JLabel("Canciones");
        TbuscadorCanciones = new JTextField("", 10);
        TbuscadorCanciones.getDocument().addDocumentListener(CAMBIO_CANCIONES);
        canciones = new JList();
        canciones.addListSelectionListener(SELECCION_CANCION_LISTADO);
        
        BloqueCanciones = new JPanel(new BorderLayout());
        BloqueCanciones.setOpaque(false);
        BloqueCanciones.add(TbuscadorCanciones, BorderLayout.NORTH);
        BloqueCanciones.add(new JScrollPane(canciones), BorderLayout.CENTER);
        
        ConsultarCancion = new JButton("Consultar Canción");
        ConsultarCancion.setVisible(false);
        ConsultarCancion.addActionListener(CONSULTAR_CANCION);
          
        D = new JPanel(new BorderLayout());
        D.setOpaque(false);
        D.add(Tcanciones, BorderLayout.NORTH);
        D.add(BloqueCanciones, BorderLayout.CENTER);
        D.add(ConsultarCancion, BorderLayout.SOUTH);
        D.setVisible(false);
        
        add(D, BorderLayout.EAST);
        
        //CANCION
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
        TduracionCancionMin = new JTextField("", 3);
        TduracionCancionMin.setEditable(false);
        TduracionCancionSeg = new JTextField("", 3);
        TduracionCancionSeg.setEditable(false);
        minutos = new JLabel("Minutos");
        segundos = new JLabel("Segundos");
        Duracion = new JPanel();
        Duracion.setOpaque(false);
        Duracion.add(minutos);
        Duracion.add(TduracionCancionMin);
        Duracion.add(segundos);
        Duracion.add(TduracionCancionSeg);
        numRCancion = new JLabel("Número Reproduciones ");;
        TnumRCancion = new JTextField("", 20);
        TnumRCancion.setEditable(false);
        
        Escuchar = new JButton("Escuchar Canción");
        Escuchar.addActionListener(ESCUCHAR);
        
        formulario = new JPanel(new GridBagLayout());
        formulario.setOpaque(false);
        formulario.add(tituloCancion, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TtituloCancion, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(autorCancion, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TautoroCancion, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(generoCancion, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TgeneroCancion, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(duracionCancion, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(Duracion, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(numRCancion, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(TnumRCancion, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        formulario.add(Escuchar, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        formtitle = new JPanel(new BorderLayout());
        formtitle.setOpaque(false);
        formtitle.add(centrarTitulo, BorderLayout.NORTH);
        formtitle.add(formulario, BorderLayout.CENTER);
        
        //REPRODUCCION
        display = new JLabel();
        ImageIcon ii = new ImageIcon("banano.gif");
        display.setIcon(ii);
        reproduccion = new JPanel();
        reproduccion.setOpaque(false);
        reproduccion.add(display);
        reproduccion.setVisible(false);
        
        formtitle.add(reproduccion, BorderLayout.SOUTH);
        
        //PANEL PRINCIPAL
        C = new JPanel();
        C.setOpaque(false);
        C.add(formtitle);
        C.setVisible(false);
        
        add(C, BorderLayout.CENTER);   
    }
    
     ActionListener LOAD = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                JFileChooser F = new JFileChooser();
                F.setDialogTitle("Selecionar Base de Datos de Listados");
                F.setCurrentDirectory(new java.io.File("."));                
                int p = F.showOpenDialog(Cargar);
                if (p == JFileChooser.APPROVE_OPTION) {
                    String path = F.getSelectedFile().getPath();
                    try {
                        CVL.ConsultarCtrlListado().cargar(path);
                        LinkedList<String> L = CVL.ConsultarCtrlListado().CtrlNombres();
                        String[] nombres = L.toArray(new String[L.size()]);
                        JL.setListData(nombres);
                    } catch (Exception ex) {
                        Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                        CVL.GestionError("No se ha podido cargar la base de datos de listados", 0);
                    }
                }
        }
    };
    
    ActionListener SAVE = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser F = new JFileChooser();
            F.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            F.setDialogTitle("Guardar Base de Datos de Canciones");
            F.setCurrentDirectory(new java.io.File("."));  
            F.setAcceptAllFileFilterUsed(false);
            int p = F.showOpenDialog(Guardar);
            if (p == JFileChooser.APPROVE_OPTION) {
                String path = F.getSelectedFile().getPath();
                try {
                    CVL.ConsultarCtrlListado().guardar(path);
                } catch (Exception ex) {
                    Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                    CVL.GestionError("No se ha podido guardar la base de datos de listados", 0);
                }
            }
        }
    };
       
    ActionListener GET = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (JL.getSelectedValue() != null) {
                D.setVisible(true);
                actualizarCanciones();
                ConsultarCancion.setVisible(true);
                C.setVisible(false);
            }
            else CVL.GestionError("No se ha seleccionado ningun listado", 0);
        }
    };
    
    ActionListener CONSULTAR_CANCION = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (canciones.getSelectedValue() != null && JL.getSelectedValue() != null) {
                C.setVisible(true);
                TtituloCancion.setText(CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).consultarCancion((String)canciones.getSelectedValue()).consultarTitulo());
                TautoroCancion.setText(CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).consultarCancion((String)canciones.getSelectedValue()).consultarAutor());
                TgeneroCancion.setText(CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).consultarCancion((String)canciones.getSelectedValue()).consultarGenero());
                TduracionCancionMin.setText(CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).consultarCancion((String)canciones.getSelectedValue()).consultarMinutos().toString());
                TduracionCancionSeg.setText(CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).consultarCancion((String)canciones.getSelectedValue()).consultarSegundos().toString());
                TnumRCancion.setText(CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).consultarCancion((String)canciones.getSelectedValue()).consultarReproducciones().toString());
                
                reproduccion.setVisible(false);
                CVL.GestionError("", 1);
            }
            else CVL.GestionError("El listado y la canción tienen que estar seleccionados", 0);
        }
    };
    
    ActionListener ESCUCHAR = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (canciones.getSelectedValue() != null && JL.getSelectedValue() != null) {
                reproduccion.setVisible(true);
                CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).escucharCancion((String)canciones.getSelectedValue());
                TnumRCancion.setText(CVL.ConsultarCtrlListado().CtrlconsultarListado((String)JL.getSelectedValue()).consultarCancion((String)canciones.getSelectedValue()).consultarReproducciones().toString());
                CVL.GestionError("", 1);
            }
            else CVL.GestionError("El listado y la canción tienen que estar seleccionados", 0);
        }
        
    };
    
    DocumentListener CAMBIO = new DocumentListener() {
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
                LinkedList<String> L = CVL.ConsultarCtrlListado().CtrlNombres(s);
                String[] nombres = L.toArray(new String[L.size()]);
                JL.setListData(nombres);
            }
            else {
                LinkedList<String> L = CVL.ConsultarCtrlListado().CtrlNombres();
                String[] nombres = L.toArray(new String[L.size()]);
                JL.setListData(nombres);
            }
    }
    
    DocumentListener CAMBIO_CANCIONES = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            actualizarCanciones();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actualizarCanciones();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actualizarCanciones();
        }
    };
    
    public void actualizarCanciones() {
        String s = TbuscadorCanciones.getText();
        if (JL.getSelectedValue() != null) {
            if (!s.equals("")) {
                LinkedList<String> L = CVL.ConsultarCtrlListado().CtrlconsultarListado((String) JL.getSelectedValue()).consultarNombres(s);
                String[] nombres = L.toArray(new String[L.size()]);
                canciones.setListData(nombres);
            }
            else {
                LinkedList<String> L = CVL.ConsultarCtrlListado().CtrlconsultarListado((String) JL.getSelectedValue()).consultarNombres();
                String[] nombres = L.toArray(new String[L.size()]);
                canciones.setListData(nombres);
            }
        }
        else CVL.GestionError("No se ha seleccionado ningun Listado", 0);
    }
    
    ListSelectionListener SELECCION_LISTADO = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            D.setVisible(false);
            C.setVisible(false);
        }
    };
    
    ListSelectionListener SELECCION_CANCION_LISTADO = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            C.setVisible(false);
        }
    };
}


