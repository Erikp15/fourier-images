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
    private ArrayList<complex> cord = new ArrayList<complex>();
    private int sz;
    private int marg = 0;

    public plot() {
        ArrayList<complex> p = new ArrayList<complex>();
        p.add(new complex(offsetX, offsetY));
        setCord(p);
        setSz(1);
    }

    public plot(complex a) {
        ArrayList<complex> p = new ArrayList<complex>();
        p.add(a);
        setCord(p);
        setSz(1);
    }

    public ArrayList<complex> getCord() {
        return cord;
    }

    public void setCord(ArrayList<complex> cord) {
        this.cord = cord;
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

    public void addpoint(complex a) {
        cord.add(a);
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
        for (int i = 0; i < cord.size(); i++) {
            graph.draw(new Line2D.Double(new Point(offsetX, offsetY), this.translate(cord.get(i))));
        }

        // find value of x and scale to plot points
        // double x = (double)(width-2*marg)/(cord.length-1);
        // double scale = (double)(height-2*marg)/getMax();

        // set color for points
        graph.setPaint(Color.RED);

        // set points to the graph
    }
}
