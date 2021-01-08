package com.example.otsa1;

public class DriverDataHolder {
    String name, password, phone_no, dri_cnic, address, pro_image;

    public DriverDataHolder(String name, String password, String phone_no, String dri_cnic, String address, String pro_image) {
        this.name = name;
        this.password = password;
        this.phone_no = phone_no;
        this.dri_cnic = dri_cnic;
        this.address = address;
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

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getDri_cnic() {
        return dri_cnic;
    }

    public void setDri_cnic(String dri_cnic) {
        this.dri_cnic = dri_cnic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }
}
