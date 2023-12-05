package hobby.eric.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import hobby.eric.whatsapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.v("ERICTAG", "LOGIN ACTIVITY OnCreate");


    }

    public void abrirTelaCadastro(View view){
        Intent abrirActivityCadastro = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(abrirActivityCadastro);

    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "REINICIE O PROGRAMA", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
}