package com.stdplatform.gui_selector.util;

import java.util.ArrayList;
import java.util.TreeMap;

import com.stdplatform.gui_selector.selector.Shape;

public class MapComponent {
	private TreeMap<String, ArrayList<Shape>> ShapesMap;
	private  ArrayList<Shape>shapeList ;

	public MapComponent( ArrayList<Shape>shapeList){
		this.shapeList = shapeList ;
		ShapesMap = new TreeMap<String, ArrayList<Shape>>();
		map();
	}

	private void map() {
		for (int i = 0; i < shapeList.size(); i++) {
		String compName = shapeList.get(i).getItemModel().getCompName();
			add(compName, shapeList.get(i));
		}
	}

	private void add(String compName, final Shape shape) {
		if (ShapesMap.get(compName) == null) {
			ArrayList<Shape> newList = new ArrayList<Shape>();
			newList.add(shape);
			ShapesMap.put(compName, newList);
		} else {
			ShapesMap.get(compName).add(shape);
		}
	}

	public TreeMap<String, ArrayList<Shape>>  getShapesMap(){
		return ShapesMap ;
	}

}
