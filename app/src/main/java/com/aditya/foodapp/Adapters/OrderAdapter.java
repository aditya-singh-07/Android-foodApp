package com.aditya.foodapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.foodapp.Database.foodmodel;
import com.aditya.foodapp.DetailActivity;
import com.aditya.foodapp.Models.OrderModels;
import com.aditya.foodapp.OrderActivity;
import com.aditya.foodapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder>{
    Context context;
    ArrayList<OrderModels> list;
    OrderActivity orderActivity;

    public OrderAdapter(Context context, ArrayList<OrderModels> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final OrderModels ordermodel=list.get(position);
        holder.oimage.setImageResource(ordermodel.getOrderimage());
        holder.oname.setText(ordermodel.getOrdername());
        holder.oprice.setText(ordermodel.getPrice());
        holder.onumber.setText(ordermodel.getOrdernumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, DetailActivity.class);
                i.putExtra("id",Integer.parseInt(ordermodel.getOrdernumber()));
                i.putExtra("type", 2);
                context.startActivity(i);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Order")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                foodmodel fmodel=new foodmodel();
                                if(fmodel.deleteorder(Integer.parseInt(ordermodel.getOrdernumber())) > 0){
                                    Toast.makeText(context, "Deleted success", Toast.LENGTH_SHORT).show();
                                    context.startActivity(new Intent(context,OrderActivity.class));
                                }else{
                                    Toast.makeText(context, "Deleted unsuccessfully", Toast.LENGTH_SHORT).show();
                                }
                            }


                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                return false;
            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView oname,oprice,onumber;
        ImageView oimage;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            oimage=itemView.findViewById(R.id.orderimage);
            oname=itemView.findViewById(R.id.ordername);
            oprice=itemView.findViewById(R.id.orderprice);
            onumber=itemView.findViewById(R.id.ordernumber);

        }
    }

}
