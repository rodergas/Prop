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
public class RelacionNOT extends RelacionC {
    
    private Relacion relacion;
    
    //Constructora
    public RelacionNOT(String nombre2,Relacion a) {
        relacion = a;
        nombre = nombre2;
    }
    
    public RelacionNOT(){}
    
    public String consultarNombre(){
        return nombre;
    }
    
    private ArrayList<Cancion> conjuntoNOT(ArrayList<Cancion> A, ArrayList<Cancion> B) {
        ArrayList<Cancion> aux = new ArrayList<Cancion>();
        for (int i = 0; i < A.size(); ++i){ 
            if(B.contains(A.get(i)) == false) aux.add(A.get(i));
           }
        return aux;
    }
    
    @Override
    public ArrayList<Cancion> obtenerConjunto(CjtCancion Cc, CjtUsuario Cu) {
            ArrayList<Cancion> I = relacion.obtenerConjunto(Cc, Cu);
            return conjuntoNOT(Cc.ObtenerCanciones(), I);
    }

}
