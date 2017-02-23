/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.gui.shapes.empty;

import eci.pdsw.draw.gui.shapes.RectangleRenderer;
import eci.pdsw.draw.model.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author fchaves
 */
public class EmptyRectangleRenderer implements RectangleRenderer {

    @Override
    public void draw(Graphics2D g2, Rectangle rectangle) {
        g2.setPaint(Color.blue);

        Float width = Math.abs(rectangle.getPoint2().getX() - rectangle.getPoint1().getX());
        Float height = Math.abs(rectangle.getPoint2().getY() - rectangle.getPoint1().getY());
        g2.drawRect(Math.round(rectangle.getPoint1().getX()),Math.round(rectangle.getPoint1().getY()), Math.round(width), Math.round(height));
    }
    
}
