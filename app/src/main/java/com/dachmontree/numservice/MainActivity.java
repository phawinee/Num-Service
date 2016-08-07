package com.dachmontree.numservice;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {


    private EditText userEditText,passEditText;
    private String userString,passString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEditText = (EditText) findViewById(R.id.editText4);
        passEditText = (EditText) findViewById(R.id.editText5);
    }//main Method


    //create inner class
    private class SynchonizeUser extends AsyncTask<Void, Void, String> {

        private Context context;
        private String myuserString ,mypassString;
        private static final String URL_json = "http://swiftcodingthai.com/6aug/get_user_noom.php";

        public SynchonizeUser(Context context, String myuserString, String mypassString) {
            this.context = context;
            this.myuserString = myuserString;
            this.mypassString = mypassString;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL_json).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            }catch (Exception e){
                Log.d("7augv1", "e=" + e.toString());
                return null;
            }

            //return null;
        }//doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("7augv1","Json ==>" + s);

        }//onpost
    }//SynchonizeUser





    public void clickSignInMain(View view){

        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();


        if (userString.equals("") || passString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,3,"Have Space","please fill all ever black");
        }else{
            //call SynchonizeUser
            SynchonizeUser synchonizeUser = new SynchonizeUser(this,userString,passString);
            synchonizeUser.execute();
        }

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }



}// Main class
