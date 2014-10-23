package com.eightbyeight.irblaws.jsonobjects;

import java.util.ArrayList;

public class Section {
	private String section;
	private ArrayList<Content>sectioncontents;
	
	public String getSectionName (){
		return section;
	}
	
	public ArrayList<Content>getSectionContents(){
		return sectioncontents;
	}
}
