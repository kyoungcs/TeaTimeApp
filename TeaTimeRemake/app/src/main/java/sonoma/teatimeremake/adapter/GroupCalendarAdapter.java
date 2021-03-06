package sonoma.teatimeremake.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import sonoma.teatimeremake.R;
import sonoma.teatimeremake.util.GroupCalendarCollection;

import sonoma.teatimeremake.util.GroupCalendarUtil;
import sonoma.teatimeremake.util.GroupDayCollection;

/**
 * Created by Austin on 11/7/2015.
 */
public class GroupCalendarAdapter extends BaseAdapter {

    private Context context;

    private java.util.Calendar month;
    public GregorianCalendar pmonth;

    public GregorianCalendar pmonthMaxSet;
    private GregorianCalendar selectedDate;
    int firstDay, maxWeekNumber, maxP, calMaxP, lastWeekDay, leftDays, mnthlength;
    String itemvalue, currentDateString;
    DateFormat df;

    private ArrayList<String> items;
    public static List<String> day_string;
    private View previousView;
    public ArrayList<GroupCalendarCollection> date_collection_arr;

    public GroupCalendarAdapter(Context context, GregorianCalendar monthCalendar, ArrayList<GroupCalendarCollection> date_collection_arr){
        this.date_collection_arr = date_collection_arr;
        GroupCalendarAdapter.day_string = new ArrayList<String>();
        Locale.setDefault(Locale.US);
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        this.context = context;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);

        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        currentDateString = df.format(selectedDate.getTime());
        refreshDays();
    }

    public void setItems(ArrayList<String> items){
        for(int ii = 0; ii != items.size(); ii++){
            if(items.get(ii).length() ==1){
                items.set(ii, "0" + items.get(ii));
            }
        }
        this.items = items;
    }

    @Override
    public int getCount() {
        return day_string.size();
    }

    @Override
    public Object getItem(int position) {
        return day_string.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView dayView;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.cal_item, null);
        }

        dayView = (TextView) v.findViewById(R.id.date);
        String[] separatedTime = day_string.get(position).split("-");

        String gridvalue = separatedTime[2].replaceFirst("^0*", "");
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {
            dayView.setTextColor(Color.WHITE);
        }

        if (day_string.get(position).equals(currentDateString)) {

            v.setBackgroundColor(Color.CYAN);
        } else {
            v.setBackgroundColor(Color.parseColor("#343434"));
        }

        dayView.setText(gridvalue);

        String date = day_string.get(position);

        if (date.length() == 1) {
            date = "0" + date;
        }
        String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }
        ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
        if (date.length() > 0 && items != null && items.contains(date)) {
            iw.setVisibility(View.VISIBLE);
        } else {
            iw.setVisibility(View.GONE);
        }

        setEventView(v, position, dayView);

        return v;
    }
    public View setSelected(View view, int pos){
        if(previousView != null){
            previousView.setBackgroundColor(Color.parseColor("#343434"));
        }

        view.setBackgroundColor(Color.CYAN);

        int len = day_string.size();
        if(len>pos){
            if(! day_string.get(pos).equals(currentDateString)){
                previousView = view;
            }
        }

        return view;
    }
    public void refreshDays(GregorianCalendar cal_month){
        items.clear();
        day_string.clear();
        Locale.setDefault(Locale.US);
        this.month = (Calendar)cal_month.clone();
        refreshDays();

    }
    public void refreshDays(){
        this.pmonth = (GregorianCalendar)this.month.clone();
        GregorianCalendar tempMonth = new GregorianCalendar(pmonth.YEAR, pmonth.MONTH, pmonth.DAY_OF_MONTH);
        tempMonth.set(Calendar.DAY_OF_MONTH, 1);

        this.firstDay = tempMonth.get(GregorianCalendar.DAY_OF_WEEK);
        this.maxWeekNumber = this.month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        this.mnthlength = this.maxWeekNumber * 7;
        this.maxP = this.getMaxP();
        this.calMaxP = this.maxP - (this.firstDay - 1);
        this.pmonthMaxSet = (GregorianCalendar)this.pmonth.clone();
        //i think that this is the thing that needs to be fixxed to make the calendar run properly
        this.pmonthMaxSet.set(Calendar.WEEK_OF_MONTH, this.calMaxP + 1);

        pmonthMaxSet.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

        for(int nn = 0; nn < mnthlength; nn++){

            itemvalue = df.format(pmonthMaxSet.getTime());
            pmonthMaxSet.add(GregorianCalendar.DATE, 1);
            day_string.add(itemvalue);
        }
    }

    private int getMaxP(){
        int maxP;
        if(month.get(GregorianCalendar.MONTH) == month.getActualMinimum(GregorianCalendar.MONTH)){
            pmonth.set((month.get(GregorianCalendar.YEAR)-1), month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH, month.get(GregorianCalendar.MONTH) -1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }

    public void setEventView(View v,int pos,TextView txt){

        int len=GroupCalendarCollection.date_collection_arr.size();
        for (int i = 0; i < len; i++) {
            GroupCalendarCollection cal_obj=GroupCalendarCollection.date_collection_arr.get(i);
            if(cal_obj.calID == GroupCalendarUtil.findID) {
                String date = cal_obj.date;
                int len1 = day_string.size();
                if (len1 > pos) {

                    if (day_string.get(pos).equals(date)) {
                        v.setBackgroundColor(Color.parseColor("#343434"));
                        v.setBackgroundResource(R.drawable.rounded_calendar_item);

                        txt.setTextColor(Color.WHITE);
                    }
                }
            }
        }
    }

    public void getPositionList(String date, final Activity act){
        GroupDayCollection.clearEvents();
        int len=GroupCalendarCollection.date_collection_arr.size();

        for (int i = 0; i < len; i++) {
            GroupCalendarCollection cal_collection=GroupCalendarCollection.date_collection_arr.get(i);
            String event_date=cal_collection.date;

            //String event_message=cal_collection.event_message;

            if (date.equals(event_date)) {

                //Toast.makeText(context, "You have event on this date: " + event_date, Toast.LENGTH_LONG).show();

                GroupDayCollection.addGroupEventToday(cal_collection);

                /*new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Date: "+event_date)
                        //.setMessage("Event: "+event_message)
                        .setPositiveButton("OK",new android.content.DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which)
                            {
                                act.finish();
                            }
                        }).show();
                break;*/
            }else{


            }
        }
        if(GroupDayCollection.hasEvents())
            GroupDayCollection.startRunNext();
        else
            GroupDayCollection.notNext();
    }
}

