package sonoma.teatimeremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import sonoma.teatimeremake.util.GroupCalendarUtil;

public class InvitingOtherPeopleActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText inputMail;
    public Button inputButton;
    public Spinner groupSpinner;

    String sendEmail;
    String sendGroup;
    private ArrayList<String> calList;
    private int groupCalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviting_other_people);

        inputMail = (EditText) findViewById(R.id.sendInvite);

        inputButton = (Button)findViewById(R.id.inviteButton);
        inputButton.setOnClickListener(this);

        groupSpinner = (Spinner) findViewById(R.id.sendGroupCal);

        calList = new ArrayList<String>();
        add();

    }

    private void add() {

        for(int jj =0 ; jj<GroupCalendarUtil.groupCals.size(); jj++){
            calList.add(GroupCalendarUtil.groupCals.get(jj).getCalName());
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, calList);
        groupSpinner.setAdapter(adp);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.inviteButton:
                sendEmail = inputMail.getText().toString();
                sendGroup = groupSpinner.getSelectedItem().toString();

                groupCalID = spinnerCalCheck();

                //GroupCalendarSelectionActivity.add();
                finish();
            default:
                break;
        }
    }

    private int spinnerCalCheck() {
        int tempInt =0;
        for(int vv =0;vv <GroupCalendarUtil.groupCals.size(); vv++){
            if(sendGroup.equalsIgnoreCase(GroupCalendarUtil.groupCals.get(vv).getCalName()))
                tempInt = GroupCalendarUtil.groupCals.get(vv).getCalID();
        }
        return tempInt;
    }


}
