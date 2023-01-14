/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fourier_images;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 *
 * @author erikp
 */
public class drawing extends JPanel{
    private Point pointStart = null;
    private Point pointEnd = null;
    private ArrayList<Point> ori_drawing = new ArrayList<Point>();
    
    drawing(int sx,int sy,int ex, int ey){
        this.setBounds(sx, sy, ex, ey);
        this.setBackground(new Color(0,0,0));
    }

    public ArrayList<Point> getOri_drawing() {
        return ori_drawing;
    }

    public void setOri_drawing(ArrayList<Point> ori_drawing) {
        this.ori_drawing = ori_drawing;
    }
    
    {
        addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            pointStart = e.getPoint();
            ori_drawing.add(pointStart);
            
        }

        public void mouseReleased(MouseEvent e) {
            pointStart = null;
        }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                pointEnd = e.getPoint();
            }

            public void mouseDragged(MouseEvent e) {
                pointEnd = e.getPoint();
                ori_drawing.add(pointEnd);
                repaint();
            }
        });       
    }
    @Override
    public void paint(Graphics grf){
        super.paint(grf);
        Graphics2D g = (Graphics2D) grf;
        g.setColor(new Color(255,255,255));
        g.setStroke(new BasicStroke(2));
        for(int i=1;i<ori_drawing.size();i++){
            g.drawLine(ori_drawing.get(i-1).x, ori_drawing.get(i-1).y, ori_drawing.get(i).x, ori_drawing.get(i).y);
        }
    } 
}
