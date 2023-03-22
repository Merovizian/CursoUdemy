package ifes.eric.whatsapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.Adapter.MensagensAdapter;
import ifes.eric.whatsapp.Model.Conversa;
import ifes.eric.whatsapp.Model.Mensagem;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.UserFacilities;

public class ChatActivity extends AppCompatActivity {
    public TextView nomeDestinatario;
    public CircleImageView fotoDestinatario;
    private Usuario destinatario;
    private ImageView imagemCamera;

    public EditText mensagemDestinatario;
    public String Remetente = UserFacilities.UsuarioEmailB64();
    public String Destinatario ;

    private FirebaseAuth auth;

    private RecyclerView recyclerMensagens;
    private MensagensAdapter mensagensAdapter;
    private List<Mensagem> listaMensagens = new ArrayList<>();

    private DatabaseReference databaseReference;
    private DatabaseReference mensagensRef;

    private ChildEventListener childEventListenerMensagens;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALERRY = 2;

    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mensagemDestinatario = findViewById(R.id.editText_contentChat_mensagens);
        imagemCamera = findViewById(R.id.imageView_contentChat_envioCamera);


//   -------------------------------     Codigos para a criação do recycler view     -----------------------------------
        mensagensAdapter = new MensagensAdapter(listaMensagens, getApplicationContext());
        recyclerMensagens = findViewById(R.id.recycler_contentChat_rolodeConversas);


        // Configuração RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMensagens.setLayoutManager(layoutManager);
        recyclerMensagens.setHasFixedSize(true);
        recyclerMensagens.setAdapter(mensagensAdapter);

//   *******************************      Codigos para a criação do recycler view     **********************************






//   ---------------------------  Codigos para apresentar a foto e o nome de usuario    --------------------------------
        nomeDestinatario = findViewById(R.id.textview_chat2_NomeUser);
        fotoDestinatario = findViewById(R.id.imageView_chat2_fotoDestinatario);



        // Recuperar os dados enviados pela fragment contatos
        Bundle bundle = getIntent().getExtras();
        if (bundle != null ){
            destinatario = (Usuario) bundle.getSerializable("chatContato");
            Destinatario = UserFacilities.codificarString(destinatario.getEmail());
            nomeDestinatario.setText(destinatario.getNome());
            String foto = destinatario.getFoto();
            if (foto != null){
                Uri url = Uri.parse(destinatario.getFoto());

                Glide.with(ChatActivity.this)
                        .load(url)
                        .into(fotoDestinatario);
            }else
                fotoDestinatario.setImageResource(R.drawable.padrao);
        }
//   **************************   Codigos para apresentar a foto e o nome de usuario     *******************************


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mensagensRef = databaseReference.child("mensagens")
                .child(Remetente)
                .child(Destinatario);

        // Evento de clique na camera da conversa
        imagemCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Essa atividade pode gerar um resultado que sera analisado em onActivityResult
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            //cria-se um objeto Bitmap
            Bitmap imagem = null;

            try {

                switch (requestCode) {
                    case REQUEST_IMAGE_CAPTURE:
                        // O objeto Bitmap é direcionado ao que foi adquirido pelo data
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
                }
                if (imagem != null){

                    // Recuperar dados da imagem para o firebase
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG,100, baos);
                    byte[] dadosImagem = baos.toByteArray();

                    // Criar nome da imagem
                    String nomeImagem = UUID.randomUUID().toString();

                    // Configurar as referencias do firebase.
                    StorageReference imagemREF = storageReference
                            .child("imagens")
                            .child("fotos")
                            .child(Remetente)
                            .child(nomeImagem);

                    UploadTask uploadTask = imagemREF.putBytes(dadosImagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChatActivity.this, "Midia não enviada", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imagemREF.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    Uri urlImagem = task.getResult();

                                    Mensagem mensagem = new Mensagem();
                                    mensagem.setIdUsuario(Remetente);
                                    mensagem.setMensagem("imagem.jpeg");
                                    mensagem.setImagem(urlImagem.toString());

                                    // Mensagem de imagem salva no remetente.
                                    salvarMensagem(Remetente, Destinatario, mensagem);

                                    // Mensagem de imagem salva no destinatário
                                    salvarMensagem(Destinatario, Remetente, mensagem);


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



    public void enviarMensagem(View view){
        auth = FirebaseAuth.getInstance();

        String textoMensagem = mensagemDestinatario.getText().toString();

        if (!textoMensagem.isEmpty()){

            Mensagem mensagem = new Mensagem();
            mensagem.setIdUsuario(Remetente);
            mensagem.setMensagem(textoMensagem);
            mensagem.setIdDestinatario(Destinatario);

            // Envio da mensagem para o firebase a partir do User1 (User1 = remetente)
            salvarMensagem(Remetente, Destinatario, mensagem);

            // Envio da mesma mensagem acima para o firebase, mas para o User2 (User2 = destinatário)
            salvarMensagem(Destinatario, Remetente, mensagem);

            // Salvar Conversa
            salvarConversa(mensagem);



        }else{

            Toast.makeText(this, "Digite a mensagem...", Toast.LENGTH_SHORT).show();
            
        }





    }


    private void salvarConversa(Mensagem msg) {

        Conversa conversaRemetente = new Conversa();
        conversaRemetente.setIdRemetente(Remetente);
        conversaRemetente.setIdDestinatario(Destinatario);
        conversaRemetente.setUltimaMensagem(msg.getMensagem());
        conversaRemetente.setUsuarioExibicao(destinatario);
        conversaRemetente.salvar();


    }

    public void salvarMensagem(String idRemetente, String idDestinatario, Mensagem msg){
        databaseReference = FirebaseDatabase.getInstance().getReference();

        mensagensRef = databaseReference.child("mensagens");

        mensagensRef.child(idRemetente)
                .child(idDestinatario)
                .push()
                .setValue(msg);

        // Limpa a caixa de texto
        mensagemDestinatario.setText("");

    }



    private void recuperarMensagens(){
        childEventListenerMensagens = mensagensRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Mensagem mensagem = snapshot.getValue(Mensagem.class);
                listaMensagens.add(mensagem);
                mensagensAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarMensagens();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mensagensRef.removeEventListener(childEventListenerMensagens);
    }


}