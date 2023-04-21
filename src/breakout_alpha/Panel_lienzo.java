/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout_alpha;

import java.awt.Graphics;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;


/**
 *
 * @author Jesus Abano
 */
public class Panel_lienzo extends JPanel {

    private ArrayList<Bola> arreglo;
    private ExecutorService thread_pool;
    private final ImageIcon FONDO = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
    
    public Panel_lienzo(){
        super();
        setLayout(null);
        
        thread_pool = Executors.newCachedThreadPool();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(FONDO.getImage(), 0, 0, getWidth(), getHeight(), this); //Cargar el panel con la fotografia
    }
    
    
    



    
}
