package ifes.eric.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BANCO DE  DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // Atributo/CLASSE DatabaseReference
    // Método getInstance() - serve para recuperar instancia para salvar os bancos.
    // Método getReference() - serve para apontar para a raiz do banco de dados. Poderia ser:
    // getReference("usuario").
    //private final DatabaseReference referencia  = FirebaseDatabase.getInstance().getReference();
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BANCO DE  DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ AUTENTIFICAÇÃO DE USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final FirebaseAuth usuarioAuth = FirebaseAuth.getInstance();

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ AUTENTIFICAÇÃO DE USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  UPLOAD DE IMAGENS  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private ImageView imageFoto;
    private Button botaoUpload;

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  UPLOAD DE IMAGENS  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView texto = findViewById(R.id.text_hello);



//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  UPLOAD DE IMAGENS  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        botaoUpload = findViewById(R.id.button_uploadImagem);
        imageFoto = findViewById(R.id.imageView_Foto);

        botaoUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Configuração para imagem ser salva em memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                // Recuperar um bitmap da imagem (imagem a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                // Comprimir o bitmap para o formato pnj/jpeg
                ByteArrayOutputStream  baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);

                //Converte o baos para pixel brutos em uma matriz de bytes
                //(dados da imagem))
                byte[] dadosImagem = baos.toByteArray();

                // Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("Imagens");
                StorageReference imagemRef = imagens.child("celular2.jpeg");

                // Retorna objeto que irá controlar o Upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Upload da imagem "+
                                imagemRef.toString() + " Falhou" + e.getMessage().toString(),
                                Toast.LENGTH_LONG  ).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,"Upload da imagem "+
                                                imagemRef.getName() + " Sucesso!",
                                        Toast.LENGTH_LONG  ).show();
                            }
                        });

                    }
                });
            }
        });
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  UPLOAD DE IMAGENS  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



        // DESLOGAR USUARIO
        //usuarioAuth.signOut();


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ AUTENTIFICACAO  DE  USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
       /*
       usuarioAuth.signInWithEmailAndPassword(

                "ericgmicaela@gmail.com",
                "eric12345"
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    // CLASSE PARA OBTER O USUARIO
                    FirebaseUser userAuth = usuarioAuth.getCurrentUser();
                    texto.setText("Sucesso ao logar " + userAuth.getEmail());
                }else{
                    texto.setText("Erro ao logar");
                }
            }
        });
        */
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ AUTENTIFICACAO  DE  USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Verifica se o Usuario está logado ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        if (usuarioAuth.getCurrentUser() != null){
            FirebaseUser user = usuarioAuth.getCurrentUser();
            Toast.makeText(this, user.getEmail() + " LOGADO" , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "USUARIO NÃO LOGADO", Toast.LENGTH_LONG).show();
        }


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Verifica se o Usuario está logado ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CADASTRO DE  USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/*
        usuarioAuth.createUserWithEmailAndPassword(
                "hulk2022@gmail.com",
                "eric12345")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    texto.setText("Sucesso ao cadastar usuario");
                }else{
                    texto.setText("Erro ao cadastar usuario");
                }
            }
        });
*/
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CADASTRO DE  USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SALVANDO DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/*
        // CRIANDO UM BANCO DE DADOS
        referencia.child("Faculdade").child("001").child("Instituição")
                .setValue("IFES",1);
        referencia.child("Faculdade").child("001").child("Local")
                .setValue("Guarapari",2);
        referencia.child("Faculdade").child("001").child("Tipo")
                .setValue("Superior",3);
        referencia.child("Faculdade").child("001").child("Ano").setValue(2018,4);
        referencia.child("Faculdade").child("001").child("Nota MEC").setValue(4,5);

        referencia.child("Faculdade").child("002").child("Instituição")
                .setValue("Univem",1);
        referencia.child("Faculdade").child("002").child("Local")
                .setValue("Nova Venecia",2);
        referencia.child("Faculdade").child("002").child("Tipo")
                .setValue("Superior",3);
        referencia.child("Faculdade").child("002").child("Ano").setValue(1998,4);
        referencia.child("Faculdade").child("002").child("Nota MEC").setValue(4.5,5);

    /*
        try {
            Thread.sleep(4000);
            referencia.removeValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

/*
        // OUTRO JEITO DE POPULAR BD
        DatabaseReference usuarios = referencia.child("Usuarios");
        usuarios.child("001").child("Nome").setValue("Eric Giobini Micaela",1);
        usuarios.child("001").child("Idade").setValue(33,2);
        usuarios.child("001").child("Nacionalidade").setValue("Brasil",4);
        usuarios.child("001").child("Trabalha?").setValue(true,3);

        usuarios.child("002").child("Nome").setValue("Ana Carolina Micaela",1);
        usuarios.child("002").child("Idade").setValue(23,2);
        usuarios.child("002").child("Nacionalidade").setValue("Brasil",4);
        usuarios.child("002").child("Trabalha?").setValue(false,3);

        // Utilizando uma clase como metodo
        Usuario usuario = new Usuario();
        usuario.setNome("Eric");
        usuario.setSobrenome("Lucifer");
        usuario.setIdade(33);
        usuario.setTrabalha(true);

        usuarios.child("003").child("Nome").setValue(usuario.getNome(),1);
        usuarios.child("003").child("Idade").setValue(usuario.getIdade(),3);
        usuarios.child("003").child("Sobrenome").setValue(usuario.getSobrenome(),2);
        usuarios.child("003").child("Trabalha").setValue(usuario.isTrabalha(),99);


        usuarios.child("33").setValue(usuario);


        //TENTATIVA GERAR BANCO
        // INSTANCIAR UM METODO

        DatabaseReference produtos = referencia.child("Produtos");

        Produto produto1 = new Produto();
        produto1.setCodigo("001");
        produto1.setNome("Note 8 Pro");
        produto1.setMarca("Xiaomi");
        produto1.setValor(2000);
        produto1.setDescricao("Celular");

        produtos.child(produto1.getCodigo()).setValue(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("Iphone X");
        produto2.setMarca("APPLE");
        produto2.setValor(7000);
        produto2.setDescricao("Celular");

        produtos.child("002").setValue(produto2);
        */
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SALVANDO DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ IDENTIFICADOR UNICO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        /*
        DatabaseReference usuarios = referencia.child("Usuarios");
        // Metodo de identificador unico utilizando classe
        Usuario usuario = new Usuario();
        usuario.setNome("Roberto");
        usuario.setSobrenome("Carlos da Silva");
        usuario.setIdade(25);
        usuario.setTrabalha(true);
        // Cria um identificador unico
        usuarios.push().setValue(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setNome("Ronaldinho");
        usuario1.setSobrenome("Gaucho");
        usuario1.setIdade(36);
        usuario1.setTrabalha(true);
        // Cria um identificador unico
        usuarios.push().setValue(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Pablo Escobar");
        usuario2.setSobrenome("Dono da Jamaica");
        usuario2.setIdade(45);
        usuario2.setTrabalha(false);
        // Cria um identificador unico
        usuarios.push().setValue(usuario2);
        */

        /*
        DatabaseReference produtos = referencia.child("Produtos");
        // Metodo de identificador unico utilizando classe
        Produto produto1 = new Produto();
        produto1.setNome("Note 8 Pro");
        produto1.setMarca("Xiaomi");
        produto1.setValor(2000);
        produto1.setDescricao("Celular");
        // Cria um identificador unico
        produtos.push().setValue(produto1);

        // Metodo de identificador unico sem utilizar classe
        DatabaseReference faculdades = referencia.child("Faculdade").push();
        faculdades.child("Nome").setValue("Ifes",2);
        faculdades.child("Local").setValue("Guarapari",3);
        faculdades.child("Tipo").setValue("Superior",5);
        faculdades.child("Nota MEC").setValue(5,4);
        // Cria um identificador unico
         */

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ IDENTIFICADOR UNICO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RECUPERANDO DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        /*
        //DatabaseReference usuarios = referencia.child("Usuarios");

        // OBJETO - METODO "addvalue"
        // Instanciar uma classe nova "ValueEvenListener()"
        // Listener funciona só quando é acionado, e somente do nó que é acionado
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            // RETORNA AS MUDANÇAS EM TEMPO REAL DO DATABASE
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("ERICBASE", Objects.requireNonNull(snapshot.child("001").child("Nome").
                        getValue()).toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RECUPERANDO DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ APLICANDO FILTROS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        /*
        DatabaseReference usuarios = referencia.child("Usuarios");

        // METODO 1
        //DatabaseReference usuarioPesquisa = usuarios.child("-NE732Z8BF_RrNGHOGY4");

        // METODO 2A
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Eric");


        // METODO 2B ">= e <="

        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(35).endAt(60);

        // METODO 3
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(3);

        // METOROD 3B
        //Query usuarioPesquisa = usuarios.orderByKey().limitToLast(3);

        // METODO 4
        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("A").endAt("E" + "\uf8ff");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                /*  METODO 1
                // SEM USAR CLASSE
                texto.setText(snapshot.child("nome").getValue().toString() + " "
                        + snapshot.child("sobrenome").getValue().toString() + " ,"
                + " Idade: " + snapshot.child("idade").getValue().toString());

                 */
                /*
                // USANDO CLASSE
                String ehTrabalhador;
                Usuario dadospesquisador = snapshot.getValue(Usuario.class);
                if (dadospesquisador.isTrabalha()){
                    ehTrabalhador = "Trabalha";
                }else ehTrabalhador = "Não Trabalha";
                texto.setText("Nome: " + dadospesquisador.getNome() +
                        "\nIdade: " + dadospesquisador.getIdade() +
                        "\n" + ehTrabalhador);

                 */
        /*


                Log.i("ERICBD", snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    */


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ APLICANDO FILTROS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    }

}