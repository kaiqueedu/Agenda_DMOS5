package com.example.agenda_dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.agenda_dmos5.R;

import com.example.agenda_dmos5.models.Contato;

public class VisualizarContatoActivity extends AppCompatActivity {

    private TextView textNome;
    private TextView textCel;
    private TextView textFix;

    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_contato);

        //Recupero elementos do layout
        textNome = findViewById(R.id.textview_nome);
        textCel = findViewById(R.id.textview_telCel);
        textFix = findViewById(R.id.textview_telFix);

        //Recupero dados trazidos do intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            String nome     = bundle.getString("nome");
            String celular  = bundle.getString("telefone");
            String fixo = bundle.getString("celular");

            contato = new Contato(nome, celular, fixo);
        }

        //Atribuo a caixa
        textNome.setText(contato.getNome());
        textCel.setText(contato.getTelefone());
        textFix.setText(contato.getCelular());
    }
}