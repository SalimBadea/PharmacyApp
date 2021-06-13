package pharmacy_manager_team.PharmacyManager.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.moduels.ClientMedicines;
import pharmacy_manager_team.PharmacyManager.moduels.MedicineModuel;

public class MyMedicinesAdapter extends RecyclerView.Adapter<MyMedicinesAdapter.VH> {

    List<ClientMedicines> medicineModuels;
    Context context;


    public MyMedicinesAdapter(List<ClientMedicines> medicineModuels, Context context) {
        this.medicineModuels = medicineModuels;
        this.context = context;
    }

    public List<ClientMedicines> getMedicineModuels() {
        return medicineModuels;
    }

    public void setMedicineModuels(List<ClientMedicines> medicineModuel) {
        if (medicineModuel != null)
            medicineModuels.addAll(medicineModuel);
        notifyDataSetChanged();

    }

    public void addItem(ClientMedicines moduel) {
        medicineModuels.add(moduel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyMedicinesAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMedicinesAdapter.VH holder, int position) {
        holder.name.setText(medicineModuels.get(position).getName());
        holder.date.setText(medicineModuels.get(position).getDate());
        holder.desc.setText(medicineModuels.get(position).getDescription());
        holder.time.setText(medicineModuels.get(position).getmTime());
    }

    @Override
    public int getItemCount() {
        return medicineModuels.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView name, date, desc, time;

        public VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.med_name);
            date = itemView.findViewById(R.id.med_date);
            desc = itemView.findViewById(R.id.med_desc);
            time = itemView.findViewById(R.id.med_time);
        }
    }
}
