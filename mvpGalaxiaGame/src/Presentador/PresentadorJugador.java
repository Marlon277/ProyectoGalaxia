/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentador;

import Modelo.Enemigo;
import Modelo.Jugador;
import Vista.IVista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PresentadorJugador {

    private List<Jugador> jugadores;

    public PresentadorJugador(List<Jugador> jugadores, IVista vista) {
        this.jugadores = jugadores;
    }

    public void reiniciar() {
        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).getPuntuacion().reiniciar();
            jugadores.get(i).getVida().reiniciar();
        }

    }

    public void procesarPuntuacion(Jugador jugador, Enemigo enemigo) {
        jugador.aumentarPuntos(enemigo);
    }

    public void procesarVida(Jugador jugador) {
        jugador.perder();
    }

    public List<Integer> getPuntuaciones() {
        List<Integer> puntuaciones = new ArrayList<>();
        for (Jugador j : jugadores) {
            puntuaciones.add(j.getPuntuacion().getPuntos());
        }
        return puntuaciones;
    }

    public List<Integer> getVidas() {
        List<Integer> vidas = new ArrayList<>();
        for (Jugador j : jugadores) {
            vidas.add(j.getVida().getVidas());
        }
        return vidas;
    }

    public boolean isGameOver() {
        return jugadores.get(0).isGameOver() && jugadores.get(1).isGameOver();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

}
