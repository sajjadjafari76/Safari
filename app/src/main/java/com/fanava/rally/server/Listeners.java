package com.fanava.rally.server;

import org.json.JSONArray;

public class Listeners {
    public interface OnSuccessListener {
        void onSuccess(JSONArray jsonObject);

        void onError(String error);
    }
}
