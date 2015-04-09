package br.com.caelum.cadastro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                + " (id INTEGER PRIMARY KEY, "
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
}
