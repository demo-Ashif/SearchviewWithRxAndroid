package com.sadikul.searchviewwithrxandroid.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.sadikul.searchviewwithrxandroid.Adapter.MainAdapter;
import com.sadikul.searchviewwithrxandroid.Presenter.Presenter;
import com.sadikul.searchviewwithrxandroid.R;
import com.sadikul.searchviewwithrxandroid.Retrofit.ApiInterface;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.NoticeItem;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.sadikul.searchviewwithrxandroid.Retrofit.RetrofiClient;
import com.sadikul.searchviewwithrxandroid.interfaces.MainActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    ApiInterface apiInterface;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.myrecyler)
    RecyclerView myrecyler;
    Presenter presenter;
    List<NoticeItem> list;

    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiInterface = RetrofiClient.getApiInterface();

        list=new ArrayList<>();

        adapter=new MainAdapter(getAppContext(),list);
        myrecyler.setLayoutManager(new LinearLayoutManager(this));
        myrecyler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        presenter=new Presenter(this);
        searchview.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //Here we request a search
                        presenter.getSearchResult(newText);
                        return false;
                    }
                });
    }

    @OnClick(R.id.getData)
    public void onViewClicked() {
        /*Observable<Cars> carsObservable=apiInterface.getData();
        carsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Cars>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Cars cars) {

                        Log.e("cars",cars.getCars().toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
        Observable<Notices> NoticesObservable = apiInterface.getData("ran");
        NoticesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Notices>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Notices notices) {

                        for (NoticeItem item : notices.getNotice()) {
                            Log.e("noticeItem", item.getTitle());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void showSearchData(Notices hashMap) {
        list=hashMap.getNotice();
        if(list!=null){
            adapter.setData(list);
        }else{
            adapter.clear();
        }

        if(hashMap!=null){

            if(hashMap.getNotice()!=null){
                for(NoticeItem noticeItem:hashMap.getNotice()){
                    Log.e("notice",noticeItem.getTitle());
                }
            }
        }
    }

    @Override
    public void startLoading() {
        Toast.makeText(getAppContext(),"Start hunting",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void stopLoading() {

        Toast.makeText(getAppContext(),"Finished",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {

        Toast.makeText(getAppContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }
}
