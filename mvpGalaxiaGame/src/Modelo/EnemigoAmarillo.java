/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class EnemigoAmarillo extends Enemigo {

    public EnemigoAmarillo() {
    }

    @Override
    public int darPuntos() {
        return isAtacando() ? 120 : 60;
    }
     public String getImagen() {
        return "../imagenes/amarillo.png";
    }
}
