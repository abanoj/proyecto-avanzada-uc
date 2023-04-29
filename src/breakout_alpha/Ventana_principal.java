/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout_alpha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jesus Abano
 */
public class Ventana_principal extends JFrame {

    public Mundo mundo1;
    public static JTextPane puntaje_txt = null;
    public static JTextPane nivel_txt = null;
    public static JTextPane vidas_txt = null;

    public Ventana_principal() {
        super();
        setSize(850, 630);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Proyecto Computación Avanzada: Breakout");
        setResizable(false);

        mundo1 = new Mundo();
        add(mundo1, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        //Configura JTextPane
        JButton info_btn = new JButton("Información");
        info_btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(panel, "Universidad de Carabobo\n"
                     + "Facultad de Ingeniería\n"
                     + "Proyecto de Computación Avanzada \n"
                     + "Sección 02\n"
                     + "Breakout Versión ALFA 2018\n"
                     , "Información", JOptionPane.INFORMATION_MESSAGE);
            requestFocusInWindow();
         }
      });
        panel.add(info_btn);

        //Puntaje Display
        puntaje_txt = new JTextPane();
        puntaje_txt.setText("Puntos: " + mundo1.getPuntaje());
        panel.add(puntaje_txt);

        //Nivel Display
        nivel_txt = new JTextPane();
        nivel_txt.setText("Nivel: " + 0);
        panel.add(nivel_txt);

        //Vidas display
        vidas_txt = new JTextPane();
        vidas_txt.setText("Vidas: " + 0);
        panel.add(vidas_txt);

        //Boton reseteo
        JButton reset_btn = new JButton("Reiniciar");
        reset_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        panel.add(reset_btn);
    }

    public void reset() {
        mundo1.setVidas(3);
        vidas_txt.setText("Vidas: " + mundo1.getVidas());
        mundo1.setPuntaje(0);
        puntaje_txt.setText("Puntos: " + mundo1.getPuntaje());
        mundo1.reset();
    }

}
