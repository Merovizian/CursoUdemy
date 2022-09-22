package ifes.eric.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import ifes.eric.listadetarefas.activity.MainActivity;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 5;
    public static String NOME_BD = "DB_ListaTarefas";
    public static String TABELA_TAREFAS = "tarefa";

    public DbHelper(Context context) {
        super(context, NOME_BD, null, VERSION);
    }

    @Override
    // METODO QUE É CHAMADO A PRIMEIRA VEZ QUE O PROGRAMA É RODADO
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL)");

            Log.i("INFO DB", "TABELA CRIADA COM SUCESSO!");


        }catch (Exception e){
            Log.i("INFO DB", "Erro: "+e.getLocalizedMessage());
        }

    }

    @Override
    // TODA VEZ QUE O APP RODAR NOVAMENTE QUANDO MUDA-SE A VERSAO
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_TAREFAS );
            Log.i("INFO DB", "TABELA DESTRUIDA");

            onCreate(sqLiteDatabase);

        }catch (Exception e){
            Log.i("INFO DB", "Erro: "+e.getLocalizedMessage());

        }
    }
}
