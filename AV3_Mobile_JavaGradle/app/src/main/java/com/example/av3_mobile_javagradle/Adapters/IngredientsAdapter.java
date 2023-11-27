package com.example.av3_mobile_javagradle.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.av3_mobile_javagradle.Listeners.RecipeClickListener;
import com.example.av3_mobile_javagradle.Models.ExtendedIngredient;
import com.example.av3_mobile_javagradle.Models.Recipe;
import com.example.av3_mobile_javagradle.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<ViewHolderIngredientes> {
    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderIngredientes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderIngredientes(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderIngredientes holder, int position) {
        holder.textViewIngredientsQuantity.setText(list.get(position).original);
        holder.textViewIngredientsQuantity.setSelected(true);
        holder.textViewIngredientsName.setText(list.get(position).name);
        holder.textViewIngredientsName.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageViewIngredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class ViewHolderIngredientes extends RecyclerView.ViewHolder {
    CardView cardViewRandomListContainer;
    TextView textViewIngredientsQuantity, textViewIngredientsName;
    ImageView imageViewIngredients;
    public ViewHolderIngredientes(@NonNull View itemView) {
        super(itemView);

        cardViewRandomListContainer = itemView.findViewById(R.id.cardViewRandomListContainer);
        textViewIngredientsQuantity = itemView.findViewById(R.id.textViewIngredientsQuantity);
        textViewIngredientsName = itemView.findViewById(R.id.textViewIngredientsName);
        imageViewIngredients = itemView.findViewById(R.id.imageViewIngredients);
    }
}

