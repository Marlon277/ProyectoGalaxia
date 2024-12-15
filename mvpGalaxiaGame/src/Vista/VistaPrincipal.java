/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Jugador;
import Presentador.IPresentadorJuego;
import Presentador.PresentadorJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class VistaPrincipal extends JPanel {

    private JButton iniciarButton;
    private JTextField nombreJugador1;
    private JTextField nombreJugador2;

    public VistaPrincipal() {
        nombreJugador1 = new JTextField();
        nombreJugador1.setBounds(200, 150, 200, 30);

        nombreJugador2 = new JTextField();
        nombreJugador2.setBounds(200, 190, 200, 30);
        
        Font fuente = new Font("Arial", Font.PLAIN, 20);
        JLabel labelJugador1 = new JLabel("Jugador 1: ");
        labelJugador1.setBounds(50, 150, 100, 30);
        labelJugador1.setForeground(Color.YELLOW);
        labelJugador1.setFont(fuente);

        JLabel labelJugador2 = new JLabel("Jugador 2: ");
        labelJugador2.setBounds(50, 190, 100, 30);
        labelJugador2.setForeground(Color.YELLOW);
        labelJugador2.setFont(fuente);
        iniciarButton = new JButton("Iniciar Juego");
        iniciarButton.setBounds(200, 520, 120, 30);

        iniciarButton.addActionListener((ActionEvent e) -> {
            String nombrejugador1 = nombreJugador1.getText().trim();
            String nombrejugador2 = nombreJugador2.getText().trim();
            if (nombrejugador1.isEmpty() || nombrejugador2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese los nombres de ambos jugadores.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Jugador j1 = new Jugador(nombrejugador1);
                Jugador j2 = new Jugador(nombrejugador2);
                List<Jugador> jugadores = List.of(j1, j2);
                Window topLevelWindow = SwingUtilities.windowForComponent(this);
                if (topLevelWindow != null) {
                    topLevelWindow.setVisible(false);
                    topLevelWindow.dispose();
                }
                iniciarJuego(jugadores);
            }
        });

        setLayout(null);
        add(nombreJugador1);
        add(nombreJugador2);
        add(labelJugador1);
        add(labelJugador2);
        add(iniciarButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);
        dibujarEnemigos(g);
        dibujarTextos(g);
    }

    public void dibujar(Graphics g) {
        ImageIcon fondo = new ImageIcon(getClass().getResource("../imagenes/space.jpg"));
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public void dibujarTextos(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.RED);
        g.drawString("Somos la misión de los galaxianos: ", 90, 40);
        g.drawString("Destruir extraterrestres. ", 140, 70);
        g.setColor(Color.WHITE);
        g.drawString("- Tabla de avance de puntuación - ", 90, 290);
        g.setColor(Color.GREEN);
        g.drawString("NORMAL     DUPLICADO", 150, 320);
        g.drawString("  60          120   PTS", 170, 370);
        g.drawString("  50          100   PTS", 170, 394);
        g.drawString("  40            80   PTS", 170, 418);
        g.drawString("  30            60   PTS", 170, 442);
    }

    public void dibujarEnemigos(Graphics g) {
        String colores[] = {"amarillo", "rojo", "morado", "azul"};
        for (int i = 0; i < 4; i++) {
            dibujar(g, colores[i], 350 + (25 * i));
        }
    }

    public void dibujar(Graphics g, String color, int cantidad) {
        ImageIcon colors = new ImageIcon(getClass().getResource("../imagenes/" + color + ".png"));
        g.drawImage(colors.getImage(), 100, cantidad, 25, 25, this);
    }

    private void iniciarJuego(List<Jugador> jugadores) {
        IVista vista = new VistaJavaSwingGame();
        IPresentadorJuego presentador = new PresentadorJuego(jugadores, vista);
        vista.setPresentador(presentador);
        vista.iniciar();
    }
}
