package pharmacy_manager_team.PharmacyManager.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import pharmacy_manager_team.PharmacyManager.moduels.MedicineModuel;

public class SharedPreferencesUtilities {

    SharedPreferences sharedPreferences;

    public SharedPreferencesUtilities(Context context) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }    ///// create the key name for what you want to save in shared

    private static String MEDICINES = "nurses_services";

    /// now create a setter and getter to edit the value and to get it


    public void setMEDICINES(List<MedicineModuel.Medicine> medicines) {
        Gson gson = new Gson();
        String json = gson.toJson(medicines);

        sharedPreferences.edit().putString(MEDICINES, json).commit();

    }

    public List<MedicineModuel.Medicine> getMEDICINES() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(MEDICINES, "");
        Type type = new TypeToken<List<MedicineModuel.Medicine>>() {}.getType();

        return gson.fromJson(json, type);
    }


}
