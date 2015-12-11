package sonoma.teatimeremake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import sonoma.teatimeremake.util.CalendarCollection;
import sonoma.teatimeremake.util.DayCollection;
import sonoma.teatimeremake.util.FrontPageCollection;
import sonoma.teatimeremake.util.GroupCalendarCollection;
import sonoma.teatimeremake.util.GroupCalendarUtil;
import sonoma.teatimeremake.util.GroupDayCollection;

public class TeaTimeMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_time_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DayCollection.daysEvents = new ArrayList();
        GroupDayCollection.daysEvents = new ArrayList();
        FrontPageCollection.daysEvents = new ArrayList();

        GroupCalendarUtil.groupCals = new ArrayList();
        GroupCalendarUtil.groupCals.add(new GroupCalendarUtil(50, "Basic Group Calendar"));
        GroupCalendarUtil.groupCals.add(new GroupCalendarUtil(100, "Other Group Calendar"));


        CalendarCollection.date_collection_arr = new ArrayList();
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-11-13", "Dad's Birthday", "Dad Birthday", -1));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-15", "Sister's Birthday", "Sister's Birthday", -1));
        //CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-11-13", "Dad's Birthday", "Dad Birthday", -1));
        //CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-15", "Sister's Birthday", "Sister's Birthday", -1));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-13", "Running", "running", -1));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-16", "Planning", "for Trip", -1));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-23", "Travel", "Travel", -1));
        //CalendarCollection.date_collection_arr.add(new CalendarCollection("2016-1-15", "A thing", "Sister's Birthday", -1));


        GroupCalendarCollection.date_collection_arr = new ArrayList();
        GroupCalendarCollection.date_collection_arr.add(new GroupCalendarCollection("2015-11-26", "Thanksgiving", "Thanksgiving", -1, GroupCalendarUtil.groupCals.get(0).getCalID() ,GroupCalendarUtil.groupCals.get(0).getCalName()));
        GroupCalendarCollection.date_collection_arr.add(new GroupCalendarCollection("2015-12-25", "Christmas", "Christmas", -1, GroupCalendarUtil.groupCals.get(0).getCalID() ,GroupCalendarUtil.groupCals.get(0).getCalName()));
        GroupCalendarCollection.date_collection_arr.add(new GroupCalendarCollection("2015-11-13", "Dad's Birthday", "Dad Birthday", -1, GroupCalendarUtil.groupCals.get(0).getCalID() ,GroupCalendarUtil.groupCals.get(0).getCalName()));
        GroupCalendarCollection.date_collection_arr.add(new GroupCalendarCollection("2015-12-15", "Sister's Birthday", "Sister's Birthday", -1, GroupCalendarUtil.groupCals.get(0).getCalID() ,GroupCalendarUtil.groupCals.get(0).getCalName()));


        startActivity(new Intent(TeaTimeMain.this, CalendarActivity.class));

    }

}
