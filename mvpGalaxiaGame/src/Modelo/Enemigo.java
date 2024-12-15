/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Rectangle;

public abstract class Enemigo extends Elemento implements Colisionable, IProyectible {

    public static final int ANCHO = 25;
    public static final int ALTO = 20;
    private static final int VELOCIDAD_HORIZONTAL = 2;
    private int velocidadVertical = 5;
    private Proyectil proyectilEnemigo;
    private boolean atacando;

    public Enemigo() {
    }

    public void asignarPosicion(int x, int y, Tablero tablero) {
        this.posicion = tablero.generarPosicion(x, y);
    }

    public void mover(int direccion) {
        posicion.setX(posicion.getX() + direccion * VELOCIDAD_HORIZONTAL);
    }

    @Override
    public void crearProyectil() {
        int posicionProyectilX = this.posicion.getX();
        int posicionProyectilY = this.posicion.getY();
        proyectilEnemigo = new ProyectilEnemigo(posicionProyectilX, posicionProyectilY);
    }

    @Override
    public void lanzarProyectil() {
        if (proyectilEnemigo == null) {
            crearProyectil();
        }
    }

    @Override
    public void actualizarProyectil() {
        if (proyectilEnemigo != null) {
            proyectilEnemigo.mover();
        }
    }

    public boolean isAtacando() {
        return atacando;
    }

    public void atacar(Nave nave) {
        int diferenciaX = nave.getPosicion().getX() - posicion.getX();
        int velocidadHorizontal = diferenciaX / 20;
        if (velocidadHorizontal > 5) {
            velocidadHorizontal = 5;
        } else if (velocidadHorizontal < -5) {
            velocidadHorizontal = -5;
        }

        posicion.setX(posicion.getX() + velocidadHorizontal);
        posicion.setY(posicion.getY() + velocidadVertical);
        atacando = true;
    }

    @Override
    public boolean colisionaCon(Elemento elemento) {
        Rectangle rectEnemigo = new Rectangle(
                posicion.getX(),
                posicion.getY(),
                ANCHO, ALTO);
        Rectangle rectProyectil = new Rectangle(elemento.getPosicion().getX(), elemento.getPosicion().getY(), 5, 10);
        return rectEnemigo.intersects(rectProyectil);
    }

    public Proyectil getProyectilEnemigo() {
        return proyectilEnemigo;
    }

    @Override
    public void reiniciarProyectil() {
        proyectilEnemigo = null;
    }

    public void aumentarVelocidad(int velocidad) {
        this.velocidadVertical += velocidad;
    }

    public abstract int darPuntos();

    public abstract String getImagen();

}
