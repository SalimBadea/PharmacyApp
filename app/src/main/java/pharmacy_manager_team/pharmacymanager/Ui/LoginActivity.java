package pharmacy_manager_team.pharmacymanager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pharmacy_manager_team.pharmacymanager.R;

public class LoginActivity extends AppCompatActivity {
    EditText userphone, userpassword;
    TextView signup;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        userphone = findViewById(R.id.phone);
        userpassword = findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
//        preferences = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        login.setOnClickListener(v -> {
            String deviceToken = "1";
            String deviceType = "android";
            String phone = userphone.getText().toString();
            String password = userpassword.getText().toString();
            if (TextUtils.isEmpty(phone)) {
                userphone.setError("please Enter Your Phone Number..");
                userphone.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                userpassword.setError("please Enter Your password..");
                userpassword.requestFocus();
            }
//            getUser(phone, password, deviceToken, deviceType);
        });
        signup = findViewById(R.id.signup);
//        signup.setOnClickListener(v ->
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));
    }
}