package ifes.eric.organizze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import ifes.eric.organizze.activity.cadastroActivity;
import ifes.eric.organizze.activity.loginActivity;

public class MainActivity extends IntroActivity {
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


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Slides      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        setButtonBackVisible(false);
        setButtonNextVisible(false);
//******************************************   Slides     ******************************************


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Slides Fragments     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        addSlide(new FragmentSlide.Builder()
                .background(R.color.teal_200)
                .fragment(R.layout.intro_um)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.teal_200)
                .fragment(R.layout.intro_dois)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.teal_200)
                .fragment(R.layout.intro_tres)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.teal_200)
                .fragment(R.layout.intro_quatro)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.teal_200)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build()
        );

//*************************************   Slides Fragments     *************************************




// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  FIREBASEAUTH   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Declara uma instancia FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();
//*****************************************  FIREBASEAUTH  *****************************************
    }

    public void btEntrar(View view){
        startActivity(new Intent(this,loginActivity.class));
    }
    public void cadastrar(View view){
        startActivity(new Intent(this, cadastroActivity.class));


    }

}
