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
public class EnemigoMagenta extends Enemigo {

    public EnemigoMagenta() {
    }

    @Override
    public int darPuntos() {
        return isAtacando() ? 80 : 40;

    }
    public String getImagen() {
        return "../imagenes/morado.png";
    }
}
