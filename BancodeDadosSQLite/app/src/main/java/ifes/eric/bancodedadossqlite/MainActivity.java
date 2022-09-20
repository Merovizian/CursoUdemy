package ifes.eric.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Cria o banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("PrimeiroBD", MODE_PRIVATE, null);

            // Cria uma tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT(3))");

            // Inserir dados
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Eric Giobini Micaela', 33)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Tomaz Lurdes', 25)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Lucas da Silva', 15)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Enzo Ferrari', 09)");


            // Recuperar Dados
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null);


            // Indices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            // Iniciar o indice no inicio
            cursor.moveToFirst();
            // Exibir Dados
            while (cursor != null){
                Log.i("Resultado - nome", cursor.getString(indiceNome));
                Log.i("Resultado - idade", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}