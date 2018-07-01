package com.sadikul.searchviewwithrxandroid.interfaces;

import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.MedicineSearchList;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;

public interface OnRequestComplete {
    void onRequestComplete(MedicineSearchList o);
    void onRequestError(String errorMsg);
}
