package com.example.m_waste;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter<UserClass,MyAdapter.myViewHolder> {
    Context context;
    ArrayList<UserClass> userClassArrayList;


    private int position;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdapter(@NonNull FirebaseRecyclerOptions<UserClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull UserClass userClass) {
        this.position = position;
        // UserClass userClass = userClassArrayList.get(position);
        holder.name.setText(userClass.getName());
        holder.point.setText(userClass.getPoint());
        holder.email.setText(userClass.getEmail());
        holder.county.setText(userClass.getCounty());
        holder.phone.setText(userClass.getPhone());

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Confirm that you want to delete");
                builder.setMessage("Deleted User can't be undone");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("attachee")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_users,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;

        TextView name, county,point,phone, email,remove;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
         county= itemView.findViewById(R.id.county);
           point = itemView.findViewById(R.id.point);
            email= itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            remove = itemView.findViewById(R.id.remove);
        }
    }
}


