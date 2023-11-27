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
import com.example.av3_mobile_javagradle.Models.SimilarRecipeResponse;
import com.example.av3_mobile_javagradle.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder>{
    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.textViewSimilarTitle.setText(list.get(position).title);
        holder.textViewSimilarTitle.setSelected(true);
        holder.textViewSimilarserning.setText(list.get(position).servings+" Persons");
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.imageViewSimilar);

        holder.cardViewSimilarRecipeHolder.setOnClickListener(new View.OnClickListener() {
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

class SimilarRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView cardViewSimilarRecipeHolder;
    TextView textViewSimilarTitle, textViewSimilarserning;
    ImageView imageViewSimilar;
    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        cardViewSimilarRecipeHolder = itemView.findViewById(R.id.cardViewSimilarRecipeHolder);
        textViewSimilarTitle = itemView.findViewById(R.id.textViewSimilarTitle);
        textViewSimilarserning = itemView.findViewById(R.id.textViewSimilarserning);
        imageViewSimilar = itemView.findViewById(R.id.imageViewSimilar);
    }
}