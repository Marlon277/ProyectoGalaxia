/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Adminsa
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("MVP Vista Pasiva Galaxian Namco Games");
            VistaPrincipal panel = new VistaPrincipal();
            frame.add(panel);
            frame.setSize(500, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }
}
