package br.com.coalman.opennebulamobilemanager.service;

import android.animation.AnimatorSet;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.opennebula.client.Client;
import org.opennebula.client.vm.VirtualMachine;
import org.opennebula.client.vm.VirtualMachinePool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.coalman.opennebulamobilemanager.R;
import br.com.coalman.opennebulamobilemanager.activities.ListarMVs;
import br.com.coalman.opennebulamobilemanager.helper.OpenNebulaConnect;
import br.com.coalman.opennebulamobilemanager.model.MaquinaVirtual;

/**
 * Created by Daniel on 09/05/2016.
 */
public class Servico extends Service {
    public List<Tarefa> tarefas = new ArrayList<Tarefa>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Tarefa tarefa = new Tarefa(this,startId);
        tarefa.start();
        tarefas.add(tarefa);
        return (super.onStartCommand(intent,flags,startId));
    }

    class Tarefa extends Thread{
        private Context context;
        public int startId;
        public boolean ativo = true;
        public static final String PREFS_QTD_MV = "preferencias qtd MV";
        public int tamanhoDaListaAtual = 0;
        public int tamanhoDaListaSalvo = 0;

        public Tarefa(Context context, int startId){
            this.context = context;
            this.startId = startId;
        }

        public void run(){
            while (ativo){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getQtdMvs();
            }

        }

        public void getQtdMvs(){
            OpenNebulaConnect connect = new OpenNebulaConnect(context);
            List<MaquinaVirtual> mvs = new ArrayList<MaquinaVirtual>();
            Client cliente = connect.getClient();

            VirtualMachinePool pool = new VirtualMachinePool(cliente);
            pool.infoAll();
            tamanhoDaListaAtual = pool.getLength();

            SharedPreferences sharedpreferences = context.getSharedPreferences(PREFS_QTD_MV, Context.MODE_PRIVATE);
            tamanhoDaListaSalvo = sharedpreferences.getInt("qtdMVs",0);

            //Se a quantidade de MVS for maior ou menor do que a quantidade de MVS salva anteriormente, será mostrada uma notificação
            //e a quantidade de MVS salvo será atualizado
            if(tamanhoDaListaAtual > tamanhoDaListaSalvo){
                gerarNotificacao("MV criada", "MV criada","Agora existem um total de " + tamanhoDaListaAtual + " MVs");
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("qtdMVs",tamanhoDaListaAtual);
                editor.commit();
            }else if(tamanhoDaListaAtual < tamanhoDaListaSalvo){
                gerarNotificacao("MV removida", "MV removida","Agora existem um total de " + tamanhoDaListaAtual + " MVs");
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("qtdMVs",tamanhoDaListaAtual);
                editor.commit();
            }
        }

        public void gerarNotificacao(String msgTicker,String msgTitle,String msgText){
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //Esta intent pendente vai ser chamada quando o usuário clicar na aplicação
            PendingIntent pending = PendingIntent.getActivity(context,0,new Intent(context, ListarMVs.class),0);

            //Criação da Notificação
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setTicker(msgTicker);
            builder.setContentTitle(msgTitle);
            builder.setContentText(msgText);
            builder.setSmallIcon(R.drawable.mv);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            builder.setContentIntent(pending);

            Notification notification = builder.build();
            notification.vibrate = new long[]{150,300,150,600};
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            //só foi passado este drawable pq precisava de um argumento que fosse inteiro
            manager.notify(R.drawable.mv,notification);
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        for(int i=0; i<tarefas.size();i++){
            tarefas.get(i).ativo = false;
        }
    }
}
