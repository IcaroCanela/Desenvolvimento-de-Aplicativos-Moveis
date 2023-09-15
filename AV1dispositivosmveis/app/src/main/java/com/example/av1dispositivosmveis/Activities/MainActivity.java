package com.example.av1dispositivosmveis.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.av1dispositivosmveis.DAO.TarefasDAO;
import com.example.av1dispositivosmveis.Model.Tarefas;
import com.example.av1dispositivosmveis.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btAdicioarTarefa, btAlterarTarefa;
    private EditText editTextPersonTarefa;
    private ListView listViewTarefas;
    private int idTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPersonTarefa = findViewById(R.id.editTextPersonTarefa);
        btAdicioarTarefa = findViewById(R.id.btAdicionarTarefa);
        btAlterarTarefa = findViewById(R.id.btAlterarTarefa);
        listViewTarefas = findViewById(R.id.listViewTarefas);

        btAdicioarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefasDAO dbTarefa = new TarefasDAO(getApplicationContext());
                Tarefas task = new Tarefas();

                task.setTarefa(editTextPersonTarefa.getText().toString());

                dbTarefa.salvar(task);
                listaTarefas();
                editTextPersonTarefa.setText("");

                Toast.makeText(MainActivity.this,
                        "Dados salvos com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btAlterarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefasDAO dbTarefa = new TarefasDAO(getApplicationContext());

                Tarefas task = new Tarefas();
                task.setId(idTemp);
                task.setTarefa(editTextPersonTarefa.getText().toString());

                dbTarefa.atualizar(task);
                editTextPersonTarefa.setText("");

                listaTarefas();
            }
        });

        listViewTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<Tarefas> adapter = (ArrayAdapter) listViewTarefas.getAdapter();
                Tarefas tarefaSelecionada = adapter.getItem(position);

                TarefasDAO dbTarefa = new TarefasDAO(getApplicationContext());
                Tarefas tarefa = new Tarefas();
                tarefa.setId((int) tarefaSelecionada.getId());

                dbTarefa.deletar(tarefa);

                listaTarefas();

                Toast.makeText(MainActivity.this,
                        "registro Excuido com sucesso!",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        listViewTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<Tarefas> adapter = (ArrayAdapter) listViewTarefas.getAdapter();
                Tarefas tarefaSelecionada = adapter.getItem(position);

                idTemp = tarefaSelecionada.getId();
                editTextPersonTarefa.setText(tarefaSelecionada.getTarefa());
            }
        });
    }

    public void listaTarefas(){
        TarefasDAO dbTarefa = new TarefasDAO(getApplicationContext());
        List<Tarefas> listaTarefas = dbTarefa.listar();

        ArrayAdapter<Tarefas> adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaTarefas
        );
        listViewTarefas.setAdapter(adaptador);
    }
}