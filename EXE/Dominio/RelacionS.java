/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 *
 */
public class RelacionS<T> extends Relacion{
     
    String criterio;
    private T valor;
    
    //Constructora
    public RelacionS(String nombre2,String criterio2, T valor2) {
        criterio = criterio2;
        valor = valor2;
        nombre = nombre2;
    }

    public RelacionS() {}

    public String consultarNombre(){
        return nombre;
    }
    
    public ArrayList<Cancion> obtenerConjunto(CjtCancion Cc, CjtUsuario Cu) {
        ArrayList<Cancion> C = Cc.ObtenerCanciones();
        ArrayList<Cancion> R = new ArrayList<Cancion> ();
        switch (criterio) {
            case "AUTOR":
                for (int i = 0; i < C.size(); ++i) {
                    if (C.get(i).consultarAutor().equals(valor)) R.add(C.get(i));
                }
                break;
            case "DURACION":
                for (int i = 0; i < C.size(); ++i) {
                    if (C.get(i).consultarDuracion().equals(valor)) R.add(C.get(i));
                }
                break;
            case "NUMERO_REPRODUCCIONES":
                for (int i = 0; i < C.size(); ++i) {
                    if (C.get(i).consultarReproducciones().equals(valor)) R.add(C.get(i));
                }
                break;
            case "GENERO":
                for (int i = 0; i < C.size(); ++i) {
                    if (C.get(i).consultarGenero().equals(valor)) R.add(C.get(i));
                }
                break;
            case "USUARIO":
                ArrayList<Listado> CL = Cu.consultarUsuario((String) valor).consultarListados();
                for (int i = 0; i < CL.size(); ++i) {
                    ArrayList<Cancion> l = CL.get(i).consultarCanciones();
                    for (int j = 0; j < l.size(); ++j) {
                        if (!R.contains(l.get(j))) R.add(l.get(j));
                    }                 
                }
                break;
            case "NACIONALIDAD":
                ArrayList<Usuario> U = Cu.ObtenerUsuarios();
                for (int i = 0; i < U.size(); ++i) {
                    if (U.get(i).consultarNacionalidad().equals(valor)) {  
                        ArrayList<Listado> CLN = U.get(i).consultarListados();
                        for (int j = 0; j < CLN.size(); ++j) {
                            ArrayList<Cancion> l = CLN.get(j).consultarCanciones();
                            for (int k = 0; k < l.size(); ++k) {
                                if (!R.contains(l.get(k))) R.add(l.get(k));
                            }                 
                        } 
                    }
                    
                }
                break;
            case "EDAD":
                ArrayList<Usuario> U2 = Cu.ObtenerUsuarios();
                for (int i = 0; i < U2.size(); ++i) {
                    if (U2.get(i).consultarEdad().equals(valor)) {  
                        ArrayList<Listado> CLN = U2.get(i).consultarListados();
                        for (int j = 0; j < CLN.size(); ++j) {
                            ArrayList<Cancion> l = CLN.get(j).consultarCanciones();
                            for (int k = 0; k < l.size(); ++k) {
                                if (!R.contains(l.get(k))) R.add(l.get(k));
                            }                 
                        }
                    }
                }
                break;
        }
        return R;
    }  
}
