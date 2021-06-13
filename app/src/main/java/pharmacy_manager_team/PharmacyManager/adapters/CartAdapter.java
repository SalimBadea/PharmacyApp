package pharmacy_manager_team.PharmacyManager.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.moduels.Medicines;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    List<Medicines> cartList;
    Context context;
    boolean clicked = false;
    double price = 0.0;
    double count = 0;
    double total = 0.0;

    public CartAdapter(List<Medicines> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }

    public void setCartList(List<Medicines> cList) {
        if (cList != null)
            cartList.addAll(cList);
        notifyDataSetChanged();
    }

    public List<Medicines> getCartList() {
        return cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent,
                false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Medicines cartDatum = cartList.get(position);

        holder.name.setText(cartList.get(position).getName());
        holder.Tprice.setText(cartList.get(position).getPrice());
        Glide.with(context).load(cartList.get(position).getImage()).error(R.drawable.logo).into(holder.imageView);
        holder.number.setText(cartList.get(position).getQuantity());


        price = Double.parseDouble(cartList.get(position).getPrice());

        //increment
        holder.plus.setOnClickListener(v -> {
            clicked = true;
            double qty = Double.parseDouble(cartList.get(position).getQuantity());
            if (clicked) {
                count = qty + 1;
                total = count * price;
                cartList.get(position).setQuantity(String.valueOf(count));

            }
            holder.number.setText(cartList.get(position).getQuantity());

            holder.Tprice.setText(total + "");
        });

        //decrement
        holder.minus.setOnClickListener(v -> {
            clicked = true;
            double qty1 = Double.parseDouble(cartList.get(position).getQuantity());
            if (clicked) {
                if (qty1 == 1) {
                    return;
                } else {
                    count = qty1 - 1;
                    total = count * price;
                    cartList.get(position).setQuantity(String.valueOf(count));
                }

                holder.number.setText(cartList.get(position).getQuantity());
                holder.Tprice.setText(total + "");

            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, plus, minus, remove;
        TextView name, number, Tprice;
        CardView cart_cv;

        public MyViewHolder(@NonNull View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView4);
            name = v.findViewById(R.id.textView14);
            number = v.findViewById(R.id.textView17);
            Tprice = v.findViewById(R.id.price);
            cart_cv = v.findViewById(R.id.cart_cv);
            minus = v.findViewById(R.id.decrement);
            plus = v.findViewById(R.id.increment);
        }
    }


}
