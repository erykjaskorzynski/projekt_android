package com.example.projekt_ejjk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_getCityID, btn_getStatsByID, btn_getCityStatsByName;
    EditText et_dataInput;
    ListView lv_ammoReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    btn_getCityID = findViewById(R.id.btn_getCityID);
    btn_getStatsByID = findViewById(R.id.btn_getStatsByID);
    btn_getCityStatsByName = findViewById((R.id.btn_getCityStatsByName));

    et_dataInput = findViewById(R.id.et_dataInput);
    lv_ammoReport = findViewById(R.id.lv_ammoReport);


    final AmmoDataService ammoDataService = new AmmoDataService(MainActivity.this);

    btn_getStatsByID.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ammoDataService.getCityForecastByID(et_dataInput.getText().toString(), new AmmoDataService.ForecastByIDResponse() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "Something's wrong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(List<AmmoReportModel> ammoReportModels) {

                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, ammoReportModels);
                    lv_ammoReport.setAdapter((arrayAdapter));

                }
            });
        }
    });

    btn_getCityID.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ammoDataService.getCityID(et_dataInput.getText().toString(), new AmmoDataService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "Something's wrong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cityID) {
                    Toast.makeText(MainActivity.this, "City has an ID of " + cityID, Toast.LENGTH_LONG).show();
                }
            });


        }
    });

    btn_getCityStatsByName.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            ammoDataService.getCityForecastByName(et_dataInput.getText().toString(), new AmmoDataService.GetCityForecastByNameCallback() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "Something's wrong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(List<AmmoReportModel> ammoReportModels) {

                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, ammoReportModels);
                    lv_ammoReport.setAdapter((arrayAdapter));

                }
            });


        }
    });

    }
}

