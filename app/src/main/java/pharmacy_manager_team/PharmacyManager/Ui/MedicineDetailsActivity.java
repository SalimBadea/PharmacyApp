package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pharmacy_manager_team.PharmacyManager.R;

public class MedicineDetailsActivity extends AppCompatActivity {
    ImageView mImage,back;
    TextView mName, mPrice, mQty, tv0;
    Button add;
    String id, name, image, qty, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        mImage = findViewById(R.id.mimage);
        back = findViewById(R.id.back);
        mName = findViewById(R.id.mname);
        mPrice = findViewById(R.id.mprice);
        mQty = findViewById(R.id.mqty);
        tv0 = findViewById(R.id.tv0);
        add = findViewById(R.id.madd);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
        qty = intent.getStringExtra("qty");
        price = intent.getStringExtra("price");

        Picasso.with(this).load(image).error(R.drawable.ic_launcher_background).into(mImage);
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

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}