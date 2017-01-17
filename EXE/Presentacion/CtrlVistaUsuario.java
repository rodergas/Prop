/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.CtrlCancion;
import Dominio.CtrlUsuario;
import java.awt.Component;

/**
 *
 * @author MonSB
 */
public class CtrlVistaUsuario {
    private CtrlUsuario CtrlU;
    private VistaUsuario VU;
    private VistaPrincipal VP;
    ///
    public CtrlVistaUsuario(CtrlVistaListado CVL, CtrlVistaCancion CVC, CtrlVistaSolucion CVS, VistaPrincipal vp) {
        CtrlU = new CtrlUsuario(CVL.ConsultarCtrlListado(), CVC.ConsultarCtrlCancion(), CVS.ConsultarCtrlSolucion());
        VU = new VistaUsuario(this);
        VU.setOpaque(false);
        VP = vp;
    }
    
    public CtrlUsuario ConsultarCtrlUsuario() {
        return CtrlU;
    }
    
    public VistaUsuario consultarVista() {
        return VU;
    }
    
    public VistaUsuario vista() {
        return VU;
    }
    
    public void GestionError(String error, int color) {
        VP.gestionErrores(error, color);
    }
}
