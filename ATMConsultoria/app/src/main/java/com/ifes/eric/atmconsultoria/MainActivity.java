package com.ifes.eric.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.ifes.eric.atmconsultoria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // METODO
                enviarEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servicos, R.id.nav_clientes, R.id.nav_contato, R.id.nav_sobre)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail(){
        // Exemplos de Intent
        //String celular = "tel: 27998404441";
        //String imagem = "https://www.viagenscinematograficas.com.br/wp-content/uploads/2014/08/Arraial-do-Cabo-RJ-Dicas-O-que-fazer-3.jpg";
        String endereco = "https://www.google.com.br/maps/place/20%C2%B039'59.3%22S+40%C2%B030'24.0%22W/@-20.66595,-40.5025375,15z/data=!4m6!3m5!1s0xb857b1972aff3d:0xb51a5e77956d929e!7e2!8m2!3d-20.666477!4d-40.5066642";

        //Intent intent = new Intent (Intent.ACTION_DIAL,Uri.parse(celular) );
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));



        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.putExtra( Intent.EXTRA_EMAIL, new String[]{"ericenois@hotmail.com","ericgmicaela@gmail.com"});
        intent.putExtra( Intent.EXTRA_SUBJECT, "Titulo do email");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem que será enviada por email");


        // São mime types
        // https://www.sitepoint.com/mime-types-complete-list/   onde tem uma lista deles.

        //intent.setType("application/pdf");


        intent.setType("message/rfc822");


        startActivity( Intent.createChooser(intent, "Escolha um APP de e-mail"));



    }





    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}