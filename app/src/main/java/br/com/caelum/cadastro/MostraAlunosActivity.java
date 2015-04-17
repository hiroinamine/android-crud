package br.com.caelum.cadastro;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.Fragment.MapaFragment;
import br.com.caelum.cadastro.R;

/**
 * Created by android5193 on 17/04/15.
 */
public class MostraAlunosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_alunos);

        MapaFragment mapaFragment = new MapaFragment();

        FragmentTransaction tx = getSupportFragmentManager()
                                    .beginTransaction();
        tx.replace(R.id.mostra_alunos_mapa, mapaFragment);
        tx.commit();
    }
}
