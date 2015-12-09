package sonoma.teatimeremake;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import sonoma.teatimeremake.adapter.DayViewAdapter;
import sonoma.teatimeremake.util.CalendarCollection;
import sonoma.teatimeremake.util.DayCollection;

public class DayViewActivity extends AppCompatActivity  {
//    private TextView viewDayEvent;
    private ListView dayEventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);


        Collections.sort(DayCollection.daysEvents);

        dayEventList = (ListView)findViewById(R.id.dayEventList);

        ArrayAdapter adapter=new DayViewAdapter(this, R.layout.list_view_helper,DayCollection.daysEvents);

        dayEventList.setAdapter(adapter);

        dayEventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DayViewActivity.this, NewEventActivity.class));
                refreshView();
            }
        });
    }

    private ArrayList<String> buildDay(){

        ArrayList<String> dayEvents = new ArrayList<String>();

        StringBuilder tempBuild;

        for(int ii=0; ii < DayCollection.daysEvents.size(); ii++){
            tempBuild = buildEvent(DayCollection.daysEvents.get(ii).getCalEvent());
            //dayEvents.add(tempBuild.toString());

        }


        return dayEvents;
    }

    private StringBuilder buildEvent(CalendarCollection tempCal){
        StringBuilder event = new StringBuilder();
        //tempCal = DayCollection.daysEvents.get(ii).getCalEvent();
        if(tempCal.time == -1)
        {
            event.append("All Day Event - ");
        }else{
            event.append(tempCal.hours);
            event.append(":");
            event.append(tempCal.mins);
            event.append(" - ");
        }
        event.append(tempCal.eventName);
        event.append(" - ");
        event.append(tempCal.event_message);
        event.append("\n");

        return event;

    }

    private void refreshView(){

    }
}
