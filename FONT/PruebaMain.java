/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import Persistencia.*;
import Dominio.*;

/**
 *
 * @author MonSB
 */
public class PruebaMain {
    public static void main(String[] args) {                
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                CtrlPrincipal CP = new CtrlPrincipal();
            }
        });
    }
}
