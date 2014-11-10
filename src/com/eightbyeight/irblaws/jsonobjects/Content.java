package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668945754818200291L;
	private String type;
	private String value;
	private ArrayList <String> images = new ArrayList<String>();
	private String extras;
	public String getType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}
	
	public ArrayList<String> getImages(){
		return images;
	}
	
	public String getExtras(){
		return extras;
	}
}
