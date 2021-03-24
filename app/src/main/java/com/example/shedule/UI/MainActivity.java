package com.example.shedule.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shedule.R;
import com.example.shedule.constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView fromTime,toTime,scheduleId;
    EditText task;
    TimePickerDialog picker;
    int thour,tminute,t1hour,t1minute;
    String h1time,h2time,txtTask,txtId;
    String TAG ="d";




    ProgressDialog loadingbar;

    Button addbtn;
    private String[] namenew;
    private String[] namenew1;
    HashMap<String, String> rejectReason = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromTime = findViewById(R.id.fromTime);
        toTime = findViewById(R.id.toTime);
        addbtn = findViewById(R.id.addbtn);
        task = findViewById(R.id.task);
        scheduleId=findViewById(R.id.scheduleId);



        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StringRequest stringRequest = new StringRequest(Request.Method.POST, constant.task_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.e(TAG, "ooon: "+response );
                                    final List<String> list1=new ArrayList<String>();
                                    final List<String> list2=new ArrayList<String>();
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject=null;
                                    String id = null;
                                    namenew=new String[jsonArray.length()];
                                    namenew1=new String[jsonArray.length()];
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        jsonObject=jsonArray.getJSONObject(i);
                                        namenew[i]=jsonObject.getString("task");

                                        namenew1[i] = jsonObject.optString("id");
                                       // Log.e(TAG, "onResponse id: "+namenew1[i] );

                                    }
                                    for (int i=0;i<namenew.length;i++)
                                    {
                                        list1.add(namenew[i]);
                                        list2.add(namenew1[i]);
                                    }
                                    Log.e(TAG, "list: "+list2 );
                                    //spinnerfuncfac();

                                    final AlertDialog.Builder mybulder=new AlertDialog.Builder(MainActivity.this);
                                    mybulder.setTitle("Select Task");
                                    mybulder.setSingleChoiceItems(namenew, -1, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            task.setText(namenew[i]);
                                            scheduleId.setText(namenew1[i]);



                                        }
                                    });
                                    mybulder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                                    mybulder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {


                                            dialogInterface.dismiss();
                                        }
                                    });

                                    AlertDialog mdialog=mybulder.create();
                                    mdialog.show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Hours.setText("Something Went Wrong....");
                        error.printStackTrace();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadTask();


                Intent intent = new Intent(MainActivity.this, viewShedule.class);
                startActivity(intent);




            }
        });




        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                thour = hourOfDay;
                                tminute = minute;

                                String time = thour + ":" + tminute;
                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );

                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );

                                    fromTime.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(thour,tminute);
                timePickerDialog.show();



            }
        });

        toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1hour = hourOfDay;
                                t1minute = minute;

                                String time = t1hour + ":" + t1minute;
                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );

                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );

                                    toTime.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(t1hour,t1minute);
                timePickerDialog.show();

            }
        });



    }

    public void uploadTask()
    {

        h1time = fromTime.getText().toString();
        h2time = toTime.getText().toString();
        txtTask = task.getText().toString();
        txtId = scheduleId.getText().toString();

        Log.e(TAG, "uploadTask: "+txtId );

        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                constant.uploadTask_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("response", "onResponse: "+response );
                          JSONArray jsonArray = new JSONArray(response);

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
                params.put("h1time",h1time);
                params.put("h2time",h2time);
                params.put("txtTask",txtTask);
                params.put("txtId",txtId);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    @Override
    public void onBackPressed() {
        finish();

        Intent intent=new Intent(MainActivity.this,dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


}