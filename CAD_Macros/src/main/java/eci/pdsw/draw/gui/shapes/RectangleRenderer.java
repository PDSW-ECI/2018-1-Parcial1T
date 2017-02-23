/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.gui.shapes;

import eci.pdsw.draw.model.Rectangle;
import java.awt.Graphics2D;

/**
 *
 * @author fchaves
 */
public interface RectangleRenderer {
    public void draw(Graphics2D g,Rectangle rectangle);
}
