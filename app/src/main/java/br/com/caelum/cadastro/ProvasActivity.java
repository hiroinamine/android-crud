package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.Fragment.ListaProvasFragment;

/**
 * Created by android5193 on 15/04/15.
 */
public class ProvasActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_provas);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        tx.replace(R.id.provas_view, new ListaProvasFragment());
        tx.commit();
    }
}
