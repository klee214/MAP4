package com.example.lastassignment.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CompanyEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String cpSymbol;
    private String cpName;

    private Double price;
    private Double change;
    private Double changePer;
    private Double week52High;
    private Double week52low;

    protected CompanyEntity(Parcel in) {
        cpSymbol = in.readString();
        cpName = in.readString();
        price = in.readDouble();
        change = in.readDouble();
        changePer = in.readDouble();
        week52High = in.readDouble();
        week52low = in.readDouble();
    }

    public static final Creator<CompanyEntity> CREATOR = new Creator<CompanyEntity>() {
        @Override
        public CompanyEntity createFromParcel(Parcel in) {
            return new CompanyEntity(in);
        }

        @Override
        public CompanyEntity[] newArray(int size) {
            return new CompanyEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cpSymbol);
        parcel.writeString(cpName);
        parcel.writeDouble(price);
        parcel.writeDouble(change);
        parcel.writeDouble(changePer);
        parcel.writeDouble(week52High);
        parcel.writeDouble(week52low);
    }

    public CompanyEntity() {
    }

    public CompanyEntity(String cpSymbol, String cpName, Double price, Double change, Double changePer, Double week52High, Double week52low) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
