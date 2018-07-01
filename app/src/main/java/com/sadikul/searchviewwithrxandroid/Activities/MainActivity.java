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
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.MedicineItem;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.MedicineSearchList;
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

public class MainActivity extends AppCompatActivity implements MainActivityView {

    ApiInterface apiInterface;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.myrecyler)
    RecyclerView myrecyler;
    Presenter presenter;
    List<MedicineItem> list;

    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiInterface = RetrofiClient.getApiInterface();

        searchview.setIconifiedByDefault(false);
        list = new ArrayList<>();

        adapter = new MainAdapter(this, list);
        myrecyler.setLayoutManager(new LinearLayoutManager(this));
        myrecyler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        presenter = new Presenter(this);
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


    @Override
    public void showSearchData(MedicineSearchList hashMap) {
        list = hashMap.medicineList;
        if (list != null) {
            adapter.setData(list);
        } else {
            adapter.clear();
        }

        if (hashMap != null) {

            if (hashMap.medicineList != null) {
                for (MedicineItem medicineItem : hashMap.medicineList) {
                    Log.e("notice", medicineItem.name);
                }
            }
        }
    }

    @Override
    public void startLoading() {
        //Toast.makeText(getAppContext(),"Start hunting",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void stopLoading() {

        //Toast.makeText(getAppContext(),"Finished",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {

        Toast.makeText(getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }
}
