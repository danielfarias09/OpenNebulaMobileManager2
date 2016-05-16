package br.com.coalman.opennebulamobilemanager.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.vm.VirtualMachinePool;

import java.util.List;

import br.com.coalman.opennebulamobilemanager.helper.OpenNebulaConnect;
import br.com.coalman.opennebulamobilemanager.model.MaquinaVirtual;

/**
 * Created by Daniel on 21/03/2016.
 */
public class TarefaRealizarAcoes extends AsyncTask <String,String,String>  {
    private Context context;
    private ProgressDialog dialog;
    private int acao;
    private String idMV;

    public TarefaRealizarAcoes(Context context, int acao, String idMV){
        this.context = context;
        this.acao = acao;
        this.idMV = idMV;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Aguarde",
                "Finalizando Ação");
    }

    @Override
    protected String doInBackground(String[] params) {
        //String usuario = params[0];
        //String senha = params[1];
        OpenNebulaConnect connect = new OpenNebulaConnect(context);
        Client cliente = connect.getClient();
        VirtualMachinePool pool = new VirtualMachinePool(cliente);
        pool.infoMine();

            switch (acao){
                case 1:
                    pool.getById(Integer.parseInt(idMV)).delete();
                    break;
                case 2:
                    pool.getById(Integer.parseInt(idMV)).stop();
                    break;
                case 3:
                    pool.getById(Integer.parseInt(idMV)).resume();
                    break;
                case 4:
                    pool.getById(Integer.parseInt(idMV)).reboot();
                    break;
                case 5:
                    pool.getById(Integer.parseInt(idMV)).suspend();
                    break;

            }
        return null;
    }

    protected void onPostExecute(String result) {
        switch (acao){
            case 1:
                Toast.makeText(context, "Máquina Virtual Sendo Removida", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context, "Máquina Virtual Sendo Parada", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(context, "Máquina Virtual Sendo Resumida", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(context, "Máquina Virtual Sendo Reiniciada", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(context, "Máquina Virtual Sendo Suspensa", Toast.LENGTH_SHORT).show();
                break;
        }

        dialog.dismiss();

    }
}
