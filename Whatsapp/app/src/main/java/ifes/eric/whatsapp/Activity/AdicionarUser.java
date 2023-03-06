package ifes.eric.whatsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.UserFacilities;

public class AdicionarUser extends AppCompatActivity {
    private FirebaseUser usuario = UserFacilities.UsuarioGetUser();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private EditText email;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_user);
        botao = findViewById(R.id.button_adicionarUser_validarUser);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarUsuario();
            }
        });

    }
    
    
    public void adicionarUsuario (){

       email = findViewById(R.id.input_adicionarUser_email);
       String textoEmail = UserFacilities.codificarString(email.getText().toString());

        Toast.makeText(this, email.getText().toString(), Toast.LENGTH_SHORT).show();

        database.child("usuarios").child(UserFacilities.UsuarioEmailB64()).child("listaContatos")
                .child(textoEmail).setValue(UserFacilities.decodificarString(textoEmail));
        
    }
    
    
    
}