package com.aditya.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;

import com.aditya.foodapp.Adapters.FoodAdapters;
import com.aditya.foodapp.Adapters.OrderAdapter;
import com.aditya.foodapp.Database.DBhelper;
import com.aditya.foodapp.Database.foodmodel;
import com.aditya.foodapp.Models.OrderModels;
import com.aditya.foodapp.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;
    foodmodel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        foodmodel.getinstance(getApplicationContext());
        model=new foodmodel();
        ArrayList<OrderModels> list=model.getorders();
//
//        list.add(new OrderModels(R.drawable.burger1,"Burger", "199","#6346354365"));
//        list.add(new OrderModels(R.drawable.burger2,"Burger cheese", "199","#6942864256"));
//        list.add(new OrderModels(R.drawable.burger2,"Burger cheese", "199","#8484646468"));

        OrderAdapter adapter=new OrderAdapter(this, list);
        binding.orderrecycler.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        binding.orderrecycler.setLayoutManager(manager);




    }
    
}