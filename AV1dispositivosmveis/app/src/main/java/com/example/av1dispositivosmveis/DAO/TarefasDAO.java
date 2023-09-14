package com.example.av1dispositivosmveis.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.av1dispositivosmveis.Helper.DBHelper;
import com.example.av1dispositivosmveis.Model.Tarefas;

import java.util.ArrayList;
import java.util.List;

public class TarefasDAO implements ITarefasDAO{
    private SQLiteDatabase objEscritor;
    private SQLiteDatabase objLeitor;

    public TarefasDAO(Context ctxt){
        DBHelper db = new DBHelper(ctxt);
        objEscritor = db.getWritableDatabase();
        objLeitor = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefas task) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("tarefas", task.getTarefa());

            objEscritor.insert("tarefas", null, contentValues);

            Log.i("INFO DB", "Dados salvos com sucesso!");
  ;      }catch(Exception e){
            Log.i("INFO DB", "Erro ao salvar os dados "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefas task) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("tarefas", task.getTarefa());

            String[] args = {String.valueOf(task.getId())};
            objEscritor.update("tarefas", contentValues, "id = ?", args);

            Log.i("INFO DB", "Dados atualizaos com sucesso!");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar os dados "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefas task) {
        try{
            objEscritor.delete("tarefas", "id = ?", new String[]{String.valueOf(task.getId())});

            Log.i("INFO DB", "Sucesso ao deletar dados!");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao deletar os dados "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefas> listar() {
        List<Tarefas> listaTarefas = new ArrayList<>();

        try{
            String sql = "SELECT * FROM tarefas ";
            Cursor cursor =objLeitor.rawQuery(sql, null);

            int iid = cursor.getColumnIndexOrThrow("id");
            int itarefa = cursor.getColumnIndexOrThrow("tarefas");

            cursor.moveToFirst();

            do{
                if(cursor.getCount() == 0){break;}

                Tarefas task = new Tarefas();
                task.setId(cursor.getInt(iid));
                task.setTarefa(cursor.getString(itarefa));

                listaTarefas.add(task);
            }while(cursor.moveToNext());

            Log.i("INFO DB", "Sucesso ao listar os dados!");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao listar as tarefas "+e.getMessage());
            return null;
        }
        return listaTarefas;
    }
}
