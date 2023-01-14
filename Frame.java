/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fourier_images;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

//Extends JPanel class  
public class Frame extends JFrame implements ActionListener {
    public int vectors = 200;
    public int fps = 360;
    // number of full rotations per second
    public double rotations = 0.1;
    public plot vec_plot = new plot(0,0,800,900);
    public drawing draw_plot = new drawing(100, 0, 2000, 2000);
    public JFrame frame = new JFrame();
    public int phase = 1;

    Frame() {
        while (true) {          
            if (phase == 1) {
                JButton reset = new JButton("Reset");
                JButton done = new JButton("Done");  
                reset.addActionListener(this);
                done.addActionListener(this);
                reset.setActionCommand("reset");
                done.setActionCommand("done");
                reset.setBounds(20, 20, 70, 40);
                done.setBounds(20, 70, 70, 40);
                frame.add(draw_plot);
                frame.add(done);
                frame.add(reset);
                frame.setLayout(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 900);
                frame.setVisible(true);
            }
            if (phase == 2) {
                frame.getContentPane().removeAll();
                repaint();
                frame.setLayout(new BorderLayout());
                compute_coefs();
                init_vectors();
                frame.add(vec_plot);
                run_program();
            }
        }
    }

    public void compute_coefs() {
        ArrayList<Point> to_comp = draw_plot.getOri_drawing();
        vec_plot.setOri_drawing(to_comp);
        for (int i = 0; i < vectors; i++) {
            complex avg = new complex(0, 0);
            for (int j = 0; j < to_comp.size(); j++) {
                avg = avg.add(
                        vec_plot.point_to_complex(to_comp.get(j)).mult(exp(-2 * Math.PI * i * j / to_comp.size())));
            }
            avg = avg.mult(new complex(1 / (double) (to_comp.size()), 0));
            vec_plot.getCoef().add(avg);
        }
        for (int i = vectors + 1; i < 2 * vectors; i++) {
            complex avg = new complex(0, 0);
            for (int j = 0; j < to_comp.size(); j++) {
                avg = avg.add(
                        vec_plot.point_to_complex(to_comp.get(j))
                                .mult(exp(2 * Math.PI * (i - vectors) * j / to_comp.size())));
            }
            avg = avg.mult(new complex(1 / (double) (to_comp.size()), 0));
            vec_plot.getCoef().add(avg);
        }
    }

    public void init_vectors() {
        for (int i = 0; i < vectors; i++) {
            vec_plot.addpoint(vec_plot.getCoef().get(i), i);
        }
        for (int i = vectors + 1; i < 2 * vectors; i++) {
            vec_plot.addpoint(vec_plot.getCoef().get(i - 1), -(i - vectors));
        }
    }

    public void run_program() {
        while (true) {
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            for (int i = 0; i < vectors; i++) {
                vec_plot.editpoint(exp(Math.PI * 2 / fps * vec_plot.getRotation().get(i) * rotations), i, true);
            }
            for (int i = vectors + 1; i < 2 * vectors; i++) {
                vec_plot.editpoint(exp(Math.PI * 2 / fps * vec_plot.getRotation().get(i - 1) * rotations), i - 1, true);
            }
            vec_plot.revalidate();
            vec_plot.repaint();
        }
    }

    // calculates the rotation of each vector
    public complex exp(double exponent) {
        return new complex(Math.cos(exponent), Math.sin(exponent));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String a = e.getActionCommand();
        if (a.equals("done")) {
            if (draw_plot.getOri_drawing().size() != 0) {
                phase = 2;
            }
        }
        if (a.equals("reset")) {
            draw_plot.setOri_drawing(new ArrayList<Point>());
            draw_plot.repaint();
        }
    }
    
    public static void main (String args[]) {
        new Frame();
    }    
}
