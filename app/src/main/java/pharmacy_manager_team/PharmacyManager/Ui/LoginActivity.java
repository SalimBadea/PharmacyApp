package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class LoginActivity extends AppCompatActivity {
    EditText userphone, userpassword;
    TextView signup;
    Button login;
    String token;
    SharedPreferencesUtilities preferencesUtilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        preferencesUtilities = new SharedPreferencesUtilities(this);

        userphone = findViewById(R.id.phone);
        userpassword = findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        login.setOnClickListener(v -> {
            String phone = userphone.getText().toString();
            String password = userpassword.getText().toString();
            if (TextUtils.isEmpty(phone)) {
                userphone.setError("please Enter Your Phone Number..");
                userphone.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                userpassword.setError("please Enter Your password..");
                userpassword.requestFocus();
            }
//            Login(phone, password);
        });
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
    }
}