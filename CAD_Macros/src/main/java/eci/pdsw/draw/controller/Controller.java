/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.controller;

import eci.pdsw.pattern.command.Command;
import eci.pdsw.draw.gui.shapes.Renderer;
import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.ShapeFactory;
import eci.pdsw.draw.model.Point;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.pattern.command.AddShape;
import eci.pdsw.pattern.command.DuplicateShapes;
import eci.pdsw.pattern.command.RotateShape;
import eci.pdsw.pattern.observer.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Logger;
import javax.naming.OperationNotSupportedException;

/**
 *
 * @author fchaves
 */
public class Controller implements IController {
    private ElementType selectedElement = ElementType.Line;
    
    private final ShapeFactory shapeFactory = new ShapeFactory();
    private final List<Shape> shapes = new ArrayList<>();
    
    private List<Observer> observers = new ArrayList<>();
        
    private Renderer renderer;
    private Stack<Command> undo;
    private Stack<Command> redo;
    
    public Controller() {
        undo = new Stack<Command>();
        redo = new Stack<Command>();
    }
    
    @Override
    public void addShapeFromScreenPoints(java.awt.Point p1,java.awt.Point p2) {
        Point mp1 = Point.newPoint(new Float(p1.x), new Float(p1.y));
        Point mp2 = Point.newPoint(new Float(p2.x), new Float(p2.y));
                
        ElementType actualElementType = getSelectedElementType();
    	setSelectedElementType(actualElementType);
        addShape(mp1, mp2);                 

    }

    
     /**
     * Duplica todas las figuras, y las ubica en una nueva posicion.
     * @pre la coleccion 'shapes' no tiene referencias duplicadas
     * @pos la coleccion 'shapes' contiene el doble de figuras
     * @pos la coleccion 'shapes' no tiene referencias duplicadas
     */
    @Override
    public void duplicateShapes(){
        Command comm = new DuplicateShapes(shapes);
        undo.push(comm);
        comm.execute();
        redo.clear();
        notifyObservers();
    }
  
    
    @Override
    public void addShape(Point p1,Point p2) {
        
        Command add = new AddShape(p1,p2,selectedElement,shapeFactory,shapes);
        //shapes.add(shapeFactory.createShape(selectedElement, p1, p2));
        
        undo.add(add);
        add.execute();
        redo.clear();
        notifyObservers();
    }




    
    @Override
    public void undo() {
    	if( !undo.isEmpty()){
            Command comm = undo.pop();
            redo.push(comm);
            comm.undo();
        }
        notifyObservers();
    }

    @Override
    public void redo() {
    	if( !redo.isEmpty()){
            Command comm = redo.pop();
            undo.push(comm);
            comm.execute();
        }
        notifyObservers();
    }

    @Override
    public void addShape(Integer index, Shape shape) {
        addShape(shape.getPoint1(),shape.getPoint2());
        //shapes.add(index,shape);
        //notifyObservers();
    }   
    
    @Override
    public void deleteShape(Integer index) {
        int idx = index;
        shapes.remove(idx);
        
        //notificar a la capa de presentación
        notifyObservers();
    }
    
    /**
     * Rota la figura correspondiente a la posicion 'index' un angulo
     * de 90 grados a la derecha, usando como eje de rotación la esquina
     * inferior izquierda del rectángulo que contenga a la figura.
     * @param index la posicion de la figura en el conjunto de figuras
     * del controlador
     */
    @Override
    public void rotateSelectedShape(Integer index) {
        Command comm = new RotateShape(index,shapes);
        undo.add(comm);
        comm.execute();
        redo.clear();
        //notificar a la capa de presentación
        notifyObservers();       
    }    
    
    
    @Override
    public void setRenderer(Renderer renderer) {
    	this.renderer = renderer;
    	notifyObservers();
    }
    
    @Override
    public Renderer getRenderer() {
    	return this.renderer;
    }

    
    @Override
    public List<Shape> getShapes() {
        return shapes;
    }
    
    @Override
    public void setSelectedElementType(ElementType elementType) {
        this.selectedElement = elementType;
    }  
    
    @Override
    public ElementType getSelectedElementType() {
        return this.selectedElement;
    }

    @Override
    public void addObserver(Observer o) {
            observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
            observers.remove(o);
    }

    @Override
    public void notifyObservers() {
            for(Observer o : observers) {
                    o.update();
            }
    }

    @Override
    public void startMacroRecording() {
        //IMPLEMENTAR
        LOG.info("Start Macro Recording: not implemented.");
        notifyObservers();
        
    }
    private static final Logger LOG = Logger.getLogger(Controller.class.getName());

    @Override
    public void stopMacroRecording() {
        //IMPLEMENTAR
        LOG.info("Stop Macro Recording: not implemented.");
        notifyObservers();
        
    }

    @Override
    public void runLastRecordedMacro() {
        //IMPLEMENTAR
        LOG.info("Run Macro: not implemented.");
        notifyObservers();
        
    }

    @Override
    public void newDraw() {
        shapes.clear(); 
        notifyObservers();
    }


}
