package hobby.eric.whatsapp.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class validacao {

    public int validar(String nome, String endereco, String rg, String companheiro, Context context) {

        if (!nome.isEmpty()) {
            if (!endereco.isEmpty()) {
                if (!rg.isEmpty()) {
                    if (!companheiro.isEmpty()) {
                        Log.i("IF INUTIL", "Só para tirar o amarelo erro");
                    } else {
                        Toast.makeText(context, "Para garantir sua segurança, informe o nome do cônjuge.", Toast.LENGTH_SHORT).show();
                        return 4;
                    }
                } else {
                    Toast.makeText(context, "Por favor, informe seu RG ou CPF.", Toast.LENGTH_SHORT).show();
                    return 3;
                }
            } else {
                Toast.makeText(context, "Por favor, informe seu endereço.", Toast.LENGTH_SHORT).show();
                return 2;
            }
        } else {
            Toast.makeText(context, "Por favor, informe seu nome.", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

    public int validar(String nome, String senha, String email, Context context) {

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {
                    Log.i("IF INUTIL", "Só para tirar o amarelo erro");
                } else {
                    Toast.makeText(context, "Preencha o senha", Toast.LENGTH_SHORT).show();
                    return 3;
                }
            } else {
                Toast.makeText(context, "Preencha a email", Toast.LENGTH_SHORT).show();
                return 2;
            }
        } else {
            Toast.makeText(context, "Preencha o nome", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

    public int validar(String senha, String email, Context context) {

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                Log.i("IF INUTIL", "Só para tirar o amarelo erro");

            } else {
                Toast.makeText(context, "Preencha o senha", Toast.LENGTH_SHORT).show();
                return 3;
            }
        } else {
            Toast.makeText(context, "Preencha a email", Toast.LENGTH_SHORT).show();
            return 2;
        }
        return 0;
    }


}




