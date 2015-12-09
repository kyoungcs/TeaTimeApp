package sonoma.teatimeremake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sonoma.teatimeremake.R;
import sonoma.teatimeremake.util.FrontPageCollection;

/**
 * Created by Austin on 12/8/2015.
 */
public class FrontPageAdapter extends ArrayAdapter<FrontPageCollection>{

    private final Context context;
    private final ArrayList<FrontPageCollection> dayCol;


    public FrontPageAdapter(Context context, int resource,ArrayList<FrontPageCollection> objects) {
        super(context, resource, objects);
        this.context = context;
        this.dayCol = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;



        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_helper, null);

        }
        FrontPageCollection calEvent= getItem(position);

        if (calEvent != null){
            TextView eventTime = (TextView) v.findViewById(R.id.label);
            String tempString = dayCol.get(position).getEventString();
            eventTime.setText(tempString);
        }

        return v;
    }

}
