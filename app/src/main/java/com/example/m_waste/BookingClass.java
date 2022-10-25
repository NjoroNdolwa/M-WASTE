package com.example.m_waste;

public class BookingClass {
    String names,types,collection_points,days,dates,statuses,phone,id;

    public BookingClass() {
    }

    public BookingClass(String names, String types, String collection_points, String days, String dates, String statuses, String phone, String id) {
        this.names = names;
        this.types = types;
        this.collection_points = collection_points;
        this.days = days;
        this.dates = dates;
        this.statuses = statuses;
        this.phone = phone;
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getCollection_points() {
        return collection_points;
    }

    public void setCollection_points(String collection_points) {
        this.collection_points = collection_points;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
