/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fourier_series;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

//Extends JPanel class  
public class plot extends JPanel {
    private int scaling = 100;
    private int offsetX = 442, offsetY = 436;
    private int marg = 0;
    // list of each of the end points of the vectors that are connected
    private ArrayList<complex> cord = new ArrayList<complex>();
    // list of each of the scaling coefficients
    private ArrayList<Double> coef = new ArrayList<Double>();
    // list of each of the rotation speeds
    private ArrayList<Double> rotation = new ArrayList<Double>();
    // list of each of the pixels of the drawing
    private ArrayList<complex> drawing = new ArrayList<complex>();

    public plot() {
        ArrayList<complex> p = new ArrayList<complex>();
        ArrayList<Double> c = new ArrayList<Double>();
        ArrayList<Double> r = new ArrayList<Double>();
        setCord(p);
        setCoef(c);
        setRotation(r);
    }

    public plot(complex a, double d) {
        ArrayList<complex> p = new ArrayList<complex>();
        p.add(a);
        ArrayList<Double> c = new ArrayList<Double>();
        c.add(a.length());
        ArrayList<Double> r = new ArrayList<Double>();
        r.add(d);
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

    public ArrayList<Double> getCoef() {
        return coef;
    }

    public void setCoef(ArrayList<Double> coef) {
        this.coef = coef;
    }

    public ArrayList<complex> getDrawing() {
        return drawing;
    }

    public void setDrawing(ArrayList<complex> drawing) {
        this.drawing = drawing;
    }

    public int getMarg() {
        return marg;
    }

    public void setMarg(int marg) {
        this.marg = marg;
    }

    public void addpoint(complex a, double r) {
        cord.add(a);
        coef.add(a.length());
        rotation.add(r);
        System.out.println("");
    }

    public void removepoint(int i) {
        cord.remove(i);
        coef.remove(i);
        rotation.remove(i);
    }

    public void editpoint(complex a, int i, boolean tomult) {
        if (tomult) {
            System.out.println(cord.get(i));
            cord.set(i, cord.get(i).mult(a));
            System.out.println(a);
            // cord.set(i, cord.get(i).mult(new complex(1 / cord.get(i).length(),
            // 0)).mult(new complex(coef.get(i), 0)));
            System.out.println(cord.get(i));
            System.out.println("");
        } else {
            cord.set(i, cord.get(i).add(a));
            coef.set(i, cord.get(i).length());
        }
    }

    public void replacepoint(complex a, int i, double r) {
        cord.set(i, a);
        coef.set(i, cord.get(i).length());
        rotation.set(i, r);
    }

    public Point translate(complex a) {
        return new Point((int) Math.round(a.real * scaling) + offsetX, (int) Math.round((-a.imag) * scaling) + offsetY);
    }

    protected void paintComponent(Graphics grf) {
        // create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;

        // Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        this.offsetX = width / 2;
        this.offsetY = height / 2;

        graph.draw(new Line2D.Double(width / 2, marg, width / 2, height - marg));
        graph.draw(new Line2D.Double(marg, height / 2, width - marg, height / 2));
        graph.setColor(new Color(155, 155, 155));
        graph.setStroke(new BasicStroke(2));
        // connecting the vector endpoints to build the fourier series
        complex pen = new complex(0, 0);
        for (int i = 0; i < cord.size(); i++) {
            graph.draw(new Line2D.Double(this.translate(pen), this.translate(pen.add(cord.get(i)))));
            pen = pen.add(cord.get(i));
        }
        graph.setColor(new Color(55, 55, 55));
        // maintaining only the last 100 points of the drawing

        drawing.add(pen);
        if (drawing.size() > 10000) {
            drawing.remove(0);
        }

        for (int i = 1; i < drawing.size(); i++) {
            graph.draw(new Line2D.Double(this.translate(drawing.get(i - 1)), this.translate(drawing.get(i))));
        }
    }
}