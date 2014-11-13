package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;

/**
 * Created by lilidotshi on 11/13/2014.
 */
public class Signal implements Serializable {

    private String image;
    private String text;
    private String video;

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getVideo() {
        return video;
    }
}
