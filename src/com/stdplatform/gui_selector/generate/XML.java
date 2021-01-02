package com.stdplatform.gui_selector.generate;

public class XML {

	String Tag ;

	public XML (){
		Tag ="";
	}

	public void CreateTag(String def){
		Tag+="<"+def;
	}

	public void addAttribute(String name , String value){
		Tag+=" "+name+" = "+"\""+value+"\"";

	}

	public String getTag(){
		return Tag+" />";
	}

}
