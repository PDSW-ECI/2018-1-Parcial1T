/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.model;

/**
 *
 * @author fchaves
 */
public class Point {
    private Float x;

    private Float y;
    
    public Point(Float x,Float y) {
        this.x = x;
        this.y = y;
    }
    
    static public Point newPoint(Float x,Float y) {
        return new Point(x,y);
    }
    
    public Float getX() {
        return x;
    }
    
    public Float getY() {
        return y;
    }
    
    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }
    
}
