package eci.pdsw.draw.gui.shapes.empty;

import eci.pdsw.draw.gui.shapes.LineRenderer;
import eci.pdsw.draw.model.Line;
import java.awt.Color;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fchaves
 */
public class EmptyLineRenderer implements LineRenderer {

    @Override
    public void draw(Graphics2D g2, Line line) {
        g2.setPaint(Color.black);
        g2.drawLine(Math.round(line.getPoint1().getX()), Math.round(line.getPoint1().getY()), Math.round(line.getPoint2().getX()), Math.round(line.getPoint2().getY()));
    }
    
}
