package hobby.eric.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hobby.eric.whatsapp.Helper.ConfiguracaoFirebase;
import hobby.eric.whatsapp.R;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = ConfiguracaoFirebase.getFirebaseAutentificacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Codigo para a criação de uma variavel toolbar que evoca a toolbar de um layout
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("WhatsApp");
        // Serve para dar suporte a versões anteriores
        setSupportActionBar(toolbar);

    }

    // Cria o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        // Aloca no inflate o layout menu
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }


    // Retorna os itens selecionados
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Ao clicar em um item do menu a variavel item retorna as opções, podendo ser o id desse item. Utiliza-se o
        // case para relacionar o item clicado com a função desejada por ele.
        switch (item.getItemId()) {
            case R.id.item_menuPrincipal_configuracao:
                Toast.makeText(this, "FUNCOES RELACIONADAS AO MENU CONFIGURACOES", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_menuPrincipal_sair:
                deslogarUsuario();
                finish();
                break;

            case R.id.item_menuPrincipal_pesquisa:
                Toast.makeText(this, "FUNCOES DE PESQUISA", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario() {

        try {
            firebaseAuth.signOut();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStart() {
        Log.d("ERICTAG", "ON START NO MAINACTIVITY");
        super.onStart();
    }
}



/*
        Log.e("ERICTAG","ESSE É O LOG VERMELHO - ERROR");
                Log.d("ERICTAG","ESSE É O LOG VERDE - TESTE LOCAL");
                Log.i("ERICTAG","ESSE É O LOG AZUL - TESTE CONDICIONAIS ");
                Log.v("ERICTAG","ESSE É O LOG BRANCO  - TESTE VARIAVEIS");


 */