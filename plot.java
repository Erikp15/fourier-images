/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fourier_series;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

//Extends JPanel class  
public class plot extends JPanel {
    private int scaling = 1;
    private int offsetX = 442, offsetY = 436;
    private int marg = 0;
    private Point pointstart = null;
    private Point pointend = null;
    // list of each of the end points of the vectors that are connected
    private ArrayList<complex> cord = new ArrayList<complex>();
    // list of each of the scaling coefficients
    private ArrayList<complex> coef = new ArrayList<complex>();
    // list of each of the rotation speeds
    private ArrayList<Double> rotation = new ArrayList<Double>();
    // list of each of the pixels of the vector drawing
    private ArrayList<complex> vec_drawing = new ArrayList<complex>();
    // list of each of the pixels of the original drawing
    private ArrayList<Point> ori_drawing = new ArrayList<Point>();    

    public plot() {
        ArrayList<complex> p = new ArrayList<complex>();
        ArrayList<complex> c = new ArrayList<complex>();
        ArrayList<Double> r = new ArrayList<Double>();
        setCord(p);
        setCoef(c);
        setRotation(r);
    }

    public plot(ArrayList<Point> to_draw) {
        ArrayList<complex> p = new ArrayList<complex>();
        ArrayList<complex> c = new ArrayList<complex>();
        ArrayList<Double> r = new ArrayList<Double>();
        setCord(p);
        setCoef(c);
        setRotation(r);
    }

    public ArrayList<Double> getRotation() {
        return rotation;
    }

    public void setRotation(ArrayList<Double> rotation) {
        this.rotation = rotation;
    }

    public ArrayList<complex> getCord() {
        return cord;
    }

    public void setCord(ArrayList<complex> cord) {
        this.cord = cord;
    }

    public ArrayList<complex> getCoef() {
        return coef;
    }

    public void setCoef(ArrayList<complex> coef) {
        this.coef = coef;
    }
    
    public ArrayList<complex> getDrawing() {
        return vec_drawing;
    }

    public ArrayList<complex> getVec_drawing() {
        return vec_drawing;
    }
    
    public void setVec_drawing(ArrayList<complex> vec_drawing) {
        this.vec_drawing = vec_drawing;
    }

    public ArrayList<Point> getOri_drawing() {
        return ori_drawing;
    }

    public void setOri_drawing(ArrayList<Point> ori_drawing) {
        this.ori_drawing = ori_drawing;
    }
    

    public void setDrawing(ArrayList<complex> vec_drawing) {
        this.vec_drawing = vec_drawing;
    }

    public int getMarg() {
        return marg;
    }

    public void setMarg(int marg) {
        this.marg = marg;
    }

    public void addpoint(complex a, double r) {
        cord.add(a);
        rotation.add(r);
    }

    public void removepoint(int i) {
        cord.remove(i);
        coef.remove(i);
        rotation.remove(i);
    }

    public void editpoint(complex a, int i, boolean tomult) {
        if (tomult) {
            cord.set(i, cord.get(i).mult(a));
        } else {
            cord.set(i, cord.get(i).add(a));
        }
    }

    public void replacepoint(complex a, int i, double r) {
        cord.set(i, a);
        rotation.set(i, r);
    }

    public Point complex_to_point(complex a) {
        return new Point((int) Math.round(a.real * scaling) + offsetX + marg/2, (int) Math.round((-a.imag) * scaling) + offsetY);
    }
    
    public complex point_to_complex(Point a) {
        return new complex(a.x / scaling - offsetX - marg/2, (-a.y) / scaling - offsetY);
    }    

    protected void paintComponent(Graphics grf) {
        // create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;

        // Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        this.offsetX = width / 2;
        this.offsetY = height / 2;
        this.setBackground(new Color(0,0,0));
        graph.setColor(new Color(255, 255, 255));
        graph.draw(new Line2D.Double((width+marg) / 2 , 0, (width+marg) / 2 , height - 0));
        graph.draw(new Line2D.Double(marg, height / 2, width - 0, height / 2));
        graph.setColor(new Color(155, 155, 155));
        graph.setStroke(new BasicStroke(2));
        // connecting the vector endpoints to build the fourier series
        complex pen = new complex(0, 0);
        // System.out.println(cord.size());
        for (int i = 0; i < cord.size(); i++) {
            graph.draw(new Line2D.Double(this.complex_to_point(pen), this.complex_to_point(pen.add(cord.get(i)))));
            System.out.println(cord.get(i) + " " + i);
            pen = pen.add(cord.get(i));
        }
        // System.out.println(pen);
        // System.out.println("");
        graph.setColor(new Color(0, 200, 200));
        // maintaining only the last 100 points of the vec_drawing

        vec_drawing.add(pen);
        if (vec_drawing.size() > 10000) {
            vec_drawing.remove(0);
        }

        for (int i = 1; i < vec_drawing.size(); i++) {
            graph.draw(new Line2D.Double(this.complex_to_point(vec_drawing.get(i - 1)), this.complex_to_point(vec_drawing.get(i))));
        }
    }
     {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    pointstart = e.getPoint();
                }

                public void mouseReleased(MouseEvent e) {
                    pointstart = null;
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent e) {
                    pointend = e.getPoint();
                }

                public void mouseDragged(MouseEvent e) {
                    pointend = e.getPoint();
                    repaint();
                }
            });
        }
}