/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package plottest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.geom.*;

//Extends JPanel class  
public class Frame extends JFrame implements ActionListener {

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        complex a = new complex(1, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int vectors = 10;
        plot p = new plot();
        for (int i = 1; i <= vectors; i++) {
            p.addpoint(a.add(p.getCord().get(i - 1)), 1 / (double) i);
        }

        frame.add(p);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        for (int time = 0; true; time++) {
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // number of full rotations per second
            double rotations = 0.5;
            for (int i = 1; i <= vectors; i++) {
                p.replacepoint(
                        rotationcalc(time, rotations * i, p.getCoef().get(i)).add(p.getCord().get(i - 1)), i);
            }
            frame.revalidate();
            frame.repaint();
        }
    }

    // calculates the rotation of each vector
    public static complex rotationcalc(int time, double rotations, double scaling) {
        return new complex(Math.cos((time * Math.PI / 30) * rotations) * scaling,
                Math.sin((time * Math.PI / 30) * rotations) * scaling);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("")) {

        }
    }
}