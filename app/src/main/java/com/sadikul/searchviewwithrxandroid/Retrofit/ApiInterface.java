package com.sadikul.searchviewwithrxandroid.Retrofit;

import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.MedicineSearchList;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.sadikul.searchviewwithrxandroid.Utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ASUS on 10-Oct-17.
 */

public interface ApiInterface{

    @POST(Constants.search_result_api)
    Observable<MedicineSearchList> getData(
            @Query("name") String key
    );

}
