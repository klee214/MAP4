package com.example.lastassignment.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lastassignment.Model.CompanyEntity;

@Database(version = 1,entities = {CompanyEntity.class})
public abstract class CompaniesDB  extends RoomDatabase {
    abstract public CompanyDAO getCompanyDAO();
}
