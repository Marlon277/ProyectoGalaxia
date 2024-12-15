/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Vista;

import java.awt.event.KeyListener;
import java.util.List;

/**
 *
 * @author Admin
 */
import Presentador.IPresentadorJuego;
import Modelo.Nave;
import Modelo.Enemigo;
import Modelo.Jugador;

public interface IVista {

    void iniciar();

    void setPresentador(IPresentadorJuego presentador);

    void addKeyListener(KeyListener keyListener);

    void repaint();

    void dibujar();

    void dibujarVidas();

    void dibujarProyectilEnemigo();

    void dibujarProyectil();

    void dibujarNave();

    void dibujarTablero();

    void dibujarEnemigos();

    void dibujarPuntuacion();

    void dibujarJugadores();

    void actualizarVidas(List<Integer> vidas);

    void actualizarNaves(List<Nave> naves);

    void actualizarEnemigo(List<Enemigo> enemigos);

    void actualizarPuntuacion(List<Integer> puntos);

    void actualizarJugadores(List<Jugador> jugadores);
}
