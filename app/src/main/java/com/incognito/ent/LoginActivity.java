package com.incognito.ent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView tv;
    EditText loginemail,loginpassword;
    TextView loginreport;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        tv=(TextView)findViewById(R.id.login_tv);
        loginemail=(EditText) findViewById(R.id.login_email);
        loginpassword=(EditText) findViewById(R.id.login_password);
        loginreport=(TextView) findViewById(R.id.login_report);
        loginbtn=findViewById(R.id.login_submit);

        // code for checking user he is already loggined or not

        verifyuserexistence();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processlogin(loginemail.getText().toString(),loginpassword.getText().toString());


            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                finish();
            }
        });
    }

    public void processlogin(String email,String password){
        Call<login_response_model> call=apicontroller.getInstance()
                .getapi()
                .getLogin(email, password);

        // we call to enqueue above call

        call.enqueue(new Callback<login_response_model>() {
            @Override
            public void onResponse(Call<login_response_model> call, Response<login_response_model> response) {
                login_response_model login_response_model= response.body();
                String result = login_response_model.getMessage();
                if (result.equals("exist")){
                    // SharedPreference
                    SharedPreferences sharedPreferences = getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usernmae",email);
                    editor.putString("password",password);
                    editor.commit();
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(),EntDashboard.class));
                    finish();


                }if(result.equals("notexist")){
                    loginreport.setText("Oops! Invalid email and password");
                    loginemail.setText("");
                    loginpassword.setText("");
                }

            }

            @Override
            public void onFailure(Call<login_response_model> call, Throwable t) {

                loginreport.setText("Oops! Something went wrong..");

            }
        });



    }

    public void verifyuserexistence(){
        SharedPreferences sharedPreferences = getSharedPreferences("credentials",MODE_PRIVATE);
        if (sharedPreferences.contains("usernmae")){
            startActivity(new Intent(getApplicationContext(),EntDashboard.class));
        }else {

        }
    }







}