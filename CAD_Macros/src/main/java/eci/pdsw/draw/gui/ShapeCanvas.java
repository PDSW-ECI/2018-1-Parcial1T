/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.gui;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.gui.shapes.LineRenderer;
import eci.pdsw.draw.gui.shapes.RectangleRenderer;
import eci.pdsw.draw.gui.shapes.Renderer;
import eci.pdsw.draw.model.Line;
import eci.pdsw.draw.model.Rectangle;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.pattern.observer.Observer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.util.List;

/**
 *
 * @author fchaves
 */
public class ShapeCanvas extends JPanel implements Observer {
    IController controller; 
    
    public ShapeCanvas(IController controller) {
        this.controller = controller;
        mouseControl();
        this.controller.addObserver(this);
    }
    
    @Override
    public void update() {
        repaint(); 
    }
    
    private void mouseControl() {
        
        addMouseListener(new MouseAdapter() {
            Point startDrag, endDrag;

            @Override
            public void mousePressed(MouseEvent e) {
               startDrag = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                if (Math.abs(endDrag.x - startDrag.x) > 2 && Math.abs(endDrag.y - startDrag.y) > 2) {
                    controller.addShapeFromScreenPoints(startDrag, endDrag);
                }
                startDrag = null;
                endDrag = null;
            }
        });
    }

    
    public void duplicateShapes(){
        controller.duplicateShapes();
        this.repaint();
    }
    
    public void rotateSelectedShape(Integer index){
        controller.rotateSelectedShape(index);
        this.repaint();
    }
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        List<Shape> list = controller.getShapes();
        Renderer renderer = controller.getRenderer();
        
        LineRenderer lineRenderer = renderer.createLineRenderer();
        RectangleRenderer rectangleRenderer = renderer.createRectangleRenderer();
        
        
        for(Shape shape : list) {
            switch(shape.getElementType()) {
                case Line: 
                    lineRenderer.draw(g2, (Line)shape);
                    break;
                case Rectangle:
                    rectangleRenderer.draw(g2, (Rectangle)shape);
                    break;
            }
        }
    } 
    
}