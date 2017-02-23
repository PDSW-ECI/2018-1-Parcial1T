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
public class ShapeFactory {
    
    public Shape createShape(ElementType elem,Point p1,Point p2) {
        Shape result = null;
        switch(elem) {
            case Line: 
                result = Line.newLine(p1, p2);
                break;
            case Rectangle: {
                Point ul = Point.newPoint(Math.min(p1.getX(), p2.getX())
                                         ,Math.min(p1.getY(), p2.getY()));
                Point lr = Point.newPoint(Math.max(p1.getX(), p2.getX())
                                         ,Math.max(p1.getY(), p2.getY()));
                
                result = Rectangle.newRectangle(ul, lr);
            } 
        }
        return result;
    }
}
