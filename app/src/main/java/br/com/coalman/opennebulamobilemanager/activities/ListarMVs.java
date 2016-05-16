package br.com.coalman.opennebulamobilemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.List;

import br.com.coalman.opennebulamobilemanager.R;
import br.com.coalman.opennebulamobilemanager.adapter.MVAdapter;
import br.com.coalman.opennebulamobilemanager.adapter.RecyclerItemClickListener;
import br.com.coalman.opennebulamobilemanager.interfaces.TarefaListarMVsInterface;
import br.com.coalman.opennebulamobilemanager.model.MaquinaVirtual;
import br.com.coalman.opennebulamobilemanager.service.Servico;
import br.com.coalman.opennebulamobilemanager.tasks.TarefaListarMVs;

public class ListarMVs extends AppCompatActivity implements TarefaListarMVsInterface {
    private static String usuario = "oneadmin";
    private static String senha = "abcdef";
    private Toolbar toolbar;
    private List<MaquinaVirtual> mvs;
    private LinearLayoutManager layoutManager;
    private RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mvs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(layoutManager);

        TarefaListarMVs tarefa = new TarefaListarMVs(this,this);
        tarefa.execute();

        chamarService();

    }

    @Override
    public void listarMVs(List<MaquinaVirtual> mvs) {
        this.mvs = mvs;
        inicializarRecycle();

    }

    public void criarMV(View view){
        Intent intent = new Intent(getApplicationContext(), CriarMV.class);
        startActivity(intent);
    }

    public void criarUsuario(View view){
        Intent intent = new Intent(getApplicationContext(), CriarNovoUsuario.class);
        startActivity(intent);
    }

    public void inicializarRecycle() {
        MVAdapter adapter = new MVAdapter(mvs);
        recList.setAdapter(adapter);
        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        MaquinaVirtual mv = mvs.get(position);

                        Intent intent = new Intent(getApplicationContext(), InfoMV.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mv", mv);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                })
        );
    }

    public void chamarService(){
        Intent intent = new Intent(this, Servico.class);
        startService(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_listar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.atualizar:
                Intent intent = new Intent(this,ListarMVs.class);
                startActivity(intent);
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

}
