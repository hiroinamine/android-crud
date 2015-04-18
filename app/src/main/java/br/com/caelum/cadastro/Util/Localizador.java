package br.com.caelum.cadastro.Util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.provider.Telephony;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by android5193 on 17/04/15.
 */
public class Localizador {

    private Geocoder geo;

    public Localizador(Context contexto){
        this.geo = new Geocoder(contexto);
    }

    public LatLng getCoordenada(String endereco){
        try {
            List<Address> ends = this.geo.getFromLocationName(endereco, 1);
            Address e = ends.get(0);
            return new LatLng(e.getLatitude(), e.getLongitude());
        } catch (Exception e){
            Log.i("Debug", e.toString());
            return  null;
        }
    }
}
