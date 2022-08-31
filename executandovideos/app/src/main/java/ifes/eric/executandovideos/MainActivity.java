package ifes.eric.executandovideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView botaoAbrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"SEUASEUASUEUA",Toast.LENGTH_LONG).show();
        botaoAbrir = findViewById(R.id.image_play);
    }

    public void abrirVideo(View view){

        startActivity(new Intent(this, PlayerActivity.class));

    }
}