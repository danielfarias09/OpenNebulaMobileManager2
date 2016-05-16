package br.com.coalman.opennebulamobilemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.opennebula.client.template.Template;

import java.util.ArrayList;

import br.com.coalman.opennebulamobilemanager.R;
import br.com.coalman.opennebulamobilemanager.interfaces.TarefaListarTemplatesInterface;
import br.com.coalman.opennebulamobilemanager.tasks.TarefaCriarMV;
import br.com.coalman.opennebulamobilemanager.tasks.TarefaListarTemplates;

public class CriarMV extends AppCompatActivity implements TarefaListarTemplatesInterface {
    private EditText editText;
    private String nomeMV;
    private Spinner spinner;
    private String usuario;
    private String senha;
    private Template templateEscolhido;
    private ArrayAdapter<String> adapter;
    private ArrayList<Template> templates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_mv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TarefaListarTemplates tarefaLT = new TarefaListarTemplates(this,this);
        tarefaLT.execute(usuario, senha);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

    }

    @Override
    public void listarTemplates(ArrayList<Template> listTemplates) {
        templates = listTemplates;
        carregarSpinner();

    }

    public void carregarSpinner(){

        for(int i=0;i<templates.size();i++){
            //Adiciono as strings com os nomes dos templates ao adapter
            adapter.add(templates.get(i).getName());
        }


        spinner = (Spinner) findViewById(R.id.spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Pega o nome do template selecionado na posição que o usuário escolher
                templateEscolhido = templates.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }


    public void criarMV(View view){

        editText = (EditText)findViewById(R.id.nomeMV);
        nomeMV = editText.getText().toString();

        TarefaCriarMV tarefaCMV = new TarefaCriarMV(this, templateEscolhido);
        tarefaCMV.execute(nomeMV);

        Intent intent = new Intent(this, ListarMVs.class);
        startActivity(intent);


    }

}
