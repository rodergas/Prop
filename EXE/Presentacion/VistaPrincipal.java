/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javafx.scene.layout.Background;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author MonSB
 */
public class VistaPrincipal extends JFrame {
    private JLabel ERRORES;
    private JTabbedPane P;
    private JLabel F;
    private Timer T;
    
    public VistaPrincipal() {
        ERRORES = new JLabel("");
        
        setVisible(true);
        setTitle("YouTube");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        //ver cual nos va mejor
        setLayout(new BorderLayout());
        
        //setMinimumSize(new Dimension(800, 650));
        setMinimumSize(new Dimension(992, 700));
        setSize(992, 700);
        
        F = new JLabel(new ImageIcon("background.jpg"));
        add(F);
        F.setLayout(new BorderLayout());
        
        /*
        try{
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        }
        catch(Exception e){
          e.printStackTrace();
        } 
        */
 
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        P = new JTabbedPane();
        P.setOpaque(false);
        P.addChangeListener(CAMBIO);
        
        F.add(P, BorderLayout.CENTER);

        add(ERRORES, BorderLayout.SOUTH);
        
        T = new Timer(5000, limpiar_error);
        
        //getContentPane().add(P);
    }
    
    public void anadirVista(Component component, String nombreVista, String path_icono) {
        ImageIcon I = new ImageIcon(path_icono);
        P.addTab(nombreVista, I, component);
    }
    
    public void confirmar() {
        getContentPane().add(F);
    }
    
    public void gestionErrores(String error, int color) {
        ERRORES.setText(error);
        if (color == 0) ERRORES.setForeground(Color.red);
        else ERRORES.setForeground(Color.blue);
        
        if (T.isRunning()) T.restart();
        else T.start();
    }
    
    ChangeListener CAMBIO = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            ERRORES.setText("");
            T.stop();
            
            switch (P.getSelectedIndex()) {
                case 1:
                    VistaListado VL = (VistaListado) P.getSelectedComponent();
                    VL.actualizar();
                    break;
                case 2:
                    VistaSolucion VS = (VistaSolucion) P.getSelectedComponent();
                    VS.actualizar();
                    break;
                case 5:
                    VistaAlgoritmo VA = (VistaAlgoritmo) P.getSelectedComponent();
                    VA.actualizarLista();
                    break;
            }
        }
    };    
    
    ActionListener limpiar_error = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gestionErrores("", 1);
            T.stop();
        }
    };
}
