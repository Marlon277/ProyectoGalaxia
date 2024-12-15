/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static Modelo.TipoDeEnemigo.*;
import Modelo.*;

/**
 *
 * @author Admin
 */
public class PresentadorEnemigo {

    private List<Enemigo> modeloEnemigos = new ArrayList<>();
    private Tablero tablero;
    private List<Enemigo> enemigosEnMovimiento = new ArrayList<>();
    private int direccionEnemigos = 1;

    public PresentadorEnemigo(Tablero tablero) {
        this.tablero = tablero;
        this.modeloEnemigos = crearEnemigos();
        generarNuevosEnemigos(3);
    }

    private List<Enemigo> crearEnemigos() {
        int y = 170;
        agregarFilasDeEnemigos(y);
        return modeloEnemigos;
    }

    public void generarNuevosEnemigos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            if (!modeloEnemigos.isEmpty()) {
                int indiceAleatorio = new Random().nextInt(modeloEnemigos.size());
                Enemigo nuevoEnemigo = modeloEnemigos.get(indiceAleatorio);
                enemigosEnMovimiento.add(nuevoEnemigo);
            }
        }
    }

    public void moverEnemigoEnMovimiento(Nave nave) {
        List<Enemigo> enemigosParaEliminar = new ArrayList<>();
        for (Enemigo enemigo : enemigosEnMovimiento) {
            enemigo.atacar(nave);
            enemigo.lanzarProyectil();
            if (enemigo.getPosicion().getY() > tablero.getAlto()) {
                enemigosParaEliminar.add(enemigo);
            }
        }

        enemigosEnMovimiento.removeAll(enemigosParaEliminar);

        if (enemigosEnMovimiento.isEmpty()) {
            generarNuevosEnemigos(3);
        }
    }

    public void reiniciarEnemigos() {
        modeloEnemigos = crearEnemigos();
        modeloEnemigos.forEach(e -> e.aumentarVelocidad(5));
        enemigosEnMovimiento.clear();
        generarNuevosEnemigos(3);
        direccionEnemigos = 1;
    }

    public void procesarMovimientoEnemigo() {
        if (modeloEnemigos.stream().anyMatch(e -> e.getPosicion().getX() <= 0
                || e.getPosicion().getX() >= tablero.getAncho() - 50)) {
            direccionEnemigos *= -1;
        }
        modeloEnemigos.forEach(enemigo -> enemigo.mover(direccionEnemigos));
    }

    public List<Enemigo> getEnemigoEnMovimiento() {
        return enemigosEnMovimiento;
    }

    private void agregarFilasDeEnemigos(int yInicial) {
        agregarFilaEnemigos(TipoDeEnemigo.AMARILLO, 2, yInicial, 120);
        agregarFilaEnemigos(TipoDeEnemigo.ROJO, 6, yInicial + 20, 40);
        agregarFilaEnemigos(TipoDeEnemigo.MAGENTA, 8, yInicial + 40, 40);
        for (int j = 0; j < 3; j++) {
            agregarFilaEnemigos(TipoDeEnemigo.AZUL, 10, yInicial + 60 + (j * 20), 40);
        }
    }

    private void agregarFilaEnemigos(TipoDeEnemigo tipo, int cantidad, int y, int separacionX) {
        for (int i = 0; i < cantidad; i++) {
            int x = calcularPosX(tipo, i, separacionX);
            Enemigo enemigo = crearEnemigo(tipo);
            enemigo.asignarPosicion(x, y, tablero);
            modeloEnemigos.add(enemigo);
        }
    }

    private Enemigo crearEnemigo(TipoDeEnemigo tipo) {
        return switch (tipo) {
            case AMARILLO ->
                new EnemigoAmarillo();
            case ROJO ->
                new EnemigoRojo();
            case MAGENTA ->
                new EnemigoMagenta();
            case AZUL ->
                new EnemigoAzul();
        };
    }

    private int calcularPosX(TipoDeEnemigo tipo, int indice, int separacionX) {
        return switch (tipo) {
            case AMARILLO ->
                160 + (indice * separacionX);
            case ROJO ->
                120 + (indice * separacionX);
            case MAGENTA ->
                80 + (indice * separacionX);
            case AZUL ->
                40 + (indice * separacionX);
        };
    }

    public List<Enemigo> getEnemigos() {
        return modeloEnemigos;
    }

    public Tablero getTablero() {
        return tablero;
    }

}
