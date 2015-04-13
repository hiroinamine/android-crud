package br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5193 on 13/04/15.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    public ListaAlunosAdapter(Activity act, List<Aluno> alunos){
        this.activity = act;
        this.alunos = alunos;
    }

    private List<Aluno> alunos;
    private Activity activity;

    @Override
    public int getCount() {
        return this.alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = this.activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        TextView tvNome = (TextView) layout.findViewById(R.id.item_nome);
        ImageView ivFoto = (ImageView) layout.findViewById(R.id.item_foto);

        Aluno aluno = this.alunos.get(position);
        tvNome.setText(aluno.getNome());

        Bitmap bm;
        if (aluno.getCaminhoFoto() != null)
        {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        }
        else{
            bm = BitmapFactory.decodeResource(this.activity.getResources(), R.drawable.ic_no_image);
        }
        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        ivFoto.setImageBitmap(bm);

        if (position % 2 == 0){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        } else {
            layout.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }

        return layout;
    }
}
