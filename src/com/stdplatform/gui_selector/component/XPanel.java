package com.stdplatform.gui_selector.component;

import javax.swing.JPanel;

public class XPanel extends JPanel {

	private static int id_counter = 0;

	private int id;

	public XPanel() {
		id = ++id_counter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
