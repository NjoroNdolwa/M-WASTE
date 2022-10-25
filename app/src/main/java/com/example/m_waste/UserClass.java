package com.example.m_waste;

public class UserClass {
    String name,county,phone,point,email,pass;

    public UserClass() {
    }

    public UserClass(String name, String county, String phone, String point, String email, String pass) {
        this.name = name;
        this.county = county;
        this.phone = phone;
        this.point = point;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
