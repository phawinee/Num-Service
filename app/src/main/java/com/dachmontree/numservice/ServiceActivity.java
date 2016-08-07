package com.dachmontree.numservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ServiceActivity extends AppCompatActivity {


    private String nameString,avataString;
    private TextView nameTextView;
    private ImageView avataImageView;
    private ListView serviceListView;
    private static final String urlPHP = "http://swiftcodingthai.com/6aug/get_service_where_noom.php";



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

        createListview();
    } //end method

    private void createListview() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder().add("isAdd","trur").add("Response",avataString).build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String strResponse = response.body().string();
                Log.d("7augV3","Response==> " + strResponse);

                try {
                    JSONArray jsonArray = new JSONArray();
                    String[] iconStrings = new String[jsonArray.length()];
                    String[] titleStrings = new String[jsonArray.length()];
                    String[] statusStrings = new String[jsonArray.length()];
                    String[] detailStrings = new String[jsonArray.length()];


                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        iconStrings[i] = jsonObject.getString("Image");
                        titleStrings[i] = jsonObject.getString("Title");
                        statusStrings[i]= jsonObject.getString("Status");
                        if (statusStrings[i]==null){
                            statusStrings[i]="0";
                        }
                        detailStrings[i] = jsonObject.getString("Detail");


                        ServiceAdapter serviceAdapter = new ServiceAdapter(ServiceActivity.this,iconStrings,titleStrings,statusStrings,detailStrings);


                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });



    }
} //end main class
