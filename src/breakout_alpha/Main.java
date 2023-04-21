/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout_alpha;

import javax.swing.JFrame;

/**
 *
 * @author Jesus Abano
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //JFrame ventana =  new JFrame("Proyecto Computación Avanzada: Breakout");//create frame
       //Mundo mundo1 = new Mundo();
        //ventana.setContentPane(mundo1);//assign instance of class to pane
        //ventana.setSize(1000, 700);  //tamaño de la ventana
        //ventana.setLocation(0, 0);
        //ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//end when window is exited
        //ventana.setVisible(true);
        //ventana.setResizable(false); //no le cambies el tamaño
        new Ventana_principal().setVisible(true);
    }

}
