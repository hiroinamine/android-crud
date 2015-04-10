package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;


public class FormularioActivity extends ActionBarActivity {

    public static final String ALUNO_SELECIONADO = "alunoSelecionado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        this.helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno alunoSelecionado = (Aluno) intent.getSerializableExtra(ALUNO_SELECIONADO);

        if (alunoSelecionado != null)
        {
            helper.colocaNoFormulario(alunoSelecionado);
        }

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
                    this.dao = new AlunoDAO(this);
                    this.dao.insereOuAltera(aluno);
                    this.dao.close();
                }
                else {
                    helper.mostraErro();
                }

                finish();
                return true;
            case R.id.menu_formulario_back:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private FormularioHelper helper;
    private AlunoDAO dao;
}
