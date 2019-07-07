package com.fanava.rally.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ClassItem {
    public int id;
    public int mode;
    public String image;
    public String competitionNumber;
    public String location;
    public String date;

    public ClassItem(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.mode = jsonObject.getInt("mode");
            this.image = jsonObject.getString("image");
            this.competitionNumber = jsonObject.getString("competitionNumber");
            this.location = jsonObject.getString("location");
            this.date = jsonObject.getString("date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
