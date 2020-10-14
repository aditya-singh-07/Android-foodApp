package com.aditya.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;

public class SwipeRefresh extends AppCompatActivity {
    TextView refreshid;
    SwipeRefreshLayout swipeRefreshLayout;
    int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        refreshid=findViewById(R.id.refreshtext);
        swipeRefreshLayout =findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              sum++;
              refreshid.setText(String.valueOf(sum));
              swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}