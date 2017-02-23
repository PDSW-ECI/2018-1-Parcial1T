/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.gui.shapes;

/**
 *
 * @author fchaves
 */
public interface Renderer {
    public LineRenderer createLineRenderer();
    public RectangleRenderer createRectangleRenderer();
    
}
