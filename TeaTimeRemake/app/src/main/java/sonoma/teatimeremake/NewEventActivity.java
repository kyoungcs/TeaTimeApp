package sonoma.teatimeremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import sonoma.teatimeremake.adapter.GroupCalendarAdapter;
import sonoma.teatimeremake.util.CalendarCollection;
import sonoma.teatimeremake.util.GroupCalendarCollection;
import sonoma.teatimeremake.util.GroupCalendarUtil;

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener{

    Button eventButton;
    String date, eventMessage, eventName;
    //for adding hours+mins
    int time;
    private Spinner inputHour, inputMin, inputDay, inputMonth, inputYear, groupCalSpinner;
    private EditText inputMessage, inputName;
    private Switch inputAMPM, inputGroup, inputAllDay;
    //boolean ampm;
    ArrayList<String> calList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        inputMessage = (EditText)findViewById(R.id.eventMessage);
        inputName = (EditText)findViewById(R.id.editName);

        inputHour = (Spinner)findViewById(R.id.hourSpin);
        inputMin = (Spinner)findViewById(R.id.minuteSpin);
        inputAMPM = (Switch)findViewById(R.id.ampmSwitch) ;

        inputGroup = (Switch)findViewById(R.id.groupSwitch);
        inputAllDay = (Switch)findViewById(R.id.alldaySwitch);

        inputDay = (Spinner)findViewById(R.id.daySpinner);
        inputMonth = (Spinner)findViewById(R.id.monthSpinner);
        inputYear = (Spinner)findViewById(R.id.yearSpinner);

        eventButton = (Button) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(this);

        groupCalSpinner = (Spinner) findViewById(R.id.grouCalSpinner);

        calList = new ArrayList<String>();
        add();
    }

    private void add() {
        for(int jj =0 ; jj<GroupCalendarUtil.groupCals.size(); jj++){
            calList.add(GroupCalendarUtil.groupCals.get(jj).getCalName());
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, calList);
        groupCalSpinner.setAdapter(adp);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.eventButton:

                String days, years;
                String months;

                String hoursStr, minStr;
                int hours, mins;

                hoursStr = inputHour.getSelectedItem().toString();
                minStr = inputMin.getSelectedItem().toString();

                hours = Integer.parseInt(hoursStr);
                if(hours == 12)
                    hours =0;
                hours = hours * 100;
                mins = Integer.parseInt(minStr);

                //ampm = inputAMPM.isChecked();

                days = inputDay.getSelectedItem().toString();
                months = inputMonth.getSelectedItem().toString();
                years = inputYear.getSelectedItem().toString();

                if(months.equals("January"))
                    months = "01";
                else if(months.equals("February"))
                    months = "02";
                else if(months.equals("March"))
                    months = "03";
                else if(months.equals("April"))
                    months = "04";
                else if(months.equals("May"))
                    months = "05";
                else if(months.equals("June"))
                    months = "06";
                else if(months.equals("July"))
                    months = "07";
                else if(months.equals("August"))
                    months = "08";
                else if(months.equals("September"))
                    months = "09";
                else if(months.equals("October"))
                    months = "10";
                else if(months.equals("November"))
                    months = "11";
                else if(months.equals("December"))
                    months = "12";

                if(inputAMPM.isChecked())
                    hours = hours + 1200;


                //date = "2015-01-26";
                date = String.format("%s-%s-%s", years, months, days);
                //at moment, unused on purpose
                if(inputAllDay.isChecked())
                    time = -1;
                else
                    time = hours+mins;

                eventName = inputName.getText().toString();
                eventMessage = inputMessage.getText().toString();
                //need to add time to CalendarCollection
                CalendarCollection.date_collection_arr.add(new CalendarCollection(date, eventName, eventMessage, hoursStr, minStr, time));
                if(inputGroup.isChecked()) {
                    addGroupEvent(groupCalSpinner.getSelectedItem().toString(),hoursStr, minStr);
                }
                finish();

            default:
                break;
        }
    }

    private void addGroupEvent(String str, String hoursStr, String minStr) {
        for(int hh =0; hh < GroupCalendarUtil.groupCals.size(); hh++){
            if(GroupCalendarUtil.groupCals.get(hh).getCalName() == str)
            GroupCalendarCollection.date_collection_arr.add(new GroupCalendarCollection(date, eventName, eventMessage, hoursStr, minStr, time, GroupCalendarUtil.groupCals.get(hh).getCalID(), str));
        }
    }
}
