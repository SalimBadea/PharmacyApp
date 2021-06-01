package pharmacy_manager_team.pharmacymanager.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import pharmacy_manager_team.pharmacymanager.R;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView nBottom;
    int mPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nBottom = findViewById(R.id.bottom_navigation);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(nBottom, navController);

//        nBottom.setOnNavigationItemSelectedListener(this);
    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.Home_page:
//
//                mPosition = 0;
//                openFragment(new HomeFragment());
//
//            case R.id.Cart_page:
//                mPosition = 1;
//                openFragment(new CartFragment());
//                break;
//            case R.id.Profile_page:
//                mPosition = 2;
//                openFragment(new ProfileFragment());
//        }
//        return true;
//    }
//
//    private void openFragment(Fragment fragment) {
//
//        getSupportFragmentManager().beginTransaction().replace(R.id._fragcontainer,
//                fragment).commit();
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPosition == 0) {
            finish();
        } else {
            mPosition = 0;
//            openFragment(new HomeFragment());
        }
    }
}