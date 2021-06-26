package lechihai.lch.finalproject;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Custom_listView extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> itemname = new ArrayList<String>();
    private final Integer[] imgid;

    public Custom_listView(Activity context, ArrayList itemname, Integer[] imgid) {
        super(context, R.layout.menu, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.menu, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(itemname.get(position));
        if (imgid.length > 1) {
            imageView.setImageResource(imgid[position]);
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_list);
            rowView.startAnimation(animation);
            return rowView;
        } else {
            imageView.setImageResource(imgid[0]);
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_list);
            rowView.startAnimation(animation);
            return rowView;
        }

    }
}


