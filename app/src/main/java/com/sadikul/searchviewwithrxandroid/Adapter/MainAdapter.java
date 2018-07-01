package com.sadikul.searchviewwithrxandroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadikul.searchviewwithrxandroid.R;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.MedicineItem;
import com.sadikul.searchviewwithrxandroid.Retrofit.POJO.NoticeItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by sakib on 10/8/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {

    private final LayoutInflater inflater;
    private Context context ;
    List<MedicineItem> list = Collections.emptyList() ;

    public MainAdapter(Context context, List<MedicineItem> list){
        this.context = context ;
        this.list = list ;
        inflater = LayoutInflater.from(context) ;
    }

    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_recyler_item, parent, false);
        MainAdapterViewHolder mainAdapterViewHolder = new MainAdapterViewHolder(view) ;
        return mainAdapterViewHolder;
    }

    public void setData(List<MedicineItem> list){
        this.list.clear();
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder holder, int position) {
        MedicineItem medicineItem = list.get(position) ;
        holder.name.setText(medicineItem.name);
        holder.company_name.setText(medicineItem.companyName);
        holder.generic_name.setText(medicineItem.genericName);
        holder.sub_generic_name.setText(medicineItem.subGenericName);
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public class MainAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView name, company_name, generic_name, sub_generic_name ;
        public MainAdapterViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            company_name = (TextView) itemView.findViewById(R.id.company_name);
            generic_name = (TextView) itemView.findViewById(R.id.generic_name);
            sub_generic_name = (TextView) itemView.findViewById(R.id.sub_generic_name);
        }
    }
}
