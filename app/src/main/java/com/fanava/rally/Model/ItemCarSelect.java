package com.fanava.rally.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemCarSelect {

    public int id;
    public String name;
    public String pelak;

    public ItemCarSelect(JSONObject object) throws JSONException {
        this.id = object.getInt("id");
        this.name = object.getString("name");
        this.pelak = object.getString("pelak");
    }
}
