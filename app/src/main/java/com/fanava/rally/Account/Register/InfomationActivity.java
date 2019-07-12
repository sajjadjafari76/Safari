package com.fanava.rally.Account.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.MainActivity;
import com.fanava.rally.R;
import com.fanava.rally.Utils.Url;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.HashMap;
import java.util.Map;

public class InfomationActivity extends AppCompatActivity {


    EditText editText_name, editText_family, editText_national_number, editText_license_number, editText_password;
    Button btn_next;
    String name, family, national_number, license_number, password;
    SharedPreferences prefs = this.getSharedPreferences("info", Context.MODE_PRIVATE);
    NiceSpinner spinner1;
    String[] items = {"آماتور", "متوسط", "حرفه ای"};
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);

        editText_name = findViewById(R.id.edt_name);
        editText_family = findViewById(R.id.edt_family);
        editText_national_number = findViewById(R.id.edt_national_number);
        editText_license_number = findViewById(R.id.edt_license_number);
        editText_password = findViewById(R.id.edt_password);
        btn_next = findViewById(R.id.btn_register);
        spinner1 = findViewById(R.id.spinner1);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner1.setAdapter(adapter);
        spinner1.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                pos = position;
                Toast.makeText(InfomationActivity.this, position, Toast.LENGTH_SHORT).show();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
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

    public void sendRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("1")) {
                    Intent intent = new Intent(InfomationActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (response.equals("0")) {
                    Toast.makeText(InfomationActivity.this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("login", "onErrorResponse: " + volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("family", family);
                params.put("number", national_number);
                params.put("license", license_number);
                params.put("password", password);
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
