package com.stdplatform.gui_selector.component;

import java.awt.Color;

import javax.swing.JLabel;

import com.stdplatform.gui_selector.Actions.Component.HoverAction;
import com.stdplatform.gui_selector.Constant.Color.HoverColor;
import com.stdplatform.gui_selector.Constant.Path.Path;

public class XLabel extends JLabel {

	private HoverAction hover;
	private boolean selected ;

	public XLabel() {
		initialize();
	}

	public XLabel(String text) {
		super(text);
		initialize();
	}

	private void initialize() {
		hover = new HoverAction();

	}

	public HoverAction getHoverAction() {
		return hover;
	}

	public void setSelectedAction (String Path, Color color){
		selected = true ;
	}

	public boolean isSelected(){
		return selected ;
	}

	public void setHoverAction(String Path, Color Enter, Color exit) {
		hover.setComponent(this);
		hover.setExitedImage(Path);
		hover.setEnteredImage(Path);
		hover.setEnterColorImage(Enter);
		hover.setExitColorImage(exit);
		this.setBackground(exit);
		addMouseListener(hover);
	}

	public void setHoverAction(Color Enter, Color exit) {
		this.setOpaque(true);
		hover.setComponent(this);
		hover.setEnteredGroundColor(Enter);
		hover.setExitedGroundColor(exit);
		addMouseListener(hover);
		this.setBackground(exit);
	}
}
