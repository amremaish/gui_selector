package com.stdplatform.gui_selector.selector;

import java.awt.Graphics;

import com.stdplatform.gui_selector.Handler.MainHandler;
import com.stdplatform.gui_selector.Handler.PaletteHandler;
import com.stdplatform.gui_selector.component.ImageLabelResized;
import com.stdplatform.gui_selector.util.ImageDrawer;

public class Rectangle extends Shape {

	public Rectangle(ImageLabelResized LoadedImage, MainHandler dwr,
			PaletteHandler paletteHandler) {
		super(LoadedImage, dwr, paletteHandler);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		SharedGraphcis(g);

		if (icon != null) {
			g.fillRect(5, 5, getWidth() - 9, getHeight() - 9);
			ImageDrawer id = new ImageDrawer();
			id.setAtCenter();
			id.drawScaledImage(icon, super.getSuperPanel(), g);

		}

	}

}
