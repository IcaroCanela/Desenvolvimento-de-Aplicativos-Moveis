package com.example.av3_mobile_javagradle.Listeners;


import com.example.av3_mobile_javagradle.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void buscaOk(List<SimilarRecipeResponse> resposta, String mensagem);
    void erroNaBusca(String mensagem);
}
