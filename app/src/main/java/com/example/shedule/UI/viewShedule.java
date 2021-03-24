package com.example.shedule.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shedule.R;
import com.example.shedule.constant;
import com.example.shedule.db.DbHelper;
import com.example.shedule.Models.getsetShedule;
import com.example.shedule.Adapter.sheduleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class viewShedule extends AppCompatActivity {
    sheduleAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<getsetShedule> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shedule);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        getShedule();


    }




    public void getShedule(){
        final DbHelper db_helper = new DbHelper(viewShedule.this);
        arrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constant.getShedule_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            Log.e("ed", "onResponse: "+jsonArray );


                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("task");
                                String fromTime = jsonObject.getString("fromTime");
                                String toTime = jsonObject.getString("toTime");
                                Log.e("TAG", "Task : "+id );



                                getsetShedule task = new getsetShedule(id, fromTime, toTime);
                                arrayList.add(task);


                            }
                            adapter = new sheduleAdapter(arrayList, viewShedule.this);
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
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        finish();

        Intent intent=new Intent(viewShedule.this,dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}