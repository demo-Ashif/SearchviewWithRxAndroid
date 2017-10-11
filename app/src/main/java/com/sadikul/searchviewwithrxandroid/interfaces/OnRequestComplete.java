package com.sadikul.searchviewwithrxandroid.interfaces;

import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;

public interface OnRequestComplete {
    void onRequestComplete(Notices o);
    void onRequestError(String errorMsg);
}
