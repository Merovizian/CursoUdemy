package ifes.eric.whatsapp.Activity;

import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;


import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.Model.Mensagem;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.UserFacilities;

public class ChatActivity extends AppCompatActivity {
    public TextView nomeDestinatario;
    public CircleImageView fotoDestinatario;
    private Usuario destinatario;

    public EditText mensagemDestinatario;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mensagemDestinatario = findViewById(R.id.editText_contentChat_mensagens);


//   ---------------------------  Codigos para apresentar a foto e o nome de usuario    --------------------------------
        nomeDestinatario = findViewById(R.id.textview_chat2_NomeUser);
        fotoDestinatario = findViewById(R.id.imageView_chat2_fotoDestinatario);



        // Recuperar os dados enviados pela fragment contatos
        Bundle bundle = getIntent().getExtras();
        if (bundle != null ){
            destinatario = (Usuario) bundle.getSerializable("chatContato");
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

    }


    public void enviarMensagem(View view){
        auth = FirebaseAuth.getInstance();

        String Remetente = UserFacilities.UsuarioEmailB64();
        String Destinatario = UserFacilities.codificarString(destinatario.getEmail());

        String textoMensagem = mensagemDestinatario.getText().toString();

        if (!textoMensagem.isEmpty()){

            Mensagem mensagem = new Mensagem();
            mensagem.setIdUsuario(Remetente);
            mensagem.setMensagem(textoMensagem);
            mensagem.setIdDestinatario(Destinatario);

            salvarMensagem(Remetente, Destinatario, textoMensagem, 1);


        }else{

            Toast.makeText(this, "Digite a mensagem...", Toast.LENGTH_SHORT).show();
            
        }





    }

    public void salvarMensagem(String idRemetente, String idDestinatario, String msg, Integer codigo){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mensagemRef = databaseReference.child("mensagens");
        DatabaseReference conversaAtiva = mensagemRef.child(idRemetente).child(idDestinatario).push();
        if (codigo == 1){
            conversaAtiva.child("mensagem").setValue(msg);
        } else if (codigo == 2) {
            conversaAtiva.child("imagem").setValue(msg);
        }
        conversaAtiva.child("idUsuario").setValue(idRemetente);
        mensagemDestinatario.setText("");

    }

}