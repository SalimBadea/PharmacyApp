package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView signup;
    Button login;
    String token;
    SharedPreferencesUtilities preferencesUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        preferencesUtilities = new SharedPreferencesUtilities(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        login.setOnClickListener(v -> {
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            if (TextUtils.isEmpty(Email)) {
                email.setError("please Enter Your Phone Number..");
                email.requestFocus();
            } else if (TextUtils.isEmpty(Password)) {
                password.setError("please Enter Your password..");
                password.requestFocus();
            } else {
                loginAcc(Email, Password);
            }
        });
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        });

    }

    String r = "";

    public void loginAcc(String email, String pass) {

        String url = "https://pharmacymanagerr.000webhostapp.com/login.php?get=1&email=" + email + "&pass=" + pass + "";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                r = response.trim();
                if (r.equals("18")) {
                    preferencesUtilities.setLoggedIn(true);
                    Toast.makeText(LoginActivity.this, "Welcome to Pharmacy manager", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, r, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("add", "mohamedlhaled");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }

}