/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

//import com.sun.javafx.css.FontFace;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
public class VistaUsuario extends VistaComun{
    
    private CtrlVistaUsuario CVU;  
    private JLabel titulo;
    
    private VistaUsuarioFormulario formulario;
    
    //CANCIONES LISTADO
    private JLabel tituloBuscadorCanciones;
    private JTextField Canciones;
    private JPanel BCtitulo;
    private JList lista_canciones;
    
    private JPanel D;

    private CardLayout CD;
    private JPanel CBT;
    
    
    //BOTONES CREACION LISTADO
    private JLabel tituloCreacionListados;
    private JButton manual;
    private JButton random;
    private JButton algortimo;
    private JPanel tiposListado;
    private JPanel CL;
    
 
    //MANUAL
    private JLabel titlem;
    private JPanel NTm;
    private JLabel nombreListadom;
    private JTextField TnombreListadom;
    private JPanel Pmanual;
    private JButton aceptar_manual;
    
    //RANDOM
    private JLabel title;
    private JPanel NT;
    private JLabel nombreListado;
    private JTextField TnombreListado;
    private JPanel Prandom;
    private JPanel NCanciones;
    private JLabel numeroCanciones;
    private JTextField TnumeroCanciones;
    private JButton aceptar_random;
    
    //ALGORITMO
    private JLabel titleA;
    private JPanel NTA;
    private JLabel nombreListadoA;
    private JTextField TnombreListadoA;
    private JPanel Palgoritmo;
    private JList Soluciones;
    private JTextField buscarSolucion;
    
    private JPanel listaListado;
    
    private JButton aceptar_algorimto;
    
    public VistaUsuario(CtrlVistaUsuario cvu) {
        CVU = cvu;
        titulo = new JLabel("Usuarios");
        
        //BLOQUE COMUN
        JTF.getDocument().addDocumentListener(CAMBIO);
        JL.addListSelectionListener(SELECCION_USUARIO);
        
        add(titulo, BorderLayout.NORTH);
        Cargar.addActionListener(LOAD);
        Guardar.addActionListener(SAVE);
        Anadir.addActionListener(ADD);
        Borrar.addActionListener(DELETE);
        Consultar.addActionListener(GET);
        Modificar.addActionListener(SET); 
        
        formulario = new VistaUsuarioFormulario(CVU);
        formulario.setOpaque(false);
        formulario.setVisible(false);
        add(formulario, BorderLayout.CENTER);
        
        CD = new CardLayout();
        CBT = new JPanel(CD);
        CBT.setOpaque(false);
        
        D = new JPanel(new BorderLayout());
        D.setOpaque(false);
        tituloBuscadorCanciones = new JLabel("Canciones");
        lista_canciones = new JList();
        Canciones = new JTextField("", 10);
        Canciones.getDocument().addDocumentListener(REFRESH);
 
        BCtitulo = new JPanel(new BorderLayout());
        BCtitulo.setOpaque(false);
        BCtitulo.add(tituloBuscadorCanciones, BorderLayout.NORTH);
        BCtitulo.add(Canciones, BorderLayout.CENTER);
        
        D.add(BCtitulo, BorderLayout.NORTH);
        D.add(new JScrollPane(lista_canciones), BorderLayout.CENTER);
        
        manual  = new JButton("Manualmente");
        random = new JButton("Aleatoriamente");
        algortimo = new JButton("Solución Algoritmo");
        tituloCreacionListados = new JLabel("Crear Listado");
        manual.addActionListener(MANUAL);
        random.addActionListener(RANDOM);
        algortimo.addActionListener(ALGORITMO);
        tiposListado = new JPanel(new BorderLayout());
        tiposListado.add(manual, BorderLayout.NORTH);
        tiposListado.add(random, BorderLayout.CENTER);
        tiposListado.add(algortimo, BorderLayout.SOUTH);
        
        CL = new JPanel(new GridBagLayout());
        CL.setOpaque(false);
        
        CL.add(tituloCreacionListados, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        CL.add(tiposListado, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        CBT.add(CL, "CreacionListados");
        CBT.add(D, "SeleccionCanciones");
        
        add(CBT, BorderLayout.EAST);
        CBT.setVisible(false);

        //MANUAL
        TnombreListadom = new JTextField(10);
        nombreListadom = new JLabel("Nombre Listado");
        titlem = new JLabel("Listado Manual");
        NTm = new JPanel();
        NTm.setOpaque(false);
        NTm.add(nombreListadom);
        NTm.add(TnombreListadom);
        Pmanual =  new JPanel(new GridBagLayout());
        Pmanual.setOpaque(false);
        Pmanual.add(titlem, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        Pmanual.add(NTm, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        aceptar_manual = new JButton("Crear");
        Pmanual.add(aceptar_manual, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        //RANDOM
        TnombreListado = new JTextField(10);
        nombreListado = new JLabel("Nombre Listado");
        title = new JLabel("Listado Random");
        NT = new JPanel();
        NT.setOpaque(false);
        NT.add(nombreListado);
        NT.add(TnombreListado);
        Prandom = new JPanel(new GridBagLayout());
        Prandom.setOpaque(false);
        NCanciones = new JPanel();
        NCanciones.setOpaque(false);
        numeroCanciones = new JLabel("Número Canciones");
        TnumeroCanciones = new JTextField(10);
        TnumeroCanciones.addKeyListener(solo_numeros);
        NCanciones.add(numeroCanciones);
        NCanciones.add(TnumeroCanciones);
        aceptar_random = new JButton("Crear");
        Prandom.add(title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        Prandom.add(NT, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        Prandom.add(NCanciones, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        Prandom.add(aceptar_random, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        //ALGORITMO
        titleA = new JLabel("Listado a partir de Solución de Algoritmo");
        NTA = new JPanel();
        NTA.setOpaque(false);
        nombreListadoA = new JLabel("Nombre Listado");
        TnombreListadoA = new JTextField("", 10);
        NTA.add(nombreListadoA);
        NTA.add(TnombreListadoA);
        Palgoritmo = new JPanel(new BorderLayout());
        Palgoritmo.setOpaque(false);
        listaListado = new JPanel(new BorderLayout());
        listaListado.setOpaque(false);
        Soluciones = new JList();
        buscarSolucion = new JTextField("", 10);
        buscarSolucion.getDocument().addDocumentListener(CAMBIO_SOLUCIONES);
        listaListado.add(buscarSolucion, BorderLayout.NORTH);
        listaListado.add(new JScrollPane(Soluciones), BorderLayout.CENTER);
        listaListado.add(NTA, BorderLayout.SOUTH);
        aceptar_algorimto = new JButton("Crear");
                
        Palgoritmo.add(titleA, BorderLayout.NORTH);
        Palgoritmo.add(listaListado, BorderLayout.CENTER);
        Palgoritmo.add(aceptar_algorimto, BorderLayout.SOUTH);
        
        CBT.add(Pmanual, "manual");
        CBT.add(Prandom, "random");
        CBT.add(Palgoritmo, "algoritmo");
        
        aceptar_manual.addActionListener(CREAR_MANUAL);
        aceptar_random.addActionListener(CREAR_RANDOM);
        aceptar_algorimto.addActionListener(CREAR_ALGORITMO); 
    }
    
    
    ActionListener LOAD = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //if(e.getSource() == Cargar) {
                visible(1);
                JFileChooser F = new JFileChooser();
                F.setDialogTitle("Selecionar Base de Datos de Usuarios");
                F.setCurrentDirectory(new java.io.File("."));                
                int p = F.showOpenDialog(Cargar);
                if (p == JFileChooser.APPROVE_OPTION) {
                    String path = F.getSelectedFile().getPath();
                    try {
                        CVU.ConsultarCtrlUsuario().cargar(path);
                        LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlNombres();
                        String[] nombres = L.toArray(new String[L.size()]);
                        JL.setListData(nombres);
                    } catch (Exception ex) {
                        Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                        CVU.GestionError("No se ha podido cargar la base de datos de usuarios", 0);
                    }
                }
                desactivarCanciones();
        }
    };
    
    ActionListener SAVE = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            visible(2);
            JFileChooser F = new JFileChooser();
            F.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            F.setDialogTitle("Guardar Base de Datos de Usuarios");
            F.setCurrentDirectory(new java.io.File("."));  
            F.setAcceptAllFileFilterUsed(false);
            int p = F.showOpenDialog(Guardar);
            if (p == JFileChooser.APPROVE_OPTION) {
                String path = F.getSelectedFile().getPath();
                try {
                    CVU.ConsultarCtrlUsuario().guardar(path);
                } catch (Exception ex) {
                    Logger.getLogger(VistaCancion.class.getName()).log(Level.SEVERE, null, ex);
                     CVU.GestionError("No se ha podido cargar la base de datos de usuarios", 0);
                }
            }
            desactivarCanciones();
        }
    };
    
    ActionListener DELETE = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(5);
            if (JL.getSelectedValue() != null) {
                String s = (String)JL.getSelectedValue();
                try {
                    CVU.ConsultarCtrlUsuario().CtrleliminarUsuario(s);
                    LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlNombres();
                    String[] nombres = L.toArray(new String[L.size()]);
                    JL.setListData(nombres);
                    desactivarCanciones();
                }
                catch (IllegalArgumentException IAE) {
                     CVU.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVU.GestionError("No se ha seleccionado ningun usuario", 0);
        }
    };
    
    ActionListener ADD = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(6);
        }
    };
    
    ActionListener SET = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(7);
        }
    };
    
    ActionListener GET = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            visible(8);
        }
    };
        
    ActionListener MANUAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CD.show(CBT, "manual");
        }
    };
    
    ActionListener RANDOM = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CD.show(CBT, "random");
        }
    };
    
    ActionListener ALGORITMO = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CD.show(CBT, "algoritmo");
            LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlConsultarCtrlSoluciones().CtrlNombres();
            String[] nombres = L.toArray(new String[L.size()]);
            Soluciones.setListData(nombres);
        }
    };
    
    ActionListener CREAR_MANUAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = TnombreListadom.getText();
            if (!s.equals("")) {
                try {
                    CVU.ConsultarCtrlUsuario().CtrlCrearListadoManual(formulario.nombreUsuario(), s);
                    formulario.actualizarListadoUsuario();
                    TnombreListadom.setText("");
                }
                catch(IllegalArgumentException IAE) {
                    CVU.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVU.GestionError("No se ha dado nombre al listado", 1);
        }
    };
    
    ActionListener CREAR_RANDOM = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = TnombreListado.getText();
            int n = Integer.parseInt(TnumeroCanciones.getText());
            if (!s.equals("")) {
                try {
                    CVU.ConsultarCtrlUsuario().CtrlCrearListadoRandom(formulario.nombreUsuario(), s, n);
                    formulario.actualizarListadoUsuario();
                    TnombreListado.setText("");
                    TnumeroCanciones.setText("");
                }
                catch (IllegalArgumentException IAE) {
                    CVU.GestionError(IAE.getMessage(), 0);
                }
            }
            else CVU.GestionError("No se ha dado nombre al listado", 0);
        }
    };
    
    ActionListener CREAR_ALGORITMO = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) Soluciones.getSelectedValue();
            String nl = TnombreListadoA.getText();
            if (s != null && !nl.equals("")) {
                try {
                    CVU.ConsultarCtrlUsuario().CtrlCrearListadoAlgoritmo(formulario.nombreUsuario(), s, nl);
                    formulario.actualizarListadoUsuario();
                    TnombreListadoA.setText("");
                }
                catch(IllegalArgumentException IAE) {
                    CVU.GestionError(IAE.getMessage(), 0);
                }
            }
            else {
                if (s == null) CVU.GestionError("No se ha dado nombre al listado", 0);
                else CVU.GestionError("No se ha seleccionado ninguna solución", 0);
            }
        }
        
    };
    
    private void visible(int x) {
        switch (x) {
            case 0:
                formulario.setVisible(false);
                break;
            case 1:
                formulario.setVisible(false);
                break;
            case 2:
                formulario.setVisible(false);
                break;
            case 4:
                formulario.setVisible(false);
                break;
            case 5:
                formulario.setVisible(false);
                break;
            case 6:
                formulario.setVisible(true);
                formulario.modoAnadir();
                break;
            case 7:
                if (JL.getSelectedValue() != null) {
                    formulario.setVisible(true);
                    formulario.modoModificar((String)JL.getSelectedValue());
                }
                else CVU.GestionError("No se ha seleccionado ningun usuario", 0);
                break;
            case 8:
                if (JL.getSelectedValue() != null) {
                    formulario.setVisible(true);
                    formulario.modoConsultar((String)JL.getSelectedValue());
                }
                else CVU.GestionError("No se ha seleccionado ningun usuario", 0);
                break;
        }
    }
    
    public void activarCanciones() {
        CBT.setVisible(true);
        CD.show(CBT, "SeleccionCanciones");
        LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlConsultarCtrlCanciones().CtrlNombres();
        String[] nombres = L.toArray(new String[L.size()]);
        lista_canciones.setListData(nombres);
    }
    
    public void activarCreacionListados() {
        CBT.setVisible(true);
        CD.show(CBT, "CreacionListados");
    }
    
    public void anadirCancionListado(String nombre, String listado) {
        String s = (String) lista_canciones.getSelectedValue();
        if (s != null) {
            try {
                CVU.ConsultarCtrlUsuario().CtrlConsultarUsuario(nombre).consultaListado(listado).anadirCancion(CVU.ConsultarCtrlUsuario().CtrlConsultarCtrlCanciones().CtrlconsultarCancion(s));
            }
            catch (IllegalArgumentException IAE) {
                CVU.GestionError(IAE.getMessage(), 0);
            }
        }
        else CVU.GestionError("No se ha seleccionado ninguna canción", 0);
    }
    
    public void desactivarCanciones() {
        CBT.setVisible(false);
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
                LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlNombres(s);
                String[] nombres = L.toArray(new String[L.size()]);
                JL.setListData(nombres);
            }
            else {
                LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlNombres();
                String[] nombres = L.toArray(new String[L.size()]);
                JL.setListData(nombres);
            }
    }
    
    DocumentListener REFRESH = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            actualizar_canciones();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actualizar_canciones();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actualizar_canciones();
        }
    };
    
    public void actualizar_canciones() {
        String s = Canciones.getText();
        if (!s.equals("")) {
            LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlConsultarCtrlCanciones().CtrlNombres(s);
            String[] nombres = L.toArray(new String[L.size()]);
            lista_canciones.setListData(nombres);
        }
        else {
            LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlConsultarCtrlCanciones().CtrlNombres();
            String[] nombres = L.toArray(new String[L.size()]);
            lista_canciones.setListData(nombres);
        }
    }
    
    DocumentListener CAMBIO_SOLUCIONES = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            actualizar_soluciones();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actualizar_soluciones();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actualizar_soluciones();
        }
    };
    
    public void actualizar_soluciones() {
        String s = buscarSolucion.getText();
        if(!s.equals("")) {
            LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlConsultarCtrlSoluciones().CtrlNombres(s);
            String[] nombres = L.toArray(new String[L.size()]);
            Soluciones.setListData(nombres);
        }
        else {
            LinkedList<String> L = CVU.ConsultarCtrlUsuario().CtrlConsultarCtrlSoluciones().CtrlNombres();
            String[] nombres = L.toArray(new String[L.size()]);
            Soluciones.setListData(nombres);
        }
    }
    
    KeyAdapter solo_numeros = new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                e.consume();
            }
        }
    };
    
    
    ListSelectionListener SELECCION_USUARIO = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            formulario.setVisible(false);
            CBT.setVisible(false);
        }
    };
}
