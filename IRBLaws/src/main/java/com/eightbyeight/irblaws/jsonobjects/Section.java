package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Section implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6437229750006718426L;
	private String section;
	private ArrayList<Content>sectioncontents;
	
	public String getSectionName (){
		return section;
	}
	
	public ArrayList<Content>getSectionContents(){
		return sectioncontents;
	}
}
