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
public class EnemigoAzul extends Enemigo {

    public EnemigoAzul() {
    }

    @Override
    public int darPuntos() {
        return isAtacando() ? 60 : 30;

    }
      public String getImagen() {
        return "../imagenes/azul.png";
    }
}
