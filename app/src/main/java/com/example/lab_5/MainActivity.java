package com.example.lab_5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private Button buttonFilter;
    private CurrencyAdapter adapter;
    private List<Currency> currencyList = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        buttonFilter = findViewById(R.id.buttonFilter);

        adapter = new CurrencyAdapter(this, currencyList);
        listView.setAdapter(adapter);

        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editText.getText().toString();
                Log.d(TAG, "Filter button clicked with query: " + query);
                adapter.filter(query);
            }
        });

        new DataLoader(this).execute("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
    }

    public void updateAdapter(List<Currency> result) {
        adapter.updateList(result);
    }
}
