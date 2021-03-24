package com.example.shedule.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shedule.R;
import com.example.shedule.Models.getsetShedule;

import java.util.ArrayList;

public class sheduleAdapter extends RecyclerView.Adapter<sheduleAdapter.MyViewHolder> {

    ArrayList<getsetShedule> arrayList = new ArrayList<getsetShedule>();
    Context context;

    public sheduleAdapter(ArrayList<getsetShedule> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shedule_layout, parent, false);
        final MyViewHolder myviewHoldernew = new MyViewHolder(view);
        return myviewHoldernew;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtid.setText(arrayList.get(position).getId());
        holder.fromTime.setText(arrayList.get(position).getFromTime());
        holder.toTime.setText(arrayList.get(position).getToTime());


    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtid, fromTime, toTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtid = (TextView) itemView.findViewById(R.id.txtid);
            fromTime = (TextView) itemView.findViewById(R.id.fromTime);
            toTime = (TextView) itemView.findViewById(R.id.toTime);

        }
    }
}
