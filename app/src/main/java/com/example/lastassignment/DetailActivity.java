package com.example.lastassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lastassignment.Model.CompanyEntity;
import com.example.lastassignment.Model.CompanyEntityManager;
import com.example.lastassignment.database.CompaniesDB;
import com.example.lastassignment.database.DBManager;

public class DetailActivity extends AppCompatActivity {

    DBManager dbManager;
    CompaniesDB db;
    static CompanyEntityManager companyManager = new CompanyEntityManager();

    TextView companyTitle;
    TextView info;
    TextView info2;

    CompanyEntity companyEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        companyEntity = getIntent().getParcelableExtra("company");

        db = DBManager.getDBInstance(this);
        dbManager = ((myApp) getApplication()).getDatabaseManager();

        companyTitle = findViewById(R.id.compTitle);
        info = findViewById(R.id.stockInfo);
        info2= findViewById(R.id.stockInfo2);

        String diff_from_yesterday;

        if (companyEntity.getChange() > 0) {
            diff_from_yesterday = "INCREASED";
        } else {
            diff_from_yesterday = "DECREASED";
        }
        companyTitle.setText(companyEntity.getCpSymbol() + " : " + companyEntity.getCpName());
        info.setText("The stock " + diff_from_yesterday + " by " + companyEntity.getChangePer() + "%. Current price is " + companyEntity.getPrice());
        info2.setText("Within 52 weeks the highest price is " + companyEntity.getWeek52High() + ", the lowest is " + companyEntity.getWeek52low());
    }

    public void removeClick(View v) {
        dbManager.deleteCompany(companyEntity);
        Toast.makeText(this, "The company " + companyEntity.getCpName() + " is removed", Toast.LENGTH_LONG);

        Intent intent = new Intent(this, ListActivity.class);
        intent.putParcelableArrayListExtra("listofcompanies", companyManager.getListOfCompanies());
        startActivity(intent);
    }
}
