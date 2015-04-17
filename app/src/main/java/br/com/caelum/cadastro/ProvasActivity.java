package br.com.caelum.cadastro;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.Fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.Fragment.ListaProvasFragment;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by android5193 on 15/04/15.
 */
public class ProvasActivity extends ActionBarActivity {

    public void selecionaProva(Prova prova){
        FragmentManager manager = getSupportFragmentManager();

        if (isTablet()){
            DetalhesProvaFragment delhatesProva = (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_detalhes);
            delhatesProva.populaCamposComDados(prova);
        } else {
            Bundle argumentos = new Bundle();
            argumentos.putSerializable("prova", prova);

            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(argumentos);

            FragmentTransaction tx = manager.beginTransaction();
            tx.replace(R.id.provas_view, detalhesProva);
            tx.addToBackStack(null); // Estado sem  nome. Com o nome eh possivel desempilhar tudo ate chegar no estado com o nome.
            tx.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_provas);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();

        if (isTablet()){
            tx.replace(R.id.provas_lista, new ListaProvasFragment())
              .replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        }
        else{
            tx.replace(R.id.provas_view, new ListaProvasFragment());
        }
        tx.commit();
    }

    private boolean isTablet(){
        return getResources().getBoolean(R.bool.isTablet);
    }
}
