package com.example.otsa1;

public class ParentDataHolder {
    String par_name, par_password, par_phone_no, par_cnic, parChildReg, family_member;

    public ParentDataHolder(String par_name, String par_password, String par_phone_no, String par_cnic, String parChildReg, String family_member) {
        this.par_name = par_name;
        this.par_password = par_password;
        this.par_phone_no = par_phone_no;
        this.par_cnic = par_cnic;
        this.parChildReg = parChildReg;
        this.family_member = family_member;
    }

    public String getPar_name() {
        return par_name;
    }

    public void setPar_name(String par_name) {
        this.par_name = par_name;
    }

    public String getPar_password() {
        return par_password;
    }

    public void setPar_password(String par_password) {
        this.par_password = par_password;
    }

    public String getPar_phone_no() {
        return par_phone_no;
    }

    public void setPar_phone_no(String par_phone_no) {
        this.par_phone_no = par_phone_no;
    }

    public String getPar_cnic() {
        return par_cnic;
    }

    public void setPar_cnic(String par_cnic) {
        this.par_cnic = par_cnic;
    }

    public String getParChildReg() {
        return parChildReg;
    }

    public void setParChildReg(String parChildReg) {
        this.parChildReg = parChildReg;
    }

    public String getFamily_member() {
        return family_member;
    }

    public void setFamily_member(String family_member) {
        this.family_member = family_member;
    }
}

