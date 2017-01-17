/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.CtrlCancion;
import Dominio.CtrlSolucion;
import java.awt.Component;

/**
 *
 * @author MonSB
 */
public class CtrlVistaSolucion {
    private CtrlSolucion CtrlS;
    private VistaSolucion VS;
    private VistaPrincipal VP;
    
    public CtrlVistaSolucion(CtrlVistaCancion CC, VistaPrincipal vp) {
        CtrlS = new CtrlSolucion(CC.ConsultarCtrlCancion());
        VS = new VistaSolucion(this);
        VS.setOpaque(false);
        VP = vp;
    }
    
    public CtrlSolucion ConsultarCtrlSolucion() {
        return CtrlS;
    }
    
    public VistaSolucion vista() {
        return VS;
    }
    
    public void GestionError(String error, int color) {
        VP.gestionErrores(error, color);
    }
}
