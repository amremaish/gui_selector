package com.stdplatform.gui_selector.component;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.stdplatform.gui_selector.Actions.Window.DragWindowRelocate;
import com.stdplatform.gui_selector.Actions.Window.WindowResizer;
import com.stdplatform.gui_selector.Actions.Window.WindowDrag;



public class XFrame extends JFrame {
	private WindowResizer cr;
	private DragWindowRelocate dwr;
	private WindowDrag WindowDrag;

	public XFrame() {
		initialize();
	}

	private void initialize() {
		cr = new WindowResizer();
		dwr = new DragWindowRelocate();

		setUndecorated(true);
		this.setSize(700, 400);
	}

	public DragWindowRelocate getDragWindowRelocate() {
		return dwr;
	}

	public void DragWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		cr.setMinimumSize(new Dimension(700, 400));
		cr.setMaximumSize(new Dimension(width, height));
		cr.registerComponent(this);
		cr.setSnapSize(new Dimension(1, 1));
		cr.setOndragEdgeListener(dwr);
	}

	public void setDragPanel(XPanel panel) {
		WindowDrag = new WindowDrag(this, panel);
		panel.addMouseListener(WindowDrag);
		WindowDrag.setDragWindowListener(dwr);
	}

}
