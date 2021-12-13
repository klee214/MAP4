package com.example.lastassignment;

public class Company {
    private String cpSymbol;
    private String cpName;

    private Double price;
    private Double change;
    private Double changePer;
    private Double week52High;
    private Double week52low;

    Company(){}

    public Company(String cpSymbol, String cpName, Double price, Double change, Double changePer, Double week52High, Double week52low){
        this.cpSymbol = cpSymbol;
        this.cpName = cpName;
        this.price = price;
        this.change = change;
        this.changePer = changePer;
        this.week52High = week52High;
        this.week52low = week52low;
    }

    public void setCpSymbol(String symbol) {
        this.cpSymbol = symbol;
    }
    public void setCpName(String cpName) {
        this.cpName = cpName;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setChange(Double change) {
        this.change = change;
    }
    public void setChangePer(Double changePer) {
        this.changePer = changePer;
    }
    public void setWeek52High(Double week52High) {
        this.week52High = week52High;
    }
    public void setWeek52low(Double week52low) {
        this.week52low = week52low;
    }

    public String getCpSymbol() {
        return this.cpSymbol;
    }
    public String getCpName() {
        return this.cpName;
    }
    public Double getPrice() {
        return this.price;
    }
    public Double getChange() {
        return this.change;
    }
    public Double getChangePer() {
        return this.changePer;
    }
    public Double getWeek52High() {
        return this.week52High;
    }
    public Double getWeek52low() {
        return this.week52low;
    }

}
