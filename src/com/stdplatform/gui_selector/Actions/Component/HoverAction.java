package com.stdplatform.gui_selector.Actions.Component;

import com.stdplatform.gui_selector.util.util;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class HoverAction extends MouseAdapter {

	private JComponent comp;
	private ImageIcon enteredImage, exitedImage;
	private Color enteredGroundColor, exitedGroundColor;
	private String EnterImagePath, ExitImagePath;

	public HoverAction(JComponent comp) {
		this.comp = comp;

	}

	public HoverAction() {
	}

	public void setComponent(JComponent comp) {
		this.comp = comp;
	}

	public void setEnteredImage(String path) {
		this.EnterImagePath = path;
		enteredImage = new ImageIcon(HoverAction.class.getResource(path));
	}

	public void setExitedImage(String path) {
		this.ExitImagePath = path;
		exitedImage = new ImageIcon(HoverAction.class.getResource(path));
	}

	public void setExitColorImage(Color color) {
		if (ExitImagePath == null)
			throw new RuntimeException("Image not found");

		exitedImage = new util().ChangeImageColor(ExitImagePath, color);
	}

	public void setEnterColorImage(Color color) {
		if (EnterImagePath == null)
			throw new RuntimeException("Image not found");

		enteredImage = new util().ChangeImageColor(EnterImagePath, color);
	}

	public void mouseEntered(MouseEvent me) {
		if (enteredImage != null) {
			ImageAction(enteredImage);
		}
		if (enteredGroundColor != null){
			comp.setBackground(enteredGroundColor);
		}
	}

	public void mouseExited(MouseEvent me) {
		if (exitedImage != null) {
			ImageAction(exitedImage);
		}

		if (exitedGroundColor != null){
			comp.setBackground(exitedGroundColor);
		}
	}

	public void ImageAction(ImageIcon actionType) {
		if (comp instanceof JLabel) {
			((JLabel) comp).setIcon(actionType);
		} else if (comp instanceof JButton) {
			((JButton) comp).setIcon(actionType);
		}
	}

	public Color getEnteredGroundColor() {
		return enteredGroundColor;
	}

	public void setEnteredGroundColor(Color enteredGroundColor) {
		this.enteredGroundColor = enteredGroundColor;
	}

	public Color getExitedGroundColor() {
		return exitedGroundColor;
	}

	public void setExitedGroundColor(Color exitedGroundColor) {
		this.exitedGroundColor = exitedGroundColor;
	}

}
