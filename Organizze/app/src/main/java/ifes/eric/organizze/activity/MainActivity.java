package ifes.eric.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import ifes.eric.organizze.R;
import ifes.eric.organizze.config.ConfiguracaoFirebase;

public class MainActivity extends IntroActivity {

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  FIREBASEAUTH   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private FirebaseAuth auth;
//*****************************************  FIREBASEAUTH  *****************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verifica se usuário está logado.
        verificarUsuario();

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Slides      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        setButtonBackVisible(false);
        setButtonNextVisible(false);
//******************************************   Slides     ******************************************

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Slides Fragments     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_um)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_dois)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_tres)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_quatro)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build()
        );
//*************************************   Slides Fragments     *************************************


    }



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Verificar Usuario   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Método para verificar se há usuário logado
    public void verificarUsuario(){

        // Usa a classe criada para retornar a referencia.
        auth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        // verifica se o usuario está logado.
        if (auth.getCurrentUser() != null){
            Toast.makeText(this, "Logado como:\n" + auth.getCurrentUser().getEmail(),
                    Toast.LENGTH_SHORT).show();

            // caso o usuario esteja logado, vá até a tela principal.
            abrirTelaPrincipal();
        }

    }
//**************************************   Verificar Usuario  **************************************

    // Método do botão que abre a activity de login
    public void btEntrar(View view){
        startActivity(new Intent(this,loginActivity.class));
    }

    // Método do botão que abre a activity de cadastro
    public void cadastrar(View view){
        startActivity(new Intent(this, cadastroActivity.class));
    }

    // Metódo para deslogar um usuário logado.
    public void Logoff(){
        auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        auth.signOut();
    }

    // Metódo para abrir a tela principal.
    public void abrirTelaPrincipal(){
        startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
        finish();
    }


}
