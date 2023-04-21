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
public class Bola {

    public static final int DIAMETRO = 10;
    private static final int VELOCIDAD = 7;
    
    private int posX, posY;
    private boolean seMueve;
    private boolean diagSD; //diagonal superior derecha
    private boolean diagSI; //diagonal superior izquierda
    private boolean diagID; //diagonal inferior derecha
    private boolean diagII; //diagonal inferior izquierda
    private Barra barra;
    private Mundo mundo;
            
    public Bola(Barra barra, Mundo mundo) {
        this.barra = barra;
        this.mundo = mundo;
        seMueve = false;
    }

    /**
     * Método que chequea si la Bola choca con la barra o las paredes
     */
    public void actualizaBola() {
        if (seMueve) {
            choqueMundo();
            choqueBarra();
            mover();
        }
    }

    public void choqueMundo() {
        //choque con la pared izquierda
        if (posX - DIAMETRO / 2 <= 0) {
            if (diagSI) {        //se mueve hacia arriba
                diagSI = false;
                diagSD = true;
            } else if (diagII) { //se mueve hacia abajo
                diagII = false;
                diagID = true;
            }
        }
        //choque con la pared derecha
        if (posX + DIAMETRO >= mundo.getAncho()) {
            if (diagSD) {        //se mueve hacia arriba
                diagSD = false;
                diagSI = true;
            } else if (diagID) { //se mueve hacia abajo
                diagID = false;
                diagII = true;
            }
        }
        //choque con el techo
        if (posY - DIAMETRO / 2 <= 0) {
            if (diagSD) {        //se mueve hacia la derecha
                diagSD = false;
                diagID = true;
            } else if (diagSI) { //se mueve hacia la izquierda
                diagSI = false;
                diagII = true;
            }
        }
        //choque con el piso
        if (posY + DIAMETRO / 2 >= mundo.getAlto()) {
            //pierdes una vida
            mundo.setVidas(mundo.getVidas()-1);
            seMueve = false;
            diagID = false;
            diagII = false;
        }
    }

    public void choqueBarra() {
        //si golpea en el primer tercio de la barra
        if ((posY + DIAMETRO / 2 >= barra.getPosY() - Barra.ALTO_BARRA) && (posY + DIAMETRO / 2 < barra.getPosY())
                && (posX - 10 >= barra.getPosX()) && (posX <= barra.getPosX() + (Barra.ANCHO_BARRA / 3))) {
            if (diagID) {
                diagID = false;
                diagSI = true;
            } else if (diagII) {
                diagII = false;
                diagSI = true;
            }
        }
        //si golpea en el último tercio de la barra
        if ((posY + DIAMETRO / 2 >= barra.getPosY() - Barra.ALTO_BARRA) && (posY + DIAMETRO / 2 < barra.getPosY())
                && (posX >= barra.getPosX() + (2 * Barra.ANCHO_BARRA / 3)) && (posX + 10 <= barra.getPosX() + Barra.ANCHO_BARRA)) {
            if (diagII) {
                diagII = false;
                diagSD = true;
            } else if (diagID) {
                diagID = false;
                diagSD = true;
            }
        }
        //si golpea en el centro de la barra
        if ((posY + DIAMETRO / 2 >= barra.getPosY() - Barra.ALTO_BARRA) && (posY + DIAMETRO / 2 < barra.getPosY())
                && (posX <= barra.getPosX() + (2 * Barra.ANCHO_BARRA / 3)) && (posX >= barra.getPosX() + Barra.ANCHO_BARRA / 3)) {
            if (diagID) {
                diagID = false;
                diagSD = true;
            } else if (diagII) {
                diagII = false;
                diagSI = true;
            }
        }
    }

    public void mover() {
        if (diagSD) {
            posY -= VELOCIDAD;
            posX += VELOCIDAD;
        }
        if (diagSI) {
            posY -= VELOCIDAD;
            posX -= VELOCIDAD;
        }
        if (diagID) {
            posY += VELOCIDAD;
            posX += VELOCIDAD;
        }
        if (diagII) {
            posY += VELOCIDAD;
            posX -= VELOCIDAD;
        }
    }

    public void dibujar(Graphics g) {
        //posicion inicial
        if (!seMueve) {
            posY = barra.getPosY() - Barra.ALTO_BARRA;
            posX = barra.getPosX() + (Barra.ANCHO_BARRA - DIAMETRO) / 2;
        } 
        g.setColor(Color.RED);
        g.fillOval(posX, posY, DIAMETRO, DIAMETRO);
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

    public boolean isDiagSD() {
        return diagSD;
    }

    public void setDiagSD(boolean diagSD) {
        this.diagSD = diagSD;
    }

    public boolean isDiagSI() {
        return diagSI;
    }

    public void setDiagSI(boolean diagSI) {
        this.diagSI = diagSI;
    }

    public boolean isDiagID() {
        return diagID;
    }

    public void setDiagID(boolean diagID) {
        this.diagID = diagID;
    }

    public boolean isDiagII() {
        return diagII;
    }

    public void setDiagII(boolean diagII) {
        this.diagII = diagII;
    }

    public boolean isSeMueve() {
        return seMueve;
    }

    public void setSeMueve(boolean seMueve) {
        this.seMueve = seMueve;
    }


}
