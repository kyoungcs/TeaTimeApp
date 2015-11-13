package sonoma.teatimeremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sonoma.teatimeremake.util.CalendarCollection;

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener{

    Button eventButton;
    String date, eventMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        eventButton = (Button) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(this);
    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.eventButton:
                date = "2015-11-26";
                eventMessage = "Thanksgiving";
                CalendarCollection.date_collection_arr.add(new CalendarCollection(date, eventMessage));

            default:
                break;
        }

    }
}
