package com.fanava.rally.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ClassSlider {
    public int id;

    String sliderImageUrl;

    public String getSliderImageUrl() {
        return sliderImageUrl;
    }

    public void setSliderImageUrl(String sliderImageUrl) {
        this.sliderImageUrl = sliderImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
