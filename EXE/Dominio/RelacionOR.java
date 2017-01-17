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
public class RelacionOR extends RelacionC {
    private Relacion relacionIzquierda;
    private Relacion relacionDerecha;
      
    //Constructora
    public RelacionOR(String nombre2,Relacion a, Relacion b) {
        relacionIzquierda = a;
        relacionDerecha = b;
        nombre = nombre2;
    }
    public RelacionOR(){}
    
    public String consultarNombre(){
        return nombre;
    }
    
    private ArrayList<Cancion> conjuntoOR(ArrayList<Cancion> A, ArrayList<Cancion> B) {
        for(int i = 0; i < A.size();++i){
            for(int j = 0; j < B.size();++j){
                if(A.get(i).consultarTitulo().equals(B.get(j).consultarTitulo())){
                    B.remove(A.get(i));
                }
            }
        }
        A.addAll(B);
        return A;
    }
    
    @Override
    public ArrayList<Cancion> obtenerConjunto(CjtCancion Cc, CjtUsuario Cu) {
            ArrayList<Cancion> I = relacionIzquierda.obtenerConjunto(Cc, Cu);
            ArrayList<Cancion> D = relacionDerecha.obtenerConjunto(Cc, Cu);
            ArrayList<Cancion> R = conjuntoOR(I, D);
            return R;
    }
    
    
}
