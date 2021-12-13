package com.example.lastassignment;

import android.util.Log;

import org.json.*;

import java.util.ArrayList;

public class JsonService {

    public ArrayList<Company> getCompaniesFromJson(String json) {
        ArrayList<Company> compaines = new ArrayList<>();
        Company companyData = new Company();
        try {
//              {"avgTotalVolume":97350766,"calculationPrice":"iexlasttrade","change":4.01,
//              "changePercent":0.02343,"close":null,"closeSource":"official","closeTime":null,
//              "companyName":"Apple Inc","currency":"USD","delayedPrice":null,"delayedPriceTime":null,
//              "extendedChange":null,"extendedChangePercent":null,"extendedPrice":null,"extendedPriceTime":null,
//              "high":null,"highSource":null,"highTime":null,"iexAskPrice":0,"iexAskSize":0,"iexBidPrice":0,
//              "iexBidSize":0,"iexClose":175.19,"iexCloseTime":1638997197447,"iexLastUpdated":1638998337686,
//              "iexMarketPercent":0.0142160861535064,"iexOpen":172.07,"iexOpenTime":1638973800490,
//              "iexRealtimePrice":175.16,"iexRealtimeSize":6,"iexVolume":1660300,"lastTradeTime":1638997199725,
//              "latestPrice":175.19,"latestSource":"IEX Last Trade","latestTime":"December 8, 2021",
//              "latestUpdate":1638997197447,"latestVolume":null,"low":null,"lowSource":null,"lowTime":null,
//              "marketCap":2874236690430,"oddLotDelayedPrice":null,"oddLotDelayedPriceTime":null,"open":null,
//              "openTime":null,"openSource":"official","peRatio":15.6,"previousClose":171.18,
//              "previousVolume":120405352,"primaryExchange":"NASDAQ","symbol":"AAPL","volume":null,
//              "week52High":171.58,"week52Low":115.67,"ytdChange":0.3214577076366613,"isUSMarketOpen":false}
            JSONObject obj = new JSONObject(json);
            String cpName = obj.getString("companyName");
            String cpSymbol = obj.getString("symbol");
            Double week52Low = obj.getDouble("week52Low");
            Double week52High = obj.getDouble("week52High");
            Double change = obj.getDouble("change");
            Double changePer = obj.getDouble("changePercent");
            Double price = obj.getDouble("latestPrice");

            Log.d("obj", obj.getString(("companyName")));

            companyData.setCpName(cpName);
            companyData.setChange(change);
            companyData.setChangePer(changePer);
            companyData.setCpSymbol(cpSymbol);
            companyData.setWeek52High(week52High);
            companyData.setWeek52low(week52Low);
            companyData.setPrice(price);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("company", companyData.getCpName() + " " + companyData.getCpSymbol());
        compaines.add(companyData);
        return compaines;
    }
}
