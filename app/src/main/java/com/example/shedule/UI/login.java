package com.example.shedule.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shedule.R;
import com.example.shedule.Shaireprefmanager;
import com.example.shedule.constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    EditText txtusername,txtpassword;
    Button btnlogin;
    String username,password,uname;
    private ProgressDialog loadingbar;
    private Toolbar mtoolbar;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);

        if(Shaireprefmanager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, dashboard.class));
            return;
        }


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }


    public void userLogin(){



        username = txtusername.getText().toString();
        password = txtpassword.getText().toString();

        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                constant.login_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("response", "onResponse: "+response );
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String code=jsonObject.getString("code");
                            String refid=jsonObject.getString("refid");
                            String name=jsonObject.getString("uname");




                            Log.e("Response", "onResponse: "+code );

                            if(code.equals("false"))
                            {
                                Log.e("response", "if " );
                                Toast.makeText(login.this,"Login Error",Toast.LENGTH_SHORT).show();


                            }
                            else {
                                Log.e("response", "else " );


                                Shaireprefmanager.getInstance(getApplicationContext())
                                        .userLogin(name,refid);


                                uname=jsonObject.getString("uname");
//

                                Toast.makeText(login.this,"Login Success",Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(login.this, dashboard.class);
                                intent.putExtra("uname",uname);
                                startActivity(intent);


                            }
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
                params.put("username",username);
                params.put("password",password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }



}