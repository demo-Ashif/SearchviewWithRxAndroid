package com.sadikul.searchviewwithrxandroid.interfaces;

import android.content.Context;

import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.NoticeItem;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;

public interface MainActivityView {
    void showSearchData(Notices hashMap);

    void startLoading();

    void stopLoading();

    void showMessage(String msg);

    Context getAppContext();
}
