package com.incognito.ent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.incognito.ent.adapters.RestaurentListAdapter;
import com.incognito.ent.model.RestaurentModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class EntDashboard extends AppCompatActivity implements RestaurentListAdapter.RestaurantListClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_dashboard);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ENT");

        List<RestaurentModel> restaurentModelList = getRestaurantData();
        initRecycledView(restaurentModelList);

    }

    private void initRecycledView(List<RestaurentModel> restaurentModelList){
        RecyclerView recViewDash = findViewById(R.id.recViewDash);
        recViewDash.setLayoutManager(new LinearLayoutManager(this));

        RestaurentListAdapter adapter = new RestaurentListAdapter(restaurentModelList,this);

        recViewDash.setAdapter(adapter);

    }

    private List<RestaurentModel> getRestaurantData(){
        InputStream inputStream =getResources().openRawResource(R.raw.restaurent);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            int n;
            while ((n=reader.read(buffer))!=-1){
                writer.write(buffer,0,n);
            }

        }catch (Exception e){

        }
        String jsonStr = writer.toString();
        Gson gson=new Gson();
        RestaurentModel[] restaurentModels=gson.fromJson(jsonStr,RestaurentModel[].class);

        List<RestaurentModel> restList = Arrays.asList(restaurentModels);
        return restList;
    }

    @Override
    public void onItemClick(RestaurentModel restaurentModel) {
        Intent intent= new Intent(EntDashboard.this,RestaurentMenuActivity.class);
        intent.putExtra("RestaurentModel",restaurentModel);
        startActivity(intent);

    }
}