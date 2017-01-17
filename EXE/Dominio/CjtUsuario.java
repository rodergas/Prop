/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 *
 *
 */
public class CjtUsuario {
    //Atributos
    private TST<Usuario> usuarios;
    
    //Constructoras
    public CjtUsuario(){
        usuarios = new TST<Usuario>();
    }
    
    //Consultoras
    public Listado consultarListadoUsuario(String nombreL, String nombreU){
        Usuario u = consultarUsuario(nombreU);
        return u.consultaListado(nombreL);
    }
    
    public CjtListado consultarListadosUsuario(String nombreU){
        Usuario u = consultarUsuario(nombreU);
        return u.consultaListados();
    }
    
    public Usuario consultarUsuario (String nombre) throws IllegalArgumentException {
        Usuario u = usuarios.consultarElemento(nombre);
        if (u == null) throw new IllegalArgumentException("No existe un usuario con ese nombre");
        else return u;
    }
    
    /*
    public Boolean existeUsuario(String nombre) {
        boolean trobat = false;
        Usuario u = usuarios.consultarElemento(nombre);
        if (u != null) trobat = true;
        return trobat;
    }
    */
    
    //Modificadoras
    public void anadirCancionListadoUsuario(String nombreU,String nombreListado, Cancion c){
        Usuario u = consultarUsuario(nombreU);
        u.anadirCancionListado(nombreListado, c);
    }
    
    public void eliminarCancionListadoUsuario(String nombreU,String nombreListado, String nombreC){
        Usuario u = consultarUsuario(nombreU);
        u.eliminarCancionListado(nombreListado, nombreC);
    }
    
    public void eliminarListado(String nombreU,String nombreListado){
        Usuario u = consultarUsuario(nombreU);
        u.borrarListado(nombreListado);
    }
    
    public void anadirListadoUsuario(String nombreU, Listado l){
        Usuario u = consultarUsuario(nombreU);
        u.anadirListado(l);
    }

    public void anadirUsuario(Usuario u) throws IllegalArgumentException {
        try { usuarios.anadirNodo(u.consultarNombre(), u); }
        catch (IllegalArgumentException IAE) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre"); 
        }
    }
    
    public void eliminarUsuario(String nombre) throws IllegalArgumentException {
        try { usuarios.eliminarElemento(nombre); }
        catch (IllegalArgumentException IAE) { 
            throw new IllegalArgumentException("No existe un usuario con ese nombre"); 
        }
    }

    public void modNombre (String nombre, String nombre_nuevo){
        Usuario u = usuarios.consultarElemento(nombre_nuevo);
        if (u != null) throw new IllegalArgumentException("Ya existe un usuario con el nuevo nombre introducido");
        else {
            u = usuarios.consultarElemento(nombre);
            if (u == null) throw new IllegalArgumentException("No existe el usuario que se desea modificar");
            else u.modificarNombre(nombre_nuevo);
        }
    }
    
    public void modEdad (String nombre, Integer edad_nueva){
        Usuario u = usuarios.consultarElemento(nombre);
        if (u == null) throw new IllegalArgumentException("No existe el usuario que se desea modificar");
        else u.modificarEdad(edad_nueva);
    }
    
    public void modNacionalidad (String nombre, String nacionalidad_nueva){
        Usuario u = usuarios.consultarElemento(nombre);
        if (u == null) throw new IllegalArgumentException("No existe el usuario que se desea modificar");
        else u.modificarNacionalidad(nacionalidad_nueva);
    }
    
    public ArrayList<Usuario> ObtenerUsuarios(){
        LinkedList<String> usuario =  (LinkedList<String>) usuarios.ObtenerClaves();
        ArrayList<Usuario> resultado = new ArrayList<>();
        for(int i = 0; i < usuario.size(); ++i){
            Usuario u = usuarios.consultarElemento(usuario.get(i));
            resultado.add(u);
        }
        return resultado;
    }
    
    public void anadirListado(String nombreU, Comunidad c,Listado l){
        TST<Cancion> t = new TST();
        for(int i = 0; i < c.consultarTamano(); ++i){
            Cancion cc = (Cancion) c.consultarNodo(i);
            t.anadirNodo(cc.consultarTitulo(), cc);
        }
        Usuario u = usuarios.consultarElemento(nombreU);
        l.modificarTST(t);
        u.anadirListado(l);
    }
    
    public LinkedList<String> nombres() {
        return (LinkedList<String>) usuarios.ObtenerClaves();
    }
    
    public LinkedList<String> nombres(String s) {
        return (LinkedList<String>) usuarios.Prefijo(s);
    }
}