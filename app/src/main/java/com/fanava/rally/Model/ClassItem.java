package com.fanava.rally.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ClassItem {
    public int id;
    public String image;
    public String competitionNumber;
    public String location;
    public String date;

    public ClassItem(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.image = jsonObject.getString("avatar");
            this.competitionNumber = jsonObject.getString("name");
            this.location = jsonObject.getString("city");
            this.date = jsonObject.getString("raceDate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
