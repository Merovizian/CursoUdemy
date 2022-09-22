package ifes.eric.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

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
}