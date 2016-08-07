package com.dachmontree.numservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {


    private String nameString,avataString;
    private TextView nameTextView;
    private ImageView avataImageView;
    private ListView serviceListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        //Get value from intent
        nameString = getIntent().getStringExtra("Name");
        avataString = getIntent().getStringExtra("Avata");
        Log.d("7aug","name="+nameString);
        Log.d("7aug","avata="+avataString);


        nameTextView = (TextView) findViewById(R.id.textView8);
        avataImageView = (ImageView) findViewById(R.id.imageView7);
        serviceListView = (ListView) findViewById(R.id.listView);

        nameTextView.setText(nameString);
        avataImageView.setImageResource((new MyAlert().findAvata(Integer.parseInt(avataString))));


    } //end method
} //end main class
