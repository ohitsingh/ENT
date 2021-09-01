package com.incognito.ent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText regemail,regmobile,regpassword;
    Button regsubmit;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regemail=(EditText)findViewById(R.id.reg_email);
        regmobile=(EditText)findViewById(R.id.reg_mobile);
        regpassword=(EditText)findViewById(R.id.reg_password);

        regsubmit=(Button)findViewById(R.id.reg_submit);
        tv=(TextView)findViewById(R.id.signup_report_tv);

        regsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userregister(regemail.getText().toString(),regmobile.getText().toString(),regpassword.getText().toString());
            }
        });


    }
    public void userregister(String email,String mobile,String password){
        String name="not applicable";
        String address="not applicable";

        // calling apiset (getregister)

        Call<signup_response_model> call= apicontroller.getInstance().getapi()
                .getregister(name,email,password,mobile,address);


        // call enqueue

        call.enqueue(new Callback<signup_response_model>() {
            @Override
            public void onResponse(Call<signup_response_model> call, Response<signup_response_model> response) {
                signup_response_model obj=response.body();
                String result=obj.getMessage().trim();
                    if(result.equals("inserted")){
                        tv.setText("Successfully Registered");
                        tv.setTextColor(Color.GREEN);
                        regemail.setText("");
                        regmobile.setText("");
                        regpassword.setText("");


                    }
                if(result.equals("exist")){
                    tv.setText("Sorry..! you are already registered");
                    tv.setTextColor(Color.RED);
                    regemail.setText("");
                    regmobile.setText("");
                    regpassword.setText("");


                }
            }

            @Override
            public void onFailure(Call<signup_response_model> call, Throwable t) {
                tv.setText("Sorry..! Something went wrong");
                tv.setTextColor(Color.RED);
                regemail.setText("");
                regmobile.setText("");
                regpassword.setText("");


            }
        });



    }
}