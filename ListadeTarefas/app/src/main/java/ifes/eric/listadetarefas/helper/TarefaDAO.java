package ifes.eric.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import ifes.eric.listadetarefas.model.Tarefa;

public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context){
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNomeTarefa());
        cv.put("status","E");


        try {
            escreve.insert(DbHelper.TABELA_TAREFAS,null, cv);
            Log.i("INFODB", "Tarefa Salva com Sucesso!");

        }catch (Exception e){
            Log.i("INFODB", "Erro ao Salvar Tarefa!" + e.getLocalizedMessage());
            return false;
        }





        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {
        return null;
    }
}
