package pharmacy_manager_team.pharmacymanager.moduels;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import pharmacy_manager_team.pharmacymanager.R;

public class Dialog extends AppCompatDialogFragment {
    MedicineModuel.Medicine medicineModuel;
    EditText name, hours, desc;
    TextView ExDate;
    CheckBox yes, no;
    RelativeLayout layout;
    Dialog_listener dialog_listener;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            dialog_listener = (Dialog_listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "imp missed");
        }
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);
        name = view.findViewById(R.id.et_MedicineName);
        ExDate = view.findViewById(R.id.ed_date);
        hours = view.findViewById(R.id.et_time);
        desc = view.findViewById(R.id.et_description);
        layout = view.findViewById(R.id.layoutTexts);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);

        medicineModuel = new MedicineModuel.Medicine();

        if (yes.isChecked()) {
            yes.setChecked(true);
            layout.setVisibility(View.VISIBLE);
            medicineModuel.setRTime(hours.getText().toString());
            no.setChecked(false);
            medicineModuel.setChronic(true);
        }

        if (no.isChecked()) {
            yes.setChecked(false);
            layout.setVisibility(View.GONE);
            medicineModuel.setChronic(false);
            medicineModuel.setRTime("");
            no.setChecked(true);
        }


        builder.setView(view).setTitle("add medicine").setNegativeButton("cancel", (dialog, which) -> {

        }).setPositiveButton("ok", (dialog, which) -> {

            medicineModuel.setName(name.getText().toString());
            medicineModuel.setEx_date(ExDate.getText().toString());
            medicineModuel.setDescreption(desc.getText().toString());
            dialog_listener.add_medicine(medicineModuel);

//            dialog_listener.add_medicine(medicineModuel);
        });

        return builder.create();
    }

    public interface Dialog_listener {
        void add_medicine(MedicineModuel.Medicine moduel);
    }
}
