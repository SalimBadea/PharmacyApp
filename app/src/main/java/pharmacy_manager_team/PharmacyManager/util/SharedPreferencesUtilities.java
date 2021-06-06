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

    private static String TOKEN = "token";
    private static String ISLOGGEDIN = "loggedIn";
    private static String EMAIL = "email";
    private static String USER_NAME = "username";
    private static String PHONE = "phone";
    private static String ADDRESS = "address";
    private static String AGE = "age";
    private static String USER_ID = "userid";
    private static String PROFILE_IMAGE = "profileimage";
    private static String GENDER = "gender";
    private static String MEDICINES = "nurses_services";

    /// now create a setter and getter to edit the value and to get it

    public void setPHONE(String phone) {
        sharedPreferences.edit().putString(PHONE, phone).apply();
    }

    public String getPHONE() {
        return sharedPreferences.getString(PHONE, "");
    }

    public void setADDRESS(String address) {
        sharedPreferences.edit().putString(ADDRESS, address).apply();
    }

    public String getADDRESS() {
        return sharedPreferences.getString(ADDRESS, "");
    }

    public void setUserName(String userName) {
        sharedPreferences.edit().putString(USER_NAME, userName).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }

    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    public void setUserId(String id) {
        sharedPreferences.edit().putString(USER_ID, id).apply();
    }

    public void setAGE(String age) {
        sharedPreferences.edit().putString(AGE, age).apply();
    }

    public String getAGE() {
        return sharedPreferences.getString(AGE, "");
    }

    public void setEmail(String email) {
        sharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setToken(String token) {
        //  key  , value
        sharedPreferences.edit().putString(TOKEN, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, "");
    }

    public void setLoggedIn(boolean isLoggedIn) {
        //  key  , value
        sharedPreferences.edit().putBoolean(ISLOGGEDIN, isLoggedIn).apply();
    }

    public boolean getLoggedIn() {
        return sharedPreferences.getBoolean(ISLOGGEDIN, false);
    }

    public void setProfileImage(String profileImage){
        sharedPreferences.edit().putString(PROFILE_IMAGE , profileImage).apply();
    }

    public String getProfileImage(){
        return sharedPreferences.getString(PROFILE_IMAGE , "");
    }

    public void setGENDER(String gender){
        sharedPreferences.edit().putString(GENDER , gender).apply();
    }

    public String getGENDER(){
        return sharedPreferences.getString(GENDER , "");
    }

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
