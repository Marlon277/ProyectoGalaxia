/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Modelo.*;
import Presentador.IPresentadorJuego;

public class VistaJavaSwingGame extends JPanel implements IVista, ActionListener {

    private Graphics g;
    private List<Enemigo> enemigos;
    private List<Nave> naves;
    private List<Jugador> jugadores;
    private List<Integer> puntos;
    private List<Integer> vidas;
    private Timer timer;

    private IPresentadorJuego presentador;

    public VistaJavaSwingGame() {
        configurarVentana();
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void iniciar() {
        JFrame ventanaJuego = new JFrame("MVP Vista Pasiva Galaxian Namco Game");
        ventanaJuego.add(this);
        ventanaJuego.setSize(500, 690);
        ventanaJuego.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaJuego.setVisible(true);
        ventanaJuego.setLocationRelativeTo(null);
        this.requestFocus();
    }

    @Override
    public void setPresentador(IPresentadorJuego presentador) {
        this.presentador = presentador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        notificarActualizacion();
    }

    public void notificarActualizacion() {
        if (presentador != null) {
            presentador.procesarActualizacion();
        }
    }

    private void configurarVentana() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        dibujar();
    }

    @Override
    public void dibujar() {
        dibujarTablero();
        dibujarNave();
        dibujarEnemigos();
        dibujarPuntuacion();
        dibujarProyectil();
        dibujarProyectilEnemigo();
        dibujarVidas();
        dibujarJugadores();

    }

    @Override
    public void actualizarVidas(List<Integer> vidas) {
        this.vidas = vidas;
    }

    @Override
    public void actualizarEnemigo(List<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

    @Override
    public void dibujarEnemigos() {
        for (Enemigo enemigo : enemigos) {
            g.setColor(enemigo.getColor());
            ImageIcon imagenEnemigo = new ImageIcon(getClass().getResource(enemigo.getImagen()));
            g.drawImage(imagenEnemigo.getImage(), enemigo.getPosicion().getX(),
                    enemigo.getPosicion().getY(), Enemigo.ANCHO, Enemigo.ALTO, null);
        }
    }

    @Override
    public void actualizarPuntuacion(List<Integer> puntos) {
        this.puntos = puntos;
    }

    @Override
    public void actualizarJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public void dibujarJugadores() {
        int x = 65;
        for (int i = 0; i < jugadores.size(); i++) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("JUGADOR " + (i + 1) + ":", x, 60);
            g.setColor(Color.RED);
            x += 95;
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString(String.valueOf(this.jugadores.get(i).getNombre()), x, 60);
            x += 135;
        }
    }

    @Override
    public void dibujarPuntuacion() {
        int x = 50;
        for (int i = 0; i < puntos.size(); i++) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("PUNTUACIÓN:", x, 90);
            g.setColor(Color.RED);
            x += 110;
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString(String.valueOf(this.puntos.get(i)), x, 90);
            x += 120;
        }
    }

    @Override
    public void dibujarVidas() {
        int x = 105;
        for (int i = 0; i < vidas.size(); i++) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("VIDAS:", x, 120);
            g.setColor(Color.RED);
            x += 55;
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString(String.valueOf(this.vidas.get(i)), x, 120);
            x += 175;
        }
    }

    @Override
    public void dibujarProyectilEnemigo() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.getProyectilEnemigo() != null) {
                g.setColor(Color.RED);
                g.fillRect(enemigo.getProyectilEnemigo().getPosicion().getX(), enemigo.getProyectilEnemigo().getPosicion().getY(), Proyectil.ANCHO, Proyectil.ALTO);
            }
        }
    }

    @Override
    public void dibujarProyectil() {
        for (int i = 0; i < naves.size(); i++) {
            if (naves.get(i).getProyectilNave() != null) {
                if (i == 0) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(naves.get(i).getProyectilNave().getPosicion().getX(), naves.get(i).getProyectilNave().getPosicion().getY(), Proyectil.ANCHO, Proyectil.ALTO);
            }
        }

    }

    @Override
    public void actualizarNaves(List<Nave> naves) {
        this.naves = naves;
    }

    @Override
    public void dibujarNave() {
        for (int i = 0; i < naves.size(); i++) {
            if (naves.get(i) != null) {
                ImageIcon imagenNave = new ImageIcon(getClass().getResource("../imagenes/nave.png"));
                g.drawImage(imagenNave.getImage(), naves.get(i).getPosicion().getX(),
                        naves.get(i).getPosicion().getY() - 75, Nave.ANCHO, Nave.ALTO, null);
            }
        }

    }

    @Override
    public void dibujarTablero() {
        ImageIcon imagenTablero = new ImageIcon(getClass().getResource("../imagenes/space.jpg"));
        g.drawImage(imagenTablero.getImage(), 0, 0, Tablero.ANCHO, Tablero.ALTO, null);
    }

}
