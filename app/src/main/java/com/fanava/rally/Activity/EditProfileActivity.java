package com.fanava.rally.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.Account.Register.InfomationActivity;
import com.fanava.rally.MainActivity;
import com.fanava.rally.R;
import com.fanava.rally.Utils.Url;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {


    EditText editText_name, editText_family, editText_national_number, editText_license_number, editText_password;
    String name, family, national_number, license_number, password;
    SharedPreferences prefs = this.getSharedPreferences("info", Context.MODE_PRIVATE);
    NiceSpinner spinnerClass, spinnerGender;
    String[] itemsClass = {"آماتور", "متوسط", "حرفه ای"};
    String[] itemsGender = {"زن", "مرد"};
    int posClass, posGender;
    ProgressBar progressBar;
    RelativeLayout parent;

    ImageView img_add, img_close;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        img_add = findViewById(R.id.img_add);
        img_close = findViewById(R.id.img_close);
        progressBar = findViewById(R.id.progress);
        parent = findViewById(R.id.parent);

        editText_name = findViewById(R.id.edt_name);
        editText_family = findViewById(R.id.edt_family);
        editText_national_number = findViewById(R.id.edt_national_number);
        editText_license_number = findViewById(R.id.edt_license_number);
        editText_password = findViewById(R.id.edt_password);
        spinnerGender = findViewById(R.id.spinner_gender);
        spinnerClass = findViewById(R.id.spinner_class);
        getData();

        ArrayAdapter<String> adapterClass = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsClass);
        spinnerClass.setAdapter(adapterClass);
        spinnerClass.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                posClass = position;
                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsGender);
        spinnerClass.setAdapter(adapterGender);
        spinnerClass.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                posGender = position;
                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();
            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText_name.getText().toString();
                family = editText_family.getText().toString();
                national_number = editText_national_number.getText().toString();
                license_number = editText_license_number.getText().toString();
                password = editText_password.getText().toString();
                sendRequest();
            }
        });
    }



    public void getData() {

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


    public void sendRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("login", "onErrorResponse: " + volleyError.toString());
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("family", family);
                params.put("number", national_number);
                params.put("license", license_number);
                params.put("gender", String.valueOf(posGender));
                params.put("class", String.valueOf(posClass));
                return super.getParams();
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
