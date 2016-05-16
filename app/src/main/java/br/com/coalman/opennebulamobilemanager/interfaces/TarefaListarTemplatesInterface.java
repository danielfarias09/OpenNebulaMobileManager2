package br.com.coalman.opennebulamobilemanager.interfaces;

import org.opennebula.client.template.Template;

import java.util.ArrayList;

/**
 * Created by Daniel on 22/12/2015.
 */
public interface TarefaListarTemplatesInterface {

    public void listarTemplates(ArrayList<Template> templates);
}
