package com.fanava.rally.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassInfoTournament {

    int id;
    String name;
    JSONObject categoryID;
    String city;
    String ciraceDatety;
    String price;
    String avatar;
    String totalCapacityCount;
    String registredCount;
    String raceState;
    String active;
    String rules;
    String strPrice;
    JSONArray images;

    public ClassInfoTournament(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.name = object.getString("name");
            this.categoryID = object.getJSONObject("categoryID");
            this.city = object.getString("city");
            this.ciraceDatety = object.getString("ciraceDatety");
            this.price = object.getString("price");
            this.avatar = object.getString("avatar");
            this.totalCapacityCount = object.getString("totalCapacityCount");
            this.registredCount = object.getString("registredCount");
            this.raceState = object.getString("raceState");
            this.active = object.getString("active");
            this.rules = object.getString("rules");
            this.strPrice = object.getString("strPrice");
            this.images = object.getJSONArray("images");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
