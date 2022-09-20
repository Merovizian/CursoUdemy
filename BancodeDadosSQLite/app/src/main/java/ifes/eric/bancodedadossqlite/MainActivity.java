package ifes.eric.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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
            bancoDados.rawQuery("", null);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}