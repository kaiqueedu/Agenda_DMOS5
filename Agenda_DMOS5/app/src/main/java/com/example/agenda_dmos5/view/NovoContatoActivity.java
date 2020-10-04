package com.example.agenda_dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.dao.ContatoDao;
import com.example.agenda_dmos5.models.Contato;

public class NovoContatoActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText nomeEditText;
    private EditText telefoneEditText;
    private EditText celularEditText;
    private Button salvarButton;

    private ContatoDao contatoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        contatoDao = new ContatoDao(this);

        nomeEditText = findViewById(R.id.edittext_nome);
        telefoneEditText = findViewById(R.id.edittext_telefone);
        celularEditText = findViewById(R.id.edittext_celular);

        salvarButton = findViewById(R.id.button_salvar);
        salvarButton.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_salvar:
            salvarContato();
            break;
        }
    }

    private void salvarContato() {
        String nome, celular, telefone;
        nome = nomeEditText.getText().toString();
        telefone = telefoneEditText.getText().toString();
        celular = celularEditText.getText().toString();

        if(!checkNomeNum(nome, telefone, celular)){
            showToast("Campos com erros!");
        }else {
            contatoDao = new ContatoDao(this);
            try {
                contatoDao.add(new Contato(nome, telefone, celular));
                finalizar(true);
            } catch (Exception e) {
                showToast(getString(R.string.erro_null_contato));
            }
        }
    }

    private void finalizar(boolean sucesso){
        if(sucesso){
            setResult(Activity.RESULT_OK);
        }else{
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }

    private void showToast(String mensagem){
        Context context = getApplicationContext();
        CharSequence text = mensagem;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private boolean checkNomeNum(String nome, String celular, String telFixo) {
        boolean valido = true;

        if((celular.isEmpty() && telFixo.isEmpty()) || nome.isEmpty()){
            valido = false;
        }
        return valido;
    }
}