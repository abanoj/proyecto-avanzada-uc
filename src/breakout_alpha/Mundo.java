/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout_alpha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 *
 * @author Jesus Abano
 */
public class Mundo extends JPanel implements Runnable {

    private final ImageIcon FONDO = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));

    private Barra barra;// public Bar bar
    private int direccion;
    private Bola bola;// public Ball ball
    private Bloque bloques[];
    public boolean actuales[] = new boolean[Bloque.CANT_FILAS*10];

    private int ancho,alto;
    private Timer timer;//public timer variable to keep track of time
    
    private Thread thread1;
    private boolean ganador;

    private int puntaje = 0;
    private int vidas = 3;

    //para efectos de multipelotas
    private ArrayList<Bola> balls;
    private ExecutorService thread_pool;

    /**
     * Método constructor de la clase Mundo
     */
    public Mundo() {//constructor
        Thread thread1 = new Thread(this);
        thread1.start();//thread for bar movement
        setBackground(Color.BLACK);

        thread_pool = Executors.newCachedThreadPool();
        balls = new ArrayList<>();
        
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {//action performed when action is called
                if (barra != null) {
                    barra.actualizaBarra();
                    bola.actualizaBola();
                    if ((bola.getPosX() >= 120) && (bola.getPosX() <= 850)
                            && (bola.getPosY() >= 80) && (bola.getPosY() <= 375+40)) {
                        for (int i = 0; i < Bloque.CANT_FILAS*10; i++) {
                            if (actuales[i]) {
                                bloques[i].actualizaBloque();
                            }
                        }
                    }
                }
                repaint();
            }
        };

        timer = new Timer(30, action);//call action every 30 milliseconds

        addMouseListener(new MouseOyente());

        addFocusListener(new FocusOyente());//Listen for focus - if window is the main window on the computer

        addKeyListener(new KeyOyente());

        for (int k = 0; k < Bloque.CANT_FILAS*10; k++) {
            actuales[k] = true;
        }
    } //end of constructor

    private class MouseOyente extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
            requestFocus();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
        }
        
        

    }

    private class FocusOyente extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            super.focusGained(e); //To change body of generated methods, choose Tools | Templates.
            timer.start();
            repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            super.focusLost(e); //To change body of generated methods, choose Tools | Templates.
            timer.stop();
            repaint();
        }
    }

    private class KeyOyente extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {//left key on keyboard
                direccion = -2;
            } else if (key == KeyEvent.VK_RIGHT) {//right key on keyboard bar moves 2 pixels every 5 miliseconds
                direccion = 2;
            } else if ((key == KeyEvent.VK_SPACE) && (vidas > 0) && (ganador == false)) {//spacebar, ball only moves is game isn't won and there are still balls left
                if (!bola.isSeMueve()) {
                    bola.setSeMueve(true);
                    if(Math.random()>0.5){
                    bola.setDiagSI(true);//ball moves northwest
                    }else{
                    bola.setDiagSD(true);
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e); //To change body of generated methods, choose Tools | Templates.
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                direccion = 0;
            }
            if (key == KeyEvent.VK_RIGHT) {
                direccion = 0;
            }
        }

    }

    @Override
    public void run() {//runs when thread starts
        try {
            while (true) {
                mover();
                Thread.sleep(5);
            }
        } catch (Exception e) {
            System.out.print("Error");
        }
    }

    /**
     * Método para mover la barra de juego (si existe)
     */
    public void mover() {
        if (barra != null) {
            barra.setPosX(barra.getPosX() + direccion);
        }
    }

    /**
     * Método para verificar el estado de juego (ganó, perdió o continua)
     * @param g
     */
    public void estadoDeJuego(Graphics g) {
        if (vidas == 0) {
            for (int i = 0; i < Bloque.CANT_FILAS*10; i++) {
                actuales[i] = false;
            }
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.drawString("Game Over", 100, 200);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
            g.drawString("Tu puntaje fue de: " + puntaje + " puntos.", 100, 400);
        }
        if (ganador) {
            bola.setSeMueve(false);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.drawString("¡Felicidades", 900, 200);
            g.drawString("Ganaste!", 900, 400);
        }
    }

    /**
     * Método para dibujar los bloques del juego
     * @param g
     */
    public void dibujarBloques(Graphics g) {
        for (int i = 0; i < Bloque.CANT_FILAS*10; i++) {
            if (actuales[i]) {
                bloques[i].dibujar(g, i);
            }
        }
    }
    
    /**
     * Método para reiniciar todos los componentes 
    */
    public void reset(){
        barra = new Barra(this);
        bola = new Bola(barra, this);
        bloques = new Bloque[Bloque.CANT_FILAS*10];
            for (int i = 0; i < Bloque.CANT_FILAS*10; i++) {
                actuales[i] = true;
                bloques[i] = new Bloque(bola, i, this);//instantiating each block
            }
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//import paintComponent of superclass
        g.drawImage(FONDO.getImage(), 0, 0, getWidth(), getHeight(), this); //Cargar el panel con la fotografia
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//makes diagnols/curves cleaner

        if (barra == null) {
            ancho = getWidth();
            alto = getHeight();
            barra = new Barra(this);
            bola = new Bola(barra, this);
            bloques = new Bloque[Bloque.CANT_FILAS*10];
            for (int i = 0; i < Bloque.CANT_FILAS*10; i++) {
                bloques[i] = new Bloque(bola, i, this);//instantiating each block
            }
        }
        
        Ventana_principal.vidas_txt.setText("Vidas: " + vidas);
        Ventana_principal.puntaje_txt.setText("Puntos: " + puntaje);
        
        if (hasFocus()) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.GREEN);
        }
        g.setColor(Color.MAGENTA);

        if (!hasFocus()) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 55));
            g.drawString("Click para comenzar el juego", 60, 350);
        }

        estadoDeJuego(g);
        

        //lineas de frontera
        g.fillRect(0, 0, 4, alto - 1);
        g.fillRect(ancho - 4, 0, 4, alto - 1);
        g.fillRect(0, 0, ancho, 4);
        g.fillRect(0, alto - 4, ancho, 4);

        //cantBolas();
        barra.dibujar(g);
        bola.dibujar(g);
        dibujarBloques(g);
    }
    //getters y setters
    public int getAncho() {
        return ancho;
    }
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    public int getAlto() {
        return alto;
    }
    public void setAlto(int alto) {
        this.alto = alto;
    }
    public boolean isGanador() {
        return ganador;
    }
    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    public int getVidas() {
        return vidas;
    }
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
}
