package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.moduels.MedicineModuel;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class AddMedicineActivity extends AppCompatActivity {

    MedicineModuel.Medicine medicine;
    MedicineModuel medicineModuel;
    List<MedicineModuel.Medicine> moduelList;
    List<MedicineModuel> moduelList1;
    List<MedicineModuel> moduelList2;
    EditText name, hours, desc;
    TextView ExDate;
    CheckBox yes, no;
    Button save;
    RelativeLayout layout;
    Gson gson;
    CalendarView dpDialog;

    SharedPreferencesUtilities sharedPreferences;
    String date;
    String mName;
    String mDate;
    String mDesc;
    String mHours;
    String mChronic;
    int time;
    int userID;
    boolean isChronic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        sharedPreferences = new SharedPreferencesUtilities(this);
        userID = Integer.parseInt(sharedPreferences.getUserId());
        Toast.makeText(this, userID+"", Toast.LENGTH_SHORT).show();
        name = findViewById(R.id.et_MedicineName);
        ExDate = findViewById(R.id.ed_date);
        hours = findViewById(R.id.et_time);
        desc = findViewById(R.id.et_description);
        layout = findViewById(R.id.layoutTexts);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        save = findViewById(R.id.btnSave);
        dpDialog = findViewById(R.id.dpDialog);

        dpDialog.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            date = dayOfMonth + "-" + (month + 1) + "-" + year;
            if (month < 10 ) {
                String mMonth = "0" + (month + 1);
                if (dayOfMonth < 10) {
                    String day = "0" + dayOfMonth;
                    date = year + "-" + (mMonth) + "-" + day;
                }else {
                    date = year + "-" + (mMonth) + "-" + dayOfMonth;
                }
            } else {
                date = year + "-" + (month + 1) + "-" + dayOfMonth;
            }

            ExDate.setText(date);
        });

        moduelList = new ArrayList<>();
        moduelList1 = new ArrayList<>();
        moduelList2 = new ArrayList<>();
        medicineModuel = new MedicineModuel();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setChecked(true);
                layout.setVisibility(View.VISIBLE);
                no.setChecked(false);
                isChronic = true;
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setChecked(false);
                layout.setVisibility(View.GONE);
                isChronic = false;
                no.setChecked(true);
            }
        });

        save.setOnClickListener(v -> {
            gson = new Gson();
            Toast.makeText(getApplicationContext(), "Data Saved Succesfully", Toast.LENGTH_LONG).show();
            mName = name.getText().toString();
            mDate = ExDate.getText().toString();
            mDesc = desc.getText().toString();
            if (isChronic) {
                mChronic = "yes";
                mHours = hours.getText().toString();
                time = Integer.parseInt(mHours);
            }else {
                mChronic = "no";
                time = 0;
                mHours = "";
            }

            AddNewMedicines(mName,mDesc,mDate,mChronic,time,userID);
//            medicine = new MedicineModuel.Medicine(mName, mDate, isChronic, mDesc, mHours);
//            moduelList.add(medicine);
//            moduelList.addAll(moduelList);
//
//            sharedPreferences.setMEDICINES(moduelList);
        });
    }

    public void AddNewMedicines(String name,String desc ,String exData , String chronic, int time, int clint_id){
        String url="https://pharmacymanagerr.000webhostapp.com/c_pharmaceuticals.php?add=1&MedicineName="+name+"&DescriptionMedicine="+desc+"&ExpireDate="+exData+"&Chronic="+chronic+"&Time="+time+"&Client_ID="+clint_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Add Medicine", "onResponse: Response >> " + response);
                        if (response.equals("error")){
                            Toast.makeText(AddMedicineActivity.this,response.trim(),Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(AddMedicineActivity.this,response.trim(),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddMedicineActivity.this, MedicinesEntriesActivity.class));
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddMedicineActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("add","medicine");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(AddMedicineActivity.this);
        requestQueue.add(stringRequest);
    }

    private String setDataFromSharedPreferences() {

        String dataList = gson.toJson(moduelList);
        return dataList;
    }
}