package com.stdplatform.gui_selector.component;


import javax.swing.JDialog;
import javax.swing.JFrame;
import com.stdplatform.gui_selector.Actions.Window.WindowDrag;

public class XDialog extends JDialog {

	public static final int OK_CANCEL = 1;
	public static final int OK = 2;

	public XDialog(JFrame windowForComponent) {
		super(windowForComponent);
		initialize();
	}

	private void initialize() {
		setModal(true);
		setUndecorated(true);
		setResizable(false);

	}

	public void setDragPanel(XPanel panel) {
		panel.addMouseListener( new WindowDrag(this, panel));
	}

}
