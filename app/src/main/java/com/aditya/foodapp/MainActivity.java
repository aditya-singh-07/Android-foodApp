package com.aditya.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aditya.foodapp.Adapters.FoodAdapters;
import com.aditya.foodapp.Models.FoodModels;
import com.aditya.foodapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final int MY_PERMISSION= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_CONTACTS)){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permission request")
                        .setMessage("Your should enable this permission to READ_CONTACT")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSION);
                            }
                        })
                        .setPositiveButton("Grant Permission", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSION);
                            }
                        })
                        .show();
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSION);
            }
        }else{

        }



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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                }else{


                }
                return;
        }
    }
}