package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class MedicineDetailsActivity extends AppCompatActivity {
    ImageView mImage, back;
    TextView mName, mPrice, mQty, tv0;
    Button add;
    String id, name, image, qty, price, userId;
    ProgressBar progressBar;
    SharedPreferencesUtilities preferencesUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        preferencesUtilities = new SharedPreferencesUtilities(this);
        userId = preferencesUtilities.getUserId();

        mImage = findViewById(R.id.mimage);
        back = findViewById(R.id.back);
        mName = findViewById(R.id.mname);
        mPrice = findViewById(R.id.mprice);
        mQty = findViewById(R.id.mqty);
        tv0 = findViewById(R.id.tv0);
        add = findViewById(R.id.madd);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
        qty = intent.getStringExtra("qty");
        price = intent.getStringExtra("price");

        Glide.with(this).load(image).error(R.drawable.logo).into(mImage);
        mName.setText(name);
        tv0.setText(name);
        mPrice.setText(price);
        mQty.setText(qty);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(userId, id, "1");
            }
        });
    }

    String r = "";
    public void addToCart(String userid, String medicineid, String quantity) {
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://pharmacymanagerr.000webhostapp.com/user_request.php?user_id=" + userid + "&pharmaceutical_id=" + medicineid + "&Quantity=" + quantity;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                r = response.trim();
                if (r.equals("Done")) {
                    Toast.makeText(MedicineDetailsActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MedicineDetailsActivity.this, r, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MedicineDetailsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("add", "mohamedlhaled");
//
//                return params;
//            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MedicineDetailsActivity.this);
        requestQueue.add(stringRequest);
    }

}