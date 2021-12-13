package com.example.lastassignment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.lastassignment.Model.CompanyEntity;

import java.util.ArrayList;

public class CompanyEntityAdapter extends BaseAdapter {
    ArrayList<CompanyEntity> listOfCompanies;
    Context list_activity_context;

    CompanyEntityAdapter(ArrayList<CompanyEntity> list, Context activity_context) {
        listOfCompanies = list;
        list_activity_context = activity_context;
    }

    @Override
    public int getCount() {
        return listOfCompanies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         String cpSymbol = listOfCompanies.get(i).getCpSymbol();
         String cpName = listOfCompanies.get(i).getCpName();

         Double price = listOfCompanies.get(i).getPrice();
         Double change = listOfCompanies.get(i).getChange();
         Double changePer = listOfCompanies.get(i).getChangePer();
         Double week52High = listOfCompanies.get(i).getWeek52High();
         Double week52low = listOfCompanies.get(i).getWeek52low();

         String diff_from_yesterday;

        view = LayoutInflater.from(list_activity_context).inflate(R.layout.company_item,null);
        TextView title = view.findViewById(R.id.company_title);
        TextView stock_info = view.findViewById(R.id.stock_info);
        TextView stock_info2 = view.findViewById(R.id.stock_info2);

        if (change < 0){
            diff_from_yesterday = "DECREASED";
            stock_info2.setTextColor(Color.parseColor("#F33A3A"));
        }else{
            diff_from_yesterday = "INCREASED";
            stock_info2.setTextColor(Color.parseColor("#29752C"));
        }

        title.setText(cpSymbol+" : " + cpName);
        stock_info.setText("This company is " + diff_from_yesterday + " today");
        stock_info2.setText(Double.toString(change));
        return view;
    }

}
