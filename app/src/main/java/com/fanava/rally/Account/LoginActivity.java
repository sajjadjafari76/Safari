package com.fanava.rally.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.Account.Register.GetPhoneActivity;
import com.fanava.rally.Model.ClassItem;
import com.fanava.rally.R;
import com.fanava.rally.Utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText editText_phone, editText_passowrd;
    Button btn_login;
    TextView textView_register;
    String phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_phone = findViewById(R.id.edt_phone);
        editText_passowrd = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        textView_register = findViewById(R.id.text_register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = editText_phone.getText().toString();
                password = editText_passowrd.getText().toString();
                sendRequest();
            }
        });
        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GetPhoneActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("login", "onErrorResponse: "+volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("password", password);
                return super.getParams();
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
