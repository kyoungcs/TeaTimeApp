package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 11/10/2015.
 */
public class GroupCalendarCollection {
    public String date = "";
    public String event_message = "";
    public int time = 0;
    public String eventName = "";
    public String hours, mins;

    public static ArrayList<GroupCalendarCollection> date_collection_arr;

    public GroupCalendarCollection(){
    }

    public GroupCalendarCollection(String date, String eventName, String event_message, int time){

        this.date = date;
        this.event_message=event_message;
        this.time = time;
        this.eventName = eventName;

    }
    public GroupCalendarCollection(String date, String eventName, String event_message, String hours, String mins, int time){

        this.date = date;
        this.event_message=event_message;
        this.hours =hours;
        this.mins = mins;
        this.time = time;
        this.eventName = eventName;

    }
}
