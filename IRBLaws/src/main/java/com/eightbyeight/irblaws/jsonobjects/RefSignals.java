package com.eightbyeight.irblaws.jsonobjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lilidotshi on 11/13/2014.
 */
public class RefSignals implements Serializable {

    private static final long serialVersionUID = -2953792818273163549L;
    private ArrayList<Signals> refsignals = new ArrayList<Signals>();

    public ArrayList<Signals> getRefsignals(){
        return refsignals;
    }

    public int getSize(){
        return refsignals.size();
    }

    public ArrayList<Signal>getAllRefSignals(){
        ArrayList<Signal> allSignals = new ArrayList<Signal>();
        for (Signals signals : refsignals){
            allSignals.addAll(signals.getContent());
        }
        return allSignals;
    }
}
