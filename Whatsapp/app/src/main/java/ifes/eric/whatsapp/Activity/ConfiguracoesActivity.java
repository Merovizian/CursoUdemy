package ifes.eric.whatsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.Objects;
import java.util.zip.Inflater;

import ifes.eric.whatsapp.R;

public class ConfiguracoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

//   ---------------------------  Codigos que configuram a toolbar  --------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Configurações");
        setSupportActionBar(toolbar);
        // Cria o botão de retorno à activity "Pai"
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
//   **************************  Codigos que configuram a toolbar   *******************************

}

