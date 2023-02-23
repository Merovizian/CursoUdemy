package ifes.eric.whatsapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Objects;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.Permissao;

public class ConfiguracoesActivity extends AppCompatActivity {
    private ImageButton imageButtonCamera, imageButtonGaleria;
    private CircleImageView imagePerfil;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALERRY = 2;


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

//   -----------------  Codigos que configuram os botoes de photo e galeria  -----------------------

        imageButtonGaleria = findViewById(R.id.config_imageButton_gallery);
        imageButtonCamera = findViewById(R.id. config_imageButton_photo);
        imagePerfil = findViewById(R.id.configuracoes_circle_perfil);


        // Evento de clique do botão, poderia ser iniciado como um metodo também
        imageButtonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pegarFoto();

            }
        });

        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tirarFoto();

            }
        });

//   *****************  Codigos que configuram os botoes de photo e galeria   **********************

//   ---------------------------  Codigos que configuram a toolbar  --------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Configurações");
        setSupportActionBar(toolbar);
        // Cria o botão de retorno à activity "Pai"
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//   *****************************  Codigos que configuram a toolbar  ******************************

        // Valida as permissões
        Permissao.validarPermissoes(permissoesNecessarias, this, 1);
    }

//   --------------------------  Metodo que recupera os dados salvos  -----------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // caso o resultado da solicitação tenha dado certo.
        if (resultCode == RESULT_OK){
            //cria-se um objeto Bitmap
            Bitmap imagem = null;

            try {

                switch (requestCode) {
                    case REQUEST_IMAGE_CAPTURE:
                        // O objeto Bitmap é direcionado ao que foi adquirido pelo data
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
                    case REQUEST_IMAGE_GALERRY:
                        // objeto que salva a URI da data
                        Uri localImagemSelecionada = data.getData();
                        // Objeto imagem busca esse data, na URI selecionada, content provem acesso
                        // aos conteudos.
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),localImagemSelecionada);
                        break;
                }
                if (imagem != null){
                    // Insere o bitmap Imagem no slot de perfil;
                   imagePerfil.setImageBitmap(imagem);

                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
//   **************************   Metodo que recupera os dados salvos   ***************************


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


    // Metodo que acessa a camera
    private void tirarFoto() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    // Metodo que acessa a galeria
    private void pegarFoto() {

        Intent takeGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takeGalleryIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeGalleryIntent, REQUEST_IMAGE_GALERRY);
        }

    }



}

