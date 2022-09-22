package ifes.eric.listadetarefas.activity;

import androidx.annotation.NonNull;
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

    private TextInputEditText editTarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.input_tarefa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_salvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                Tarefa tarefa = new Tarefa();
                tarefa.setNomeTarefa("Ir ao mercado");
                tarefaDAO.salvar(tarefa);



                break;
            case R.id.action_editar:

                break;
            case R.id.action_configuracao:

                break;









        }


        return super.onOptionsItemSelected(item);
    }
}