package com.sadikul.searchviewwithrxandroid.Model;

import android.content.Context;

import com.sadikul.searchviewwithrxandroid.Retrofit.ApiInterface;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.sadikul.searchviewwithrxandroid.Retrofit.RetrofiClient;
import com.sadikul.searchviewwithrxandroid.interfaces.OnRequestComplete;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 11-Oct-17.
 */

public class SearchApi {
    OnRequestComplete requestComplete;
    PublishSubject publishSubject;
    public SearchApi(final Context context, final String query, final OnRequestComplete onRequestComplete) {
        this.requestComplete = onRequestComplete;
        ApiInterface apiInterface= RetrofiClient.getApiInterface();
        if (publishSubject == null) {
            publishSubject = PublishSubject.create();
            publishSubject
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .distinctUntilChanged()
                    .switchMap(searchValue -> apiInterface.getData(query)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()))
                    .subscribeWith(new DisposableObserver<Notices>() {
                        @Override
                        public void onNext(Notices response) {
                            //Update View here
                            requestComplete.onRequestComplete(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //On error
                            requestComplete.onRequestError("Something went wrong..");
                        }

                        @Override
                        public void onComplete() {
                            //On complete
                        }
                    });
            publishSubject.onNext(query);
        }


    }
}
