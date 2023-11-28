package hobby.eric.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, LoginActivity.class));






        Log.e("ERICTAG", "ESSE É O LOG VERMELHO - ERROR");
        Log.d("ERICTAG", "ESSE É O LOG VERDE - ");
        Log.i("ERICTAG", "ESSE É O LOG AZUL");
        Log.v("ERICTAG", "ESSE É O LOG BRANCO");

    }

    @Override
    protected void onStart() {
        Log.d("ERICTAG", "ON START NO MAINACTIVITY");
        super.onStart();
    }
}