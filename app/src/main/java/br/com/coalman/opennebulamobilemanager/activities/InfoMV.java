package br.com.coalman.opennebulamobilemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.opennebula.client.vm.VirtualMachine;

import at.markushi.ui.CircleButton;
import br.com.coalman.opennebulamobilemanager.R;
import br.com.coalman.opennebulamobilemanager.model.MaquinaVirtual;
import br.com.coalman.opennebulamobilemanager.tasks.TarefaRealizarAcoes;

public class InfoMV extends AppCompatActivity {
    private MaquinaVirtual mv;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private CircleButton buttonResumir1;
    private CircleButton buttonResumir2;
    private CircleButton buttonSuspender;
    private CircleButton buttonParar;
    private TextView legendaButton1;
    private TextView legendaButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mv);

        buttonResumir1 = (CircleButton)findViewById(R.id.buttonResumir1);
        buttonResumir1.setVisibility(View.GONE);
        buttonResumir2 = (CircleButton)findViewById(R.id.buttonResumir2);
        buttonResumir2.setVisibility(View.GONE);
        buttonSuspender = (CircleButton)findViewById(R.id.buttonSuspender);
        buttonParar = (CircleButton)findViewById(R.id.buttonParar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mv = (MaquinaVirtual) bundle.getSerializable("mv");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mv.getNome());
        setSupportActionBar(toolbar);

        textView1 = (TextView) findViewById(R.id.id);
        textView2 = (TextView) findViewById(R.id.status);
        textView3 = (TextView) findViewById(R.id.ip);
        textView4 = (TextView) findViewById(R.id.memoria);
        textView5 = (TextView) findViewById(R.id.vcpu);
        textView6 = (TextView) findViewById(R.id.mac);
        textView7 = (TextView) findViewById(R.id.disco);

        legendaButton1 = (TextView) findViewById(R.id.legendaButton1);
        legendaButton2 = (TextView) findViewById(R.id.legendaButton2);

        textView1.setText(mv.getId());
        textView2.setText(mv.getStatus());
        textView3.setText(mv.getIp());
        textView4.setText(mv.getMemoria());
        textView5.setText(mv.getVcpu());
        textView6.setText(mv.getMac());
        textView7.setText(mv.getDisco() + " MB");

        if(mv.getStatus() == "STOPPED"){
            buttonResumir2.setVisibility(View.VISIBLE);
            buttonParar.setVisibility(View.GONE);
            legendaButton2.setText("Resumir");
        }else if(mv.getStatus() == "SUSPENDED"){
            buttonResumir1.setVisibility(View.VISIBLE);
            buttonSuspender.setVisibility(View.GONE);
            legendaButton1.setText("Resumir");
        }
    }


    public void removerMV(View view){
        TarefaRealizarAcoes tarefa = new TarefaRealizarAcoes(this,1,mv.getId());
        tarefa.execute();
        chamarListagem();
    }

    public void pararMV(View view){
        TarefaRealizarAcoes tarefa = new TarefaRealizarAcoes(this,2,mv.getId());
        tarefa.execute();
        chamarListagem();
    }

    public void resumirMV(View view){
        TarefaRealizarAcoes tarefa = new TarefaRealizarAcoes(this,3,mv.getId());
        tarefa.execute();
        chamarListagem();
    }

    public void reiniciarMV(View view){
        TarefaRealizarAcoes tarefa = new TarefaRealizarAcoes(this,4,mv.getId());
        tarefa.execute();
        chamarListagem();
    }

    public void suspenderMV(View view){
        TarefaRealizarAcoes tarefa = new TarefaRealizarAcoes(this,5,mv.getId());
        tarefa.execute();
        chamarListagem();
    }

    public void chamarListagem() {
        Intent intent = new Intent(this, ListarMVs.class);
        startActivity(intent);
    }

}
