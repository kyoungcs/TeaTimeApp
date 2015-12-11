package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 12/8/2015.
 */
public class FrontPageCollection implements Comparable {
    public static ArrayList<FrontPageCollection> daysEvents;

    private CalendarCollection calEvent;
    private int time;
    private int date;
    private String eventString;


    FrontPageCollection(CalendarCollection cal_collection){
        this.calEvent=cal_collection;
        this.time = cal_collection.time;
        this.date = dateInt(cal_collection.date);
        this.eventString = makeString(cal_collection);
    }

    private int dateInt(String date) {
        int checkYear, checkMonth, checkDay;

        String year = date.substring(0, 4);
        String month = date.substring(5,7);
        String day = date.substring(8, 10);

        checkYear = Integer.parseInt(year);
        checkMonth = Integer.parseInt(month);
        checkDay = Integer.parseInt(day);

        return (checkYear*10000)+ (checkMonth * 100)+(checkDay);

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
        int compareDay =((FrontPageCollection)another).getDate();

        return this.date-compareDay;
    }

    public int getTime() {
        return time;
    }

    public String getEventString() {
        return eventString;
    }

    public int getDate() {
        return date;
    }
}
