/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fourier_series;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.geom.*;

//Extends JPanel class  
public class Frame extends JFrame implements ActionListener {
    public static int vectors = 3;
    public static int fps = 100;
    // number of full rotations per second
    public static double rotations = 1;
    public static plot p = new plot();
    public static JFrame frame = new JFrame();

    public static void main(String args[]) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init_vectors();
        // compute_coefs();
        frame.add(p);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        run_program();
    }

    public static void init_vectors() {
        complex a = new complex(1, 0);
        for (int i = 0; i < vectors; i++) {
            p.addpoint(a, i);
        }
    }

    public static void compute_coefs() {
        for (int i = 0; i < vectors; i++) {

        }
    }

    public static void run_program() {
        for (int time = 0; true; time++) {
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            for (int i = 0; i < vectors; i++) {
                p.editpoint(exp(p.getRotation().get(i) * rotations), i, true);
            }
            frame.revalidate();
            frame.repaint();
        }
    }

    // calculates the rotation of each vector
    public static complex exp(double rotations) {
        return new complex(Math.cos(Math.PI * 2 / fps * rotations),
                Math.sin(Math.PI * 2 / fps * rotations));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("")) {

        }
    }
}