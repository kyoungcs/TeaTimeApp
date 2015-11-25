package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 11/24/2015.
 */
public class GroupDayCollection {
    public static ArrayList<CalendarCollection> daysEvents;
    private static boolean runNext;

    public static void addEventToday(CalendarCollection cal_collection) {
        daysEvents.add(cal_collection);
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
}
