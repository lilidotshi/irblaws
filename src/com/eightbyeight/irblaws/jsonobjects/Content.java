package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;

public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668945754818200291L;
	private String type;
	private String value;
	private String extras;
	public String getType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getExtras(){
		return extras;
	}
}
