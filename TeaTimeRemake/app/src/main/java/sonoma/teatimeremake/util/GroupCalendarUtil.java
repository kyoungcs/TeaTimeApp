package sonoma.teatimeremake.util;

import java.util.ArrayList;

/**
 * Created by Austin on 12/9/2015.
 */
public class GroupCalendarUtil {
    public static ArrayList<GroupCalendarUtil> groupCals;

    private int calID;
    private String calName;

    public GroupCalendarUtil(int id, String name){
        this.calID=id;
        this.calName=name;
    }

    public String getCalName() {
        return calName;
    }

    public void setCalName(String calName) {
        this.calName = calName;
    }

    public int getCalID() {
        return calID;
    }

    public void setCalID(int calID) {
        this.calID = calID;
    }
}
