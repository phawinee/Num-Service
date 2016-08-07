package com.dachmontree.numservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mywallet on 8/7/16 AD.
 */
public class ServiceAdapter extends BaseAdapter{

    private Context context;
    private String[] iconStrings,titleStrings,ststusStrings,detailStrings;

    public ServiceAdapter(Context context, String[] iconStrings, String[] titleStrings, String[] ststusStrings, String[] detailStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
        this.ststusStrings = ststusStrings;
        this.detailStrings = detailStrings;
    }


    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.service_listview,viewGroup,false);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView8);
        TextView titleTextView = (TextView) view1.findViewById(R.id.textView7);
        ImageView statusImageView = (ImageView) view1.findViewById(R.id.imageView9);
        TextView detailTextView = (TextView) view1.findViewById(R.id.textView9);


        Picasso.with(context).load(i).resize(120,120).into(iconImageView);
        titleTextView.setText(titleStrings[i]);
        detailTextView.setText(detailStrings[i]);

        switch (Integer.parseInt(ststusStrings[i])) {
            case 0:
                statusImageView.setImageResource(R.drawable.myfalse);
                break;
            case 1:
                statusImageView.setImageResource(R.drawable.mytrue);
                break;
        }

        return view1;
    }
}//main class
