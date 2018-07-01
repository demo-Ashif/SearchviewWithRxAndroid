package com.sadikul.searchviewwithrxandroid.interfaces;

import android.content.Context;

import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.MedicineSearchList;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.NoticeItem;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;

public interface MainActivityView {
    void showSearchData(MedicineSearchList hashMap);

    void startLoading();

    void stopLoading();

    void showMessage(String msg);

    Context getAppContext();
}
