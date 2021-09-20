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

public class feedback extends AppCompatActivity {
    EditText fedemail,fedname,fedfeed;
    Button fedsubmit;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        fedemail=(EditText)findViewById(R.id.edEmail);
        fedname=(EditText)findViewById(R.id.edName);
        fedfeed=(EditText)findViewById(R.id.edFeedback);

        fedsubmit=(Button)findViewById(R.id.fed_button);
        tv=(TextView)findViewById(R.id.feedback_report);


        fedsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedenter(fedemail.getText().toString(),fedname.getText().toString(),fedfeed.getText().toString());
            }
        });
    }

    public void feedenter(String email,String name,String feedback){


        Call<feedback_response_model> call= apicontroller.getInstance().getapi()
                .getfeedback(email, name, feedback);

        // call enqueue

        call.enqueue(new Callback<feedback_response_model>() {
            @Override
            public void onResponse(Call<feedback_response_model> call, Response<feedback_response_model> response) {
                feedback_response_model obj=response.body();
                String result=obj.getMessage().trim();
                if(result.equals("inserted")){
                    tv.setText("Successfully Entered");
                    tv.setTextColor(Color.GREEN);
                    fedemail.setText("");
                    fedname.setText("");
                    fedfeed.setText("");


                }
                if(result.equals("exist")){
                    tv.setText("Sorry..! you are already Entered");
                    tv.setTextColor(Color.RED);
                    fedemail.setText("");
                    fedname.setText("");
                    fedfeed.setText("");



                }
            }

            @Override
            public void onFailure(Call<feedback_response_model> call, Throwable t) {
                tv.setText("Sorry..! Something went wrong");
                tv.setTextColor(Color.RED);
                fedemail.setText("");
                fedname.setText("");
                fedfeed.setText("");



            }
        });



    }
}