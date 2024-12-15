/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Tablero {

    public static final int ANCHO = 500;
    public static final int ALTO = 700;
    public static final int TAMAÑO_UNIDAD = 50;

    public Tablero() {
    }

    public Posicion generarPosicion(int x, int y) {
        return new Posicion(x, y);
    }

    public Posicion generarPosicionNave() {
        int x = ANCHO / 2;
        int y = ALTO - TAMAÑO_UNIDAD;
        return new Posicion(x, y);
    }

    public int getAncho() {
        return ANCHO;
    }

    public int getAlto() {
        return ALTO;
    }
}
