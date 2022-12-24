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
public class Frame extends JFrame implements ActionListener{  
    
    public static void main(String args[]){  
        JFrame frame = new JFrame();  
        
        complex a = new complex(3,4);
        complex b = new complex(-2,-1);
        complex c = new complex(4,-4);
        System.out.println(a.mult(b));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        plot p = new plot(a);
        p.addpoint(b);
        p.addpoint(c);
        frame.add(p);
        frame.setSize(1000, 1000);  
        frame.setLocation(200, 200);  
        frame.setVisible(true);  
    }  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("")){

        }
    }
}  