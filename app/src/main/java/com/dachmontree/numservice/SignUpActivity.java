package com.dachmontree.numservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
    //Explicit
    private EditText nameEditText , userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton avata0RadioButton,avata1RadioButton,avata2RadioButton,avata3RadioButton,avata4RadioButton;
    private String nameString,userString,passString,avataString;


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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton:
                        avataString = "0";
                        break;
                    case  R.id.radioButton2:
                        avataString = "1";
                        break;
                    case R.id.radioButton3:
                        avataString = "2";
                        break;
                    case R.id.radioButton4:
                        avataString = "3";
                        break;
                    case R.id.radioButton5:
                        avataString = "4";
                        break;
                }
            }
        });

    }//Main method

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passString = passwordEditText.getText().toString().trim();

        //check
        if (nameString.equals("")  || userString.equals("")||passString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, 0,"มีช่องว่าง","กรุณากรอกทุกช่อง");

        }else if(avata0RadioButton.isChecked() || avata1RadioButton.isChecked() ||avata2RadioButton.isChecked()||avata3RadioButton.isChecked()||avata4RadioButton.isChecked()){
            confirmValue();

        }else{
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, 1,"ยังไม่เลือก Avata","กรุณากรอกเลือก Avata ด้วยคร๊าบบบบ");


        }

    }//click sign

    private void confirmValue() {
        MyAlert myAlert =new MyAlert();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(myAlert.findAvata(Integer.parseInt(avataString)));
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage("Name : "+nameString+ "\n User : "+userString + "\n Password : " +passString);
        builder.setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                uploadNewValueToServer();
                dialogInterface.dismiss();
            }
        });
           builder.show();
    }

    private void uploadNewValueToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passString)
                .add("Avata", avataString).build();
        Request.Builder builder =new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/6aug/add_user_noom.php").post(requestBody).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });
    }
}//Main class
