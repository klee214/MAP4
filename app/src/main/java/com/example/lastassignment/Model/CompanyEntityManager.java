package com.example.lastassignment.Model;

import java.util.ArrayList;

public class CompanyEntityManager {

    ArrayList<CompanyEntity> listOfCompanies = new ArrayList<>();

    public ArrayList<CompanyEntity> getListOfCompanies() {
        return listOfCompanies;
    }

    public void addNewCompany(CompanyEntity c) {
        listOfCompanies.add(c);
    }
}
