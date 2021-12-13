package com.example.lastassignment.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.lastassignment.Model.CompanyEntity;

import java.util.List;

@Dao
public interface CompanyDAO {
    @Insert
    void insertNewCompany(CompanyEntity company);

    @Query("DELETE FROM CompanyEntity WHERE cpName IS :name")
    void deleteCompany(String name);

//    @Update
//    void updateDonation(Donation toUpdate);

    @Query("Select * FROM CompanyEntity")
    List<CompanyEntity> getAll();

    @Query("SELECT * FROM CompanyEntity WHERE id >= :id ")
    CompanyEntity getOnebyId(int id);


//
//    @Query("SELECT * FROM Donation WHERE paymentMethod = :method")
//    List<Donation> getAllDonationsWithPaymentMethod(int method);


}
