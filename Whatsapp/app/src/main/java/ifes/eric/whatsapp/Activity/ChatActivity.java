package ifes.eric.whatsapp.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;


import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;

public class ChatActivity extends AppCompatActivity {
    public TextView nomeDestinatario;
    public CircleImageView fotoDestinatario;
    private Usuario destinatario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

}