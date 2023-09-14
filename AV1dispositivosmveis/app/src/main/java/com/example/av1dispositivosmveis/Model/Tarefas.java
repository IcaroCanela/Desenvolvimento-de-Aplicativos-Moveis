package com.example.av1dispositivosmveis.Model;

public class Tarefas {
    private int id;
    private String tarefa;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTarefa() {return tarefa;}

    public void setTarefa(String tarefa) {this.tarefa = tarefa;}

    @Override
    public String toString(){
        return tarefa;
    }
}
