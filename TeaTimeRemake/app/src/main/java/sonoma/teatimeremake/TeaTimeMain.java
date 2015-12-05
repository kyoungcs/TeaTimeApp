package sonoma.teatimeremake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import sonoma.teatimeremake.util.CalendarCollection;
import sonoma.teatimeremake.util.DayCollection;
import sonoma.teatimeremake.util.GroupCalendarCollection;
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
        CalendarCollection.date_collection_arr = new ArrayList();
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-11-13", "Dad's Birthday", "Dad Birthday", -1));

        GroupCalendarCollection.date_collection_arr = new ArrayList();
        GroupCalendarCollection.date_collection_arr.add(new GroupCalendarCollection("2015-11-26", "Thanksgiving", "Thanksgiving", -1));

        startActivity(new Intent(TeaTimeMain.this, CalendarActivity.class));

    }

}
