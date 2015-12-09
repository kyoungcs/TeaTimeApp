package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 11/24/2015.
 */
public class DayCollection implements Comparable {
    public static ArrayList<DayCollection> daysEvents;
    private static boolean runNext;

    private CalendarCollection calEvent;
    private int time;
    private String eventString;


    DayCollection(CalendarCollection cal_collection){
        this.calEvent=cal_collection;
        this.time = cal_collection.time;
        this.eventString = makeString(cal_collection);
    }

    private String makeString(CalendarCollection cal_collection) {
        StringBuilder tempDay = new StringBuilder();
        if(cal_collection.time == -1)
        {
            tempDay.append("All Day Event - ");
        }else{
            tempDay.append(cal_collection.hours);
            tempDay.append(":");
            tempDay.append(cal_collection.mins);
            tempDay.append(" - ");
        }
        tempDay.append(cal_collection.eventName);
        tempDay.append(" - ");
        tempDay.append(cal_collection.event_message);
        //tempDay.append(tempCal.event_message);
        //tempDay.append("\n");
        return tempDay.toString();
    }

    public static void addEventToday(CalendarCollection cal_collection) {
        DayCollection tempEvent = new DayCollection(cal_collection);

        daysEvents.add(tempEvent);
    }

    public static void clearEvents() {
        daysEvents.clear();
    }

    public static boolean hasEvents(){
        if(daysEvents.isEmpty())
            return false;
        else
            return true;
    }

    public static void startRunNext(){
        runNext = true;
    }

    public static boolean waitingToRun(){
        return runNext;
    }

    public static void notNext(){
        runNext = false;
    }

    @Override
    public int compareTo(Object another) {
        int compareTime =((DayCollection)another).getTime();

        return this.time-compareTime;
    }

    public int getTime(){return this.time;}

    public CalendarCollection getCalEvent(){return this.calEvent;}

    public String getEventString(){return this.eventString;}

}
