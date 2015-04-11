package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;


public class FormularioActivity extends ActionBarActivity {

    public static final String ALUNO_SELECIONADO = "alunoSelecionado";
    public static final int TIRA_FOTO = 100;

    private FormularioHelper helper;
    private AlunoDAO dao;
    private String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        this.helper = new FormularioHelper(this);

        Button tiraFoto = helper.getFotoButton();
        tiraFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CUIDADO: verificar se tem sd card, esta montado, tem espaco ....
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivo = new File(localArquivoFoto);
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivo));
                startActivityForResult(intentCamera, TIRA_FOTO);
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TIRA_FOTO){
            if (resultCode == Activity.RESULT_OK){
                helper.carregaImagem(this.localArquivoFoto);
            }
            else
            {
                this.localArquivoFoto = null;
            }
        }
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


}
