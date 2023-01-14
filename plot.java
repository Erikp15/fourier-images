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

//Extends JPanel class  
public class plot extends JPanel {
    private double scaling = 100;
    private int offsetX = 442, offsetY = 436;
    private int marg = 100;
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

    public plot(int sx, int sy, int ex, int ey) {
        ArrayList<complex> p = new ArrayList<complex>();
        ArrayList<complex> c = new ArrayList<complex>();
        ArrayList<Double> r = new ArrayList<Double>();
        ArrayList<Point> o = new ArrayList<Point>();
        setCord(p);
        setCoef(c);
        setRotation(r);
        setOri_drawing(o);
        this.setBounds(sx, sy, ex, ey);
        this.setBackground(new Color(0,0,0));
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
        return new Point((int) Math.round(a.real * scaling) + offsetX + marg,
                (int) Math.round((-a.imag) * scaling) + offsetY);
    }

    public complex point_to_complex(Point a) {
        return new complex((a.x - offsetX) / scaling, -(a.y - offsetY) / scaling);
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

        graph.setColor(new Color(255, 255, 255));
        graph.setStroke(new BasicStroke(2));
        // connecting the vector endpoints to build the fourier series
        complex pen = new complex(0, 0);
        for (int i = 0; i < cord.size(); i++) {
            graph.draw(new Line2D.Double(this.complex_to_point(pen), this.complex_to_point(pen.add(cord.get(i)))));
            pen = pen.add(cord.get(i));
        }
        
        
        graph.setColor(new Color(100, 200, 100));
        for (int i = 1; i < ori_drawing.size(); i++) {
            graph.draw(new Line2D.Double(new Point(ori_drawing.get(i - 1).x+100,ori_drawing.get(i - 1).y), new Point(ori_drawing.get(i).x+100,ori_drawing.get(i).y)));
        }        
        graph.setColor(new Color(0, 200, 200));
        vec_drawing.add(pen);
        // maintaining the last 10000 points of the vec_drawing
        if (vec_drawing.size() > 10000) {
            vec_drawing.remove(0);
        }

        for (int i = 1; i < vec_drawing.size(); i++) {
            graph.draw(new Line2D.Double(this.complex_to_point(vec_drawing.get(i - 1)),
                    this.complex_to_point(vec_drawing.get(i))));
        }
    }
}