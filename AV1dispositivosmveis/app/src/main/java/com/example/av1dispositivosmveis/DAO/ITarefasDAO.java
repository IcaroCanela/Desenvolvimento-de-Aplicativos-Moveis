package com.example.av1dispositivosmveis.DAO;

import com.example.av1dispositivosmveis.Model.Tarefas;

import java.util.List;

public interface ITarefasDAO {
    public boolean salvar(Tarefas task);
    public boolean atualizar(Tarefas task);
    public boolean deletar(Tarefas task);

    public List<Tarefas> listar();
}
