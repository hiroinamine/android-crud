package br.com.caelum.cadastro.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.cadastro.AtualizadorDeLocalizacao;
import br.com.caelum.cadastro.Util.Localizador;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5193 on 17/04/15.
 */
public class MapaFragment extends SupportMapFragment {

    // Nao eh necessario sobreescrever o metodo onCreate, pois a classe suportMap ja faz a criacao da view.


    @Override
    public void onResume() {
        super.onResume();

        AlunoDAO dao = new AlunoDAO(getActivity());
        List<Aluno> alunos = dao.getLista();
        dao.close();

        Localizador localizador = new Localizador(getActivity());

        for (Aluno aluno : alunos){
            MarkerOptions marcador = new MarkerOptions();
            marcador.position(localizador.getCoordenada(aluno.getEndereco()));
            marcador.title(aluno.getNome());

//            if (aluno.getCaminhoFoto() != null) {
//                Bitmap imagemFoto = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
//                Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
//
//                marcador.icon(BitmapDescriptorFactory.fromFile(aluno.getCaminhoFoto()));
//            }
            getMap().addMarker(marcador);
        }


        // Se auto localiza no mapa atraves das configuracoes de localizacao.
        new AtualizadorDeLocalizacao(getActivity(), this);

        LatLng local = localizador.getCoordenada("Rua Vergueiro, 3185 Vila Mariana");
        this.centralizaNo(local);
    }

    public void centralizaNo(LatLng local){
        GoogleMap mapa = getMap();
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(local, 11);
        mapa.moveCamera(camera);
    }
}
