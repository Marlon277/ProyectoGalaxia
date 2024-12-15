/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Color;

/**
 *
 * @author Admin
 */
public class ProyectilEnemigo extends Proyectil {

    public ProyectilEnemigo(int x, int y) {
        super(Color.RED, x, y);
    }

    @Override
    public void mover() {
        posicion.setY(posicion.getY() + VELOCIDAD);
    }
}
