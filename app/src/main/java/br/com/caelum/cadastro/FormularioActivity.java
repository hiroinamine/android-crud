package br.com.caelum.cadastro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;


public class FormularioActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        this.helper = new FormularioHelper(this);
        this.dao = new AlunoDAO(this);

        Button btnSalvar = (Button) findViewById(R.id.formulario_botao);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnVoltar = (Button) findViewById(R.id.formulario_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setAlpha(0);
                //finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAlunoDoFormulario();

                if (helper.temNome()){
                    dao.insere(aluno);
                }
                else {
                    helper.mostraErro();
                }

                Log.i("degug", aluno.getNome() );

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private FormularioHelper helper;
    private AlunoDAO dao;
}
