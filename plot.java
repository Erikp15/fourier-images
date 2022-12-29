/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plottest;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

//Extends JPanel class  
public class plot extends JPanel {
    private int scaling = 100;
    private int offsetX = 442, offsetY = 436;
    // list of each of the end points of the vectors that are connected
    private ArrayList<complex> cord = new ArrayList<complex>();
    // list of each of the scaling coefficients
    private ArrayList<Double> coef = new ArrayList<Double>();
    // list of each of the pixels of the drawing
    private ArrayList<complex> drawing = new ArrayList<complex>();
    private int sz;
    private int marg = 0;

    public plot() {
        ArrayList<complex> p = new ArrayList<complex>();
        p.add(new complex(0, 0));
        ArrayList<Double> c = new ArrayList<Double>();
        c.add((double) 0);
        setCord(p);
        setCoef(c);
        setSz(1);
    }

    public plot(complex a) {
        ArrayList<complex> p = new ArrayList<complex>();
        p.add(a);
        ArrayList<Double> c = new ArrayList<Double>();
        c.add(a.length());
        setCord(p);
        setCoef(c);
        setSz(1);
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

    public int getSz() {
        return sz;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public int getMarg() {
        return marg;
    }

    public void setMarg(int marg) {
        this.marg = marg;
    }

    public void addpoint(complex a, double c) {
        cord.add(a);
        coef.add(c);
        sz++;
    }

    public void editpoint(complex a, int i, boolean tomult) {
        if (tomult) {
            cord.set(i, cord.get(i).mult(a));
        } else {
            cord.set(i, cord.get(i).add(a));
        }
    }

    public void replacepoint(complex a, int i) {
        cord.set(i, a);
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
        graph.setColor(new Color(0, 0, 255));
        graph.setStroke(new BasicStroke(2));
        // connecting the vector endpoints to build the fourier series
        for (int i = 1; i < cord.size(); i++) {
            graph.draw(new Line2D.Double(this.translate(cord.get(i - 1)), this.translate(cord.get(i))));
        }

        // maintaining only the last 100 points of the drawing
        drawing.add(cord.get(cord.size() - 1));
        if (drawing.size() > 100) {
            drawing.remove(0);
        }
        for (int i = 1; i < drawing.size(); i++) {
            graph.draw(new Line2D.Double(this.translate(drawing.get(i - 1)), this.translate(drawing.get(i))));
        }
    }
}
