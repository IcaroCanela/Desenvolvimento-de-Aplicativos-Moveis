package com.example.av3_mobile_javagradle.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.av3_mobile_javagradle.Adapters.IngredientsAdapter;
import com.example.av3_mobile_javagradle.Adapters.SimilarRecipeAdapter;
import com.example.av3_mobile_javagradle.Listeners.RecipeClickListener;
import com.example.av3_mobile_javagradle.Listeners.RecipesDetailsListener;
import com.example.av3_mobile_javagradle.Listeners.SimilarRecipesListener;
import com.example.av3_mobile_javagradle.Models.RecipeDetailsResponse;
import com.example.av3_mobile_javagradle.Models.SimilarRecipeResponse;
import com.example.av3_mobile_javagradle.R;
import com.example.av3_mobile_javagradle.RequestManeger.RequestManeger;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesDetailsActivity extends AppCompatActivity {
    TextView textViewMealName, textViewMealSource, textViewMealSummary;
    ImageView imageViewMealImage;
    RecyclerView recyclerViewMealIngredients, recyclerViewMealSimilar;
    RequestManeger maneger;
    ProgressDialog progressDialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_details);
        textViewMealName=findViewById(R.id.textViewMealName);
        textViewMealSource=findViewById(R.id.textViewMealSource);
        textViewMealSummary=findViewById(R.id.textViewMealSummary);
        imageViewMealImage=findViewById(R.id.imageViewMealImage);
        recyclerViewMealIngredients=findViewById(R.id.recyclerViewMealIngredients);
        recyclerViewMealSimilar=findViewById(R.id.recyclerViewMealSimilar);



        id = Integer.parseInt(getIntent().getStringExtra("id"));

        maneger = new RequestManeger(this);
        maneger.getRecipesDetails(recipesDetailsListener, id);
        maneger.getSimilarRecipes(similarRecipesListener, id);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Carregando Detalhes... ");
        progressDialog.show();
    }


    private final RecipesDetailsListener recipesDetailsListener = new RecipesDetailsListener() {
        @Override
        public void buscaOk(RecipeDetailsResponse resposta, String mensagem) {
            progressDialog.dismiss();
            textViewMealName.setText(resposta.title);
            textViewMealSource.setText(resposta.sourceName);
            textViewMealSummary.setText(resposta.summary);
            Picasso.get().load(resposta.image).into(imageViewMealImage);

            recyclerViewMealIngredients.setHasFixedSize(true);
            recyclerViewMealIngredients.setLayoutManager(new LinearLayoutManager(RecipesDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipesDetailsActivity.this, resposta.extendedIngredients);
            recyclerViewMealIngredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void erroNaBusca(String mensagem) {
            Toast.makeText(RecipesDetailsActivity.this, "Error \n"+mensagem, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void buscaOk(List<SimilarRecipeResponse> resposta, String mensagem) {
            recyclerViewMealSimilar.setHasFixedSize(true);
            recyclerViewMealSimilar.setLayoutManager(new LinearLayoutManager(RecipesDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipesDetailsActivity.this, resposta, recipeClickListener);
            recyclerViewMealSimilar.setAdapter(similarRecipeAdapter);

        }

        @Override
        public void erroNaBusca(String mensagem) {
            Toast.makeText(RecipesDetailsActivity.this, "Error \n"+mensagem, Toast.LENGTH_SHORT).show();
        }
    };

    private RecipeClickListener recipeClickListener = new RecipeClickListener() {

        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipesDetailsActivity.this, RecipesDetailsActivity.class)
                    .putExtra("id", id));
        }
    };
}