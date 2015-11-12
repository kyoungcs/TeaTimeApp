package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 11/10/2015.
 */
public class CalendarCollection {
    public String date="";
    public String event_message = "";

    public static ArrayList<CalendarCollection> date_collection_arr;

    public CalendarCollection(){
        this.date_collection_arr = new ArrayList();
    }

    public  CalendarCollection(String date, String event_message){

        this.date = date;
        this.event_message=event_message;

    }
}
