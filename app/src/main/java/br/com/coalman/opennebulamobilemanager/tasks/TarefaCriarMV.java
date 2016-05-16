package br.com.coalman.opennebulamobilemanager.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.opennebula.client.OneResponse;
import org.opennebula.client.template.Template;

/**
 * Created by Daniel on 22/12/2015.
 */
public class TarefaCriarMV extends AsyncTask <String, String, OneResponse> {
    private Context contexto;
    private Template templateEscolhido;
    private ProgressDialog dialog;

    public TarefaCriarMV(Context contexto, Template templateEscolhido){
        this.contexto = contexto;
        this.templateEscolhido = templateEscolhido;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(contexto, "Aguarde",
                "Instanciando Máquina Virtual");
    }

    @Override
    protected OneResponse doInBackground(String... params) {

        String nomeTemplate = params[0];

        return templateEscolhido.instantiate(nomeTemplate);
    }

    @Override
    protected void onPostExecute(OneResponse rc){

        if(rc.isError()){
            Toast.makeText(contexto, "Erro: " + rc.getErrorMessage(), Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(contexto, "Máquina Virtual Instanciada com Sucesso", Toast.LENGTH_LONG).show();
        }

        dialog.dismiss();

    }
}
