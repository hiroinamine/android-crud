package br.com.caelum.cadastro;

import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by android5193 on 17/04/15.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks{

    private AtualizadorDeLocalizacao atualizador;

    public Configurador(AtualizadorDeLocalizacao atualizador){
        this.atualizador = atualizador;
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest configs = LocationRequest.create();
        configs.setInterval(10000);
        configs.setSmallestDisplacement(50); // dispara caso alguma condicao seja verdadeira
        configs.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        this.atualizador.inicia(configs);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
