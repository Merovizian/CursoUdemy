package ifes.eric.organizze;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  FIREBASEAUTH   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Declara uma instancia FirebaseAuth.
    private FirebaseAuth mAuth;
//*****************************************  FIREBASEAUTH  *****************************************

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   DATABASE      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Mensagem");

//*****************************************   DATABASE     *****************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  FIREBASEAUTH   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Declara uma instancia FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();



//*****************************************  FIREBASEAUTH  *****************************************

        TextView revelação = findViewById(R.id.text_texto);
        revelação.setText(mAuth.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Toast.makeText(this, currentUser.toString(), Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "Usuario Não Logado", Toast.LENGTH_SHORT).show();
        }
    }
}
