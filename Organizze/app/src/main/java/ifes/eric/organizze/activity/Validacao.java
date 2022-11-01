package ifes.eric.organizze.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class Validacao {



    public int validar(String nome, String senha, String email, Context context) {

        if (!nome.isEmpty() ){
            if (!email.isEmpty() ){
                if (!senha.isEmpty() ){

                }else{
                    Toast.makeText(context, "Preencha o senha", Toast.LENGTH_SHORT).show();
                    return 3;

                }
            }else{
                Toast.makeText(context, "Preencha a email", Toast.LENGTH_SHORT).show();
                return 2;

            }
        }else{
            Toast.makeText(context, "Preencha o nome", Toast.LENGTH_SHORT).show();
            return 1;

        }
        return 0;
    }

    public int validar(String senha, String email, Context context) {

            if (!email.isEmpty() ){
                if (!senha.isEmpty() ){

                }else{
                    Toast.makeText(context, "Preencha o senha", Toast.LENGTH_SHORT).show();
                    return 3;
                }
            }else{
                Toast.makeText(context, "Preencha a email", Toast.LENGTH_SHORT).show();
                return 2;
            }
        return 0;
    }



}


