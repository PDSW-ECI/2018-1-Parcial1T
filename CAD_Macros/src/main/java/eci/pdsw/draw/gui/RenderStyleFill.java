/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.gui;

import eci.pdsw.draw.gui.shapes.empty.EmptyRenderer;
import eci.pdsw.draw.gui.shapes.fill.FillRenderer;

/**
 *
 * @author fchaves
 */
public class RenderStyleFill implements RenderStyle {
    private Style styles[] = { 
    	new Style(new EmptyRenderer(), "Empty Style")
    	, new Style(new FillRenderer(), "Fill Style")
    };

    @Override
    public Style[] styles() {
        return styles;
    }

}
