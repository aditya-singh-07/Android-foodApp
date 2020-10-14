package com.aditya.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aditya.foodapp.Adapters.FoodAdapters;
import com.aditya.foodapp.Models.FoodModels;
import com.aditya.foodapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<FoodModels> list=new ArrayList<FoodModels>();
        list.add(new FoodModels(R.drawable.burger1,"Burger","199","Delicious burger with spice"));
        list.add(new FoodModels(R.drawable.burger2,"Burger cheese","399","Delicious Burger cheese with spice"));
        list.add(new FoodModels(R.drawable.burger3,"Burger mashroom","299","Delicious Burger mashroom with spice"));
        list.add(new FoodModels(R.drawable.burger4,"Burger chilli","199","Delicious Burger chilli with spice"));
        list.add(new FoodModels(R.drawable.pizza1,"Pizza","199","Delicious Pizza with less price"));
        list.add(new FoodModels(R.drawable.pizza2,"pizza cheese","399","Delicious pizza cheese with spice"));
        list.add(new FoodModels(R.drawable.pizza3,"pizza mashroom","299","Delicious pizza mashroom with spice"));
        list.add(new FoodModels(R.drawable.pizza4,"pizza chilli","199","Delicious pizza chilli with spice"));

        FoodAdapters adapter=new FoodAdapters(this, list);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(manager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orderid:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}