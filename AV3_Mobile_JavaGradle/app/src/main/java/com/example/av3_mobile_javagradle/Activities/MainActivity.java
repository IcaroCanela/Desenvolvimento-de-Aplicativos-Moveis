package com.example.av3_mobile_javagradle.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.av3_mobile_javagradle.Adapters.FavoriteRecipeAdapter;
import com.example.av3_mobile_javagradle.Adapters.RecicleViewReceitasAleatoriasAdapter;
import com.example.av3_mobile_javagradle.Listeners.RandomRecipeResponseListener;
import com.example.av3_mobile_javagradle.Listeners.RecipeClickListener;
import com.example.av3_mobile_javagradle.Models.FavoritesRecipes;
import com.example.av3_mobile_javagradle.Models.ResAPIReceitasAleatorias;
import com.example.av3_mobile_javagradle.R;
import com.example.av3_mobile_javagradle.RequestManeger.RequestManeger;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    RequestManeger maneger;
    RecicleViewReceitasAleatoriasAdapter adapterReceitasAleatorias;
    FloatingActionButton floatingActionButtonProfile;
    RecyclerView recyclerView;
    Spinner spinnerTags;
    List<String> tags = new ArrayList<>();
    SearchView searchViewHome;
    List<FavoritesRecipes> favoritesRecipesList;
    FavoriteRecipeAdapter favoriteRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButtonProfile = findViewById(R.id.floatingActionButtonProfile);

        floatingActionButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritesRecipesActivity.class);
                startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Carregando...");

        searchViewHome = findViewById(R.id.seacrhViewHome);
        searchViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                maneger.getReceitasAleatorias(receitasAleatoriasResponseListener, tags);
                progressDialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinnerTags = findViewById(R.id.spinnerTags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinnerTags.setAdapter(arrayAdapter);
        spinnerTags.setOnItemSelectedListener(spinnerSelectedListenner);

        maneger = new RequestManeger(this);
    }

    private final RandomRecipeResponseListener receitasAleatoriasResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void buscaOk(ResAPIReceitasAleatorias resposta, String mensagem) {
            progressDialog.dismiss();
            recyclerView = findViewById(R.id.recyclerViewReceitasAleatorias);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            adapterReceitasAleatorias = new RecicleViewReceitasAleatoriasAdapter(MainActivity.this, resposta.recipes, recipeClickListener);
            recyclerView.setAdapter(adapterReceitasAleatorias);
        }

        @Override
        public void erroNaBusca(String mensagem) {
            Toast.makeText(MainActivity.this, "Error \n"+mensagem, Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListenner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            maneger.getReceitasAleatorias(receitasAleatoriasResponseListener, tags);
            progressDialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(MainActivity.this, RecipesDetailsActivity.class)
                    .putExtra("id", id));
        }
    };
}