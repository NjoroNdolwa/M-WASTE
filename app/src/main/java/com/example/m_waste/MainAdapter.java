package com.example.m_waste;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    Context context;
    ArrayList<TruckClass> truckClassesArray;

    public MainAdapter(Context context, ArrayList<TruckClass> truckClassesArray) {
        this.context = context;
        this.truckClassesArray = truckClassesArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.available_details,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TruckClass myData=truckClassesArray.get(position);
        holder.name.setText(myData.getT_name());
        holder.date.setText(myData.getDate());
        holder.type.setText(myData.getW_type());
        holder.point.setText(myData.getCollection_point());
        holder.day.setText(myData.getDay());
        holder.status.setText(myData.getStatus());


        String Names = holder.name.getText().toString();
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = new View(context);
                view1.getContext();
                Intent intent= new Intent(context, BookTruckNow.class);
                intent.putExtra("",Names);
                view1.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return truckClassesArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,type,point,date,day,status,book;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txtname);
            type=itemView.findViewById(R.id.txttype);
            point=itemView.findViewById(R.id.txtcollection_point);
            date=itemView.findViewById(R.id.txtdate);
            day=itemView.findViewById(R.id.txtday);
            status=itemView.findViewById(R.id.status);
            book=itemView.findViewById(R.id.book);

        }
    }
}
