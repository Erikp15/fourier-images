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
    public static int vectors = 0;
    public static int fps = 240;

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        complex a = new complex(1, 1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plot p = new plot();
        p.addpoint(a, 1);
        for (int i = 2; i <= vectors; i++) {
            p.addpoint(a.add(p.getCord().get(i - 1)), 1);
        }
        for (int i = vectors + 1; i <= 2 * vectors + 1; i++) {
            p.addpoint(a.add(p.getCord().get(i - 1)), 1);
        }        
        /*
        for (int i = 1; i <= vectors; i++) {
            p.addpoint(a.add(p.getCord().get(i - 1)), 1);
        }
        */
        frame.add(p);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        for (int time = 0; true; time++) {
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // number of full rotations per second
            double rotations = 0.1;
            for (int i = 2; i <= vectors; i++) {
                p.replacepoint(rotationcalc(time, rotations * (i-1), p.getCoef().get(i)).add(p.getCord().get(i - 1)), i);
            }
            for (int i = vectors + 1; i <= 2 * vectors + 1; i++) {
                p.replacepoint(rotationcalc(time, rotations * -(i-vectors), p.getCoef().get(i)).add(p.getCord().get(i - 1)), i);
            }            
            /*
            for (int i = vectors + 1; i <= 2*vectors + 1; i++) {
                p.replacepoint(
                        rotationcalc(time, rotations * -(i-vectors), p.getCoef().get(i)).add(p.getCord().get(i)),
                        1);
            } 
*/
            
            frame.revalidate();
            frame.repaint();
        }
    }

    // calculates the rotation of each vector
    public static complex rotationcalc(int time, double rotations, double scaling) {
        return new complex(Math.cos(time * Math.PI * 2 / fps * rotations) * scaling,
                Math.sin(time * Math.PI * 2 / fps * rotations) * scaling);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("")) {

        }
    }
}