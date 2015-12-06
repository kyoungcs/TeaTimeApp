package sonoma.teatimeremake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sonoma.teatimeremake.R;
import sonoma.teatimeremake.util.CalendarCollection;
import sonoma.teatimeremake.util.DayCollection;

/**
 * Created by Austin on 12/3/2015.
 */
public class DayViewAdapter extends ArrayAdapter<DayCollection>{
    private final Context context;
    private final ArrayList<DayCollection> dayCol;


    public DayViewAdapter(Context context, ArrayList<DayCollection> dayevents ) {
        super(context, 0, dayevents);
        this.context = context;
        this.dayCol = dayevents;
    }

    public DayViewAdapter(Context context,int resource, ArrayList<DayCollection> dayevents) {
        super(context, resource, dayevents);
        this.context = context;
        this.dayCol = dayevents;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;



        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.content_day_display, null);

        }
        DayCollection calEvent= getItem(position);

        if (calEvent != null){
            TextView eventTime = (TextView) v.findViewById(R.id.label);
            eventTime.setText(dayCol.get(position).getEventString());
        }
        return v;
    }

}
