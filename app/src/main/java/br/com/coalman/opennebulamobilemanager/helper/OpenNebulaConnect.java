package br.com.coalman.opennebulamobilemanager.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Daniel on 15/03/2016.
 */
public class OpenNebulaConnect {
    public static final String PREFS_NAME = "preferencias";
    private Context context;
    private Client cliente;

    public OpenNebulaConnect(Context context){
        this.context = context;
    }

    public Client getClient(){
        String usuario;
        String senha;
        String endPoint;
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

            usuario = sharedpreferences.getString("usuario", ""); //"" é o valor default caso não venha nada
            senha = sharedpreferences.getString("senha", "");
            endPoint = sharedpreferences.getString("endPoint", "");


        try {
            cliente = new Client(usuario + ":" + senha , endPoint);
        } catch (ClientConfigurationException e) {
            e.printStackTrace();
        }

        return cliente;
    }

}