package br.com.caelum.cadastro.converter;

import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5193 on 14/04/15.
 */
public class AlunoConverter {
    public String toJSON(List<Aluno> alunos){

        try {
            JSONStringer js = new JSONStringer();
            js.object().key("list").array().object().key("aluno").array();
            for (Aluno aluno : alunos){
                js.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("endereco").value(aluno.getEndereco())
                        .key("nota").value(aluno.getNota())
                        .key("site").value(aluno.getSite())
                        .key("telefone").value(aluno.getTelefone())
                .endObject();
            }
            js.endArray().endObject().endArray().endObject();
            return js.toString();
        }
        catch (Exception e){
            return "Error";
        }

    }
}
