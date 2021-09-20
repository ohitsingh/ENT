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

public class support extends AppCompatActivity {

    EditText supname,supemail,supmobile,supquery;
    Button supsubmit;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        supname=(EditText)findViewById(R.id.supName);
        supemail=(EditText)findViewById(R.id.supEmail);
        supmobile=(EditText)findViewById(R.id.supMobile);
        supquery=(EditText)findViewById(R.id.supQuery);

        supsubmit=(Button)findViewById(R.id.sup_button);
        tv=(TextView)findViewById(R.id.sup_report);

        supsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supenter(supname.getText().toString(),supemail.getText().toString(),supmobile.getText().toString(),supquery.getText().toString());
            }
        });


    }
    public void supenter(String name,String email,String mobile,String query){


        Call<support_response_model> call= apicontroller.getInstance().getapi()
                .getsupport(name,email,mobile,query);


        // call enqueue

        call.enqueue(new Callback<support_response_model>() {
            @Override
            public void onResponse(Call<support_response_model> call, Response<support_response_model> response) {
                support_response_model obj=response.body();
                String result=obj.getMessage().trim();
                if(result.equals("inserted")){
                    tv.setText("Successfully Registered");
                    tv.setTextColor(Color.GREEN);
                    supname.setText("");
                    supemail.setText("");
                    supmobile.setText("");
                    supquery.setText("");


                }
                if(result.equals("exist")){
                    tv.setText("Sorry..! you are already registered");
                    tv.setTextColor(Color.RED);
                    supname.setText("");
                    supemail.setText("");
                    supmobile.setText("");
                    supquery.setText("");


                }
            }

            @Override
            public void onFailure(Call<support_response_model> call, Throwable t) {
                tv.setText("Sorry..! Something went wrong");
                tv.setTextColor(Color.RED);
                supname.setText("");
                supemail.setText("");
                supmobile.setText("");
                supquery.setText("");


            }
        });



    }
}