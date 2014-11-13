package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Law implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 533839060725549503L;
	private String name;
	private ArrayList<Section> content;
	
	public ArrayList<Section> getContent(){
		return content;
	}
	
	public String getLawName(){
		return name;
	}
}
