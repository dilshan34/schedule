package com.example.shedule.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shedule.R;

public class dashboard extends AppCompatActivity {

    CardView schedule,task,chart,selectDayTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        schedule = findViewById(R.id.schedule);
        task = findViewById(R.id.task);
        chart = findViewById(R.id.chart);
        selectDayTask = findViewById(R.id.selectDayTask);

        selectDayTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,selectDateTask.class);
                startActivity(intent);

            }
        });



        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,chart.class);
                startActivity(intent);
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,MainActivity.class);
                startActivity(intent);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,viewShedule.class);
                startActivity(intent);
            }
        });
    }
}