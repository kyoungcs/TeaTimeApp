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



    DayCollection(CalendarCollection cal_collection){
        this.calEvent=cal_collection;
        this.time = cal_collection.time;
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

}
