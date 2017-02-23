/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.pattern.command;

import eci.pdsw.draw.model.Point;
import eci.pdsw.draw.model.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Esteban
 */
public class DuplicateShapes implements Command{

    private final List<Shape> shapes;
    private List<Shape> newShapes;

    public DuplicateShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void execute() {
        /*List<Point> newShapesFirstPoints=new LinkedList<>();
        List<Point> newShapesSecondPoints=new LinkedList<>();*/
        newShapes = new ArrayList<>();
        int displacementDelta=10+new Random(System.currentTimeMillis()).nextInt(50);
        
        for (Shape s:shapes){
            /*newShapesFirstPoints.add(new Point(s.getPoint1().getX(),s.getPoint1().getY()+displacementDelta));
            newShapesSecondPoints.add(new Point(s.getPoint2().getX(),s.getPoint2().getY()+displacementDelta));*/
            Shape nSp = s.cloneShape();
            nSp.setPoint1(new Point(s.getPoint1().getX(),s.getPoint1().getY()+displacementDelta));
            nSp.setPoint2(new Point(s.getPoint2().getX(),s.getPoint2().getY()+displacementDelta));
            newShapes.add(nSp);
        }
        /*Iterator<Point> it1=newShapesFirstPoints.iterator();
        Iterator<Point> it2=newShapesSecondPoints.iterator();
        
        while (it1.hasNext() && it2.hasNext()){
            newShapes.add(it1.next(), it2.next());
        }*/
        for (Shape sp: newShapes){
            shapes.add(sp);
        }
    }

    @Override
    public void undo() {
        while(shapes.size()>newShapes.size()){
            shapes.remove(shapes.size()-1);
        }
    }
    
}
