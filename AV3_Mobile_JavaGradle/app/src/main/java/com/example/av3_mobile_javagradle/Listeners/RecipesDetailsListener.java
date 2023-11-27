package com.example.av3_mobile_javagradle.Listeners;

import com.example.av3_mobile_javagradle.Models.RecipeDetailsResponse;
import com.example.av3_mobile_javagradle.Models.ResAPIReceitasAleatorias;

public interface RecipesDetailsListener {
    void buscaOk(RecipeDetailsResponse resposta, String mensagem);
    void erroNaBusca(String mensagem);
}
