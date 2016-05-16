package br.com.coalman.opennebulamobilemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import br.com.coalman.opennebulamobilemanager.R;
import br.com.coalman.opennebulamobilemanager.tasks.TarefaCriarUsuario;

public class CriarNovoUsuario extends AppCompatActivity {
    private static EditText usuario;
    private static EditText senha;
    private static EditText confirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_novo_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void criarUsuario(View view){

        usuario = (EditText)findViewById(R.id.usuario2);
        senha = (EditText)findViewById(R.id.senha2);
        confirmarSenha = (EditText)findViewById(R.id.confirmarSenha2);

        TarefaCriarUsuario tarefaCU = new TarefaCriarUsuario(this);
        tarefaCU.execute(usuario.getText().toString(),senha.getText().toString());

        Intent intent = new Intent(this,ListarMVs.class);
        startActivity(intent);

    }

}
