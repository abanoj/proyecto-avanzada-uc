/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout_alpha;

import java.awt.*;

/**
 *
 * @author Jesus Abano
 */
public class Barra {

    public static final int ALTO_BARRA = 12;
    public static final int ANCHO_BARRA = 90;
    public static final Color COLOR_BARRA = Color.YELLOW;

    private int posX;//posición medida desde arriba
    private int posY;//posición medida desde la izquierda
    
    private Mundo mundo;
    
    public Barra(Mundo mundo) {
        this.mundo = mundo;
        posX = 120;
        posY = 520;
    }

    public void actualizaBarra() {
        if (posX + ANCHO_BARRA >= mundo.getAncho()-4) {//Comprobar que no se salga de la pantalla
            posX = mundo.getAncho() - ANCHO_BARRA-4;
        } else if (posX <= 4) {
            posX = 4;
        }
    }

    public void dibujar(Graphics g) {//draws bar
        g.setColor(COLOR_BARRA);
        g.fillOval(posX, posY, ANCHO_BARRA, ALTO_BARRA);
        //g.fillRoundRect(posX, posY, ANCHO_BARRA, ALTO_BARRA, 20, 20);
        //g.fillRect(posX, posY, ANCHO_BARRA, ALTO_BARRA);
    }

    //getters y setters
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
   
}

