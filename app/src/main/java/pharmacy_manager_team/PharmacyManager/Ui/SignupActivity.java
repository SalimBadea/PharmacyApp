package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import pharmacy_manager_team.PharmacyManager.R;

public class SignupActivity extends AppCompatActivity {
    Button rigesterBtn;
    EditText fName, email, userphone, age, fPassword, address;
    CheckBox male, female;
    ImageView back;
    SharedPreferences preferences;
    String r_token;
    String Phone , Name, Address, Age, Email, Password, Male, Female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userphone = findViewById(R.id.phone);
        fName = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        age = findViewById(R.id.age);
        fPassword = findViewById(R.id.password1);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        rigesterBtn = findViewById(R.id.reg_btn);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

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
                Female = "female";
                male.setChecked(false);
            }
        });
        rigesterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = fName.getText().toString();
                Email = email.getText().toString();
                Phone = userphone.getText().toString();
                Address = address.getText().toString();
                Age = age.getText().toString();
                Password = fPassword.getText().toString();
            }
        });
    }
//        public void createNewAcount(String firstname, String lastname, String password, String password_confirm,
//        String phone_number, String email, String device_token, String device_type){

//        Retrofit_Builder builder = new Retrofit_Builder();
//        JsonPlaceHolderApi.Register register = builder.register();
//        Call<R_Example> call = register.register(firstname, lastname, password, password_confirmation,
//                phone_number, email, device_token, device_type);
//        call.enqueue(new Callback<R_Example>() {
//            @Override
//            public void onResponse(Call<R_Example> call, Response<R_Example> response) {
//                Log.e("TAG", "onResponse: Response >> " + response);
//                if (response.code() == 200) {
//                    r_token = response.body().getData().getToken();
//                    Toast.makeText(getApplicationContext(), response.body().getMassage(), Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//            @Override
//            public void onFailure(Call<R_Example> call, Throwable t) {
//                Log.e("SignUpActivity", t.getLocalizedMessage());
//                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }/


}