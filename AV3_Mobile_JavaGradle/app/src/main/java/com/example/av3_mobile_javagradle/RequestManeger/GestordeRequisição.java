package com.example.av3_mobile_javagradle.RequestManeger;

import android.content.Context;

import com.example.av3_mobile_javagradle.Listeners.ReceitasAleatoriasResponseListener;
import com.example.av3_mobile_javagradle.Listeners.RecipesDetailsListener;
import com.example.av3_mobile_javagradle.Listeners.SimilarRecipesListener;
import com.example.av3_mobile_javagradle.Models.RecipeDetailsResponse;
import com.example.av3_mobile_javagradle.Models.ResAPIReceitasAleatorias;
import com.example.av3_mobile_javagradle.Models.SimilarRecipeResponse;
import com.example.av3_mobile_javagradle.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class GestordeRequisição {
    private Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public GestordeRequisição(Context context) {
        this.context = context;
    }

    public void getReceitasAleatorias(ReceitasAleatoriasResponseListener listener, List<String> tags){
        CallReceitasAleatorias callReceitasAleatorias = retrofit.create(CallReceitasAleatorias.class);
        Call<ResAPIReceitasAleatorias> call = callReceitasAleatorias.callReceitasAleatoria(context.getString(R.string.api_key), "10", tags);
        call.enqueue(new Callback<ResAPIReceitasAleatorias>() {
            @Override
            public void onResponse(Call<ResAPIReceitasAleatorias> call, Response<ResAPIReceitasAleatorias> response) {
                if(!response.isSuccessful()){
                    listener.erroNaBusca(response.message());
                    return;
                }
                listener.buscaOk(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ResAPIReceitasAleatorias> call, Throwable t) {
                listener.erroNaBusca(t.getMessage());
            }
        });
    }

    public void getRecipesDetails(RecipesDetailsListener listener, int id){
        CallDetalhesDaReceita callDetalhesDaReceita = retrofit.create(CallDetalhesDaReceita.class);
        Call<RecipeDetailsResponse> call = callDetalhesDaReceita.callDetalhesDaReceita(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.erroNaBusca(response.message());
                    return;
                }
                listener.buscaOk(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.erroNaBusca(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipesListener listener, int id){
        CallReceitasSimilates callReceitasSimilates = retrofit.create(CallReceitasSimilates.class);
        Call<List<SimilarRecipeResponse>> call = callReceitasSimilates.callReceitaSimilar(id, "6", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.erroNaBusca(response.message());
                    return;
                }
                listener.buscaOk(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.erroNaBusca(t.getMessage());
            }
        });
    }
    private interface CallReceitasAleatorias{
        @GET("recipes/random")
        Call<ResAPIReceitasAleatorias> callReceitasAleatoria(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    private interface CallDetalhesDaReceita{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callDetalhesDaReceita(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallReceitasSimilates{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callReceitaSimilar(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }
}
