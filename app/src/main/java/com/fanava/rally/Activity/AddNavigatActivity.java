package com.fanava.rally.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.R;
import com.fanava.rally.Utils.Url;

import org.angmarch.views.NiceSpinner;

import java.util.HashMap;
import java.util.Map;

public class AddNavigatActivity extends AppCompatActivity {

    ImageView img_add, img_close;
    EditText edt_name, edt_family, edt_national_number, edt_phone, edt_relation;
    NiceSpinner spinner_gender;

    ProgressBar progressBar;
    RelativeLayout parent;

    SharedPreferences prefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_navigat);
        prefs = getSharedPreferences("info", Context.MODE_PRIVATE);
        img_add = findViewById(R.id.img_add);
        img_close = findViewById(R.id.img_close);
        edt_name = findViewById(R.id.edt_name);
        edt_family = findViewById(R.id.edt_family);
        edt_national_number = findViewById(R.id.edt_national_number);
        edt_phone = findViewById(R.id.edt_phone);
        edt_relation = findViewById(R.id.edt_relation);
        spinner_gender = findViewById(R.id.spinner_gender);
        progressBar = findViewById(R.id.progress);
        parent = findViewById(R.id.parent);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                sendData();
            }
        });
    }

//    public void getData() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.GlobalUrl + "", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                parent.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.GONE);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                parent.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.GONE);
//                Log.i("login", "onErrorResponse: " + volleyError.toString());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> header = new HashMap<>();
//                header.put("Authorization", "bearer" + prefs.getString("token", ""));
//                return header;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//
//    }

    public void sendData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.GlobalUrl + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Log.i("login", "onErrorResponse: " + volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", edt_name.getText().toString());
                params.put("family", edt_family.getText().toString());
                params.put("number", edt_national_number.getText().toString());
                params.put("phone", edt_phone.getText().toString());
                params.put("relation", edt_relation.getText().toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "bearer" + prefs.getString("token", ""));
                return header;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}
