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
import com.example.av3_mobile_javagradle.Models.Recipe;
import com.example.av3_mobile_javagradle.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecicleViewReceitasAleatoriasAdapter extends RecyclerView.Adapter<ViewHolderReceitasAleatorias> {
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;
    public RecicleViewReceitasAleatoriasAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderReceitasAleatorias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderReceitasAleatorias(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReceitasAleatorias holder, int position) {
        holder.textViewTitle.setText(list.get(position).title);
        holder.textViewTitle.setSelected(true);
        holder.textViewLikes.setText(list.get(position).aggregateLikes+" Likes");
        holder.textViewServings.setText(list.get(position).servings+" Servings");
        holder.textViewTime.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageViewFood);

        holder.cardViewRandomListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class ViewHolderReceitasAleatorias extends RecyclerView.ViewHolder {
    CardView cardViewRandomListContainer;
    TextView textViewTitle, textViewServings, textViewLikes, textViewTime;
    ImageView imageViewFood;
    public ViewHolderReceitasAleatorias(@NonNull View itemView) {
        super(itemView);

        cardViewRandomListContainer = itemView.findViewById(R.id.cardViewRandomListContainer);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewServings = itemView.findViewById(R.id.textViewServings);
        textViewLikes = itemView.findViewById(R.id.textViewLikes);
        textViewTime = itemView.findViewById(R.id.textViewTime);
        imageViewFood = itemView.findViewById(R.id.imageViewFood);

    }
}