/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;
import java.util.*;
import java.io.*;
//import java.nio.charset.Charset;

/**
 *
 */

public class GestorDeDatos {
    
    String path;
    //Lectura
    //private FileReader FL;
    private BufferedReader BL = null;
    //Escritura
    //private FileWriter FE;
    private BufferedWriter BE = null;
    
    //private ArrayList<String> Datos;
    
    private Integer posicion_dato;
    
    
    public GestorDeDatos(String directorio) {
        path = directorio;
        //Datos = new ArrayList<String>();
        posicion_dato = 0;
    }
    
    public void modificarDestino(String directorio) {
        path = directorio;
    }
    
    //Datos actuales en el Array
    /*public ArrayList<String> consultarDatos() {
        return Datos;
    }*/
    //Si abres en lectura y el fichero no existe salta excepcion
    public void abrirLectura() throws FileNotFoundException, Exception {
        if (BE != null) throw new Exception("ABIERTO EN MODO ESCRITURA");
         //FL = new FileReader(path);
         BL = new BufferedReader(new FileReader(path));
    }
    
     // si abres en escritura y el fichero no existe se crea el fichero
    public void abrirEscritura() throws FileNotFoundException, IOException, Exception{
        if (BL != null) throw new Exception("ABIERTO EN MODO LECTURA");
         //FE = new FileWriter(path);
         BE = new BufferedWriter(new FileWriter(path));
    }
    
    //cierra el fichero abierto
    public void cerrar() throws IOException {
        if (BL != null) {
            BL.close();
            BL = null;
        }
        if (BE != null) {
            BE.close();
            BE = null;
        }
        //podriamos poner el try
        //puede que no hagan falta los ifs
        //nose si depues de hacer close se ponen a null
    }
    
    
    public void escribir(String bloque) throws IOException, Exception {
        if (BL != null) throw new Exception("ABIERTO EN MODO ESCRITURA");
        BE.write(bloque);
        BE.newLine();
    }
    
    public String leer() throws IOException, Exception {
        if (BE != null) throw new Exception("ABIERTO EN MODO LECTURA");
        return BL.readLine();
    }
    
    //lee la lineas del fichero y las mete en el array
    /*
    public void leer() throws IOException, Exception {
        if (BE != null) throw new Exception("NO PUEDES LEER EN MODO ESCRITURA");
        String tmp = BL.readLine();
        //if (tmp == null) throw new Excep
        while (tmp != null) {
            Datos.add(tmp);
            tmp = BL.readLine();
        }
    }
    */
    
    /*
    //Escribe el array en el fichero
    public void escribir() throws IOException, Exception {
        if (BE != null) throw new Exception("NO PUEDES ESCRIBIR EN MODO LECTURA");
        for (int i = 0; i < Datos.size(); ++i){
            BE.write(Datos.get(i));
            BE.newLine();
        }
    }
    
    
    //Carga datos en el Array
    //se envian los datos en bloques de 100 elementos es decir una 
    //linea con 100 elementos separados por "\n"
    public void enviarDatos(ArrayList<String> CE) throws Exception {
        if (CE.size() > 100) throw new Exception("OVERFLOW");
        if (CE.size() == 0) throw new Exception("NO HAY DATOS PARA ENVIAR");
        String M = "/";//PATILLADA
        for (int i = 0; i < CE.size(); ++i) M += CE.get(i) + "/";
        Datos.add(M); 
    }

    //Leemos la linea y la convertimos en array
    public ArrayList<String> recibirDatos() throws Exception {
        if (Datos.isEmpty() || posicion_dato >= Datos.size()) throw new Exception("NO HAY DATOS");
        if (Datos.get(posicion_dato).length() == 0) throw new Exception("NO HAY DATOS PARA RECIBIR");
        
        ArrayList<String> CR = new ArrayList<String>();
        String S = Datos.get(posicion_dato);
        ++posicion_dato;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) != '/') {
                int j = i;
                while (i < S.length() && S.charAt(i) != '/') ++i;
                CR.add(S.substring(j, i));
            }
        }
        return CR;
    }
    */
}    
