package br.com.caelum.cadastro.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.WebClient;

/**
 * Created by android5193 on 14/04/15.
 */
public class EnviaContatosTask extends AsyncTask<Object, Object, String> {

    private Context context;
    private ProgressDialog progress;

    public EnviaContatosTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(this.context);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        String json = new AlunoConverter().toJSON(alunos);
        String media = new WebClient().post(json);

        return media;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progress = ProgressDialog.show(context, "Aguarde ...", "Envio de dados para a web", true, true);
        // ProgressDialog ...
    }

    @Override
    protected void onPostExecute(String media) {
        super.onPostExecute(media);
        Toast.makeText(this.context, "media:" + media, Toast.LENGTH_SHORT).show();
        this.progress.dismiss();
    }

}
