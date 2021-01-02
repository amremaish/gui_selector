package com.stdplatform.gui_selector.Model;

import java.util.ArrayList;

import javax.swing.Box;

import com.stdplatform.gui_selector.util.pair;

public class ItemModel {
	private static int idCounter = 0;
	private String CompName;
	private String VBenchName;
	private int id;
	private String source;
	private ArrayList<Attribute> attributes;
	private ArrayList<Box> BoxContainerList;

	public ItemModel() {
		BoxContainerList = new ArrayList<Box>();
		attributes = new ArrayList<Attribute>();
		this.id = ++idCounter;
	}

	public void addAttribute(Attribute att) {
		attributes.add(att);
	}

	public Attribute getAttribute(int i) {
		return attributes.get(i);
	}

	public String getCompName() {
		return CompName;
	}

	public void setCompName(String compName) {
		CompName = compName;
	}

	public String getVBenchName() {
		return VBenchName;
	}

	public void setVBenchName(String vBenchName) {
		VBenchName = vBenchName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<Box> getBoxContainerList() {
		return BoxContainerList;
	}

	public void setBoxContainerList(ArrayList<Box> boxContainerList) {
		BoxContainerList = boxContainerList;
	}


}
