/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.CtrlRelacion;
import Dominio.CtrlSolucion;
import java.awt.Component;

/**
 *
 * @author MonSB
 */
public class CtrlVistaRelacion {
    private CtrlRelacion CtrlR;
    private VistaRelacion VR;
    private VistaPrincipal VP;
    
    public CtrlVistaRelacion(CtrlVistaCancion CVC, CtrlVistaUsuario CVU, VistaPrincipal vp) {
        CtrlR = new CtrlRelacion(CVC.ConsultarCtrlCancion(), CVU.ConsultarCtrlUsuario());
        VR = new VistaRelacion(this);
        VR.setOpaque(false);
        VP = vp;
    }
    
    public CtrlRelacion ConsultarCtrlRelacion() {
        return CtrlR;
    }
    
    public VistaRelacion vista() {
        return VR;
    }
    
    public void GestionError(String error, int color) {
        VP.gestionErrores(error, color);
    }
}
