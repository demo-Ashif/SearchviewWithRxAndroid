package com.sadikul.searchviewwithrxandroid.Retrofit;

import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.sadikul.searchviewwithrxandroid.Utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ASUS on 10-Oct-17.
 */

public interface ApiInterface{

    @POST(Constants.getList)
    Observable<Notices> getData(@Query("key") String key);
    @GET(Constants.getCartoonLinks)
    Observable<Notices> getNotices();

}
