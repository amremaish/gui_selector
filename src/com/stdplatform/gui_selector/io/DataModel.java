package com.stdplatform.gui_selector.io;

import java.io.Serializable;
import java.util.ArrayList;
import com.stdplatform.gui_selector.Model.Attribute;

public class DataModel implements Serializable {

	private ArrayList<Double> LPWidthList;
	private ArrayList<Double> LPHeightList;
	private ArrayList<Double> LPXList;
	private ArrayList<Double> LPYList;
	private ArrayList<String> CompMameList;
	private ArrayList<Attribute> attList ;

	private String ImagePath;

	public  DataModel() {
		LPWidthList = new ArrayList<Double>();
		LPHeightList = new ArrayList<Double>();
		LPXList = new ArrayList<Double>();
		LPYList = new ArrayList<Double>();
		CompMameList = new ArrayList<String>();
		 attList  = new ArrayList<Attribute>();
	}

	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}

	public ArrayList<Double> getLPWidthList() {
		return LPWidthList;
	}

	public void setLPWidthList(ArrayList<Double> lPWidthList) {
		LPWidthList = lPWidthList;
	}

	public ArrayList<Double> getLPHeightList() {
		return LPHeightList;
	}

	public void setLPHeightList(ArrayList<Double> lPHeightList) {
		LPHeightList = lPHeightList;
	}

	public ArrayList<Double> getLPXList() {
		return LPXList;
	}

	public void setLPXList(ArrayList<Double> lPXList) {
		LPXList = lPXList;
	}

	public ArrayList<Double> getLPYList() {
		return LPYList;
	}

	public void setLPYList(ArrayList<Double> lPYList) {
		LPYList = lPYList;
	}

	public ArrayList<String> getCompNameList() {
		return CompMameList;
	}

	public void setCompNameList(ArrayList<String> CompNameList) {
		CompMameList = CompNameList;
	}

	public ArrayList<Attribute> getAttList() {
		return attList;
	}

	public void setAttList(ArrayList<Attribute> attList) {
		this.attList = attList;
	}




}
