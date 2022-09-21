package ifes.eric.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textozinho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textozinho = findViewById(R.id.text_inout);

        try {
            // Cria o banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("PrimeiroBD", MODE_PRIVATE,
                    null);

            // Cria uma tabela
            bancoDados.execSQL("DROP TABLE IF EXISTS pessoas");
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT(3))");


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ POPULAÇÃO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Inserir dados | CRIADOR DE BANCO DE DADOS EXCEL
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES" +
                    "   (   'Eric Giobini Micaela'  ,   33  )   ,   "   +
                    "	(	'Bianca Nogueira	'	,	58	)	,	"	+
                    "	(	'Daniela Barbosa	'	,	38	)	,	"	+
                    "	(	'Lavínia Aragão	'	,	16	)	,	"	+
                    "	(	'Yago da Cruz	'	,	62	)	,	"	+
                    "	(	'Vitor Gabriel Barros	'	,	22	)	,	"	+
                    "	(	'Bernardo da Conceição	'	,	50	)	,	"	+
                    "	(	'João Felipe Barros	'	,	53	)	,	"	+
                    "	(	'Sr. Kaique da Cunha	'	,	60	)	,	"	+
                    "	(	'Ana Clara da Luz	'	,	10	)	,	"	+
                    "	(	'Isadora da Paz	'	,	19	)	,	"	+
                    "	(	'João Guilherme Ferreira	'	,	6	)	,	"	+
                    "	(	'Luana Duarte	'	,	33	)	,	"	+
                    "	(	'Isaac Carvalho	'	,	25	)	,	"	+
                    "	(	'Pedro Miguel Alves	'	,	24	)	,	"	+
                    "	(	'Vicente Silveira	'	,	34	)	,	"	+
                    "	(	'João Felipe Rocha	'	,	45	)	,	"	+
                    "	(	'Ana Beatriz Fogaça	'	,	35	)	,	"	+
                    "	(	'Vitor Gabriel Silveira	'	,	16	)	,	"	+
                    "	(	'Dr. Antônio Ribeiro	'	,	36	)	,	"	+
                    "	(	'Mariane da Mata	'	,	10	)	,	"	+
                    "	(	'Paulo Cardoso	'	,	62	)	,	"	+
                    "	(	'Emanuella Rodrigues	'	,	42	)	,	"	+
                    "	(	'Sra. Evelyn Moraes	'	,	63	)	,	"	+
                    "	(	'Enzo Moraes	'	,	60	)	,	"	+
                    "	(	'João Pedro Duarte	'	,	12	)	,	"	+
                    "	(	'Maysa Gonçalves	'	,	8	)	,	"	+
                    "	(	'Kaique Barbosa	'	,	83	)	,	"	+
                    "	(	'João Guilherme Nunes	'	,	63	)	,	"	+
                    "	(	'Juan Araújo	'	,	45	)	,	"	+
                    "	(	'Lorenzo da Luz	'	,	48	)	,	"	+
                    "	(	'Dra. Natália Oliveira	'	,	37	)	,	"	+
                    "	(	'Alícia Cavalcanti	'	,	82	)	,	"	+
                    "	(	'Olivia da Cunha	'	,	50	)	,	"	+
                    "	(	'Stella Pereira	'	,	10	)	,	"	+
                    "	(	'Maitê Caldeira	'	,	25	)	,	"	+
                    "	(	'Ana Sophia Caldeira	'	,	29	)	,	"	+
                    "	(	'Luiza Santos	'	,	65	)	,	"	+
                    "	(	'Raul da Rosa	'	,	50	)	,	"	+
                    "	(	'Dra. Larissa Vieira	'	,	52	)	,	"	+
                    "	(	'Henrique Jesus	'	,	8	)	,	"	+
                    "	(	'Erick Cardoso	'	,	63	)	,	"	+
                    "	(	'Sr. Nathan da Mata	'	,	80	)	,	"	+
                    "	(	'Sra. Maria Sophia da Cruz	'	,	59	)	,	"	+
                    "	(	'Paulo Mendes	'	,	78	)	,	"	+
                    "	(	'Olivia Costa	'	,	10	)	,	"	+
                    "	(	'Gustavo Henrique Gomes	'	,	52	)	,	"	+
                    "	(	'Gustavo Henrique Lima	'	,	48	)	,	"	+
                    "	(	'Levi Azevedo	'	,	42	)	,	"	+
                    "	(	'Sr. Davi Luiz Ribeiro	'	,	72	)	,	"	+
                    "	(	'Igor Barros	'	,	71	)");


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ EXEMPLOS DE CONSULTAS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            // Recuperar Dados | EXEMPLOS DE Consultas
            /*
            String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " WHERE idade < 18 OR idade > 65";*/

            /*
            String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " WHERE idade in(19,24,07,25)";*/

            /*
            String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " WHERE idade BETWEEN 0 and 99";*/


            // LIKE - QUALUQER COISA A DIREITA DO % OU A ESQUERDA
            // UTILIZAÇÂO DE CONCATENAÇÃO "+"

            /*String filtro = "Eri";
            String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " WHERE idade >= 18 AND nome LIKE '%"+filtro+"%'";*/

            // ORDENAÇÃO - SEM NADA, ASC, DESC
            /*String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " ORDER BY idade DESC";*/

            /*String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " ORDER BY nome";*/
            // LIMITE
            /*String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " LIMIT 3";*/

            // TODOS OS MANIPULADORES
            String consulta = "SELECT nome, idade" +
                    " FROM pessoas" +
                    " WHERE idade BETWEEN 18 and 65" +
                    " ORDER BY idade" +
                    " LIMIT 5";

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


            Cursor cursor = bancoDados.rawQuery(consulta, null);


            // Indices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            // Iniciar o indice no inicio
            cursor.moveToFirst();
            // Exibir Dados
            while (cursor != null){
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                textozinho.setText(nome + "," + idade);

                Log.i("Resultado", ">> nome: " + nome +", idade: "+ idade);
                cursor.moveToNext();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}