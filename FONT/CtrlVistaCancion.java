/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.CtrlCancion;
import java.awt.Component;

/**
 *
 * @author MonSB
 */
public class CtrlVistaCancion {
    private CtrlCancion CtrlC;
    private VistaCancion VC;
    private VistaPrincipal VP;
    
    public CtrlVistaCancion(VistaPrincipal vp) {
        CtrlC = new CtrlCancion();
        VC = new VistaCancion(this);
        VC.setOpaque(false);
        VP = vp;
    }
    
    public CtrlCancion ConsultarCtrlCancion() {
        return CtrlC;
    }
    
    public VistaCancion vista() {
        return VC;
    }
    
    public void GestionError(String error, int color) {
        VP.gestionErrores(error, color);
    }
    
}
