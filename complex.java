/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plottest;

/**
 *
 * @author erikp
 */
public class complex {
    public double real;
    public double imag;

    public complex() {
        this.real = 0;
        this.imag = 0;
    }

    public complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public complex(complex c) {
        this.real = c.real;
        this.imag = c.imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    @Override
    public String toString() {
        return "complex{" + "real=" + real + ", imag=" + imag + '}';
    }

    public complex add(complex a) {
        return new complex(a.real + this.real, a.imag + this.imag);
    }

    public complex mult(complex a) {
        return new complex(a.real * this.real - a.imag * this.imag, a.real * this.imag + a.imag * this.real);
    }

}
