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
public class EnemigoRojo extends Enemigo {

    public EnemigoRojo() {
    }

    @Override
    public int darPuntos() {
        return isAtacando() ? 100 : 50;
    }
    public String getImagen() {
        return "../imagenes/rojo.png";
    }
}
