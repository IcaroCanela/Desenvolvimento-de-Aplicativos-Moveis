package com.example.av3_mobile_javagradle.Listeners;

import com.example.av3_mobile_javagradle.Models.ResAPIReceitasAleatorias;

public interface RandomRecipeResponseListener {
    void buscaOk(ResAPIReceitasAleatorias resposta, String mensagem);
    void erroNaBusca(String mensagem);
}
