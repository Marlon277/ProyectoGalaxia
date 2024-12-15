/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Puntuacion {

    private int puntos;

    public Puntuacion() {
        this.puntos = 0;
    }

    public void aumentarPuntos(Enemigo enemigo) {
        puntos += enemigo.darPuntos();
    }

    public int getPuntos() {
        return puntos;
    }

    public void reiniciar() {
        puntos = 0;
    }
}
