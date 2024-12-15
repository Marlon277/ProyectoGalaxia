/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Admin
 */
public class Jugador {

    private String nombre;
    private Puntuacion puntuacion;
    private Vida vida;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntuacion = new Puntuacion();
        this.vida = new Vida();
    }

    public void aumentarPuntos(Enemigo enemigo) {
        puntuacion.aumentarPuntos(enemigo);
    }

    public void perder() {
        vida.perderVida();
    }

    public boolean isGameOver() {
        return vida.isGameOver();
    }

    public Vida getVida() {
        return vida;
    }

    public String getNombre() {
        return nombre;
    }

    public Puntuacion getPuntuacion() {
        return puntuacion;
    }
}
