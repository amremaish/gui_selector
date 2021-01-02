package com.stdplatform.gui_selector.Model;

import javax.swing.JTextField;

public class Attribute {

	public static String STRING = "String";
	public static String INTEGER = "Integer";

	private String AttributeName;
	private String Type;
	private String Content ;
	private String VBenchName;
	private JTextField refTextField ;

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getVBenchName() {
		return VBenchName;
	}

	public void setVBenchName(String vBenchName) {
		VBenchName = vBenchName;
	}

	public String getAttributeName() {
		return AttributeName;
	}

	public void setAttributeName(String text) {
		this.AttributeName = text;
	}

	public JTextField getRefTextField() {
		return refTextField;
	}

	public void setRefTextField(JTextField refTextField) {
		this.refTextField = refTextField;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}



}
