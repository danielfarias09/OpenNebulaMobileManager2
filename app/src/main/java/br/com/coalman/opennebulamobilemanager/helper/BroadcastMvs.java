package br.com.coalman.opennebulamobilemanager.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.coalman.opennebulamobilemanager.service.Servico;

/**
 * Created by Daniel on 10/05/2016.
 */
//Broadcast que ouve quando o celular é reiniciado
public class BroadcastMvs extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent(context, Servico.class);
        context.startService(intent);

    }
}
