package com.example.m_waste;

public class TruckClass {
    String t_name,w_type,collection_point,day,date,status;
    public TruckClass() {
    }

    public TruckClass(String t_name, String w_type, String collection_point, String day, String date, String status) {
        this.t_name = t_name;
        this.w_type = w_type;
        this.collection_point = collection_point;
        this.day = day;
        this.date = date;
        this.status = status;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getW_type() {
        return w_type;
    }

    public void setW_type(String w_type) {
        this.w_type = w_type;
    }

    public String getCollection_point() {
        return collection_point;
    }

    public void setCollection_point(String collection_point) {
        this.collection_point = collection_point;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
