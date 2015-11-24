package sonoma.teatimeremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import sonoma.teatimeremake.util.CalendarCollection;

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener{

    Button eventButton;
    String date, eventMessage;
    //for adding hours+mins
    int time;
    private Spinner inputHour, inputMin, inputDay, inputMonth, inputYear;
    private EditText inputMessage;
    private Switch inputAMPM;
    boolean ampm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        inputMessage = (EditText)findViewById(R.id.eventMessage);

        inputHour = (Spinner)findViewById(R.id.hourSpin);
        inputMin = (Spinner)findViewById(R.id.minuteSpin);
        inputAMPM = (Switch)findViewById(R.id.ampmSwitch) ;

        inputDay = (Spinner)findViewById(R.id.daySpinner);
        inputMonth = (Spinner)findViewById(R.id.monthSpinner);
        inputYear = (Spinner)findViewById(R.id.yearSpinner);

        eventButton = (Button) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(this);
    }



    public void onClick(View v){
        switch(v.getId()){
            case R.id.eventButton:

                String days, years;
                String months;

                ampm = inputAMPM.isChecked();

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

                //date = "2015-01-26";
                date = String.format("%s-%s-%s", years, months, days);
                //at moment, unused on purpose
                time = 0;
                eventMessage = inputMessage.getText().toString();
                //need to add time to CalendarCollection
                CalendarCollection.date_collection_arr.add(new CalendarCollection(date, eventMessage));
                finish();

            default:
                break;
        }

    }
}
