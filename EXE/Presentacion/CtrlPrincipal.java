/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

/**
 *
 * @author MonSB
 */
public class CtrlPrincipal {
    
    private CtrlVistaCancion CtrlVC;
    private CtrlVistaUsuario CtrlVU;
    private CtrlVistaListado CtrlVL;
    private CtrlVistaSolucion CtrlVS;
    private CtrlVistaRelacion CtrlVR;
    private CtrlVistaAlgoritmo CtrlVA;
    private VistaAyuda vistaAyuda;
    private VistaPrincipal VP;
    
    public CtrlPrincipal () {
        VP = new VistaPrincipal();
        
        CtrlVC = new CtrlVistaCancion(VP);
        CtrlVL = new CtrlVistaListado(CtrlVC, VP); 
        CtrlVS = new CtrlVistaSolucion(CtrlVC, VP);
        CtrlVU = new CtrlVistaUsuario(CtrlVL, CtrlVC, CtrlVS, VP);
        CtrlVR = new CtrlVistaRelacion(CtrlVC, CtrlVU, VP);
        CtrlVA = new CtrlVistaAlgoritmo(CtrlVR, CtrlVS, VP);
        vistaAyuda = new VistaAyuda();
        
        VP.anadirVista(CtrlVC.vista(), "Gestión Canciones", "icono_cancion.png");
        VP.anadirVista(CtrlVL.vista(), "Gestión Listados", "icono_listado.png");
        VP.anadirVista(CtrlVS.vista(), "Gestión Soluciones", "icono_solucion.png");
        VP.anadirVista(CtrlVU.vista(), "Gestión Usuarios", "icono_usuario.png");
        VP.anadirVista(CtrlVR.vista(), "Gestión Relaciones", "icono_relacion.png");
        VP.anadirVista(CtrlVA.vista(), "Gestión Algoritmos", "icono_algoritmo.png");
        VP.anadirVista(vistaAyuda, "", "icono_ayuda.png");
        VP.confirmar();
    }
    
    /*
    public void inicializarPresentacion() throws IOException {
        new VistaPrincipal(this).setVisible(true);
    }
    */
    
}
