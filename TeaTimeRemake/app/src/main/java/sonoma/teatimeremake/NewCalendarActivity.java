package sonoma.teatimeremake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sonoma.teatimeremake.util.GroupCalendarUtil;

public class NewCalendarActivity extends AppCompatActivity implements View.OnClickListener{

    EditText inputCal;
    Button inputButton;

    String newCalName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calendar);

        inputCal = (EditText)findViewById(R.id.newCal);

        inputButton = (Button)findViewById(R.id.CalendarButton);
        inputButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.CalendarButton:
                newCalName = inputCal.getText().toString();
                int tempInt = generateID();

                GroupCalendarUtil.groupCals.add(new GroupCalendarUtil(tempInt,newCalName));

                finish();
            default:
                break;
        }
    }

    private int generateID() {
        int tempInt;

        tempInt = (GroupCalendarUtil.groupCals.get(0).getCalID() + 5);

        return tempInt;
    }
}
