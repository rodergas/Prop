/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.CtrlListado;
import Dominio.CtrlUsuario;
import java.awt.Component;

/**
 *
 * @author MonSB
 */
public class CtrlVistaListado {
    private CtrlListado CtrlL;
    private VistaListado VL;
    private CtrlVistaCancion CVC;
    private VistaPrincipal VP;
    
    public CtrlVistaListado(CtrlVistaCancion cvc, VistaPrincipal vp) {
        CtrlL = new CtrlListado(cvc.ConsultarCtrlCancion());
        VL = new VistaListado(this);
        VL.setOpaque(false);
        CVC = cvc;
        VP = vp;
    }
    
    public CtrlVistaCancion ConsultarCtrLVistaCancion() {
        return CVC;
    }
    
    public CtrlListado ConsultarCtrlListado() {
        return CtrlL;
    }
    
    public VistaListado vista() {
        return VL;
    }
    
    public void GestionError(String error, int color) {
        VP.gestionErrores(error, color);
    }
}
