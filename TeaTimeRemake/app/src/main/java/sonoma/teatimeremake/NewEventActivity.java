package sonoma.teatimeremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sonoma.teatimeremake.util.CalendarCollection;

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener{

    Button eventButton;
    String date, eventMessage;
    //for adding hours+mins
    int time;
    private EditText inputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        inputMessage = (EditText)findViewById(R.id.eventMessage);


        eventButton = (Button) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(this);
    }



    public void onClick(View v){
        switch(v.getId()){
            case R.id.eventButton:

                date = "2015-11-26";
                //at moment, unused on purpose
                time = 0;
                eventMessage = inputMessage.getText().toString();
                //need to add time to CalendarCollection
                CalendarCollection.date_collection_arr.add(new CalendarCollection(date, eventMessage));

            default:
                break;
        }

    }
}
