package com.eightbyeight.irblaws.jsonobjects;

import java.util.ArrayList;

public class Law {

	private String name;
	private ArrayList<String> value;
	private ArrayList<Section> content;
	
	public ArrayList<String> getValues(){
		return value;
	}
	
	public ArrayList<Section> getContent(){
		return content;
	}
	
	public String getLawName(){
		return name;
	}
}
