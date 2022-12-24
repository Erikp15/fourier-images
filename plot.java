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
public class plot extends JPanel{  
    private int scaling = 100;  
    private int offset = 500;
    private ArrayList<Point> cord = new ArrayList<Point>();
    private int sz;
    private int marg = 10;  

    public plot() {
        ArrayList<Point> p = new ArrayList<Point>();
        p.add(new Point(100+offset,100+offset));
        setCord(p);
        setSz(1);
    }
    public plot(complex a){
        ArrayList<Point> p = new ArrayList<Point>();
        p.add(new Point((int)(a.real*scaling)+offset, (int)((-a.imag)*scaling)+offset));
        setCord(p);
        setSz(1);
    }    

    public ArrayList<Point> getCord() {
        return cord;
    }

    public void setCord(ArrayList<Point> cord) {
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
    
    public void addpoint(complex a){
        cord.add(new Point((int)(a.real*scaling)+offset, (int)((-a.imag)*scaling)+offset));
        sz++;
    }

    public void editpoint(complex a,int i){
        cord.set(i, new Point((int)(a.real*scaling)+offset, (int)((-a.imag)*scaling)+offset));
    }    
    
    protected void paintComponent(Graphics grf){  
        //create instance of the Graphics to use its methods  
        super.paintComponent(grf);  
        Graphics2D graph = (Graphics2D)grf;  
          
        //Sets the value of a single preference for the rendering algorithms.  
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
          
        // get width and height  
        int width = getWidth();  
        int height = getHeight();  
           
        // draw graph  
        
        graph.draw(new Line2D.Double(width/2, marg, width/2, height-marg));  
        graph.draw(new Line2D.Double(marg, height/2, width-marg, height/2));  
        graph.setStroke(new BasicStroke(3));
        for(int i=0;i<cord.size();i++){
            graph.draw(new Line2D.Double(cord.get(i), cord.get(i)));
        }  

        
        //find value of x and scale to plot points  
        //double x = (double)(width-2*marg)/(cord.length-1);  
        //double scale = (double)(height-2*marg)/getMax();  
        
        //set color for points  
        graph.setPaint(Color.RED);  
          
        // set points to the graph  
    }  
      
    //create getMax() method to find maximum value  
    private int getMax(){  
        int max = -Integer.MAX_VALUE;  
        for(int i=0; i<cord.size(); i++){  
            if(cord.get(i).x>max)  
                max = cord.get(i).x;  
             
        }  
        return max;  
    }
}
