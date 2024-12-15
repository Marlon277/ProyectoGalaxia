/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Color;

public abstract class Proyectil extends Elemento {

    public static final int ANCHO = 2;
    public static final int ALTO = 8;
    protected static final int VELOCIDAD = 20;

    public Proyectil(Color color, int x, int y) {
        super(color);
        this.posicion = new Posicion(x, y);
    }

    public abstract void mover();
}
