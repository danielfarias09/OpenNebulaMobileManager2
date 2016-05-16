package br.com.coalman.opennebulamobilemanager.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.opennebula.client.Client;
import org.opennebula.client.vm.VirtualMachine;
import org.opennebula.client.vm.VirtualMachinePool;

import br.com.coalman.opennebulamobilemanager.R;
import br.com.coalman.opennebulamobilemanager.helper.OpenNebulaConnect;

public class MainActivity extends AppCompatActivity {
    private static EditText usuario;
    private static EditText senha;
    private static EditText endPoint;
    public static final String PREFS_NAME = "preferencias";
    private SharedPreferences sharedpreferences;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usuario = (EditText)findViewById(R.id.usuario);
        senha = (EditText)findViewById(R.id.senha);
        endPoint = (EditText)findViewById(R.id.endpoint);

        sharedpreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setChecked(sharedpreferences.getBoolean("checkbox",false));

        usuario.setText(sharedpreferences.getString("usuario", ""));
        senha.setText(sharedpreferences.getString("senha",""));
        endPoint.setText(sharedpreferences.getString("endPoint", ""));

    }

    public void autenticar(View view){
        OpenNebulaConnect connect = new OpenNebulaConnect(this);
        connect.getClient();
        Intent intent = new Intent(this,ListarMVs.class);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sharedpreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if(checkBox.isChecked()){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("usuario", usuario.getText().toString().trim());
            editor.putString("senha", senha.getText().toString().trim());
            editor.putString("endPoint", endPoint.getText().toString().trim());
            editor.putBoolean("checkbox",checkBox.isChecked());
            editor.commit();
        } else {

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();

        }
    }


}
