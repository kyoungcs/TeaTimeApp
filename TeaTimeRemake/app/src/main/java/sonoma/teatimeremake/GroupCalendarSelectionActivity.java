package sonoma.teatimeremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GroupCalendarSelectionActivity extends AppCompatActivity {
    private int calID;
    private String calName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_calendar_selection);
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
