package ifes.eric.whatsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import ifes.eric.whatsapp.Fragmants.ContatosFragments;
import ifes.eric.whatsapp.Fragmants.ConversasFragment;
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


//   ---------------------------  Codigos para a criação de abas   --------------------------------
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.titleA, ConversasFragment.class)
                .add(R.string.titleB, ContatosFragments.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
//   **************************    Codigos para a criação de abas    *******************************


//   --------------------------- Metodo que cria o menu na Toolbar --------------------------------
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

            case R.id.menu_Configuracao:
                configuracoesUsuario();
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

    // Metodo para jogar o usuario no activity de configurações
    public void configuracoesUsuario(){

        startActivity(new Intent(getApplicationContext(), ConfiguracoesActivity.class));

    }


}

//   --------------------------- Metodo que retorna a receitaTotal  --------------------------------

//   **************************  Metodo que retorna a receitaTotal   *******************************