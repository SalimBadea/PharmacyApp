package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SignupActivity extends AppCompatActivity {
    Button rigesterBtn;
    EditText fName, email, userphone, age, fPassword, address;
    CheckBox male, female;
    ImageView back;
    String r_token;
    String Phone , Name, Address, Age, Email, Password, Male;

    SharedPreferencesUtilities preferencesUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        preferencesUtilities = new SharedPreferencesUtilities(this);

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
                finish();
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
                Male = "female";
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

                String msg = createNewAcount(Phone,Name,Email,Address,Age,Password);

                preferencesUtilities.setUserName(Name);
                preferencesUtilities.setEmail(Email);
                preferencesUtilities.setPHONE(Phone);
                preferencesUtilities.setADDRESS(Address);
                preferencesUtilities.setAGE(Age);
                preferencesUtilities.setGENDER(Male);
                Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    String r = "";
    public String createNewAcount(String phone,String name ,String email , String address , String age,String pass ){
        String url="https://pharmacymanagerr.000webhostapp.com/signup.php?add=1&name="+name+"&email="+email+"&telephoneNumber="+phone+"&address="+address+"&age="+age+"&gender="+Male+"&pass="+pass;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignupActivity.this,response.trim(),Toast.LENGTH_LONG).show();
                        r = response.trim() ;
                        Log.e("SignUp", "onResponse: Response >> " + r);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("add","mohamedlhaled");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
        requestQueue.add(stringRequest);
        return  r ;
    }

}