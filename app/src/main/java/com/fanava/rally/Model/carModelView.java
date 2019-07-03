package com.fanava.rally.Model;

import android.net.Uri;

public class carModelView {
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Uri getCar_pic() {
        return car_pic;
    }

    public void setCar_pic(Uri car_pic) {
        this.car_pic = car_pic;
    }

    public Uri getCar_pelak() {
        return car_pelak;
    }

    public void setCar_pelak(Uri car_pelak) {
        this.car_pelak = car_pelak;
    }

    public Uri getCar_bimeh() {
        return car_bimeh;
    }

    public void setCar_bimeh(Uri car_bimeh) {
        this.car_bimeh = car_bimeh;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    private   String carName;
    private int ID;
    private String model;
    private String color;
    private Uri car_pic;
    private Uri car_pelak ;
    private Uri car_bimeh;
    private int driverId;
}
