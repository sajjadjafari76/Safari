package com.fanava.rally.Account.Register;

import androidx.appcompat.app.AppCompatActivity;

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
import com.fanava.rally.R;
import com.fanava.rally.Utils.Url;

import java.util.HashMap;
import java.util.Map;

public class GetPhoneActivity extends AppCompatActivity {

    EditText editText_phone;
    Button btn_register;
    TextView textView_login;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone);

        editText_phone = findViewById(R.id.edt_phone);
        btn_register = findViewById(R.id.btn_register);
        textView_login = findViewById(R.id.text_login);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = editText_phone.getText().toString();
                sendRequest();
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
                return super.getParams();
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


}
