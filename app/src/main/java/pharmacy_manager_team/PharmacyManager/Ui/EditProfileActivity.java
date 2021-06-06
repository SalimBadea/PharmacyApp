package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class EditProfileActivity extends AppCompatActivity {
    Button saveBtn;
    EditText fName, userphone, age, fPassword, address;
    TextView email;
    CheckBox male, female;
    ImageView back;
    String r_token;
    String Phone , Name, Address, Age, Email, Password, Male;

    SharedPreferencesUtilities preferencesUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferencesUtilities = new SharedPreferencesUtilities(this);

        userphone = findViewById(R.id.phone);
        fName = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        age = findViewById(R.id.age);
        fPassword = findViewById(R.id.password1);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        saveBtn = findViewById(R.id.edit_btn);
        back = findViewById(R.id.back);

        String name = preferencesUtilities.getUserName();
        fName.setText(name);
        String phone = preferencesUtilities.getPHONE();
        userphone.setText(phone);
        String uEmail = preferencesUtilities.getEmail();
        email.setText(uEmail);
        String uAddress = preferencesUtilities.getADDRESS();
        address.setText(uAddress);
        String uAge = preferencesUtilities.getAGE();
        age.setText(uAge);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (preferencesUtilities.getGENDER().equals("male")) {
            male.setChecked(true);
            female.setChecked(false);
            Male = "male";
        }else {
            male.setChecked(false);
            female.setChecked(true);
            Male = "female";
        }

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setChecked(true);
                Male = "male";
                female.setChecked(false);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setChecked(true);
                Male = "female";
                male.setChecked(false);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = fName.getText().toString();
                Email = email.getText().toString();
                Phone = userphone.getText().toString();
                Address = address.getText().toString();
                Age = age.getText().toString();
//                Password = fPassword.getText().toString();

//                String msg = createNewAcount(Phone,Name,Email,Address,Age,Password);
                preferencesUtilities.setUserName(Name);
                preferencesUtilities.setEmail(Email);
                preferencesUtilities.setPHONE(Phone);
                preferencesUtilities.setADDRESS(Address);
                preferencesUtilities.setAGE(Age);
                preferencesUtilities.setGENDER(Male);
                Toast.makeText(EditProfileActivity.this, "Profile Edited Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProfileActivity.this, MainActivity.class).putExtra("position", 0));
                finish();
            }
        });
    }
}