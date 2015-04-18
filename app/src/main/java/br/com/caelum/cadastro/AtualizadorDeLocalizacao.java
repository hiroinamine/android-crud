package br.com.caelum.cadastro;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import br.com.caelum.cadastro.Fragment.MapaFragment;

/**
 * Created by android5193 on 17/04/15.
 */
public class AtualizadorDeLocalizacao implements LocationListener {
    //GeoFence

    private GoogleApiClient client;
    private MapaFragment mapaFragment;

    public AtualizadorDeLocalizacao(Context context, MapaFragment mapaFragment){

        this.mapaFragment = mapaFragment;

        Configurador configs = new Configurador(this);

        this.client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(configs)
                .build();

        this.client.connect();
    }

    public void inicia(LocationRequest configs){
        LocationServices.FusedLocationApi.requestLocationUpdates(this.client, configs, this);
    }

    // Metodos da interface LocationListener
    @Override
    public void onLocationChanged(Location location) {
        this.mapaFragment.centralizaNo(new LatLng(location.getLatitude(), location.getLongitude()));
    }
}
