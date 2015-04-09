package br.com.caelum.cadastro;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5193 on 08/04/15.
 */
public class FormularioHelper {

    public FormularioHelper(FormularioActivity activity){
        this.aluno = new Aluno();

        this.nome = (EditText) activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.site = (EditText) activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        this.endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
    }

    public Aluno pegaAlunoDoFormulario(){
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setEndereco(endereco.getText().toString());
        return this.aluno;
    }

    private Aluno aluno;

    private EditText nome;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private EditText endereco;
}
