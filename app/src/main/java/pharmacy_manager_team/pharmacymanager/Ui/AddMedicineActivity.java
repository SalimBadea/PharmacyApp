package pharmacy_manager_team.pharmacymanager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import pharmacy_manager_team.pharmacymanager.R;
import pharmacy_manager_team.pharmacymanager.moduels.MedicineModuel;
import pharmacy_manager_team.pharmacymanager.util.SharedPreferencesUtilities;

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
    boolean isChronic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        sharedPreferences = new SharedPreferencesUtilities(this);
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
            if (month < 10) {
                String mMonth = "0" + (month + 1);
                date = year + "-" + (mMonth) + "-" + dayOfMonth;
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
            if (isChronic)
                mHours = hours.getText().toString();
            else mHours = "";

            medicine = new MedicineModuel.Medicine(mName, mDate, isChronic, mDesc, mHours);
            moduelList.add(medicine);
            moduelList.addAll(moduelList);

            sharedPreferences.setMEDICINES(moduelList);


            Log.e("List", "addMedicine: List >> " + moduelList.size());
            finish();
        });
    }

    private String setDataFromSharedPreferences() {

        String dataList = gson.toJson(moduelList);
        return dataList;
    }
}