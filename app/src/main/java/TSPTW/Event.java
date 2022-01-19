package TSPTW;

import TSPTW.Position;
import TSPTW.Time;

import java.util.*;

public class Event implements Comparable<Event>{
    // Function
    public Event(final String p_title,
                 final Position p_position,
                 final Time p_starTime,
                 final Time p_endTime,
                 final Time p_serviceTime,
                 final int p_type,
                 final String p_description) {
        title = p_title;
        position = p_position;
        starTime = p_starTime;
        endTime = p_endTime;
        serviceTime = p_serviceTime;
        type = p_type;
        description = p_description;
    }

    // Set function
    public void setTimeWindow(final long p_starTime, final long p_endTime) {
        starTime.setTime(p_starTime);
        endTime.setTime(p_endTime);
    }

    // Print function
    @Override
    public String toString() {
        String typeName = "None";
        if (type == START) {
            typeName = "Start event";
        } else if (type == END) {
            typeName = "End event";
        } else if (type == NEW) {
            typeName = "New event";
        }else if (type == OLD) {
            typeName = "Old event";
        }

        return "\"Title\":\"" + title + "\"\n," +
                position + "\n," +
                "\"Start Time\":\"" + starTime + "\"\n,"+
                "\"End Time\":\"" + endTime + "\"\n," +
                "\"Service Time\":\"" + serviceTime.toSerString() + "\"\n,"+
                "\"Type\":\"" + typeName + "\"\n," +
                "\"Description\":\"" + description + "\"\n";
    }

    @Override
    public int compareTo(Event e2) {
        if (starTime.getTime() < e2.starTime.getTime()) {
            return -1;
        } else if (starTime.getTime() > e2.starTime.getTime()) {
            return 1;
        } else if (starTime.getTime() == e2.starTime.getTime()) {
            if (endTime.getTime() < e2.endTime.getTime()) {
                return -1;
            } if (endTime.getTime() > e2.endTime.getTime()) {
                return 1;
            }
        }
        return 0;
    }

    // Data
    public String title;
    public Position position;
    public Time starTime;
    public Time endTime;
    public Time serviceTime;
    public int type = 0; // 0 : None, 1 : Start event, 2 : End event, 3 : New event, 4 : Old event.
    public String description;

    // Static final type
    static public final int NONE = 0;  // 0 : None
    static public final int START = 1; // 1 : Start event
    static public final int END = 2;   // 2 : End event
    static public final int NEW = 3;   // 3 : New event
    static public final int OLD = 4;   // 4 : Old event
}
