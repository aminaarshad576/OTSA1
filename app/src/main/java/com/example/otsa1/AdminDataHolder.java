package com.example.otsa1;

public class AdminDataHolder {
    String name,password,email,phone_no, adm_cnic, designation;

    public AdminDataHolder(String name, String password, String email, String phone_no, String adm_cnic, String designation) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone_no = phone_no;
        this.adm_cnic = adm_cnic;
        this.designation = designation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAdm_cnic() {
        return adm_cnic;
    }

    public void setAdm_cnic(String adm_cnic) {
        this.adm_cnic = adm_cnic;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
