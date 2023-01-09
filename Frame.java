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
    public static int fps = 360;
    // number of full rotations per second
    public static double rotations = 0.1;
    public static plot p = new plot();
    public static JFrame frame = new JFrame();

    public static void main(String args[]) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        compute_coefs();
        init_vectors();
        frame.add(p);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        run_program();
    }

    public static void compute_coefs() {
        /*
         * for (int i = 0; i < vectors; i++) {
         * p.getCoef().add(new complex(1, 0));
         * System.out.println(p.getCoef().get(i));
         * }
         * for (int i = vectors + 1; i < 2 * vectors; i++) {
         * p.getCoef().add(new complex(1, 0));
         * System.out.println(p.getCoef().get(i - 1));
         * }
         */
        p.getCoef().add(new complex(10.72, 16.52));
        p.getCoef().add(new complex(-12.64, 20.90));
        p.getCoef().add(new complex(-44.85, -23.71));
        p.getCoef().add(new complex(-135.66, -45.57));
        p.getCoef().add(new complex(66.75, -53.07));

    }

    public static void init_vectors() {
        for (int i = 0; i < vectors; i++) {
            p.addpoint(p.getCoef().get(i), i);
            System.out.println(p.getRotation().get(i) + " " + i);
        }
        for (int i = vectors + 1; i < 2 * vectors; i++) {
            p.addpoint(p.getCoef().get(i - 1), -(i - vectors));
            System.out.println(p.getRotation().get(i - 1) + " " + (i - 1));
        }
    }

    public static void run_program() {
        while (true) {
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            for (int i = 0; i < vectors; i++) {
                p.editpoint(exp(p.getRotation().get(i) * rotations), i, true);
                // System.out.println(p.getCord().get(i) + " " + (i));
            }
            for (int i = vectors + 1; i < 2 * vectors; i++) {
                p.editpoint(exp(p.getRotation().get(i - 1) * rotations), i - 1, true);
                // System.out.println(p.getCord().get(i - 1) + " " + (i - 1));
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