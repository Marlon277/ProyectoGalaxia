/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Modelo.Nave;
import Modelo.Tablero;
import Vista.IVista;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class PresentadorNave {

    private List<Nave> modeloNaves;
    private IVista vista;
    private Set<Integer> teclasPresionadas;

    public PresentadorNave(Tablero tablero, IVista vista) {
        teclasPresionadas = new HashSet<>();
        this.vista = vista;
        this.modeloNaves = crearNaves(tablero);
    }

    public void procesarMovimiento() {
        this.vista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                teclasPresionadas.add(e.getKeyCode());
                seleccionarTeclas();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                teclasPresionadas.remove(e.getKeyCode());
                seleccionarTeclas();
            }
        });
    }

    private void seleccionarTeclas() {
        for (int i = 0; i < modeloNaves.size(); i++) {
            Nave nave = modeloNaves.get(i);

            if (nave != null) {
                if (i == 0) {
                    if (teclasPresionadas.contains(KeyEvent.VK_A)) {
                        nave.moverIzquierda();
                    }
                    if (teclasPresionadas.contains(KeyEvent.VK_D)) {
                        nave.moverDerecha();
                    }
                    if (teclasPresionadas.contains(KeyEvent.VK_SPACE)) {
                        nave.lanzarProyectil();
                    }
                } else if (i == 1) {
                    if (teclasPresionadas.contains(KeyEvent.VK_LEFT)) {
                        nave.moverIzquierda();
                    }
                    if (teclasPresionadas.contains(KeyEvent.VK_RIGHT)) {
                        nave.moverDerecha();
                    }
                    if (teclasPresionadas.contains(KeyEvent.VK_ENTER)) {
                        nave.lanzarProyectil();
                    }
                }
            }
        }
    }

    public List<Nave> crearNaves(Tablero tablero) {
        this.modeloNaves = new ArrayList<>();
        int[] desplazamientos = {-50, 50};

        for (int desplazamiento : desplazamientos) {
            Nave nave = new Nave();
            nave.asignarPosicion(tablero);
            nave.getPosicion().setX(nave.getPosicion().getX() + desplazamiento);
            modeloNaves.add(nave);
        }

        return modeloNaves;
    }

    public void eliminarNave(Nave nave) {
        modeloNaves.remove(nave);
    }

    public List<Nave> getNave() {
        return modeloNaves;
    }

    public void actualizarProyectil() {
        for (Nave nave : modeloNaves) {
            nave.actualizarProyectil();
        }
    }

    public void isProyectilActivo() {
        for (Nave nave : modeloNaves) {
            if (!nave.isProyectilActivo()) {
                nave.crearProyectil();
                nave.getProyectilNave().setPosicion(nave.getProyectilNave().getPosicion());
            }
        }
    }

}
