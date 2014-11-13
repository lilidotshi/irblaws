package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Laws implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8819518918887015803L;
	private ArrayList<Law> laws;
	
	public ArrayList<Law> getLaws(){
		return laws;
	}
	
	public int size(){
		return laws.size();
	}
}
