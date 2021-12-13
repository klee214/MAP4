package com.example.lastassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lastassignment.Model.CompanyEntity;
import com.example.lastassignment.database.DBManager;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements DBManager.DatabaseListener {
    ArrayList<CompanyEntity> listFromDB;
    ListView listOfCompanies;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listOfCompanies = findViewById(R.id.list_of_companies);
        dbManager = ((myApp) getApplication()).getDatabaseManager();
        dbManager.listener = this;

        dbManager.getAllCompanies();

    }

    @Override
    public void databaseAllCompaniesListener(List<CompanyEntity> list) {
        listFromDB = new ArrayList<>(list);
        CompanyEntityAdapter adapter = new CompanyEntityAdapter(listFromDB, this);
        listOfCompanies.setAdapter(adapter);

        listOfCompanies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), DetailActivity.class);
                intent.putExtra("company", listFromDB.get(i));

                startActivity(intent);
            }
        });
    }

}
