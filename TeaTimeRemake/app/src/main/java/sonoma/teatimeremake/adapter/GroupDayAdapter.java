package sonoma.teatimeremake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sonoma.teatimeremake.GroupDayActivity;
import sonoma.teatimeremake.R;
import sonoma.teatimeremake.util.DayCollection;
import sonoma.teatimeremake.util.GroupDayCollection;

/**
 * Created by Austin on 12/8/2015.
 */
public class GroupDayAdapter extends ArrayAdapter<GroupDayCollection> {
    private final ArrayList<GroupDayCollection> dayCol;
    private Context context;

    /*public GroupDayAdapter(Context c) {
        context = c;
    }*/

    public GroupDayAdapter(Context context, int resource, ArrayList<GroupDayCollection> dayevents) {
        super(context, resource, dayevents);
        this.context = context;
        this.dayCol = dayevents;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;



        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_helper, null);

        }
        GroupDayCollection calEvent= getItem(position);

        if (calEvent != null){
            TextView eventTime = (TextView) v.findViewById(R.id.label);
            String tempString = dayCol.get(position).getEventString();
            eventTime.setText(tempString);
        }

        return v;
    }
}
