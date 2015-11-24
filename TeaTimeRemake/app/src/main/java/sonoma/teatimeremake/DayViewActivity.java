package sonoma.teatimeremake;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

//import java.util.ArrayList;

import sonoma.teatimeremake.util.CalendarCollection;
import sonoma.teatimeremake.util.DayCollection;

public class DayViewActivity extends AppCompatActivity {

    private TextView viewDayEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StringBuilder day = buildDay();

        viewDayEvent = (TextView) findViewById(R.id.dayEventView);


        setContentView(R.layout.activity_day_view);
        //viewDayEvent.setText(day.toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DayViewActivity.this, NewEventActivity.class));
                refreshView();
            }
        });
    }

    private StringBuilder buildDay(){
        StringBuilder tempDay = new StringBuilder();
        CalendarCollection tempCal;
        for(int ii=0; ii < DayCollection.daysEvents.size(); ii++){
            tempCal = DayCollection.daysEvents.get(ii);
            if(tempCal.time == -1)
            {
                tempDay.append("All Day Event - ");
            }else{
                tempDay.append(tempCal.hours);
                tempDay.append(":");
                tempDay.append(tempCal.mins);
                tempDay.append(" - ");
            }
            tempDay.append(tempCal.eventName);
            tempDay.append(" ");
            tempDay.append(tempCal.event_message);
            tempDay.append("\n");
        }


        return tempDay;
    }

    private void refreshView(){

    }

}
