package pharmacy_manager_team.PharmacyManager.Ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    Button edit,logOut;
    TextView name, email, phone, address, gender, age;
    SharedPreferencesUtilities preferencesUtilities;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preferencesUtilities = new SharedPreferencesUtilities(getActivity());
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        edit = view.findViewById(R.id.edit);
        logOut = view.findViewById(R.id.logOut);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        address = view.findViewById(R.id.address);
        gender = view.findViewById(R.id.gender);
        age = view.findViewById(R.id.age);

        String Name = preferencesUtilities.getUserName();
        name.setText(Name);
        String Phone = preferencesUtilities.getPHONE();
        phone.setText(Phone);
        String Email = preferencesUtilities.getEmail();
        email.setText(Email);
        String Address = preferencesUtilities.getADDRESS();
        address.setText(Address);
        String Age = preferencesUtilities.getAGE();
        age.setText(Age);
        String Gender = preferencesUtilities.getGENDER();
        gender.setText(Gender);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesUtilities.setLoggedIn(false);
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }
}