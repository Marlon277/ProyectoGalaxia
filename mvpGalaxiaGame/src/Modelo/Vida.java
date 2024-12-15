/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Admin
 */
public class Vida {

    private int vidas;

    public Vida() {
        this.vidas = 3;
    }

    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    public int getVidas() {
        return vidas;
    }

    public boolean isGameOver() {
        return vidas == 0;
    }

    public void reiniciar() {
        this.vidas = 3;
    }
}
