package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 11/24/2015.
 */
public class GroupDayCollection implements Comparable {
    public static ArrayList<GroupDayCollection> daysEvents;
    private static boolean runNext;


    private GroupCalendarCollection calEvent;
    private int time;
    private String eventString;

    GroupDayCollection(GroupCalendarCollection cal_collection){
        this.calEvent=cal_collection;
        this.time = cal_collection.time;
        this.eventString = makeGroupString(cal_collection);
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

    private String makeGroupString(GroupCalendarCollection cal_collection){
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
        //tempDay.append(tempCal.event_message);
        //tempDay.append("\n");
        return tempDay.toString();
    }

    public static void addGroupEventToday(GroupCalendarCollection cal_collection){
        GroupDayCollection tempEvent = new GroupDayCollection(cal_collection);

        daysEvents.add(tempEvent);
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

    public String getEventString() {
        return eventString;
    }

    @Override
    public int compareTo(Object another) {
        int compareTime =((GroupDayCollection)another).getTime();

        return this.time-compareTime;
    }

    public int getTime() {
        return time;
    }
}
