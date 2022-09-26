package ifes.eric.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 2;
    public static String NOME_DB = "DB_Tarefas";
    public static String TABELA_TAREFAS = "tarefas";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }


    // Metodo de criação de tabelas, ele roda apenas uma vez.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT) ";


        try {

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                    + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL)");

            Log.i("INFODB", "Sucesso ao criar a tabela " + TABELA_TAREFAS + "!");

        } catch (Exception e) {
            Log.i("INFODB", "ERRO AO CRIAR TABELA " + e.getMessage());
        }


    }

    // Metodo para atualização, modificação de tabelas, ele roda uma vez a cada nova versão
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        try {

            db.execSQL("DROP TABLE IF EXISTS " + TABELA_TAREFAS );
            Log.i("INFODB", "Sucesso ao Deletar a tabela " + TABELA_TAREFAS + "!");

            onCreate(db);


        } catch (Exception e) {
            Log.i("INFODB", "ERRO ao Deletar TABELA " + e.getMessage());
        }


        Log.i("INFODB", "VERSAO ATUAL: " + i1 + " VERSÂO ANTERIOR: " + i);

    }
}
