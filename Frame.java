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
    public static int vectors = 1;
    public static int fps = 60;
    // number of full rotations per second
    public static double rotations = 1;
    public static plot p = new plot();
    public static JFrame frame = new JFrame();

    public static void main(String args[]) {
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init_vectors();
        //compute_coefs();
        frame.add(p);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        run_program();
    }
    public static void init_vectors(){
        complex a = new complex(1, 0);
        for (int i = 0; i < vectors; i++) {
            p.addpoint(a, i);
        }
        /*
        for (int i = vectors + 1; i <= 2 * vectors + 1; i++) {
            p.addpoint(a.add(p.getCord().get(i - 1)), 1);
        } 
        */
    }
    
    public static void compute_coefs(){
    
    }
    
    public static void run_program(){
        for (int time = 0; true; time++) {
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            for (int i = 0; i < vectors; i++) {
                p.editpoint(rotationcalc(time, p.getRotation().get(i) * rotations, p.getCoef().get(i)), i, true);
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