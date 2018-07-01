package com.sadikul.searchviewwithrxandroid.Retrofit.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineItem {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("medicine_sub_group_id")
    @Expose
    public int medicineSubGroupId;
    @SerializedName("company_id")
    @Expose
    public int companyId;
    @SerializedName("sub_generic_name")
    @Expose
    public String subGenericName;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("generic_name")
    @Expose
    public String genericName;

    @Override
    public String toString() {
        return "MedicineItem{" +
                "id=" + id +
                ", medicineSubGroupId=" + medicineSubGroupId +
                ", companyId=" + companyId +
                ", subGenericName='" + subGenericName + '\'' +
                ", name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", genericName='" + genericName + '\'' +
                '}';
    }
}
