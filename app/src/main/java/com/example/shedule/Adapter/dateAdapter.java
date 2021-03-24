package com.example.shedule.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shedule.Models.getsetDatePicker;
import com.example.shedule.Models.getsetShedule;
import com.example.shedule.R;

import java.util.ArrayList;
import java.util.Random;

public class dateAdapter extends RecyclerView.Adapter<dateAdapter.MyViewHolder> {

    ArrayList<getsetDatePicker> arrayList = new ArrayList<getsetDatePicker>();
    Context context;

    public dateAdapter(ArrayList<getsetDatePicker> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public dateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_date_layout, parent, false);
        final dateAdapter.MyViewHolder myviewHoldernew = new dateAdapter.MyViewHolder(view);
        return myviewHoldernew;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       int randomAndroidColor = holder.androidColors[new Random().nextInt(holder.androidColors.length)];





        holder.parent.setBackgroundColor(randomAndroidColor);


        holder.txtid.setText(arrayList.get(position).getId());
        holder.fromTime.setText(arrayList.get(position).getFromtime());
        holder.toTime.setText(arrayList.get(position).getTotime());



    }



    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtid, fromTime, toTime;
        int[] androidColors;
        LinearLayout parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = (LinearLayout) itemView.findViewById(R.id.parent) ;
            androidColors = itemView.getResources().getIntArray(R.array.androidcolors);
            txtid = (TextView) itemView.findViewById(R.id.dateTask);
            fromTime = (TextView) itemView.findViewById(R.id.datefromtime);
            toTime = (TextView) itemView.findViewById(R.id.datetotime);

        }
    }
}
