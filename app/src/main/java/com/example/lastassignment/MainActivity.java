package com.example.lastassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lastassignment.Model.CompanyEntity;
import com.example.lastassignment.Model.CompanyEntityManager;
import com.example.lastassignment.database.CompaniesDB;
import com.example.lastassignment.database.DBManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkService.NetworkingListener, CompanyAdapter.companyClickListner {

    static CompanyEntityManager companyManager = new CompanyEntityManager();
    CompanyEntity companyEntityObject;

    DBManager dbManager;
    CompaniesDB db;

    AlertDialog.Builder builder;
    RecyclerView recyclerView;
    NetworkService networkManager;
    JsonService jsonService;
    CompanyAdapter companyAdapter;

    ArrayList<Company> companies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkManager = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();

        networkManager.listener = this;
        recyclerView = findViewById(R.id.companySearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        companyAdapter = new CompanyAdapter(this, companies);
        recyclerView.setAdapter(companyAdapter);
        builder = new AlertDialog.Builder(this);

        companyEntityObject = new CompanyEntity();
        db = DBManager.getDBInstance(this);
        dbManager = ((myApp) getApplication()).getDatabaseManager();

        setTitle("Search for new company...");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for Company by its' symbol");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 3) {
                    // search for cities
                    Log.d("newText", newText);
                    networkManager.searchForStock(newText);
                } else {
                    companies = new ArrayList<>(0);
                    companyAdapter.companyList = companies;
                    companyAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public void dataListener(String jsonString) {
        companies = jsonService.getCompaniesFromJson(jsonString);
        companyAdapter = new CompanyAdapter(this, companies);
        recyclerView.setAdapter(companyAdapter);
        companyAdapter.notifyDataSetChanged();
    }

    @Override
    public void companyClicked(Company selectedCompany) {
        companyEntityObject.setChange(selectedCompany.getChange());
        companyEntityObject.setChangePer(selectedCompany.getChangePer());
        companyEntityObject.setCpName(selectedCompany.getCpName());
        companyEntityObject.setPrice(selectedCompany.getPrice());
        companyEntityObject.setCpSymbol(selectedCompany.getCpSymbol());
        companyEntityObject.setWeek52High(selectedCompany.getWeek52High());
        companyEntityObject.setWeek52low(selectedCompany.getWeek52low());

        dbManager.insertNewCompany(companyEntityObject);

        showAnAlert();

        companyEntityObject = new CompanyEntity();
    }

    public void listClick(View v) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putParcelableArrayListExtra("listofcompanies", companyManager.getListOfCompanies());
        startActivity(intent);
    }

    private void showAnAlert() {
        builder.create();

        String companyName = companyEntityObject.getCpName();
        String companySymbol = companyEntityObject.getCpSymbol();

        builder.setMessage(companySymbol +
                " : " + companyName + " is added in your list");
        builder.setTitle("Completed!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplication(), ListActivity.class);
                intent.putParcelableArrayListExtra("listofcompanies", companyManager.getListOfCompanies());
                startActivity(intent);
            }
        });
        builder.show();
    }

}