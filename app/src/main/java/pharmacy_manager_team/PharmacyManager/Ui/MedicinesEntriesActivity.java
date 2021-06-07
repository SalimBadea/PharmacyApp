package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.adapters.MedicinesAdapter;
import pharmacy_manager_team.PharmacyManager.adapters.MyMedicinesAdapter;
import pharmacy_manager_team.PharmacyManager.moduels.ClientMedicines;
import pharmacy_manager_team.PharmacyManager.moduels.MedicineModuel;
import pharmacy_manager_team.PharmacyManager.moduels.Medicines;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class MedicinesEntriesActivity extends AppCompatActivity {
    RecyclerView RVmedicines;
    List<ClientMedicines> moduelList;

    ProgressBar progressBar;
    MyMedicinesAdapter myMedicinesAdapter;
    ImageView back, plus;
    Gson gson;
    String medicines;

    SharedPreferencesUtilities preferencesUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines_entries);

        preferencesUtilities = new SharedPreferencesUtilities(this);

        RVmedicines = findViewById(R.id.rvMedicines);

        moduelList = new ArrayList<>();
        myMedicinesAdapter = new MyMedicinesAdapter(moduelList, this);

        back = findViewById(R.id.back);
        plus = findViewById(R.id.ivplus);
        progressBar = findViewById(R.id.progressBar);
        plus.setOnClickListener(v -> {
            startActivity(new Intent(this, AddMedicineActivity.class));
            finish();
        });

        getData();

        back.setOnClickListener(v -> finish());

    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        String homeUrl = "https://pharmacymanagerr.000webhostapp.com/c_pharmaceuticals_1.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, homeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Log.e("Home", "onResponse: Response >> " + response);
                moduelList.clear();
                try {
                    JSONArray info = new JSONArray(response);

                    for (int j = 0; j < info.length(); j++) {
                        JSONObject finall = info.getJSONObject(j);

                        String id = finall.getString("ID");
                        String name = finall.getString("MedicineName");
                        String desc = finall.getString("DescriptionMedicine");
                        String date = finall.getString("ExpireDate");
                        String time = finall.getString("Time");


                        ClientMedicines medicines = new ClientMedicines();
                        medicines.setId(id);
                        medicines.setName(name);
                        medicines.setDate(date);
                        medicines.setDescription(desc);
                        medicines.setTime(time);

                        moduelList.add(medicines);
                        myMedicinesAdapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setupRecycleview(moduelList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("X-Requested-With", "XMLHttpRequest");
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void setupRecycleview(List<ClientMedicines> list) {
        myMedicinesAdapter = new MyMedicinesAdapter(list, this);
        RVmedicines.setHasFixedSize(true);
        myMedicinesAdapter = new MyMedicinesAdapter(moduelList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RVmedicines.setLayoutManager(layoutManager);
        RVmedicines.setAdapter(myMedicinesAdapter);

    }

}