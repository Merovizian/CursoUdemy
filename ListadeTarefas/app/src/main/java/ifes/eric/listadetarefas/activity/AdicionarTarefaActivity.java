package ifes.eric.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import ifes.eric.listadetarefas.R;
import ifes.eric.listadetarefas.helper.TarefaDAO;
import ifes.eric.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    public TextInputEditText tarefaInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        tarefaInput = findViewById(R.id.input_tarefa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_salvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                Tarefa tarefa = new Tarefa();
                //tarefa.setNomeTarefa(tarefaInput.getText().toString());
                tarefa.setNomeTarefa("IR PARA O IFES");
                tarefaDAO.salvar(tarefa);

                break;

            case R.id.action_editar:
                Toast.makeText(this, "Item editar pressionado",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_configuracoes:
                Toast.makeText(this, "Item configurações pressionado",
                        Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}