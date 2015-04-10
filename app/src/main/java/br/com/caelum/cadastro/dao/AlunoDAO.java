package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5193 on 09/04/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";

    public AlunoDAO(Context context){
        super(context, DATABASE, null, VERSAO);
    }

    public void onCreate(SQLiteDatabase database){
        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "  // QUANDO DECLARA UMA COLUNA DO TIPO "INTEGER PRIMARY KEY", ELE VAI SER UM ALIAS ROWID. SE NAO QUISER UTILIZAR O INT AO INVES DO INTEGER.
                + " nome TEXT NOT NULL, "
                + " telefone TEXT, "
                + " endereco TEXT, "
                + " site TEXT, "
                + " nota REAL);";
        database.execSQL(ddl);
    }

    public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova) {
        String sql = " DROP TABLE IF EXISTS " + TABELA;
        database.execSQL(sql);
        onCreate(database);
    }

    public List<Aluno> getLista(){

        List<Aluno> alunos = new ArrayList<Aluno>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void deletar(Aluno aluno){
        SQLiteDatabase database = getWritableDatabase();
        String[] args = { aluno.getId().toString()};
        database.delete("Alunos", "id=?", args);
    }

    public void insereOuAltera(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("site", aluno.getSite());
        values.put("telefone", aluno.getTelefone());
        values.put("nota", aluno.getNota());
        values.put("endereco", aluno.getEndereco());

        if (aluno.getId() == null) {
            long row_id = getWritableDatabase().insert(TABELA, null, values);
        }
        else {
            String[] args = {aluno.getId().toString()};
            long row_id = getWritableDatabase().update(TABELA, values, "id=?", args);
        }
    }
}
