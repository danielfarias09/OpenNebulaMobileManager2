package br.com.coalman.opennebulamobilemanager.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.opennebula.client.Client;
import org.opennebula.client.vm.VirtualMachine;
import org.opennebula.client.vm.VirtualMachinePool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.coalman.opennebulamobilemanager.helper.OpenNebulaConnect;
import br.com.coalman.opennebulamobilemanager.interfaces.TarefaListarMVsInterface;
import br.com.coalman.opennebulamobilemanager.model.MaquinaVirtual;

/**
 * Created by Daniel on 26/11/2015.
 */
public class TarefaListarMVs extends AsyncTask <String,String,List<MaquinaVirtual>> {

    private Context context;
    private ProgressDialog dialog;
    private TarefaListarMVsInterface it;
    public static final String PREFS_QTD_MV = "preferencias qtd MV";

    public TarefaListarMVs(Context context, TarefaListarMVsInterface it){
        this.context = context;
        this.it = it;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Aguarde",
                "Carregando dados");
    }

    @Override
    protected List<MaquinaVirtual> doInBackground(String... params) {
        OpenNebulaConnect connect = new OpenNebulaConnect(context);
        Client cliente = connect.getClient();
        List<MaquinaVirtual> mvs = new ArrayList<MaquinaVirtual>();

        VirtualMachinePool pool = new VirtualMachinePool(cliente);
        Iterator<VirtualMachine> iterator = pool.iterator();
        pool.infoAll();

        //Salva pela primeira vez a quantidade de MVs
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREFS_QTD_MV, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("qtdMVs",pool.getLength());
        editor.commit();


            while(iterator.hasNext()){
                VirtualMachine vm = iterator.next();
                MaquinaVirtual mv = new MaquinaVirtual();

                mv.setNome(vm.getName());
                mv.setId(vm.getId());
                mv.setMemoria(vm.xpath("MEMORY"));
                mv.setStatus(vm.stateStr());
                mv.setIp(vm.xpath("TEMPLATE/NIC/IP"));
                mv.setMac(vm.xpath("TEMPLATE/NIC/MAC"));
                mv.setVcpu(vm.xpath("TEMPLATE/VCPU"));
                mv.setDisco(vm.xpath("TEMPLATE/DISK/SIZE"));
                mvs.add(mv);
            }

        return mvs;
    }

    protected void onPostExecute(List<MaquinaVirtual>  mvs) {
        it.listarMVs(mvs);
        dialog.dismiss();

    }
}
