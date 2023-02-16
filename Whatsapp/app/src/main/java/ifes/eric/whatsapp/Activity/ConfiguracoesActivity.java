package ifes.eric.whatsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.Objects;
import java.util.zip.Inflater;

import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.Permissao;

public class ConfiguracoesActivity extends AppCompatActivity {


    //   ---------------------------  Lista de Permissoes solicitadas   --------------------------------
    private String[] permissoesNecessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
//   ****************************  Lista de Permissoes solicitadas   *******************************

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
//   *****************************  Codigos que configuram a toolbar  *******************************

        // Valida as permissões
        Permissao.validarPermissoes(permissoesNecessarias, this, 1);

    }

    //   ---------------------  Metodo que aponta os resultados das permissoes  ------------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // laço que percorre as permissoes requeridas
        for (int permissaoResultado : grantResults) {

            // Condição para verificar se há alguma que não foi aceita, se não houver, AlertDialog
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao(permissions[0]);
            }
        }
    }
//   **********************  Metodo que aponta os resultados das permissoes  ***********************

    // Metodo que cria um AlertDialog
    public void alertaValidacaoPermissao(String resultado) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissoes Negadas");
        builder.setCancelable(false);
        builder.setMessage("Por favor, autorize a permissão " + resultado);
        builder.setPositiveButton("Entendo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Fecha a Activity, pois o usuario precisa aceitar as permissões
                finish();
            }
        });
        builder.create();
        builder.show();
    }


}

