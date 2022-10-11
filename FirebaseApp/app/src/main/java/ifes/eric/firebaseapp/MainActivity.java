package ifes.eric.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BANCO DE  DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // Atributo/CLASSE DatabaseReference
    // Método getInstance() - serve para recuperar instancia para salvar os bancos.
    // Método getReference() - serve para apontar para a raiz do banco de dados. Poderia ser:
    // getReference("usuario").
    private final DatabaseReference referencia  = FirebaseDatabase.getInstance().getReference();
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BANCO DE  DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ AUTENTIFICAÇÃO DE USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final FirebaseAuth usuarioAuth = FirebaseAuth.getInstance();

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ AUTENTIFICAÇÃO DE USUARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView texto = findViewById(R.id.text_hello);

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
        /*
        if (usuarioAuth.getCurrentUser() != null){
            FirebaseUser user = usuarioAuth.getCurrentUser();
            Toast.makeText(this, user.getEmail() + " LOGADO" , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "USUARIO NÃO LOGADO", Toast.LENGTH_LONG).show();
        }

         */
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
        DatabaseReference usuarios = referencia.child("Usuarios");

        Usuario usuario = new Usuario();
        usuario.setNome("Eric");
        usuario.setSobrenome("Giobni Micaela");
        usuario.setIdade(33);
        usuario.setTrabalha(false);
        // Cria um identificador unico
        usuarios.push().setValue(usuario);



//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ IDENTIFICADOR UNICO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~





//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RECUPERANDO DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RECUPERANDO DADOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




    }






}