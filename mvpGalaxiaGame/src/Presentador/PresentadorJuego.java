/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentador;

import java.util.Iterator;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Modelo.*;
import Vista.IVista;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PresentadorJuego implements IPresentadorJuego {

    private PresentadorNave pNave;
    private PresentadorEnemigo pEnemigo;
    private PresentadorJugador pJugador;
    private Tablero tablero;
    private IVista vista;
    private Timer timer;
    private boolean gameOver = false;
    private Random random = new Random();
    private List<Jugador> jugadores;

    public PresentadorJuego(List<Jugador> jugadores, IVista vista) {
        this.vista = vista;
        this.jugadores = jugadores;
        tablero = new Tablero();
        this.inicializarJuego();
        actualizarVista();
    }

    private void inicializarJuego() {
        this.pNave = new PresentadorNave(tablero, vista);
        pNave.procesarMovimiento();
        this.pEnemigo = new PresentadorEnemigo(tablero);
        this.pJugador = new PresentadorJugador(jugadores, vista);

    }

    private void actualizarVista() {
        vista.actualizarNaves(pNave.getNave());
        vista.actualizarEnemigo(pEnemigo.getEnemigos());
        vista.actualizarPuntuacion(pJugador.getPuntuaciones());
        vista.actualizarVidas(pJugador.getVidas());
        vista.actualizarJugadores(jugadores);
    }

    @Override
    public void procesarActualizacion() {
        if (!gameOver()) {
            actualizar();
        } else {
            manejarGameOver();
        }
        vista.repaint();
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void actualizar() {
        pEnemigo.procesarMovimientoEnemigo();
        if (pNave.getNave().size() == 2) {
            pEnemigo.moverEnemigoEnMovimiento(pNave.getNave().get(new Random().nextInt(2)));
        } else {
            for (Nave nave : pNave.getNave()) {
                pEnemigo.moverEnemigoEnMovimiento(nave);
            }
        }
        pNave.actualizarProyectil();
        pEnemigo.getEnemigos().forEach(Enemigo::actualizarProyectil);
        verificarColisiones();
        if (pEnemigo.getEnemigos().isEmpty()) {
            pEnemigo.reiniciarEnemigos();
        }
        pNave.isProyectilActivo();
        this.vista.actualizarNaves(pNave.getNave());
        this.vista.actualizarEnemigo(pEnemigo.getEnemigos());
    }

    private void verificarColisiones() {
        for (int i = 0; i < pNave.getNave().size(); i++) {
            Nave nave = pNave.getNave().get(i);
            Jugador jugador = pJugador.getJugadores().get(i);
            if (nave.isProyectilActivo()) {
                verificarColisionesProyectil(jugador, nave);
            }
            verificarColisionesNave(jugador, nave);
        }
    }

    private void verificarColisionesProyectil(Jugador jugador, Nave nave) {
        Iterator<Enemigo> it = pEnemigo.getEnemigos().iterator();

        while (it.hasNext()) {
            Enemigo enemigo = it.next();
            if (enemigo.colisionaCon(nave.getProyectilNave())) {
                pJugador.procesarPuntuacion(jugador, enemigo);
                vista.actualizarPuntuacion(pJugador.getPuntuaciones());
                it.remove();
                nave.reiniciarProyectil();
                break;
            }

            if (enemigo.getPosicion().getY() >= Tablero.ALTO) {
                it.remove();
            }
        }
    }

    private void verificarColisionesNave(Jugador jugador, Nave nave) {
        for (Enemigo enemigo : pEnemigo.getEnemigos()) {
            if (enemigo.getProyectilEnemigo() != null) {
                if (nave.colisionaCon(enemigo.getProyectilEnemigo())) {
                    pJugador.procesarVida(jugador);
                    vista.actualizarVidas(pJugador.getVidas());

                    if (jugador.getVida().getVidas() == 0) {
                        int naveIndex = pNave.getNave().indexOf(nave);
                        if (naveIndex != -1) {
                            pNave.getNave().remove(naveIndex);
                        }

                        if (pNave.getNave().isEmpty()) {
                            gameOver = true;
                            return;
                        }
                    } else {
                        Nave nuevaNave = new Nave();
                        int naveIndex = pNave.getNave().indexOf(nave);
                        if (naveIndex != -1) {
                            pNave.getNave().set(naveIndex, nuevaNave);
                            nuevaNave.asignarPosicion(tablero);

                            if (naveIndex == 0) {
                                nuevaNave.getPosicion().setX(nuevaNave.getPosicion().getX() - 50);
                            } else if (naveIndex == 1) {
                                nuevaNave.getPosicion().setX(nuevaNave.getPosicion().getX() + 50);
                            }

                        }
                    }

                }
            }
        }
    }

    private void reiniciarJuego() {
        inicializarJuego();
        pJugador.reiniciar();
        pNave.getNave().forEach(Nave::reiniciarProyectil);
        gameOver = false;
        this.vista.actualizarPuntuacion(pJugador.getPuntuaciones());
        this.vista.actualizarVidas(pJugador.getVidas());
        this.vista.actualizarJugadores(jugadores);
    }

    public void manejarGameOver() {
        int opcion = JOptionPane.showOptionDialog(
                null,
                "¿Qué deseas hacer?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Reiniciar", "Salir"},
                "Reiniciar"
        );

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarJuego();
        } else {
            System.exit(0);
        }
    }

}
