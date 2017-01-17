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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
public class VistaUsuarioFormulario extends JPanel {
    
    private CtrlVistaUsuario CU;
    
    //FORMULARIO
    private JLabel titulo_modo;
    private JLabel nombre;
    private JLabel nacionalidad;
    private JLabel edad;
    
    private JLabel titulo_listados;
    private JLabel titulo_canciones;
    
    private JTextField Tnombre;
    private JTextField Tnacionalidad;
    private JTextField Tedad;
    private JTextField Tlistados;
    private JTextField Tcanciones;
    
    private JPanel FormularioUsuario;
    private JPanel ListaListdos;
    private JPanel ListaCanciones;
    private JPanel LISTADOS;
    private JPanel CANCIONES;
    private JPanel vistaListados;
    private JPanel botonesListado;
    private JPanel botonesCanciones;
    private JPanel panelListadosUsuario;
    private JPanel panelCancionesListado;
    private JPanel panelPrincipal;
    private JPanel ACEPTAR;
    
    private JButton aceptar_anadir;
    private JButton aceptar_modificar;
    private JButton ver;
    private JButton crearListado;
    private JButton eliminarListado;
    private JButton anadirCancion;
    private JButton eliminarCancion;
    
    
    private JList Listados;
    private JList Canciones;
    
    
       
    public VistaUsuarioFormulario(CtrlVistaUsuario cu) {
        CU = cu;
        
        //USUARIO
        titulo_modo = new JLabel();
        
        nombre = new JLabel("Nombre  ");
        nacionalidad = new JLabel("Nacionalidad  ");
        edad = new JLabel("Edad  ");

        Tnombre = new JTextField(20);
        Tnacionalidad = new JTextField(20);
        Tedad = new JTextField(20);
        Tedad.addKeyListener(solo_numeros);
        
        aceptar_anadir = new JButton("Añadir Usuario");
        aceptar_anadir.setVisible(false);
        aceptar_modificar = new JButton("Modificar Usuario");
        aceptar_modificar.setVisible(false);
        ACEPTAR = new JPanel();
        ACEPTAR.setOpaque(false);
        ACEPTAR.add(aceptar_anadir);
        ACEPTAR.add(aceptar_modificar);
        
        FormularioUsuario = new JPanel(new GridBagLayout());
        FormularioUsuario.setOpaque(false);
        
        FormularioUsuario.add(titulo_modo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        FormularioUsuario.add(nombre, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        FormularioUsuario.add(nacionalidad, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        FormularioUsuario.add(edad, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        FormularioUsuario.add(Tnombre, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        FormularioUsuario.add(Tnacionalidad, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        FormularioUsuario.add(Tedad, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        FormularioUsuario.add(ACEPTAR, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        //LISTADOS USUARIO
        titulo_listados = new JLabel("Listados");
        Listados = new JList();
        Listados.addListSelectionListener(SELECCION_CANCIONES_USUARIO);
        Tlistados = new JTextField("", 10);
        Tlistados.getDocument().addDocumentListener(REFRESH_LISTADO_USUARIO);
        
        LISTADOS = new JPanel(new BorderLayout());
        LISTADOS.setOpaque(false);
        LISTADOS.add(Tlistados, BorderLayout.NORTH);
        LISTADOS.add(new JScrollPane(Listados), BorderLayout.CENTER);

        ListaListdos = new JPanel(new GridBagLayout());
        ListaListdos.setOpaque(false);
        ListaListdos.add(titulo_listados, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        ListaListdos.add(LISTADOS, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        ver = new JButton("Ver Listado");
        crearListado = new JButton("Crear Listado");
        eliminarListado = new JButton("Eliminar Listado");
        
        botonesListado = new JPanel(new BorderLayout());
        botonesListado.add(ver, BorderLayout.NORTH);
        botonesListado.add(crearListado, BorderLayout.CENTER);
        botonesListado.add(eliminarListado, BorderLayout.SOUTH);
        
        panelListadosUsuario = new JPanel(new BorderLayout());
        panelListadosUsuario.setOpaque(false);
        panelListadosUsuario.add(ListaListdos, BorderLayout.CENTER);
        panelListadosUsuario.add(botonesListado, BorderLayout.SOUTH);
        
        ver.addActionListener(VER);
        crearListado.addActionListener(CREARLISTADO);
        eliminarListado.addActionListener(ELIMINARLISTADO);
        
        //CANCIONES LISTADO USUARIO
        titulo_canciones = new JLabel("Canciones");
        Canciones = new JList();
        Tcanciones = new JTextField("", 10);
        Tcanciones.getDocument().addDocumentListener(REFRESH_CANCIONES_LISTADO_USUARIO);
        
        CANCIONES = new JPanel(new BorderLayout());
        CANCIONES.setOpaque(false);
        CANCIONES.add(Tcanciones, BorderLayout.NORTH);
        CANCIONES.add(new JScrollPane(Canciones), BorderLayout.CENTER);
        
        ListaCanciones = new JPanel(new GridBagLayout());
        ListaCanciones.setOpaque(false);
        ListaCanciones.add(titulo_canciones, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        ListaCanciones.add(CANCIONES, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        anadirCancion = new JButton("Añadir Canción");
        eliminarCancion = new JButton("Eliminar Canción");
        
        botonesCanciones = new JPanel(new BorderLayout());
        botonesCanciones.add(anadirCancion, BorderLayout.NORTH);
        botonesCanciones.add(eliminarCancion, BorderLayout.CENTER);
        
        panelCancionesListado = new JPanel(new BorderLayout());
        panelCancionesListado.setOpaque(false);
        panelCancionesListado.add(ListaCanciones, BorderLayout.CENTER);
        panelCancionesListado.add(botonesCanciones, BorderLayout.SOUTH);

        anadirCancion.addActionListener(ANADIRCANCION);
        eliminarCancion.addActionListener(ELIMINARCANCION);
        
        //PANEL PRINCIPAL
        vistaListados  = new JPanel(new BorderLayout());
        vistaListados.setOpaque(false);
        vistaListados.add(panelListadosUsuario, BorderLayout.WEST);
        vistaListados.add(panelCancionesListado, BorderLayout.EAST);
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setOpaque(false);
        panelPrincipal.add(FormularioUsuario, BorderLayout.CENTER);
        panelPrincipal.add(vistaListados, BorderLayout.SOUTH);
        
        setLayout(new FlowLayout());
        add(panelPrincipal);
    }
    
    public void modoAnadir() {
        titulo_modo.setText("Añadir Usuario");
        Tnombre.setText("");
        Tnacionalidad.setText("");
        Tedad.setText("");
        
        vistaListados.setVisible(false);
        ver.setVisible(false);
  
        aceptar_anadir.setVisible(true);
        aceptar_modificar.setVisible(false);
        aceptar_anadir.addActionListener(anadir);
        
        Tnombre.setEditable(true);
        Tnacionalidad.setEditable(true);
        Tedad.setEditable(true);
        
        crearListado.setVisible(false);
        eliminarListado.setVisible(false);
        anadirCancion.setVisible(false);
        eliminarCancion.setVisible(false);
        CU.consultarVista().desactivarCanciones();
    }
    
    public void modoModificar(String s) {
        titulo_modo.setText("Modificar Usuario");
        
        aceptar_modificar.setVisible(true);
        aceptar_anadir.setVisible(false);
        aceptar_modificar.addActionListener(modificar);
        
        Tnombre.setEditable(false);
        Tnacionalidad.setEditable(true);
        Tedad.setEditable(true);

        vistaListados.setVisible(true);
        ListaCanciones.setVisible(false);
        ver.setVisible(true);
        
        Tnombre.setText(CU.ConsultarCtrlUsuario().CtrlConsultarUsuario(s).consultarNombre());
        Tnacionalidad.setText(CU.ConsultarCtrlUsuario().CtrlConsultarUsuario(s).consultarNacionalidad());
        Tedad.setText(CU.ConsultarCtrlUsuario().CtrlConsultarUsuario(s).consultarEdad().toString());
        
        LinkedList<String> L = CU.ConsultarCtrlUsuario().CtrlConsultarListadosUsuario(Tnombre.getText()).Nombres();
        String[] nombres = L.toArray(new String[L.size()]);
        Listados.setListData(nombres);
        
        crearListado.setVisible(true);
        eliminarListado.setVisible(true);
        anadirCancion.setVisible(false);
        eliminarCancion.setVisible(false);
        
        CU.consultarVista().desactivarCanciones();
    }
    
    public void modoConsultar(String s) {
        titulo_modo.setText("Consultar Usuario");
        aceptar_anadir.setVisible(false);
        aceptar_modificar.setVisible(false);
        Tnombre.setEditable(false);
        Tnacionalidad.setEditable(false);
        Tedad.setEditable(false);
        
        vistaListados.setVisible(true);
        ListaCanciones.setVisible(false);
        ver.setVisible(true);
        
        Tnombre.setText(CU.ConsultarCtrlUsuario().CtrlConsultarUsuario(s).consultarNombre());
        Tnacionalidad.setText(CU.ConsultarCtrlUsuario().CtrlConsultarUsuario(s).consultarNacionalidad());
        Tedad.setText(CU.ConsultarCtrlUsuario().CtrlConsultarUsuario(s).consultarEdad().toString()); 
        
        LinkedList<String> L = CU.ConsultarCtrlUsuario().CtrlConsultarListadosUsuario(Tnombre.getText()).Nombres();
        String[] nombres = L.toArray(new String[L.size()]);
        Listados.setListData(nombres);
        
        crearListado.setVisible(false);
        eliminarListado.setVisible(false);
        anadirCancion.setVisible(false);
        eliminarCancion.setVisible(false);
        CU.consultarVista().desactivarCanciones();
    }    
    
    ActionListener anadir = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] at = {
                Tnombre.getText(),
                Tnacionalidad.getText(),
                Tedad.getText(),
            };
            if (!at[0].equals("") && !at[1].equals("") && !at[2].equals("")) {
                try {
                    CU.ConsultarCtrlUsuario().CtrlanadirUsuario(at[0],  at[1], Integer.parseInt(at[2]));
                    Tnombre.setText("");
                    Tnacionalidad.setText("");
                    Tedad.setText("");
                    CU.consultarVista().actualizar();
                }
                catch(IllegalArgumentException IAE) {
                    CU.GestionError(IAE.getMessage(), 0);
                }
            }
            else CU.GestionError("No se han llenado los campos del usuario", 0);
        }
    };
    
    ActionListener modificar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CU.ConsultarCtrlUsuario().CtrlmodNacionalidad(Tnombre.getText(), Tnacionalidad.getText());
                CU.ConsultarCtrlUsuario().CtrlmodEdad(Tnombre.getText(), Integer.parseInt(Tedad.getText()));
            }
            catch(IllegalArgumentException IAE) {
                CU.GestionError(IAE.getMessage(), 0);
            }
        }
    };
    
    ActionListener VER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) Listados.getSelectedValue();
            if (s != null) {
                if (!Tnombre.isEditable() && Tnacionalidad.isEditable()) {
                    CU.consultarVista().activarCanciones();
                    anadirCancion.setVisible(true);
                    eliminarCancion.setVisible(true);
                }
                ListaCanciones.setVisible(true);
                actualizarCancionesListadoUsuario();
            }
            else CU.GestionError("No se ha seleccionado ningun listado", 0);
        }
    };
      
    ActionListener ANADIRCANCION = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = Tnombre.getText();
            String listado = (String) Listados.getSelectedValue();
            if (Listados.getSelectedValue() != null && !nombre.equals("")) {
                CU.consultarVista().anadirCancionListado(nombre, listado);
                actualizarCancionesListadoUsuario();
            }
            else CU.GestionError("El listado y la canción tienen que estar seleccionados", 0);
        }
    };
    
    ActionListener ELIMINARCANCION = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) Canciones.getSelectedValue();
            if (Canciones.getSelectedValue() != null) {
                try {
                    CU.ConsultarCtrlUsuario().CtrlConsultarListadoUsuario(Tnombre.getText(), (String)Listados.getSelectedValue()).eliminarCancion(s);
                    actualizarCancionesListadoUsuario();
                }
                catch (IllegalArgumentException IAE) {
                    CU.GestionError(IAE.getMessage(), 0);
                }
            }
            else CU.GestionError("No se ha seleccionado ninguna canción", 0);
        }
    };
    
    ActionListener ELIMINARLISTADO = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) Listados.getSelectedValue();
            if (s != null) {
                try {
                    CU.ConsultarCtrlUsuario().CtrlEliminarListado(Tnombre.getText(), s);
                    ListaCanciones.setVisible(false);
                    anadirCancion.setVisible(false);
                    eliminarCancion.setVisible(false);
                    actualizarListadoUsuario();
                }
                catch (IllegalArgumentException IAE) {
                    CU.GestionError(IAE.getMessage(), 0);
                }
            }
            else CU.GestionError("No se ha seleccionado ningun listado", 0);
        }
    };
    
    ActionListener CREARLISTADO = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CU.consultarVista().activarCreacionListados();
            anadirCancion.setVisible(false);
            eliminarCancion.setVisible(false);
            ListaCanciones.setVisible(false);
        }
    };
    
    public String nombreUsuario() {
        return Tnombre.getText();
    }
    
    DocumentListener REFRESH_LISTADO_USUARIO = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            actualizarListadoUsuario();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actualizarListadoUsuario();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actualizarListadoUsuario();
        }
    };
    
    public void actualizarListadoUsuario() {
        String s = Tlistados.getText();
        if (!s.equals("")) {
            LinkedList<String> L = CU.ConsultarCtrlUsuario().CtrlConsultarListadosUsuario(Tnombre.getText()).Nombres(s);
            String[] nombres = L.toArray(new String[L.size()]);
            Listados.setListData(nombres);
        }
        else {
            LinkedList<String> L = CU.ConsultarCtrlUsuario().CtrlConsultarListadosUsuario(Tnombre.getText()).Nombres();
            String[] nombres = L.toArray(new String[L.size()]);
            Listados.setListData(nombres);
        }
    }
    
    DocumentListener REFRESH_CANCIONES_LISTADO_USUARIO = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            actualizarCancionesListadoUsuario();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actualizarCancionesListadoUsuario();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actualizarCancionesListadoUsuario();
        }
    };
    
    public void actualizarCancionesListadoUsuario() {
        ListaCanciones.setVisible(true);
        String s = Tcanciones.getText();
        if (!s.equals("")) {
            LinkedList<String> L = CU.ConsultarCtrlUsuario().CtrlConsultarListadoUsuario(Tnombre.getText(), (String)Listados.getSelectedValue()).consultarNombres(s);
            String[] nombres = L.toArray(new String[L.size()]);
            Canciones.setListData(nombres);
        }
        else {
            LinkedList<String> L = CU.ConsultarCtrlUsuario().CtrlConsultarListadoUsuario(Tnombre.getText(), (String)Listados.getSelectedValue()).consultarNombres();
            String[] nombres = L.toArray(new String[L.size()]);
            Canciones.setListData(nombres);
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
    
    ListSelectionListener SELECCION_CANCIONES_USUARIO = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListaCanciones.setVisible(false);
            anadirCancion.setVisible(false);
            eliminarCancion.setVisible(false);
            CU.consultarVista().desactivarCanciones();
        }
    };
}
