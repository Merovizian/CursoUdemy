package ifes.eric.whatsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.Model.Validacao;
import ifes.eric.whatsapp.R;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar_principal);


        toolbar.setTitle("WhatsAPP");
        setSupportActionBar(toolbar);
    }


    //   --------------------------- Metodo que cria o meuno na Toolbar --------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

//   **************************  Metodo que cria o meuno na Toolbar   *******************************


//   ---------------- Metodo que recupera os itens do menu que foram selecionados ------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sair:
                deslogarUsuario();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

//   ************    Metodo que recupera os itens do menu que foram selecionados    ****************


//   ---------------------------   Metodo que desloga o usuario    --------------------------------

    public void deslogarUsuario (){
        auth.signOut();
        Toast.makeText(this, "Usuario Saiu", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

//   **************************   Metodo que desloga o usuario      *******************************

}

//   --------------------------- Metodo que retorna a receitaTotal  --------------------------------

//   **************************  Metodo que retorna a receitaTotal   *******************************