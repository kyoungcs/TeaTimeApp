package sonoma.teatimeremake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import sonoma.teatimeremake.util.GroupCalendarUtil;

public class GroupCalendarSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    private int calID;
    private String calName;
    private ArrayList<String> calList;

    private Spinner calSel;
    private Button nextButton, newCalButton, inviteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_calendar_selection);

        calSel = (Spinner) findViewById(R.id.GroupCalSelectspinner);

        nextButton = (Button) findViewById(R.id.GoTobutton);
        nextButton.setOnClickListener(this);

        calList = new ArrayList<String>();
        add();

        inviteButton = (Button)findViewById(R.id.inviteActivity);
        inviteButton.setOnClickListener(this);

        newCalButton = (Button) findViewById(R.id.MakeNewCalendarButton);
        newCalButton.setOnClickListener(this);
    }

    public void add() {

        for(int jj =0 ; jj<GroupCalendarUtil.groupCals.size(); jj++){
            calList.add(GroupCalendarUtil.groupCals.get(jj).getCalName());
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, calList);
        calSel.setAdapter(adp);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.GoTobutton:
                calName = calSel.getSelectedItem().toString();
                calID = spinnerCalCheck();

                GroupCalendarUtil.findID = calID;

                startActivity(new Intent(GroupCalendarSelectionActivity.this, GroupCalendarActivity.class));
            case R.id.MakeNewCalendarButton:
                startActivity(new Intent(GroupCalendarSelectionActivity.this, NewCalendarActivity.class));

            case R.id.inviteActivity:
                startActivity(new Intent(GroupCalendarSelectionActivity.this, InvitingOtherPeopleActivity.class));

            default:
                break;
        }
    }

    private int spinnerCalCheck() {
        int tempInt =0;
        for(int vv =0;vv <GroupCalendarUtil.groupCals.size(); vv++){
            if(calName.equalsIgnoreCase(GroupCalendarUtil.groupCals.get(vv).getCalName()))
                tempInt = GroupCalendarUtil.groupCals.get(vv).getCalID();
        }
        return tempInt;
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
