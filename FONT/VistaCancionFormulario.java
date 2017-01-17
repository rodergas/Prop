/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeCellEditor;

/**
 *
 * @author MonSB
 */
public class VistaCancionFormulario extends JPanel {
    
    private CtrlVistaCancion CC;
    
    private JLabel titulo;
    private JLabel autor;
    private JLabel genero;
    private JLabel duracion;
    private JLabel minutos;
    private JLabel segundos;
    private JLabel num_reproducciones;
    
    private JTextField Ttitulo;
    private JTextField Tautor;
    private JTextField Tgenero;
    private JTextField TduracionMin;
    private JTextField TduracionSeg;
    private JPanel DURACION;
    private JTextField Tnum_reproducciones;
    
    private JButton aceptar_anadir;
    private JButton aceptar_modificar;
    private JPanel ACEPTAR;
    private JLabel titulo_modo;
    
    private JPanel PanelPrincipal;
       
    public VistaCancionFormulario(CtrlVistaCancion cc) {
        CC = cc;
        //TITULO
        titulo_modo = new JLabel();
        
        //NOMBRES FORMULARIO
        titulo = new JLabel("Título  ");
        autor = new JLabel("Autor  ");
        genero = new JLabel("Género  ");
        duracion = new JLabel("Duración  ");
        num_reproducciones = new JLabel("Número Reproducciones  ");
        
        //ESPACIOS FORMULARIO
        Ttitulo = new JTextField(20);
        Tautor = new JTextField(20);
        Tgenero = new JTextField(20);
        minutos = new JLabel("Minutos");
        segundos = new JLabel("Segundos");
        TduracionMin  = new JTextField("", 3);
        TduracionMin.addKeyListener(solo_numeros);
        TduracionSeg  = new JTextField("", 3);
        TduracionSeg.addKeyListener(solo_numeros);
        DURACION = new JPanel();
        DURACION.setOpaque(false);
        DURACION.add(minutos);
        DURACION.add(TduracionMin);
        DURACION.add(segundos);
        DURACION.add(TduracionSeg);
        Tnum_reproducciones = new JTextField(20);
        Tnum_reproducciones.addKeyListener(solo_numeros);

        aceptar_anadir = new JButton("Añadir Canción");
        aceptar_anadir.addActionListener(anadir);
        aceptar_anadir.setVisible(false);
        aceptar_modificar = new JButton("Modificar Canción");
        aceptar_modificar.addActionListener(modificar);
        aceptar_modificar.setVisible(false);
        ACEPTAR = new JPanel();
        ACEPTAR.add(aceptar_anadir);
        ACEPTAR.setOpaque(false);
        ACEPTAR.add(aceptar_modificar);

        PanelPrincipal = new JPanel(new GridBagLayout());
        PanelPrincipal.setOpaque(false);
        //TITULO
        PanelPrincipal.add(titulo_modo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        //NOMBRES FORMULARIO
        PanelPrincipal.add(titulo, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(autor, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(genero, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(duracion, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(num_reproducciones, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        //ESPACIOS FORMULARIO
        PanelPrincipal.add(Ttitulo, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(Tautor, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(Tgenero, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(DURACION, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        PanelPrincipal.add(Tnum_reproducciones, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        PanelPrincipal.add(ACEPTAR, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        setLayout(new FlowLayout());
        add(PanelPrincipal);
    }
    
    public void modoAnadir() {
        titulo_modo.setText("Añadir Canción");
        Ttitulo.setText("");
        Tautor.setText("");
        Tgenero.setText("");
        TduracionMin.setText("");
        TduracionSeg.setText("");
        Tnum_reproducciones.setText("");
        aceptar_anadir.setVisible(true);
        aceptar_modificar.setVisible(false);
        Ttitulo.setEditable(true);
        Tautor.setEditable(true);
        Tgenero.setEditable(true);
        TduracionMin.setEditable(true);
        TduracionSeg.setEditable(true);
        Tnum_reproducciones.setEditable(true);       
    }
    
    public void modoModificar(String s) {
        titulo_modo.setText("Modificar Canción");
        aceptar_modificar.setVisible(true);
        aceptar_anadir.setVisible(false);
        Ttitulo.setEditable(false);
        Tautor.setEditable(true);
        Tgenero.setEditable(false);
        TduracionMin.setEditable(true);
        TduracionSeg.setEditable(true);
        Tnum_reproducciones.setEditable(true);  
        
        Ttitulo.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarTitulo());
        Tautor.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarAutor());
        Tgenero.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarGenero());
        TduracionMin.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarMinutos().toString());
        TduracionSeg.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarSegundos().toString());
        Tnum_reproducciones.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarReproducciones().toString());
    }
    
    public void modoConsultar(String s) {
        titulo_modo.setText("Consultar Canción");
        aceptar_anadir.setVisible(false);
        aceptar_modificar.setVisible(false);
        Ttitulo.setEditable(false);
        Tautor.setEditable(false);
        Tgenero.setEditable(false);
        TduracionMin.setEditable(false);
        TduracionSeg.setEditable(false);
        Tnum_reproducciones.setEditable(false);
        
        Ttitulo.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarTitulo());
        Tautor.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarAutor());
        Tgenero.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarGenero());
        TduracionMin.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarMinutos().toString());
        TduracionSeg.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarSegundos().toString());
        Tnum_reproducciones.setText(CC.ConsultarCtrlCancion().CtrlconsultarCancion(s).consultarReproducciones().toString());  
    }    
    
    ActionListener anadir = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] at = {
                Ttitulo.getText(),
                Tautor.getText(),
                Tgenero.getText(),
                TduracionMin.getText(),
                TduracionSeg.getText(),
                Tnum_reproducciones.getText(),
            };
            if (at[0].equals("") || at[1].equals("") || at[2].equals("") || at[3].equals("") || at[4].equals("") || at[5].equals("")) CC.GestionError("No se han llenado los campos de la canción", 0);
            else {
                try {
                    CC.ConsultarCtrlCancion().CtrlanadirCancion(at[0],  at[1], Integer.parseInt(at[3]), Integer.parseInt(at[4]), Integer.parseInt(at[5]), at[2]);
                    
                    Ttitulo.setText("");
                    Tautor.setText("");
                    Tgenero.setText("");
                    TduracionMin.setText("");
                    TduracionSeg.setText("");
                    Tnum_reproducciones.setText("");
                    
                    CC.vista().actualizar();
                }
                catch(IllegalArgumentException IAE) {
                    CC.GestionError(IAE.getMessage(), 0);
                }
            }
        }
    };
    
    ActionListener modificar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] at = {
                Ttitulo.getText(),
                Tautor.getText(),
                TduracionMin.getText(),
                TduracionSeg.getText(),
                Tnum_reproducciones.getText(),
            };
            if (at[0].equals("") || at[1].equals("") || at[2].equals("") || at[3].equals("") || at[4].equals("")) CC.GestionError("No se han llenado los campos de la canción", 0);
            else {    
                try {
                    CC.ConsultarCtrlCancion().CtrlmodAutor(at[0], at[1]);
                    CC.ConsultarCtrlCancion().CtrlmodMinutos(at[0], Integer.parseInt(at[2]));
                    CC.ConsultarCtrlCancion().CtrlmodSegundos(at[0], Integer.parseInt(at[3]));
                    CC.ConsultarCtrlCancion().CtrlmodReproducciones(at[0], Integer.parseInt(at[4]));
                }
                catch (IllegalArgumentException IAE) {
                    CC.GestionError(IAE.getMessage(), 0);
                }
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
