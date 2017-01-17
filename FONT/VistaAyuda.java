/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author MonSB
 */
public class VistaAyuda extends JPanel {
    SwingController controller; 
    SwingViewBuilder factory;
    JPanel V; 
    

    public VistaAyuda() {
        controller = new SwingController(); 
        factory = new SwingViewBuilder(controller); 
        V = factory.buildViewerPanel(); 
        setLayout(new BorderLayout());
        add(V, BorderLayout.CENTER);
        controller.openDocument("Youtube-Mix-Manual-de-usuario.pdf");
    }
}
