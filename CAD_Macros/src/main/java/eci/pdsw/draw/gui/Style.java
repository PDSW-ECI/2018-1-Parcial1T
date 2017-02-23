package eci.pdsw.draw.gui;

import eci.pdsw.draw.gui.shapes.Renderer;

public class Style {
	private Renderer renderer;
	private String name;
	
	public Style(Renderer renderer, String name) {
		super();
		this.renderer = renderer;
		this.name = name;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	public String getName() {
		return name;
	}
	
	
}
