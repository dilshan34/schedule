package com.example.shedule.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shedule.Adapter.dateAdapter;
import com.example.shedule.Adapter.sheduleAdapter;
import com.example.shedule.Models.getsetDatePicker;
import com.example.shedule.Models.getsetShedule;
import com.example.shedule.R;
import com.example.shedule.constant;
import com.example.shedule.db.DbHelper;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class selectDateTask extends AppCompatActivity implements DatePickerListener {

    HorizontalPicker picker;
    String date;
    dateAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    ArrayList<getsetDatePicker> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_task);

         picker = (HorizontalPicker) findViewById(R.id.datePicker);
         recyclerView= (RecyclerView) findViewById(R.id.daterecyclerview) ;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // initialize it and attach a listener
        picker.setListener(this)

                .setDays(7)
                .setOffset(6)
                .setDateSelectedColor(getResources().getColor(R.color.primary))
                .setDateSelectedTextColor(Color.WHITE)
                .setMonthAndYearTextColor(Color.BLACK)
                .setTodayButtonTextColor(getResources().getColor(R.color.colorPrimary))
                .setTodayDateTextColor(getResources().getColor(R.color.colorPrimary))
                .setTodayDateBackgroundColor(Color.GRAY)
                .setUnselectedDayTextColor(Color.DKGRAY)
                .setDayOfWeekTextColor(Color.DKGRAY)
                .setUnselectedDayTextColor(getResources().getColor(R.color.primaryTextColor))
                .showTodayButton(false)
                .init();




        picker.setBackgroundColor(getResources().getColor(R.color.smoke));
        picker.setDate(new DateTime().plusDays(4));
    }


    @Override
    public void onDateSelected(DateTime dateSelected) {

        arrayList.clear();
        date= dateSelected.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constant.getDateTask_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("ed", "date response: "+response );
                            JSONArray jsonArray = new JSONArray(response);



                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("task");
                                String fromTime = jsonObject.getString("fromTime");
                                String toTime = jsonObject.getString("toTime");
                                Log.e("TAG", "Task : "+id );



                                getsetDatePicker task = new getsetDatePicker(id, fromTime, toTime);
                                arrayList.add(task);


                            }
                            adapter = new dateAdapter(arrayList, selectDateTask.this);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                })
        {
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> params = new HashMap<String,String>();
                params.put("date",date);


                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //Log.e("TAG", "Date Selected: "+date );

    }


    public void selectDate()
    {



        Log.e("TAG", "uploadTask: "+date );

        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                constant.getDateTask_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("response", "onResponse: "+response );
                            JSONArray jsonArray = new JSONArray(response);

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("task ");
                                String fromTime = jsonObject.getString("fromTime");
                                String toTime = jsonObject.getString("toTime");
                                Log.e("TAG", "Task : "+id );



                                getsetDatePicker task = new getsetDatePicker(id, fromTime, toTime);
                                arrayList.add(task);


                            }
                            adapter = new dateAdapter(arrayList, selectDateTask.this);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> params = new HashMap<String,String>();
                params.put("date",date);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    public void getShedule(){




    }
}

