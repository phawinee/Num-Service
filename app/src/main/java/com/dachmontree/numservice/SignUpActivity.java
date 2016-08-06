package com.dachmontree.numservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {
    //Explicit
    private EditText nameEditText , userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton avata0RadioButton,avata1RadioButton,avata2RadioButton,avata3RadioButton,avata4RadioButton;
    private String nameString,userString,passString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText);
        radioGroup = (RadioGroup) findViewById(R.id.regAvata);
        avata0RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avata1RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avata2RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avata3RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avata4RadioButton = (RadioButton) findViewById(R.id.radioButton5);

    }//Main method

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passString = passwordEditText.getText().toString().trim();

        //check
        if (nameString.equals("")  || userString.equals("")||passString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, 0,"มีช่องว่าง","กรุณากรอกทุกช่อง");

        }

    }//click sign
}//Main class
