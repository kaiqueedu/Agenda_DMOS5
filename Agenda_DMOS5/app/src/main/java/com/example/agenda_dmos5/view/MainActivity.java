package com.example.agenda_dmos5.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.constantes.Constantes;
import com.example.agenda_dmos5.dao.ContatoDao;
import com.example.agenda_dmos5.models.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int VISUALIZAR_CONTATO = 98;

        private ListView contatosListView;
        private List<Contato> contatoList;
        private FloatingActionButton adicionarButton;

        private Context conText = this;

        private ContatoDao contatoDao;
        private ArrayAdapter<Contato> contatoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupero a referencia a lista de contatos no layout
        contatosListView = findViewById(R.id.listview_lista);
        adicionarButton = findViewById(R.id.fab_adicionar);
        adicionarButton.setOnClickListener(this);

        //Carrega a fonte de dados
        contatoDao = new ContatoDao(this);
        contatoList = contatoDao.buscaTodos();

        //Instancia do adapter, aqui configura-se como os dados serão apresentados e também
        //qual a fonte de dados será utilizada.
        contatoArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatoList);

        //Com o adapter pronto, vinculamos ele ao nosso ListView. Após esse comando o
        //ListView já consegue apresentar os dados na tela.
        contatosListView.setAdapter(contatoArrayAdapter);

        contatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle argumentos = new Bundle();

                argumentos.putString("nome", contatoList.get(i).getNome());
                argumentos.putString("telefone", contatoList.get(i).getTelefone());
                argumentos.putString("celular", contatoList.get(i).getCelular());

                Intent in = new Intent(conText, VisualizarContatoActivity.class);
                in.putExtras(argumentos);

                startActivityForResult(in, VISUALIZAR_CONTATO);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == adicionarButton){
            Intent novoContato = new Intent(conText, NovoContatoActivity.class);
            startActivityForResult(novoContato, Constantes.NEW_CONTATO_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constantes.NEW_CONTATO_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    updateDataSet();
                    contatoArrayAdapter.notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        super.onResume();
        atualiza();
    }

    private void atualiza() {
        if (contatosListView.getAdapter().getCount() == 0) {
            contatosListView.setVisibility(View.GONE);
        } else {

            contatosListView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDataSet(){
        contatoList.clear();
        contatoList.addAll(contatoDao.buscaTodos());
    }

}