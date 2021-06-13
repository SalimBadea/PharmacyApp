package pharmacy_manager_team.PharmacyManager.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pharmacy_manager_team.PharmacyManager.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView nBottom;
    int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nBottom = findViewById(R.id.bottom_navigation);

        nBottom.setOnNavigationItemSelectedListener(this);

        mPosition = getIntent().getIntExtra("position", 0);

        switch (mPosition) {
            case 0:
                openFragment(new HomeFragment());
                openFragmentByPosition();
                break;
            case 1:
                openFragment(new CartFragment());
                openFragmentByPosition();
                break;
            case 2:
                openFragment(new ProfileFragment());
                openFragmentByPosition();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeFragment:
                mPosition = 0;
                openFragment(new HomeFragment());
                break;

            case R.id.cartFragment:
                mPosition = 1;
                openFragment(new CartFragment());
                break;
            case R.id.profileFragment:
                mPosition = 2;
                openFragment(new ProfileFragment());
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPosition == 0) {
            finish();
        } else {
            mPosition = 0;
            openFragmentByPosition();
        }
    }

    private void openFragmentByPosition() {
        switch (mPosition) {
            case 0:
                nBottom.setSelectedItemId(R.id.homeFragment);
                break;
            case 1:
                nBottom.setSelectedItemId(R.id.cartFragment);
                break;
            case 2:
                nBottom.setSelectedItemId(R.id.profileFragment);
                break;
        }
    }
}