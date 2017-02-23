/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.gui.shapes.fill;

import eci.pdsw.draw.gui.shapes.Renderer;
import eci.pdsw.draw.gui.shapes.LineRenderer;
import eci.pdsw.draw.gui.shapes.RectangleRenderer;

/**
 *
 * @author fchaves
 */
public class FillRenderer implements Renderer {
    
	static final int STROKE_WIDTH = 1;
	
    @Override
    public LineRenderer createLineRenderer() {
        return new FillLineRenderer();
    }

    
    @Override
    public RectangleRenderer createRectangleRenderer() {
        return new FillRectangleRenderer();
    }


}
