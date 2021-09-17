package com.incognito.ent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.incognito.ent.adapters.MenuListAdapter;
import com.incognito.ent.model.Menu;
import com.incognito.ent.model.RestaurentModel;

import java.util.List;

public class RestaurentMenuActivity extends AppCompatActivity implements MenuListAdapter.MenuListClickListener {
    private List<Menu> menuList=null;
    private MenuListAdapter menuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_menu);

        RestaurentModel restaurentModel = getIntent().getParcelableExtra("RestaurentModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurentModel.getName());
        actionBar.setSubtitle(restaurentModel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(true);

        menuList=restaurentModel.getMenus();

        initRecyclerView();

        TextView buttonCheckout =findViewById(R.id.buttonCheckout);

        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        menuListAdapter = new MenuListAdapter(menuList,this);
        recyclerView.setAdapter(menuListAdapter);

    }


    @Override
    public void onAddToCartClick(Menu menu) {

    }
}