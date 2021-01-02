package com.stdplatform.gui_selector.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.stdplatform.gui_selector.util.ImageDrawer;

public class ImageLabelResized extends JLabel {

	private int CalcX , CalcY , CalcWidth , CalcHeight;

	public ImageLabelResized(String text) {
		super(text);
	}

	@Override
	public void paint(Graphics g) {
		JPanel parent = (JPanel) this.getParent();
		ImageIcon icon = (ImageIcon) getIcon();
		if (icon != null) {
			ImageDrawer draw  = new ImageDrawer();
			draw.drawScaledImage(icon.getImage(), parent, g);
			CalcX = draw.getCalcX();
			CalcY = draw.getCalcY();
			CalcWidth = draw.getCalcWidth();
			CalcHeight = draw.getCalcHeight();
		}
	}

	public int getCalcX() {
		return CalcX;
	}
	public int getCalcY() {
		return CalcY;
	}
	public int getCalcWidth() {
		return CalcWidth;
	}

	public int getCalcHeight() {
		return CalcHeight;
	}


}