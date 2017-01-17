/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

/**
 *
 * @author MonSB
 */
public class VistaComun extends JPanel{
    //PANEL IZQUIERDO
    protected JTextField JTF;
    protected JList JL;
    
    protected JPanel I;
    
    //BOTONES
    protected JButton Cargar;
    protected JButton Guardar;
    protected JButton Anadir;
    protected JButton Borrar;
    protected JButton Consultar;
    protected JButton Modificar;    
    
    protected JPanel S;
    
    //ERRORES
    protected JLabel errores;
    
 
    public VistaComun() {
        //PANEL IZQUIERDO
        JTF = new JTextField(10);
        JL = new JList();
        
        I = new JPanel(new BorderLayout());
        I.add(JTF, BorderLayout.NORTH);
        I.add(new JScrollPane(JL), BorderLayout.CENTER);
        
        //BOTONES       
        Cargar = new JButton("Cargar");
        Guardar = new JButton("Guardar");
        Anadir = new JButton("Añadir");
        Borrar = new JButton("Borrar");
        Consultar = new JButton("Consultar");
        Modificar = new JButton("Modificar");
        
        S = new JPanel(new FlowLayout());
        S.setOpaque(false);
        S.add(Cargar);
        S.add(Guardar);
        S.add(Anadir);
        S.add(Borrar);
        S.add(Consultar);
        S.add(Modificar);
        
        //ERRORES
        errores = new JLabel("");
        
        I.add(errores, BorderLayout.SOUTH);
        
        //PRINCIPAL
        setLayout(new BorderLayout());
        add(S, BorderLayout.SOUTH);
        add(I, BorderLayout.WEST);       
    }
}
