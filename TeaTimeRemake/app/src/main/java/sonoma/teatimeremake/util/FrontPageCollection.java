package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 12/8/2015.
 */
public class FrontPageCollection implements Comparable {
    public static ArrayList<FrontPageCollection> daysEvents;

    private CalendarCollection calEvent;
    private int time;
    private String eventString;


    FrontPageCollection(CalendarCollection cal_collection){
        this.calEvent=cal_collection;
        this.time = cal_collection.time;
        this.eventString = makeString(cal_collection);
    }

    private String makeString(CalendarCollection cal_collection) {
        StringBuilder tempDay = new StringBuilder();
        tempDay.append(cal_collection.date);
        tempDay.append(" - ");
        if(cal_collection.time == -1)
        {
            tempDay.append(" All Day - ");
        }else{
            tempDay.append(cal_collection.hours);
            tempDay.append(":");
            tempDay.append(cal_collection.mins);
            tempDay.append(" - ");
        }
        tempDay.append(cal_collection.eventName);
        //tempDay.append(tempCal.event_message);
        //tempDay.append("\n");
        return tempDay.toString();
    }

    public static void addEventToList(CalendarCollection cal_collection) {
        FrontPageCollection tempEvent = new FrontPageCollection(cal_collection);

        daysEvents.add(tempEvent);
    }

    public static int getNumEvents(){
        return daysEvents.size();
    }

    @Override
    public int compareTo(Object another) {
        int compareTime =((FrontPageCollection)another).getTime();

        return this.time-compareTime;
    }

    public int getTime() {
        return time;
    }

    public String getEventString() {
        return eventString;
    }
}
