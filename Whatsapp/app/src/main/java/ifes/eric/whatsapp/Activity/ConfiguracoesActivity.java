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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.UserFacilities;
import ifes.eric.whatsapp.helper.Permissao;

public class ConfiguracoesActivity extends AppCompatActivity {
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private final String usuarioID64 = UserFacilities.UsuarioEmailB64();

    private ImageButton imageButtonCamera, imageButtonGaleria;
    private CircleImageView imagePerfil;
    private ImageView aplicarNomeUsuario;
    private EditText mudarNome;

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
        aplicarNomeUsuario = findViewById(R.id.configuracao_button_nome);
        mudarNome = findViewById(R.id.configuracao_edit_nome);

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


//   -----------------        Codigos que configuram o nome de usuario       -----------------------
        aplicarNomeUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Aponta o bandoDados para a referencia do Firebase
                DatabaseReference bandoDados = FirebaseDatabase.getInstance().getReference();

                // Cria uma referencia que aponta para "usuarios"."email(codificado)
                DatabaseReference usuarioDB = bandoDados.child("usuarios").child(usuarioID64);

                // Seta o nome do usuario no BD a partir do que foi colocado na caixa de texto
                usuarioDB.child("nome").setValue(mudarNome.getText().toString());
                Toast.makeText(ConfiguracoesActivity.this, "Usuario "
                        + mudarNome.getText().toString() + " alterado com sucesso!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
//   ***********************   Codigos que configuram o nome de usuario   **************************


//   --------------------  Codigos que aplica a foto de perfil na pagina  --------------------------
        // Cria um objeto Firebase User e aponta para o usuario atual
        FirebaseUser usuario = UserFacilities.UsuarioGetUser();

        // metodo para extrair a Url da foto.
        Uri url = usuario.getPhotoUrl();

        if (url != null){
            // Utiliza uma implementação para baixar a foto e aplicar a uma imagemview
            Glide.with(this)
                    .load(url)
                    .into(imagePerfil);
        }else{
            // Caso não tenha essa foto no Firebase, usa-se uma pré definida
            imagePerfil.setImageResource(R.drawable.padrao);
        }
//   **********************  Codigos que aplica a foto de perfil na pagina  ************************

        // Coloca na caixa de texto o nome de usuario baixado do Firebase
        mudarNome.setText(usuario.getDisplayName());


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
    // Acessa uma atividade iniciada e que gera algum resultado.
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
                        //aos conteudos.
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),localImagemSelecionada);
                        break;
                }
                if (imagem != null){

                    // Insere o bitmap Imagem no slot de perfil;
                   imagePerfil.setImageBitmap(imagem);


                   // Recuperar dados da imagem no FirebaseStorage
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);

                    // Transformou a imagem em um Array de bytes.
                    byte[] dadosImagem = baos.toByteArray();



                   // Referencia um objeto no Firebase Storage
                    StorageReference imageREF = storageReference
                            .child("imagens")
                            .child("perfil")
                            .child(usuarioID64)
                            .child("perfil.jpeg");

                    // Utiliza a referencia do objeto do firebase para fazer uma tarefa
                    UploadTask uploadTask = imageREF.putBytes(dadosImagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ConfiguracoesActivity.this,
                                    e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(ConfiguracoesActivity.this,
                                    "A foto de perfil de "
                                            + UserFacilities.decodificarString(usuarioID64)
                                            + " enviada com sucesso!", Toast.LENGTH_LONG).show();
                            // Usa o objeto que referencia um objeto no Firebase para extrair seu
                            // Url de download
                            imageREF.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    // Assim que a tarefa retorna com o pedido cria-se um Url
                                    Uri url = task.getResult();
                                    atualizarFotoUsuario( url );
                                }
                            });
                        }
                    });

                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
//   **************************   Metodo que recupera os dados salvos   ***************************



    public void atualizarFotoUsuario(Uri url){
        UserFacilities.atualizarFotoUsuario(url);

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


    // Metodo que acessa a camera
    private void tirarFoto() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Essa atividade pode gerar um resultado que sera analisado em onActivityResult
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    // Metodo que acessa a galeria
    private void pegarFoto() {

        Intent takeGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takeGalleryIntent.resolveActivity(getPackageManager()) != null) {
            // Essa atividade pode gerar um resultado que sera analisado em onActivityResult
            startActivityForResult(takeGalleryIntent, REQUEST_IMAGE_GALERRY);
        }

    }



}

