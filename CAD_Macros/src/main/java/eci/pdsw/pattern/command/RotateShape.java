/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.pattern.command;

import eci.pdsw.draw.model.Point;
import eci.pdsw.draw.model.Shape;
import java.util.List;

/**
 *
 * @author Esteban
 */
public class RotateShape implements Command{

    private final List<Shape> shapes;
    private final Integer index;
    private Point point1In;
    private Point point2In;

    public RotateShape(Integer index, List<Shape> shapes) {
        this.shapes = shapes;
        this.index = index;
    }

    @Override
    public void execute() {
        Float x1 = shapes.get(index).getPoint1().getX();Float y1 = shapes.get(index).getPoint1().getY();        
        Float x2 = shapes.get(index).getPoint2().getX();Float y2 = shapes.get(index).getPoint2().getY();
        point1In = Point.newPoint(x1, y1);
        point2In = Point.newPoint(x2, y2);
        Point point1 = shapes.get(index).getPoint1();
        Point point2 = shapes.get(index).getPoint2();
        Point minPointX = (Math.min(point1.getX(), point2.getX())== point1.getX()) ? point1:point2;
        Point minPointY = (Math.min(point1.getY(), point2.getY())== point1.getY()) ? point1:point2;
        Point maxPointX = (Math.max(point1.getX(), point2.getX())== point1.getX()) ? point1:point2;
        Point maxPointY = (Math.max(point1.getY(), point2.getY())== point1.getY()) ? point1:point2;
        if (minPointX==minPointY && minPointX!=maxPointX){
            minPointX.setX(Math.min(x1, x2)+Math.abs(y1-y2));
            minPointX.setY(Math.max(y1, y2));
            maxPointX.setX(Math.min(x1, x2));
            maxPointX.setY(Math.max(y1, y2)+Math.abs(x1-x2));
        }else if(minPointX==minPointY && minPointX==maxPointX){
            minPointX.setX(Math.min(x1, x2)+Math.abs(y1-y2));
            minPointX.setY(Math.max(y1, y2));
            maxPointY.setX(Math.min(x1, x2));
            maxPointY.setY(Math.max(y1, y2)+Math.abs(x1-x2));
            maxPointX = maxPointY;
        }else{
            minPointX.setX(Math.min(x1, x2));
            minPointX.setY(Math.max(y1, y2));
            maxPointX.setX(Math.min(x1, x2)+Math.abs(y1-y2));
            maxPointX.setY(Math.max(y1, y2)+Math.abs(x1-x2));
        }
        shapes.get(index).setPoint1(minPointX);
        shapes.get(index).setPoint2(maxPointX);
    }

    @Override
    public void undo() {
        shapes.get(index).setPoint1(point1In);
        shapes.get(index).setPoint2(point2In);
    }
    
}
