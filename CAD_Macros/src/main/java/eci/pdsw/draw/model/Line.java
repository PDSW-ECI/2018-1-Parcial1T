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
public class Line extends Shape {
    
    @Override
    public ElementType getElementType() {
        return ElementType.Line;
    }

    private Line(Point p1, Point p2) {
        super(p1,p2);        
    }
    
    static public Line newLine(Point p1,Point p2) {
        return new Line(p1,p2);
    }

    @Override
    public Shape cloneShape() {
        return new Line(this.getPoint1(), this.getPoint2());
    }
    
    
}
