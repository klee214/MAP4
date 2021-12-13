package com.example.lastassignment.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.room.Room;

import com.example.lastassignment.Company;
import com.example.lastassignment.Model.CompanyEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBManager {
    static CompaniesDB db;
    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());

    public interface DatabaseListener {
        void databaseAllCompaniesListener(List<CompanyEntity> list);
    }

    public DatabaseListener listener;

    private static void BuildDBInstance(Context context) {
        db = Room.databaseBuilder(context, CompaniesDB.class, "company_db").build();
    }

    public static CompaniesDB getDBInstance(Context context) {
        if (db == null) {
            BuildDBInstance(context);
        }
        return db;
    }

    public void insertNewCompany(CompanyEntity c){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getCompanyDAO().insertNewCompany(c);
            }
        });
    }

    public void deleteCompany (CompanyEntity c){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getCompanyDAO().deleteCompany(c.getCpName());
            }
        });
    }

    public void getCompany (CompanyEntity c){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getCompanyDAO().getOnebyId(c.getId());
            }
        });
    }

    public void getAllCompanies(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<CompanyEntity> list =  db.getCompanyDAO().getAll();
                db_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseAllCompaniesListener(list);
                    }
                });

            }
        });

    }

}
