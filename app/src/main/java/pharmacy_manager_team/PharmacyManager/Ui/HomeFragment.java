package pharmacy_manager_team.PharmacyManager.Ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.adapters.MedicinesAdapter;
import pharmacy_manager_team.PharmacyManager.moduels.Medicines;


public class HomeFragment extends Fragment {
    RelativeLayout add;
    RecyclerView recyclerView;
    List<Medicines> list;
    MedicinesAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.rvHome);
        add=view.findViewById(R.id.Rlayout);
        add.setOnClickListener(v -> { startActivity(new Intent(getContext(),MedicinesEntriesActivity.class)); });

//        list = new ArrayList<>();
//        String homeUrl = "https://pharmacymanagerr.000webhostapp.com/pharmaceuticals.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, homeUrl , new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject parent = new JSONObject(response);
//
//                    JSONObject mobject = parent.getJSONObject("data");
//
//                    for (int i=0;i<mobject.length();i++){
//                        list.clear();
//                        JSONArray info = mobject.getJSONArray("");
//                        for (int j=0;j<info.length();j++) {
//                            JSONObject finall = info.getJSONObject(j);
//
//                            int id = finall.getInt("id");
//                            String name = finall.getString("name");
//                            int qty = finall.getInt("qty");
//                            int pr = finall.getInt("price");
//                            String ph = finall.getString("images");
//
//                            Medicines medicines = new Medicines();
//                            medicines.setId(id);
//                            medicines.setName(name);
//                            medicines.setQuantity(qty);
//                            medicines.setPrice(pr);
//                            medicines.setImage(ph);
//
//                            list.add(medicines);
//                            mAdapter.notifyDataSetChanged();
//
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                setupRecycleview(list);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//
//
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String , String> headers = new HashMap<String, String>();
//                headers.put("X-Requested-With" , "XMLHttpRequest");
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(stringRequest);

        return view;
    }

    private void setupRecycleview(List<Medicines> data) {
        mAdapter = new MedicinesAdapter( data , getActivity());
        recyclerView.setHasFixedSize(true);
        mAdapter = new MedicinesAdapter(list,  getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

    }
}