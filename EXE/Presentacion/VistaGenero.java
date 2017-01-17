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
import java.util.LinkedList;
import javax.swing.JButton;
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
public class VistaGenero extends JPanel {
    
        CtrlVistaCancion CC;
        
        JLabel titulo_generos;
        JList generos;
        JTextField buscador_generos;
        JPanel LISTA_GENERO;
        JTextField anadir_genero;
        JButton anadir;
        JPanel AnadirGeneros;
        
        JPanel PrincipalGeneros;
        
        public VistaGenero(CtrlVistaCancion cc) {
            CC = cc;               
            
            titulo_generos = new JLabel("Géneros");

            buscador_generos = new JTextField("", 15);
            buscador_generos.getDocument().addDocumentListener(CAMBIO);
            
            generos = new JList();
            LISTA_GENERO = new JPanel(new BorderLayout());
            LISTA_GENERO.setOpaque(false);
            LISTA_GENERO.add(buscador_generos, BorderLayout.NORTH);
            LISTA_GENERO.add(new JScrollPane(generos), BorderLayout.CENTER);
            
            anadir_genero = new JTextField(10);
            anadir = new JButton("Añadir");
            anadir.addActionListener(ADD);
            AnadirGeneros = new JPanel();
            AnadirGeneros.setOpaque(false);
            AnadirGeneros.add(anadir_genero);
            AnadirGeneros.add(anadir);
            
            PrincipalGeneros = new JPanel(new GridBagLayout());
            PrincipalGeneros.setOpaque(false);
            
            //Paneles
            setLayout(new FlowLayout());
            
            PrincipalGeneros.add(titulo_generos, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
            PrincipalGeneros.add(LISTA_GENERO, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
            PrincipalGeneros.add(AnadirGeneros, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
            add(PrincipalGeneros);
        }
        
        public void actualizarLista() {
            generos.setListData(CC.ConsultarCtrlCancion().CtrlobtenerGeneros().toArray());
        }
        
        ActionListener ADD = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = anadir_genero.getText();
                if (!s.equals("")) {
                    try {
                        CC.ConsultarCtrlCancion().CtrlanadirGenero(s);
                        actualizarLista();
                        anadir_genero.setText("");
                    }
                    catch(IllegalArgumentException IAE) {
                        CC.GestionError(IAE.getMessage(), 0);
                    }
                }
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
        
        private void actualizar() {
             String s = buscador_generos.getText();
                if(s.equals("")) {
                    actualizarLista();
                }
                else {
                    generos.setListData(CC.ConsultarCtrlCancion().CtrlNombresGeneros(s).toArray());
                }
        }
        
}
