package pharmacy_manager_team.PharmacyManager.Ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.adapters.CartAdapter;
import pharmacy_manager_team.PharmacyManager.adapters.MedicinesAdapter;
import pharmacy_manager_team.PharmacyManager.moduels.Medicines;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class CartFragment extends Fragment {

    RecyclerView cart_rv;
    Button next;
    SharedPreferencesUtilities preferencesUtilities;
    String userId, medicineId, qty;
    CartAdapter cartAdapter;
    List<Medicines> medicines;
    ProgressBar progressBar;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        preferencesUtilities = new SharedPreferencesUtilities(getActivity());
        userId = preferencesUtilities.getUserId();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        cart_rv = view.findViewById(R.id.cart_rv);
        next = view.findViewById(R.id.btn_next_step);
        progressBar = view.findViewById(R.id.progressBar);

        medicines = new ArrayList<>();
        cartAdapter = new CartAdapter(medicines, getActivity());

        getCart(userId);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < medicines.size(); i++) {
                    medicineId = medicines.get(i).getId();
                    qty = medicines.get(i).getQuantity();
                    cartConfirm(userId, medicineId, qty);
                }
            }
        });
        return view;
    }

    public void getCart(String userid) {
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://pharmacymanagerr.000webhostapp.com/user_request.php?user_id=" + userid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);
                medicines.clear();
                try {
                    JSONArray info = new JSONArray(response);

                    for (int j = 0; j < info.length() - 1; j++) {
                        JSONObject finall = info.getJSONObject(j);

                        String id = finall.getString("ID");
                        String name = finall.getString("Name");
                        String qty = finall.getString("Quantity");
                        String pr = finall.getString("Price");
                        String ph = finall.getString("Picture");

                        Medicines mediciness = new Medicines();
                        mediciness.setId(id);
                        mediciness.setName(name);
                        mediciness.setQuantity(qty);
                        mediciness.setPrice(pr);
                        mediciness.setImage(ph);

                        medicines.add(mediciness);
                        cartAdapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setupRecycleview(medicines);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void setupRecycleview(List<Medicines> data) {
        cartAdapter = new CartAdapter(data, getActivity());
        cart_rv.setHasFixedSize(true);
        cartAdapter = new CartAdapter(medicines, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        cart_rv.setLayoutManager(layoutManager);
        cart_rv.setAdapter(cartAdapter);

    }

    String r = "";
    private void cartConfirm(String userId, String medicineId, String qty) {
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://pharmacymanagerr.000webhostapp.com/confirm_request.php?user_id=" + userId + "&pharmaceutical_id=" + medicineId + "&Quantity=" + qty;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                r = response.trim();
                Log.e("cart Response", "onResponse: Response >> " + r);
                if (response.equals("Done")) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Confirm Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MainActivity.class).putExtra("position", 0));

                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), r, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}