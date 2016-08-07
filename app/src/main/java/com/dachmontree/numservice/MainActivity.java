package com.dachmontree.numservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public void clickSignInMain(View view){

        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();


        if (userString.equals("") || passString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,3,"Have Space","please fill all ever black");
        }else{

        }

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }



}// Main class
