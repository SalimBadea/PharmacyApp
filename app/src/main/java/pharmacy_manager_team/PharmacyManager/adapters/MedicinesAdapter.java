package pharmacy_manager_team.PharmacyManager.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.Ui.MedicineDetailsActivity;
import pharmacy_manager_team.PharmacyManager.moduels.Medicines;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.ViewHolder> {
    List<Medicines> medicinesList;
    Context context;

    public MedicinesAdapter(List<Medicines> list, Context context) {
        this.medicinesList = list;
        this.context = context;
    }

    public void setMedicinesList(List<Medicines> list) {
        if (list != null)
            medicinesList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.imageView.setImageResource(R.drawable.logo);
//        if (medicinesList.get(position).getImage() != null && !medicinesList.get(position).getImage().isEmpty())
//            Glide.with(context)
//                    .load("http://" + medicinesList.get(position).getImage())
//                    .error(R.drawable.logo)
//                    .into(holder.imageView);

        Log.e("TAG", "onBindViewHolder: image >> " + medicinesList.get(position).getImage());
        holder.name.setText(medicinesList.get(position).getName());
        holder.count.setText(medicinesList.get(position).getQuantity() + "");
        holder.price.setText(medicinesList.get(position).getPrice());

        holder.item.setOnClickListener(v -> {
            Intent i = new Intent(context, MedicineDetailsActivity.class);
            i.putExtra("id", medicinesList.get(position).getId());
            i.putExtra("name", medicinesList.get(position).getName());
            i.putExtra("image", "http://"+medicinesList.get(position).getImage());
            i.putExtra("qty", medicinesList.get(position).getQuantity());
            i.putExtra("price", medicinesList.get(position).getPrice());
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return medicinesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout item;
        ImageView imageView;
        TextView name, count, price;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.cart_cv);
            imageView = itemView.findViewById(R.id.imageView4);
            name = itemView.findViewById(R.id.textView14);
            count = itemView.findViewById(R.id.textView17);
            price = itemView.findViewById(R.id.price);

        }
    }

    public void setFilter(List<Medicines> newlist) {
        medicinesList = new ArrayList<>();
        medicinesList.addAll(newlist);
        notifyDataSetChanged();
    }

}
