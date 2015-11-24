package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 11/10/2015.
 */
public class CalendarCollection {
    public String date = "";
    public String event_message = "";
    public String time = "";

    public static ArrayList<CalendarCollection> date_collection_arr;

    public CalendarCollection(){
    }

    public  CalendarCollection(String date, String event_message, String time){

        this.date = date;
        this.event_message=event_message;
        this.time = time;

    }
}
