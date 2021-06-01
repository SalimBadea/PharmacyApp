package pharmacy_manager_team.pharmacymanager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pharmacy_manager_team.pharmacymanager.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    Button rigesterBtn;
    EditText fName, lName, userphone, fPassword, lPassword, userEmail;
    SharedPreferences preferences;
    String r_token;
    String phone ="";
    String firstName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userphone = findViewById(R.id.phone);
        fName = findViewById(R.id.first);
        lName = findViewById(R.id.sec);
        fPassword = findViewById(R.id.password1);
        lPassword = findViewById(R.id.confirm);
        userEmail = findViewById(R.id.email);
        rigesterBtn = findViewById(R.id.reg_btn);
        rigesterBtn.setOnClickListener(this);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_btn:
                phone = userphone.getText().toString();
                firstName = fName.getText().toString();
                String secondname = lName.getText().toString();
                String firstpass = fPassword.getText().toString();
                String secondpass = lPassword.getText().toString();
                String email = userEmail.getText().toString();
                String device_token = "1";
                String device_type = "android";
                if (TextUtils.isEmpty(firstName)) {
                    fName.setError("Please Enter Your First Name");
                    fName.requestFocus();
                } else if (TextUtils.isEmpty(secondname)) {
                    lName.setError("please Enter Your Last Name");
                    lName.requestFocus();
                } else if (TextUtils.isEmpty(phone)) {
                    userphone.setError("please Enter Your Phone Number");
                    userphone.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    userEmail.setError("please Enter Your Phone Number");
                    userEmail.requestFocus();
                } else if (TextUtils.isEmpty(firstpass)) {
                    fPassword.setError("please Enter Your password");
                    fPassword.requestFocus();
                } else if (TextUtils.isEmpty(secondpass)) {
                    lPassword.setError("please Confirm Your password");
                    lPassword.requestFocus();
                } else {
                    preferences.edit().putString("user_name",firstName).apply();
                    preferences.edit().putString("user_phone",phone).apply();
//                    createNewAcount(firstName, secondname, firstpass, secondpass, phone, email, device_token, device_type);
                }
                break;
        }
    }

}