package br.com.coalman.opennebulamobilemanager.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.OneResponse;
import org.opennebula.client.user.User;
import org.opennebula.client.user.UserPool;

import java.util.List;

import br.com.coalman.opennebulamobilemanager.helper.OpenNebulaConnect;
import br.com.coalman.opennebulamobilemanager.model.MaquinaVirtual;

/**
 * Created by Daniel on 14/03/2016.
 */
public class TarefaCriarUsuario extends AsyncTask <String, String, OneResponse> {
    private Context contexto;
    private ProgressDialog dialog;

    public TarefaCriarUsuario(Context contexto){
        this.contexto = contexto;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(contexto, "Aguarde!",
                "Alocando um Novo Usuário");
    }

    @Override
    protected OneResponse doInBackground(String[] params) {
        String novoUsuario = params[0];
        String senhaNovoUsuario = params[1];
        Client cliente = null;
        OneResponse rc = null;
        OpenNebulaConnect connect = new OpenNebulaConnect(contexto);

            cliente = connect.getClient();
            UserPool userpool = new UserPool(cliente);
            rc = userpool.info();
            rc = User.allocate(cliente, novoUsuario, senhaNovoUsuario);

        return rc;
    }

    @Override
    protected void onPostExecute(OneResponse rc) {

        if(rc.isError()){
            Toast.makeText(contexto, "Erro: " + rc.getErrorMessage(), Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(contexto, "Usuário Alocado com Sucesso", Toast.LENGTH_LONG).show();
        }
        dialog.dismiss();
    }
}
