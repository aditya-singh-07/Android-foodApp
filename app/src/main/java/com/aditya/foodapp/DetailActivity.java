package com.aditya.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ObbInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.foodapp.Database.DBhelper;
import com.aditya.foodapp.Database.foodmodel;
import com.aditya.foodapp.databinding.ActivityDetailBinding;


public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    DBhelper dBhelper;
     foodmodel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dBhelper =new DBhelper(getApplicationContext());
        if(getIntent().getIntExtra("type", 0) == 1){
            final int image=getIntent().getIntExtra("image",0);
            final int price=Integer.parseInt(getIntent().getStringExtra("price"));
            final String name=getIntent().getStringExtra("name");
            final String desc=getIntent().getStringExtra("description");
            final String email=binding.editTextEmailAddress.getText().toString();
            final String phone=binding.editTextPhone.getText().toString();
            binding.foodimage.setImageResource(image);
            binding.foodname.setText(name);
            binding.btnorderprice.setText(String.format("%d", price));
            binding.descriptions.setText(desc);

            binding.btnorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(binding.editTextEmailAddress.getText().toString().equals("") || binding.editTextPhone.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Please fill phone and email  ", Toast.LENGTH_SHORT).show();
                        TextView editTextEmailAddress,editTextPhone;
                        editTextEmailAddress=findViewById(R.id.editTextEmailAddress);
                        editTextPhone=findViewById(R.id.editTextPhone);
                        if(binding.editTextEmailAddress.getText().toString().equals(""))
                            editTextEmailAddress.setError("please enter Email");
                        if(binding.editTextPhone.getText().toString().equals(""))
                            editTextPhone.setError("please enter Phone Number");
                        if(!Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmailAddress.getText().toString()).matches())
                            editTextEmailAddress.setError("please enter valid email");
                    }else{
                        boolean inserted= foodmodel.insertorder(
                                binding.editTextPersonName.getText().toString(),
                                binding.editTextEmailAddress.getText().toString(),
                                binding.editTextPhone.getText().toString(),
                                image,
                                name,
                                desc,
                                price,
                                Integer.parseInt(binding.quantity.getText().toString())
                        );

                        if(inserted){
                            Toast.makeText(getApplicationContext(), "Order placed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Order unsuccess ", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            });
        }else{
            model=new foodmodel();
            final int orderid=getIntent().getIntExtra("id",0);
            Cursor cursor=model.getordersbyid(orderid);
            final int image=cursor.getInt(4);
            binding.foodimage.setImageResource(cursor.getInt(4));
            binding.foodname.setText(cursor.getString(5));
            binding.btnorderprice.setText(String.format("%d", cursor.getInt(7)));
            binding.descriptions.setText(cursor.getString(6));
            binding.editTextEmailAddress.setText(cursor.getString(2));
            binding.editTextPhone.setText(cursor.getString(3));
            binding.editTextPersonName.setText(cursor.getString(1));
            binding.btnorder.setText("Update Order");


            binding.btnorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(DetailActivity.this,OrderActivity.class);
                    i.putExtra("id",orderid);
                  Boolean updated=  foodmodel.updateorder(
                            binding.editTextPersonName.getText().toString(),
                            binding.editTextEmailAddress.getText().toString(),
                            binding.editTextPhone.getText().toString(),
                            image,
                            binding.foodname.getText().toString(),
                            binding.descriptions.getText().toString(),
                            Integer.parseInt(binding.btnorderprice.getText().toString()),
                            Integer.parseInt(binding.quantity.getText().toString()),
                            orderid);
                    if(updated){
                        Toast.makeText(getApplicationContext(), "Successfully updated ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Unsuccessfully updated ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }
}