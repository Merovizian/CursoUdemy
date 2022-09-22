package ifes.eric.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflar transforma um objeto XML em objeto do tipo view
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_configurações:
                Toast.makeText(this, "Item Configurações", Toast.LENGTH_SHORT).show();


                break;
            case R.id.item_editar:
                Toast.makeText(this, "Item Editar", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_salvar:
                Toast.makeText(this, "Item Salvar", Toast.LENGTH_SHORT).show();

                break;

        }


        return super.onOptionsItemSelected(item);
    }
}