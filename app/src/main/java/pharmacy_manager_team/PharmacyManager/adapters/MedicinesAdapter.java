package pharmacy_manager_team.PharmacyManager.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.Ui.MedicineDetailsActivity;
import pharmacy_manager_team.PharmacyManager.moduels.Medicines;
import pharmacy_manager_team.PharmacyManager.util.SharedPreferencesUtilities;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.ViewHolder> {
    List<Medicines> medicinesList;
    Context context;
    LayoutInflater inflater;
    private Typeface custom_font;
    SharedPreferencesUtilities preferencesUtilities;

    public MedicinesAdapter(List<Medicines> list, Context context) {
        this.medicinesList = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        preferencesUtilities = new SharedPreferencesUtilities(context);
    }

    public void setMedicinesList(List<Medicines> list) {
        if (list != null)
            medicinesList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.home_item, parent, false);
        try {
            custom_font = Typeface.createFromAsset(parent.getContext().getAssets(), "Cairo-Regular.ttf");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Picasso.with(context).load(medicinesList.get(position).getImage()).error(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.name.setText(medicinesList.get(position).getName());
        holder.count.setText(medicinesList.get(position).getQuantity()+"");

        holder.item.setOnClickListener(v -> {
            Intent i = new Intent(context, MedicineDetailsActivity.class);
            i.putExtra("id", medicinesList.get(position).getId());
            i.putExtra("name", medicinesList.get(position).getName());
            i.putExtra("image", medicinesList.get(position).getImage());
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

        CardView item;
        ImageView imageView;
        TextView name, count, price;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.cart_cv);
            imageView = itemView.findViewById(R.id.imageView4);
            name = itemView.findViewById(R.id.textView14);
            count = itemView.findViewById(R.id.textView17);
            price = itemView.findViewById(R.id.price);

            name.setTypeface(custom_font);
            count.setTypeface(custom_font);
        }
    }


}