/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.CtrlAlgoritmo;
import Dominio.CtrlCancion;
import java.awt.Component;

/**
 *
 * @author MonSB
 */
public class CtrlVistaAlgoritmo {
    private CtrlAlgoritmo CtrlA;
    private VistaAlgoritmo VA;
    private VistaPrincipal VP;
    
    public CtrlVistaAlgoritmo(CtrlVistaRelacion CVR, CtrlVistaSolucion CVS, VistaPrincipal vp) {
        CtrlA = new CtrlAlgoritmo(CVR.ConsultarCtrlRelacion(), CVS.ConsultarCtrlSolucion());
        VA = new VistaAlgoritmo(this);
        VA.setOpaque(false);
        VP = vp;
    }
    
    public CtrlAlgoritmo ConsultarCtrlAlgoritmo() {
        return CtrlA;
    }
    
    public VistaAlgoritmo vista() {
        return VA;
    }
    
    public void GestionError(String error, int color) {
        VP.gestionErrores(error, color);
    }
}
