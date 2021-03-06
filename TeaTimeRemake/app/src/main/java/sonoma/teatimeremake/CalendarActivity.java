package sonoma.teatimeremake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import sonoma.teatimeremake.adapter.CalendarAdapter;
import sonoma.teatimeremake.adapter.FrontPageAdapter;
import sonoma.teatimeremake.util.CalendarCollection;
import sonoma.teatimeremake.util.DayCollection;
import sonoma.teatimeremake.util.FrontPageCollection;


public class CalendarActivity extends AppCompatActivity {
    public GregorianCalendar cal_month, cal_month_copy;
    private CalendarAdapter cal_adapter;
    private TextView tv_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DayCollection.clearEvents();
/*
        DayCollection.daysEvents = new ArrayList();
        CalendarCollection.date_collection_arr = new ArrayList();
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-11-13","Dad's Birthday", "Dad Birthday", -1));
*/



        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        cal_adapter = new CalendarAdapter(this, cal_month, CalendarCollection.date_collection_arr);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));

        ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });

        ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();

            }
        });


        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(cal_adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ((CalendarAdapter) parent.getAdapter()).setSelected(v, position);
                String selectedGridDate = CalendarAdapter.day_string.get(position);

                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*", "");
                int gridvalue = Integer.parseInt(gridvalueString);

                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v, position);

                ((CalendarAdapter) parent.getAdapter()).getPositionList(selectedGridDate, CalendarActivity.this);
                if (DayCollection.waitingToRun()) {
                    /*Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    StringBuilder tempDay = new StringBuilder();
                    CalendarCollection tempCal =DayCollection.daysEvents.get(0);
                    if(tempCal.time == -1)
                    {
                        tempDay.append("All Day Event - ");
                    }else{
                        int temphour = tempCal.hours/100;
                        if(tempCal.hours > 12){
                            temphour = temphour - 11;
                            tempDay.append(temphour);
                        }
                        tempDay.append(temphour);
                        tempDay.append(":");
                        tempDay.append(tempCal.mins);
                        tempDay.append(" - ");
                    }
                    tempDay.append(tempCal.eventName);
                    tempDay.append(" ");
                    //tempDay.append(tempCal.event_message);
                    //tempDay.append("\n");
                    Toast toast = Toast.makeText(context, tempDay.toString(), duration);
                    toast.show();*/
                    startActivity(new Intent(CalendarActivity.this, DayViewActivity.class));
                    DayCollection.notNext();
                    //DayCollection.clearEvents();
                }

            }

        });

        getFrontPageList();

        Collections.sort(FrontPageCollection.daysEvents);

        if(FrontPageCollection.daysEvents.size()>10){
            while(FrontPageCollection.daysEvents.size() > 10)
            FrontPageCollection.daysEvents.remove(FrontPageCollection.daysEvents.size());
        }

        ListView frontList;
        frontList = (ListView)findViewById(R.id.dayEventList);

        ArrayAdapter adapter=new FrontPageAdapter(this, R.layout.list_view_helper, FrontPageCollection.daysEvents);

        frontList.setAdapter(adapter);

        frontList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(CalendarActivity.this, NewEventActivity.class));
                refreshCalendar();
                //Snackbar.make(view, "This is for the adding elements.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        FloatingActionButton tester = (FloatingActionButton) findViewById(R.id.tester);
        tester.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*
                do the stuff you want to do in here, probably befor the pop-up
                */
                startActivity(new Intent(CalendarActivity.this, GroupCalendarSelectionActivity.class));
                refreshCalendar();
                //Snackbar.make(view, "This is for testing server-side.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        FloatingActionButton refresh = (FloatingActionButton) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*
                do the stuff you want to do in here, probably befor the pop-up
                */
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                //Snackbar.make(view, "This is for testing server-side.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    private void getFrontPageList() {
        GregorianCalendar todayCal = new GregorianCalendar();


        FrontPageCollection.daysEvents.clear();

        GregorianCalendar tempCal;

        int checkYear, checkMonth, checkDay;
        int todayYear, todayMonth, todayDay;

        todayYear = todayCal.get(GregorianCalendar.YEAR);
        todayMonth = todayCal.get(GregorianCalendar.MONTH);
        todayDay = todayCal.get(GregorianCalendar.DATE);

        for (int ii =0; ii < CalendarCollection.getLengthOfArray(); ii++){
            CalendarCollection calCol = CalendarCollection.date_collection_arr.get(ii);
            //String fulldate = calCol.getDate();
            String year = calCol.getDate().substring(0, 4);
            String month = calCol.getDate().substring(5,7);
            String day = calCol.getDate().substring(8, 10);

            checkYear = Integer.parseInt(year);
            checkMonth = Integer.parseInt(month);
            checkDay = Integer.parseInt(day);

            checkMonth=checkMonth-1;


            if(calCol.time == -1)
                tempCal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            else
                tempCal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(calCol.hours), Integer.parseInt(calCol.mins));

            if(todayYear==checkYear && todayMonth==checkMonth && todayDay==checkDay){
                FrontPageCollection.addEventToList(calCol);
            }else if(todayYear==checkYear && todayMonth<=checkMonth && todayDay<checkDay){
                FrontPageCollection.addEventToList(calCol);
            }else if(todayYear<checkYear){
                FrontPageCollection.addEventToList(calCol);
            }


            /*if(todayCal.equals(tempCal)){
                FrontPageCollection.addEventToList(calCol);
            }else if(todayCal.before(tempCal)){
                FrontPageCollection.addEventToList(calCol);
            }*/

        }
    }

    protected void setNextMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) + 1);
        }

    }

    protected void setPreviousMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
        }

    }

    public void refreshCalendar() {
        cal_adapter.refreshDays(cal_month);
        cal_adapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
