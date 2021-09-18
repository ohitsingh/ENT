package com.incognito.ent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.incognito.ent.model.RestaurentModel;

public class OrderSucceessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_succeess);

        RestaurentModel restaurentModel = getIntent().getParcelableExtra("RestaurentModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurentModel.getName());
        actionBar.setSubtitle(restaurentModel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(false);

        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}