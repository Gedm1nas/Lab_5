package com.example.lab_5;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends BaseAdapter {

    private Context context;
    private List<Currency> originalList;
    private List<Currency> filteredList;
    private static final String TAG = "CurrencyAdapter";

    public CurrencyAdapter(Context context, List<Currency> currencyList) {
        this.context = context;
        this.originalList = new ArrayList<>(currencyList);
        this.filteredList = currencyList;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Currency currency = filteredList.get(position);
        textView.setText(currency.getCode() + " - " + currency.getRate());

        return convertView;
    }

    public void filter(String query) {
        Log.d(TAG, "Filtering with query: " + query);
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (Currency currency : originalList) {
                if (currency.getCode().toLowerCase().contains(query.toLowerCase())) {
                    Log.d(TAG, "Adding currency: " + currency.getCode());
                    filteredList.add(currency);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateList(List<Currency> newList) {
        originalList.clear();
        originalList.addAll(newList);
        filter("");
    }
}
