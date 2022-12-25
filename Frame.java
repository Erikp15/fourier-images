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
        plot p = new plot(a);
        frame.add(p);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        for (int time = 0; true; time++) {
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // number of rotations per second
            double rotations = 0.5;
            p.replacepoint(
                    new complex(Math.cos((time * Math.PI / 30) * rotations),
                            Math.sin((time * Math.PI / 30) * rotations)),
                    0);
            System.out.println(p.getCord().get(0));
            frame.revalidate();
            frame.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("")) {

        }
    }
}