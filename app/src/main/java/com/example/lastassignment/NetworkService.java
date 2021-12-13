package com.example.lastassignment;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NetworkService {

    private String stockBaseURL = "https://sandbox.iexapis.com/stable/stock/";
    private  String stockParams = "/quote?token=Tpk_b8402ab18931497d9ec4cc22ccb6274a";

    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler =new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void dataListener(String jsonString);
    }

    public NetworkingListener listener;

    public void searchForStock(String cpSymbol){
        String URL = stockBaseURL + cpSymbol + stockParams;
        Log.d("URL", URL);
        connect(URL);
    }

    public void connect(String url){
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    String jsonData = "";
                    URL urlObj = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setRequestMethod("GET");// post, delete, put
                    httpURLConnection.setRequestProperty("Conent-Type","application/json");

                    InputStream in = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    int inputSteamData = 0;
                    while ((inputSteamData = reader.read()) != -1){
                        char current = (char)inputSteamData;
                        jsonData += current;
                    }
                    Log.d("jsonData", jsonData);
                    final String finalData = jsonData;

                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.dataListener(finalData);
                        }
                    });

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }

            }
        });
    }
}
