package com.example.lab_5;

import android.os.AsyncTask;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataLoader extends AsyncTask<String, Void, List<Currency>> {

    private MainActivity mainActivity;

    public DataLoader(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected List<Currency> doInBackground(String... urls) {
        List<Currency> result = new ArrayList<>();
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            result = Parser.parse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Currency> result) {
        super.onPostExecute(result);
        mainActivity.updateAdapter(result);
    }
}
