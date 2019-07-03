package com.fanava.rally.Utils.DataBase;

public class Car {
    public static String CreateTable="create table If Not Exists Car"
            + "( ServerId  integer primary key autoincrement, "
            + "email  text ,"
            + "mobile  text ,"
            + "refID  text," +
            "amount integer);";
}
