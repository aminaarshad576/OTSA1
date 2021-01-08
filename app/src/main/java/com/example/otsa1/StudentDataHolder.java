package com.example.otsa1;

public class StudentDataHolder {
    String name,password, address, father_name,phone_no, pro_image ;


    public StudentDataHolder(String name, String password, String address, String father_name, String phone_no, String pro_image) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.father_name = father_name;
        this.phone_no = phone_no;
        this.pro_image = pro_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }
}
