package pharmacy_manager_team.pharmacymanager.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pharmacy_manager_team.pharmacymanager.R;
import pharmacy_manager_team.pharmacymanager.adapters.MyMedicinesAdapter;
import pharmacy_manager_team.pharmacymanager.moduels.Dialog;
import pharmacy_manager_team.pharmacymanager.moduels.MedicineModuel;
import pharmacy_manager_team.pharmacymanager.util.SharedPreferencesUtilities;

public class MedicinesEntriesActivity extends AppCompatActivity {
    RecyclerView RVmedicines;
    List<MedicineModuel.Medicine> moduelList;
    List<MedicineModuel.Medicine> moduelList1;

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

        moduelList = new ArrayList<>();
        moduelList1 = new ArrayList<>();

        back = findViewById(R.id.back);
        plus = findViewById(R.id.ivplus);
        plus.setOnClickListener(v -> {
            startActivity(new Intent(this, AddMedicineActivity.class));
        });

        moduelList1.clear();
        moduelList = preferencesUtilities.getMEDICINES();
        for (int i = 0; i < moduelList.size(); i++) {
            MedicineModuel.Medicine medicine = new MedicineModuel.Medicine();
            medicine.setName(moduelList.get(i).getName());
            medicine.setRTime(moduelList.get(i).getRTime());
            medicine.setEx_date(moduelList.get(i).getEx_date());
            medicine.setDescreption(moduelList.get(i).getDescreption());
            medicine.setChronic(moduelList.get(i).isChronic());
            moduelList1.add(i,medicine);
        }
        Log.e("TAG", "onCreate: List >> " + moduelList1.size());

        myMedicinesAdapter = new MyMedicinesAdapter(moduelList1, this);

        RVmedicines = findViewById(R.id.rvMedicines);
        RVmedicines.setHasFixedSize(true);
        RVmedicines.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        RVmedicines.setAdapter(myMedicinesAdapter);

        back.setOnClickListener(v -> finish());


    }

    @Override
    protected void onResume() {
        moduelList = preferencesUtilities.getMEDICINES();
        super.onResume();
    }
}