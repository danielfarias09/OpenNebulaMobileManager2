package br.com.coalman.opennebulamobilemanager.tasks;

import java.util.ArrayList;
import java.util.Iterator;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.Pool;
import org.opennebula.client.template.Template;
import org.opennebula.client.template.TemplatePool;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.com.coalman.opennebulamobilemanager.helper.OpenNebulaConnect;
import br.com.coalman.opennebulamobilemanager.interfaces.TarefaListarTemplatesInterface;

public class TarefaListarTemplates extends AsyncTask <String, String, ArrayList<Template> >{

    private Context context;
    private TarefaListarTemplatesInterface it;
    private ProgressDialog dialog;

    public TarefaListarTemplates(Context context, TarefaListarTemplatesInterface it){
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
    protected ArrayList<Template> doInBackground(String... params) {
        Client cliente = null;
        OpenNebulaConnect connect = new OpenNebulaConnect(context);
        ArrayList<Template> templates = null;
        templates = new ArrayList<Template>();

            cliente = connect.getClient();
            TemplatePool pool = new TemplatePool(cliente,Pool.ALL);
            Iterator<Template> iterator = pool.iterator();
            templates = new ArrayList<Template>();
            pool.infoAll();

            while(iterator.hasNext()){
                Template template = iterator.next();
                //Preenche o adapter com os templates
                templates.add(template);
            }


        return templates;
    }

    //Recebe como parâmetro o retorno do doInbackground
    protected void onPostExecute(final ArrayList<Template> templates) {
        it.listarTemplates(templates);
        dialog.dismiss();

    }


}

