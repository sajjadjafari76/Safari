package com.fanava.rally.server;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class ShowData {
    public void insert(final Context context,String url,JSONObject jsonObject, final Listeners.OnSuccessListener listener)
    {
        String URL =Url()+"kala/insert";
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth("123456","123456");
        client.setConnectTimeout(5000);
        client.setResponseTimeout(5000);
        client.setMaxConnections(10000);
        client.setMaxRetriesAndTimeout(2,5000);
      //  Map<String, String> jsonParams = new HashMap<String, String>();
       // jsonParams.put("name",name);
       // JSONObject jsonObject=new JSONObject(jsonParams);
        HttpEntity entity = new StringEntity(jsonObject.toString(), Charset.forName("UTF-8"));
        String  contentType = "application/json";
        // client.get()
        client.post(context, URL, entity, contentType,new JsonHttpResponseHandler(){

            @Override
            public void onStart() {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                listener.onError("");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if(statusCode==200){
                    Map<String, String> jsonParams = new HashMap<String, String>();
                    jsonParams.put("ID",responseString );
                    JSONArray jsonObject = new JSONArray();
                    jsonObject.put(jsonParams);
                    listener.onSuccess(jsonObject);
                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                listener.onError("");

            }
        });
    }

    public static String Url() {
        return "http://10.0.2.2:43193/api/";
    }
}
