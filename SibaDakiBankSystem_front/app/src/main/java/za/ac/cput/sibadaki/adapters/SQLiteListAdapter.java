package za.ac.cput.sibadaki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import za.ac.cput.sibadaki.sibadakibanksystem.R;

/**
 * Created by User on 2016/09/03.
 */
public class SQLiteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> userID;
    ArrayList<String> UserName;
    ArrayList<String> UserSurname;
    //ArrayList<String> UserSubject ;


    public SQLiteListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> surname
           // ArrayList<String> subject
    )
    {

        this.context = context2;
        this.userID = id;
        this.UserName = name;
        this.UserSurname = surname;
        //this.UserSubject = subject ;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewname = (TextView) child.findViewById(R.id.textViewNAME);
            holder.textviewsubject = (TextView) child.findViewById(R.id.textViewSurname);
            //holder.textviewsubject = (TextView) child.findViewById(R.id.textViewSUBJECT);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userID.get(position));
        holder.textviewname.setText(UserName.get(position));
        holder.textviewsubject.setText(UserSurname.get(position));
        //holder.textviewsubject.setText(UserSubject.get(position));

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
        //TextView textviewp;
        TextView textviewsubject;
    }
}

