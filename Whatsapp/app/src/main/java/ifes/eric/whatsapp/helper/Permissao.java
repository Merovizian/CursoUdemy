package ifes.eric.whatsapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

    // Metodo para que seja solicitado ao usuario para que aceite ou nao as permissoes.
    public static boolean validarPermissoes(String[] permissoes, Activity contexto, int requestCode) {

        if (Build.VERSION.SDK_INT >= 23) {

            List<String> listaPermissoes = new ArrayList<>();

            // Laço que ira percorrer todas as permissões.
            for (String permissao : permissoes) {

                // Verifica se a permissao deste laço NÃO foi dada.
                if (!(ContextCompat.checkSelfPermission(contexto, permissao) == PackageManager.PERMISSION_GRANTED)) {
                    // Caso não tenha sido dada: incrementa ela na listaPermissoes.
                    listaPermissoes.add(permissao);
                }

            }
            // Não solicita nada se a lista estiver vazia.
            if (listaPermissoes.isEmpty()) return true;

            //  ActivityCompat.requestPermissions requer uma array, só que listaPermissoes é lista
            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);

            // Solicita a permissão
            ActivityCompat.requestPermissions(contexto, novasPermissoes, requestCode);

        }


        return true;
    }
}
