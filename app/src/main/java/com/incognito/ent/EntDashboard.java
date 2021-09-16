package com.incognito.ent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.nio.file.OpenOption;
import java.util.ArrayList;

public class EntDashboard extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    RecyclerView rcv;
    myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_dashboard);
        setTitle("ENT");

        navigationView=findViewById(R.id.navmenuview);
        drawerLayout=findViewById(R.id.ent_drawer_layout);
        rcv=(RecyclerView)findViewById(R.id.recViewDash);
        rcv.setLayoutManager(new LinearLayoutManager(this));

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_logout:
                        Toast.makeText(getApplicationContext(),"you clicked on menu",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

        adapter=new myAdapter(dataqueue(),getApplicationContext());
        rcv.setAdapter(adapter);



//        String arr[]={"Kiosk","Michael","Indian Coffee House","Mingos","Mingos2","Nandini","Freshetaria","Gourmet central canteen","Kiosk","Michael","Indian Coffee House","Mingos","Mingos2","Nandini","Freshetaria","Gourmet central canteen","Kiosk","Michael","Indian Coffee House","Mingos","Mingos2","Nandini","Freshetaria","Gourmet central canteen"};
//        rcv.setAdapter(new myDashAdapter(arr));

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Model> dataqueue()
    {
        ArrayList<Model> holder=new ArrayList<>();

        Model ob1=new Model();
        ob1.setHeader("Mingos");
        ob1.setDesc("Veg");
        ob1.setImgnmae(R.drawable.canteen);
        holder.add(ob1);

        Model ob2=new Model();
        ob2.setHeader("Mingos 2");
        ob2.setDesc("Veg & NonVeg");
        ob2.setImgnmae(R.drawable.canteen);
        holder.add(ob2);

        Model ob3=new Model();
        ob3.setHeader("Kiosk");
        ob3.setDesc("Veg & NonVeg");
        ob3.setImgnmae(R.drawable.canteen);
        holder.add(ob3);

        Model ob4=new Model();
        ob4.setHeader("Michael's");
        ob4.setDesc("Veg & NonVeg");
        ob4.setImgnmae(R.drawable.canteen);
        holder.add(ob4);

        Model ob5=new Model();
        ob5.setHeader("Freshetaria");
        ob5.setDesc("Veg");
        ob5.setImgnmae(R.drawable.canteen);
        holder.add(ob5);

        Model ob6=new Model();
        ob6.setHeader("Coffee House");
        ob6.setDesc("Veg");
        ob6.setImgnmae(R.drawable.canteen);
        holder.add(ob6);

        Model ob7=new Model();
        ob7.setHeader("Nandini");
        ob7.setDesc("Veg");
        ob7.setImgnmae(R.drawable.canteen);
        holder.add(ob7);

        Model ob8=new Model();
        ob8.setHeader("Gourmet canteen");
        ob8.setDesc("Veg & NonVeg");
        ob8.setImgnmae(R.drawable.canteen);
        holder.add(ob8);

        return holder;
    }

}