/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

/**
 *
 * @author MonSB
 */
public class VistaCancion extends VistaComun {
    
    private CtrlVistaCancion CVC;
    
    private JLabel titulo;
    
    private VistaCancionFormulario formulario;
    
    private VistaGenero formulario_genero;
    
    private JButton Genero;
    
    private JPanel C;
    
    private CardLayout L;
    
    
    public VistaCancion(CtrlVistaCancion cvc) {
        CVC = cvc;
        
        //BLOQUE COMUN
        JTF.getDocument().addDocumentListener(CAMBIO);
        JL.addListSelectionListener(SELECCION_LISTADO);
        Genero = new JButton("Géneros");
        S.add(Genero);
        
        Cargar.addActionListener(LOAD);
        Guardar.addActionListener(SAVE);
        Anadir.addActionListener(ADD);
        Borrar.addActionListener(DELETE);
        Consultar.addActionListener(GET);
        Modificar.addActionListener(SET); 
        Genero.addActionListener(GENERO);
        
        //TITULO
        titulo = new JLabel("Canciones");
        add(titulo, BorderLayout.NORTH);
        
        //PANEL CENTRAL
        L = new CardLayout();
        
        C = new JPanel(L);
        C.setOpaque(false);
        
        formulario = new VistaCancionFormulario(CVC);
        formulario.setOpaque(false);
        C.add(formulario, "FC");
        
        formulario_genero = new VistaGenero(CVC);
        formulario_genero.setOpaque(false);
        C.add(formulario_genero, "FG");
        
        C.setVisible(false);
        
        add(C, BorderLayout.CENTER); 
    }
    
    ActionListener LOAD = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                visible(0);
                JFileChooser F = new JFileChooser();
                F.setDialogTitle("Selecionar Base de Datos de Canciones");
                F.setCurrentDirectory(new java.io.File("."));                
                int p = F.showOpenDialog(Cargar);
                if (p == JFileChooser.APPROVE_OPTION) {
                    String path = F.getSelectedFile().getPath();
                    try {
                        CVC.ConsultarCtrlCancion().cargar(path);
                        actualizar();
                    } catch (Exception ex) {
                        Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                        CVC.GestionError("No se ha podido cargar la base de datos de canciones", 0);
                    }
                }
        }
    };
    
    ActionListener SAVE = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            visible(1);
            JFileChooser F = new JFileChooser();
            F.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            F.setDialogTitle("Guardar Base de Datos de Canciones");
            F.setCurrentDirectory(new java.io.File("."));  
            F.setAcceptAllFileFilterUsed(false);
            int p = F.showOpenDialog(Guardar);
            if (p == JFileChooser.APPROVE_OPTION) {
                String path = F.getSelectedFile().getPath();
                try {
                    CVC.ConsultarCtrlCancion().guardar(path);
                } catch (Exception ex) {
                    Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                    CVC.GestionError("No se ha podido guardar la base de datos de canciones", 0);
                }
            }
        }
    };
    
    ActionListener DELETE = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (JL.getSelectedValue() != null) {
                try {
                    visible(3);
                    String s = (String)JL.getSelectedValue();
                    CVC.ConsultarCtrlCancion().CtrleliminarCancion(s);
                    actualizar();
                }
                catch(IllegalArgumentException IAE) {
                    CVC.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVC.GestionError("No se ha seleccionado ninguna canción", 0);
        }
    };
    
    ActionListener ADD = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(4);
        }
    };
    
    ActionListener SET = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(5);
        }
    };
    
    ActionListener GET = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(6);
        }
    };
    
    ActionListener GENERO = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(7);
        }
    };
    
    private void visible(int x) {
        switch (x) {
            case 0:
                C.setVisible(false);
                break;
            case 1:
                C.setVisible(false);
                break;
            case 2:
                C.setVisible(false);
                break;
            case 3:
                C.setVisible(false);
                break;
            case 4:
                C.setVisible(true);
                L.show(C, "FC");
                formulario.modoAnadir();                
                break;
            case 5:
                if (JL.getSelectedValue() != null) {
                    formulario.modoModificar((String)JL.getSelectedValue());
                    C.setVisible(true);
                    L.show(C, "FC");
                }
                else CVC.GestionError("No se ha seleccionado ninguna canción", 0);
                break;
            case 6:  
                if (JL.getSelectedValue() != null) {
                    formulario.modoConsultar((String)JL.getSelectedValue());
                    C.setVisible(true);
                    L.show(C, "FC");
                }
                else CVC.GestionError("No se ha seleccionado ninguna canción", 0);
                break;
            case 7:
                C.setVisible(true);
                L.show(C, "FG");
                formulario_genero.actualizarLista();
                break;
        }
        repaint();
    }
    
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
                LinkedList<String> L = CVC.ConsultarCtrlCancion().CtrlNombres(s);
                String[] nombres = L.toArray(new String[L.size()]);
                JL.setListData(nombres);
            }
            else {
                LinkedList<String> L = CVC.ConsultarCtrlCancion().CtrlNombres();
                String[] nombres = L.toArray(new String[L.size()]);
                JL.setListData(nombres);
            }
    }
    
    ListSelectionListener SELECCION_LISTADO = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
           C.setVisible(false);
        }
    };
}
