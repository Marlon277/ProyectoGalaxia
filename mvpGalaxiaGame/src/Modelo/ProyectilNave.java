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
public class ProyectilNave extends Proyectil {

    public ProyectilNave(int x, int y) {
        super(Color.YELLOW, x, y);
    }

    @Override
    public void mover() {
        posicion.setY(posicion.getY() - VELOCIDAD);
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

}
