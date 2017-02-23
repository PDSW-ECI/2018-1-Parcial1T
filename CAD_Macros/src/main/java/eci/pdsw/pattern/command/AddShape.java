/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.pattern.command;

import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Point;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.draw.model.ShapeFactory;
import java.util.List;

/**
 *
 * @author Esteban
 */
public class AddShape implements Command{

    private final Point point1;
    private final Point point2;
    private final ShapeFactory shapeFactory;
    private final List<Shape> shapes;
    private final ElementType selectedElement;

    public AddShape(Point p1, Point p2, ElementType selectedElement, ShapeFactory shapeFactory, List<Shape> shapes) {
        this.point1 = p1;
        this.point2 = p2;
        this.shapeFactory = shapeFactory;
        this.shapes = shapes;
        this.selectedElement = selectedElement;
    }

    @Override
    public void execute() {
        Point spPoint1 = Point.newPoint(point1.getX(), point1.getY());
        Point spPoint2 = Point.newPoint(point2.getX(), point2.getY());
        shapes.add(shapeFactory.createShape(selectedElement, spPoint1, spPoint2));
    }

    @Override
    public void undo() {
        shapes.remove(shapes.size()-1);
    }
    
}
