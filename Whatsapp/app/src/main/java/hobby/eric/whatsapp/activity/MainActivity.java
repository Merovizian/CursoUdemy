package hobby.eric.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import hobby.eric.whatsapp.Helper.ConfiguracaoFirebase;
import hobby.eric.whatsapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.e("ERICTAG", "ESSE É O LOG VERMELHO - ERROR");
        Log.d("ERICTAG", "ESSE É O LOG VERDE - TESTE LOCAL");
        Log.i("ERICTAG", "ESSE É O LOG AZUL - TESTE CONDICIONAIS ");
        Log.v("ERICTAG", "ESSE É O LOG BRANCO  - TESTE VARIAVEIS");

    }

    @Override
    protected void onStart() {
        Log.d("ERICTAG", "ON START NO MAINACTIVITY");
        super.onStart();
    }
}