package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lilidotshi on 11/13/2014.
 */
public class Signals implements Serializable {
    private static final long serialVersionUID = 6787372847082866437L;

    private String name;
    private ArrayList<Signal> content;

    public ArrayList<Signal> getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSignals(){
        return content.size();
    }
}
