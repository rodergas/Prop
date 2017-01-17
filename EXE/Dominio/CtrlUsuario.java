/*S
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.GestorDeDatos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 *
 */
public class CtrlUsuario {
    private CjtUsuario cjtu;
    private CtrlListado ctrll;
    private CtrlCancion ctrlc;
    private CtrlSolucion ctrls;
    public CtrlUsuario(){}
    
    public CtrlUsuario(CtrlListado ctrl, CtrlCancion c,CtrlSolucion s){
        cjtu = new CjtUsuario();
        ctrll = ctrl;
        ctrlc = c;
        ctrls = s;
    }
    
    public CtrlCancion CtrlConsultarCtrlCanciones() {
        return ctrlc;
    }
    
    public CtrlSolucion CtrlConsultarCtrlSoluciones() {
        return ctrls;
    }
   
    public CjtUsuario CtrlObtenerConjunto(){
        return cjtu;
    }
    
    public void CtrlEliminarListado(String nombreU, String nombre){
        ctrll.CtrleliminarListado(nombre);
        cjtu.eliminarListado(nombreU, nombre);
    }

    public void CtrlCrearListadoManual(String nombreU,String nombre){
        ctrll.CtrlCrearListadoManual(nombre);
        Listado l = ctrll.CtrlconsultarListado(nombre);
        cjtu.anadirListadoUsuario(nombreU,l);
    }
    
    public void CtrlCrearListadoRandom(String nombreU, String nombre,int n){
        ctrll.CtrlCrearListadoRandom(nombre,n);
        Listado l = ctrll.CtrlconsultarListado(nombre);
        cjtu.anadirListadoUsuario(nombreU, l);
    }
    
    public void CtrlCrearListadoAlgoritmo(String nombreU, String nombreSolucion,String nombreListado){
        Solucion s = ctrls.CtrlConsultarSolucion(nombreSolucion);
        for(int i = 0; i < s.obtenerComunidades().size();++i){
            String nombreFinal = nombreListado + i;
            ctrll.CtrlCrearListadoManual(nombreFinal);
            Listado l = ctrll.CtrlconsultarListado(nombreFinal);
            cjtu.anadirListado(nombreU,s.obtenerComunidad(i),l);     
        }
    }
    
    public Listado CtrlConsultarListadoUsuario(String nombreU, String nombreL){
        return cjtu.consultarListadoUsuario(nombreL, nombreU);
    }
    
    public CjtListado CtrlConsultarListadosUsuario(String nombreU){
        return cjtu.consultarListadosUsuario(nombreU);
    }
    
     public void CtrlInsertarCancionListado(String nombreU, String nombreListado, String nombreCancion){
         Cancion c = ctrlc.CtrlconsultarCancion(nombreCancion);
         cjtu.anadirCancionListadoUsuario(nombreU, nombreListado, c);
    }
    public void CtrlEliminarCancionListado(String nombreU, String nombreListado, String nombreCancion){
        ctrll.CtrleliminarCancionListado(nombreListado, nombreCancion);
         cjtu.eliminarCancionListadoUsuario(nombreU, nombreListado, nombreCancion);
    }
    
    public void CtrlanadirUsuario (String name, String nationality, Integer age){
        Usuario u = new Usuario(name, nationality, age);
        cjtu.anadirUsuario(u);
    }
    
    public void CtrleliminarUsuario(String nombre) {
        Usuario u = cjtu.consultarUsuario(nombre);
        for (String name : u.consultaListados().Nombres()) ctrll.CtrleliminarListado(name);
        cjtu.eliminarUsuario(nombre);
    }
    
    public Usuario CtrlConsultarUsuario(String nombre) {
        return cjtu.consultarUsuario(nombre);
    }
    
    public void CtrlmodNombre (String nombre, String nombre_nuevo){
        cjtu.modNombre(nombre, nombre_nuevo);
    }
    
    public void CtrlmodEdad (String nombre, Integer edad_nueva){
        cjtu.modEdad(nombre, edad_nueva);
    }
    
    public void CtrlmodNacionalidad (String nombre, String nacionalidad_nueva){
        cjtu.modNacionalidad(nombre, nacionalidad_nueva);
    }
    
    /*
    public Boolean CtrlexisteUsuario (String nombre){
        return cjtu.existeUsuario(nombre);
    }
    */
    
    public LinkedList<String> CtrlNombres() {
        return cjtu.nombres();
    }
    
    public LinkedList<String> CtrlNombres(String s) {
        return cjtu.nombres(s);
    }
   
    public void guardar(String path) throws FileNotFoundException, IOException, Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirEscritura();
        Iterator<Usuario> it = cjtu.ObtenerUsuarios().iterator();
        while (it.hasNext()) {
            String s = "/";
            int i = 0;
            while (it.hasNext() && i < 100) {
                Usuario u = it.next();
                s += "*" + u.consultarNombre() + "*" + u.consultarNacionalidad() + "*" + u.consultarEdad().toString() + "*" + clavesListados(u)+ "*" + "/";
                ++i;
            }
            GD.escribir(s);
        }
        GD.cerrar();
    }
    
    public void cargar(String path) throws FileNotFoundException, Exception {
        GestorDeDatos GD = new GestorDeDatos(path);
        GD.abrirLectura();
        String s = GD.leer();
        while (s != null) { 
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != '/' && s.charAt(i) != '*') {
                    String nombre, nacionalidad, edad;
                    int j = i;
                    while (i < s.length() && s.charAt(i) != '*') ++i;
                    nombre = s.substring(j, i);
                    ++i;
                    j = i;
                    while (i < s.length() && s.charAt(i) != '*') ++i;
                    nacionalidad = s.substring(j, i);
                    ++i;
                    j = i;
                    while (i < s.length() && s.charAt(i) != '*') ++i;
                    edad = s.substring(j, i);
                    try {
                        CtrlanadirUsuario(nombre, nacionalidad, Integer.parseInt(edad));
                    }
                    catch(IllegalArgumentException IAE) {
                        while (i < s.length() && s.charAt(i) != '*') ++i;
                        System.out.println("No he podido cargar el usuario " + nombre);
                    }
                    ++i;
                    while (i < s.length() && s.charAt(i) != '*') {
                        if (s.charAt(i) != '.') {
                            j = i;
                            while (i < s.length() && s.charAt(i) != '.') ++i;
                            try {
                                CtrlConsultarUsuario(nombre).anadirListado(ctrll.CtrlconsultarListado(s.substring(j, i)));
                            }
                            catch(IllegalArgumentException IAE) {
                                System.out.println("No he podido cargar el listado " + s.substring(j, i));
                            }
                        }
                        ++i;
                    }
                }
            }
            s = GD.leer();
        }
        GD.cerrar();
    }
    
    private String clavesListados(Usuario u) {
        ArrayList<Listado> lu = new ArrayList<Listado> (u.consultarListados());
        String s = ".";
        if (lu.size() != 0) for (Listado l : lu) s += l.consultarNombre() + ".";
        else s += ".";
        return s;
    }
    
    public ArrayList<Usuario> CtrlobtenerUsuarios(){
        return cjtu.ObtenerUsuarios();
    }
}
