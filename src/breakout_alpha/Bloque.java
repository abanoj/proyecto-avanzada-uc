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
public class Bloque {

    private static final int ANCHO_BLOQUE = 50;//Width of block
    private static final int ALTO_BLOQUE = 10;//Height of block
    private static final int iniX = 130;
    private static final int iniY = 100;
    private static final int sep = 10;
    public static final int CANT_FILAS = 9;

    
    private int posX;//posicion desde la izquierda
    private int posY;//posición desde arriba
    private Bola bola;
    private Mundo mundo;
    private int i;
    private int pisoY;
    private int techoY;
    private int pared_izqX;
    private int pared_derX;
    private boolean noHayBloques;

    public Bloque(Bola bola, int i, Mundo mundo) {//contructor for Block
        this.i = i;
        this.mundo = mundo;
        this.bola = bola;

        //Determinar posición y dimensiones del bloque
        int y = 0;
        int x = i;
        if (i > 9) { //10 bloques por fila
            y = i / 10;
            x = i - (y * 10);
        }
        posX = (iniX + (x * (ANCHO_BLOQUE + sep)));//pixels to the left side of the screen
        posY = (iniY + (y * (ALTO_BLOQUE + sep)));
        pared_izqX = iniX;
        pared_derX = pared_izqX + 10 * ANCHO_BLOQUE;
        techoY = iniY;
        pisoY = techoY + ALTO_BLOQUE;
    }

    void actualizaBloque() {
        //variables auxiliares
        int izqBall = bola.getPosX();
        int derBall = bola.getPosX() + Bola.DIAMETRO;
        int techoBall = bola.getPosY();
        int pisoBall = bola.getPosY() + Bola.DIAMETRO;

        if (bola.isDiagSD()) { //si la bola va en dirección superior derecha
            //si la bola choca con el bloque
            if ((techoBall >= posY) && (techoBall <= posY + ALTO_BLOQUE)
                    && (derBall >= posX) && (derBall <= posX + ANCHO_BLOQUE)) {
                bola.setDiagSD(false);
                //si la bola toca la parte inferior del bloque
                if (techoBall == posY + ALTO_BLOQUE) {//cambia la dirección
                    bola.setDiagID(true);
                } else {
                    bola.setDiagSI(true);
                }
                //elimina el bloque
                mundo.actuales[i] = false;
                //suma puntaje
                mundo.setPuntaje(mundo.getPuntaje() + 5);
                noHayBloques = true;
                //chequea si aún quedan bloques
                for (int i = 0; i < CANT_FILAS*10; i++) {
                    if (mundo.actuales[i] == true) {
                        noHayBloques = false;
                    }
                }
                if (noHayBloques) {
                    mundo.setGanador(true);
                }
            }
        } else if (bola.isDiagSI()) { //si la bola va en dirección superior izquierda
            //si la bola choca con el bloque
            if ((techoBall >= posY) && (techoBall <= posY + ALTO_BLOQUE)
                    && (izqBall >= posX) && (izqBall <= posX + ANCHO_BLOQUE)) {
                bola.setDiagSI(false);
                //si la bola toca la parte inferior del bloque
                if (techoBall == posY + ALTO_BLOQUE) { //cambia la dirección
                    bola.setDiagII(true);
                } else {
                    bola.setDiagSD(true);
                }
                //elimina el bloque
                mundo.actuales[i] = false;
                //suma puntaje
                mundo.setPuntaje(mundo.getPuntaje() + 5);
                noHayBloques = true;
                //chequea si aún quedan bloques
                for (int i = 0; i < CANT_FILAS*10; i++) {
                    if (mundo.actuales[i] == true) {
                        noHayBloques = false;
                    }
                }
                if (noHayBloques) {
                    mundo.setGanador(true);
                }
            }
        } else if (bola.isDiagID()) { //si la bola va en dirección inferior derecha
            //si la bola choca con el bloque
            if ((pisoBall >= posY) && (pisoBall <= posY + ALTO_BLOQUE)
                    && (derBall >= posX) && (derBall <= posX + ANCHO_BLOQUE)) {
                bola.setDiagID(false);
                //si la bola choca el techo del bloque
                if (pisoBall == posY) {
                    bola.setDiagSD(true);
                } else {
                    bola.setDiagII(true);
                }
                //elimina el bloque
                mundo.actuales[i] = false;
                //suma puntaje
                mundo.setPuntaje(mundo.getPuntaje() + 5);
                noHayBloques = true;
                //verifica si existen más bloques
                for (int i = 0; i < CANT_FILAS*10; i++) {
                    if (mundo.actuales[i] == true) {
                        noHayBloques = false;
                    }
                }
                if (noHayBloques) {
                    mundo.setGanador(true);
                }

            }
        } else if (bola.isDiagII()) { //si la bola va en dirección inferior izquierda
            //si la bola choco con el bloque
            if ((pisoBall >= posY) && (pisoBall <= posY + ALTO_BLOQUE)
                    && (izqBall >= posX) && (izqBall <= posX + ANCHO_BLOQUE)) {
                bola.setDiagII(false);
                //si choco con el techo del bloque
                if (pisoBall == posY) {
                    bola.setDiagSI(true);
                } else {
                    bola.setDiagID(true);
                }
                //elimina el bloque
                mundo.actuales[i] = false;
                //suma el puntaje
                mundo.setPuntaje(mundo.getPuntaje() + 5);
                noHayBloques = true;
                //verifica si hay más bloques
                for (int i = 0; i < CANT_FILAS*10; i++) {
                    if (mundo.actuales[i] == true) {
                        noHayBloques = false;
                    }
                }
                if (noHayBloques) {
                    mundo.setGanador(true);
                }
            }
        }
    }

    public void dibujar(Graphics g, int i) {
    
        int y = 0;
        int x = i;
        if (i > 9) {
            y = i / 10;
            x = i - (y * 10);
        }
        if (i < 10) {
            g.setColor(Color.RED);
        } else if (i < 20) {
            g.setColor(Color.WHITE);
        } else if (i < 30) {
            g.setColor(Color.YELLOW);
        } else if (i < 40) {
            g.setColor(Color.GREEN);
        } else if (i < 50) {
            g.setColor(Color.BLUE);
        } else if (i < 60) {
            g.setColor(Color.CYAN);
        } else if (i < 70) {
            g.setColor(Color.MAGENTA);
        } else if (i < 80){
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
        
        g.fillRect(iniX + (x * (ANCHO_BLOQUE + sep)), iniY + (y * (ALTO_BLOQUE + sep)), ANCHO_BLOQUE, ALTO_BLOQUE);
        g.setColor(Color.BLACK);
        g.drawRect(iniX + (x * (ANCHO_BLOQUE + sep)), iniY + (y * (ALTO_BLOQUE + sep)), ANCHO_BLOQUE, ALTO_BLOQUE);
        g.setColor(Color.red);
    }

    
}
