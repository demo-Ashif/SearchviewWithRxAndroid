package com.sadikul.searchviewwithrxandroid.Retrofit.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicineSearchList {

    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<MedicineItem> medicineList = null;

    @Override
    public String toString() {
        return "MedicineSearchList{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", medicineList=" + medicineList +
                '}';
    }
}
