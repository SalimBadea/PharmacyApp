package pharmacy_manager_team.PharmacyManager.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Builder {
    public static Retrofit_Builder retrofit_builder;
    public static String BASE_URL = "https://pharmacymanagerr.000webhostapp.com/";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static synchronized Retrofit_Builder getInstance() {

        if (retrofit_builder == null) {
            return new Retrofit_Builder();
        }

        return retrofit_builder;
    }

//    public JsonPlaceHolderApi.GetCats getCats() {
//        return retrofit.create(JsonPlaceHolderApi.GetCats.class);
//    }
//
//    public JsonPlaceHolderApi.GetProducts getProducts() {
//        return retrofit.create(JsonPlaceHolderApi.GetProducts.class);
//    }
//
//    public JsonPlaceHolderApi.GetNotification getNotification() {
//        return retrofit.create(JsonPlaceHolderApi.GetNotification.class);
//    }
//
//    public JsonPlaceHolderApi.Like like() {
//        return retrofit.create(JsonPlaceHolderApi.Like.class);
//    }
//
//    public JsonPlaceHolderApi.Login login() {
//        return retrofit.create(JsonPlaceHolderApi.Login.class);
//    }
//
//    public JsonPlaceHolderApi.Register register() {
//        return retrofit.create(JsonPlaceHolderApi.Register.class);
//    }
//    public JsonPlaceHolderApi.Wishlist wishlist(){
//        return retrofit.create( JsonPlaceHolderApi.Wishlist.class);
//    }
//    public JsonPlaceHolderApi.Unlike unlike (){
//        return retrofit.create(JsonPlaceHolderApi.Unlike.class);
//    }
//    public JsonPlaceHolderApi.Product product(){
//        return  retrofit.create(JsonPlaceHolderApi.Product.class);
//    }
//    public JsonPlaceHolderApi.addToCart toCart(){
//        return retrofit.create(JsonPlaceHolderApi.addToCart.class);
//    }
//    public JsonPlaceHolderApi.cart cart(){
//        return retrofit.create(JsonPlaceHolderApi.cart.class);
//    }
//
//    public JsonPlaceHolderApi.remove_cartItem remove_cartItem(){
//        return retrofit.create(JsonPlaceHolderApi.remove_cartItem.class);
//    }
//    public JsonPlaceHolderApi.Logout logout(){
//        return retrofit.create(JsonPlaceHolderApi.Logout.class);
//    }
//    public JsonPlaceHolderApi.EditProfile editProfile(){
//        return retrofit.create(JsonPlaceHolderApi.EditProfile.class);
//    }
//    public JsonPlaceHolderApi.ChangePassword changePassword(){
//        return retrofit.create(JsonPlaceHolderApi.ChangePassword.class);
//    }
//    public JsonPlaceHolderApi.Page page(){
//        return retrofit.create(JsonPlaceHolderApi.Page.class);
//    }
//    public JsonPlaceHolderApi.Governments governments(){
//        return retrofit.create(JsonPlaceHolderApi.Governments.class);
//    }
//    public JsonPlaceHolderApi.Cities cities(){
//        return retrofit.create(JsonPlaceHolderApi.Cities.class);
//    }
}
