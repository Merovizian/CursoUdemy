package ifes.eric.whatsapp.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.Adapter.MensagensAdapter;
import ifes.eric.whatsapp.Model.Mensagem;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.UserFacilities;

public class ChatActivity extends AppCompatActivity {
    public TextView nomeDestinatario;
    public CircleImageView fotoDestinatario;
    private Usuario destinatario;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mensagemDestinatario = findViewById(R.id.editText_contentChat_mensagens);

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
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mensagensRef = databaseReference.child("mensagens")
                .child(Remetente)
                .child(Destinatario);

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



        }else{

            Toast.makeText(this, "Digite a mensagem...", Toast.LENGTH_SHORT).show();
            
        }





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