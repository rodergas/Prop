/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.Calendar;
/**
 *
 *
 */
public class Reproduccion {
    //Atributos
    
    private String fecha;
    private String hora;
    private String nombre;
    
    //Constructora
    
    public Reproduccion(String nom){
        Calendar date = Calendar.getInstance();
        String ano = Integer.toString(date.get(Calendar.YEAR));
        String mes = Integer.toString(date.get(Calendar.MONTH) + 1);
        String dia = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        String hora2 = Integer.toString(date.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(date.get(Calendar.MINUTE));
        String segundo = Integer.toString(date.get(Calendar.SECOND));
        fecha = dia + "/" + mes + "/" + ano;
        hora = hora2 + ":" + minuto + ":" + segundo;
        nombre = nom;
    }
    
    public Reproduccion(String f, String h, String n) {
        fecha = f;
        hora = h;
        nombre = n;
    }
    
    public Reproduccion() {}
    
    // Consultoras
    
    public String consultarFecha() {
        return fecha;
    }
    
    public String consultarHora() {
        return hora;
    }
    
    public String consutlarNombre() {
        return nombre;
    }
    
    //Modificadora
    
    public void modificarReproduccion() {
        Calendar date = Calendar.getInstance();
        String ano = Integer.toString(date.get(Calendar.YEAR));
        String mes = Integer.toString(date.get(Calendar.MONTH) + 1);
        String dia = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        String hora2 = Integer.toString(date.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(date.get(Calendar.MINUTE));
        String segundo = Integer.toString(date.get(Calendar.SECOND));
        fecha = dia + "/" + mes + "/" + ano;
        hora = hora2 + ":" + minuto + ":" + segundo;
    }
}
