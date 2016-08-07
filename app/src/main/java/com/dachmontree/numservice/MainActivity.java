package com.dachmontree.numservice;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

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
        private String myuserString ,mypassString , truePasswordString , nameString;
        private static final String URL_json = "http://swiftcodingthai.com/6aug/get_user_noom.php";
        private  boolean statusABoolean = true;



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

            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (myuserString.equals(jsonObject.getString("user"))) {
                        truePasswordString = jsonObject.getString("password");
                        nameString = jsonObject.getString("name");
                        statusABoolean = false;

                    }//end if

                }//end for
                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context,4,"เกิดข้อผิดพลาด","ไม่มี User นี้");
                }else if(passString.equals(truePasswordString)){
                    Toast.makeText(context,"Wecome",Toast.LENGTH_SHORT).show();


                }else{
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context,4,"เกิดข้อผิดพลาด","Password ไม่ถูกต้อง");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

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
