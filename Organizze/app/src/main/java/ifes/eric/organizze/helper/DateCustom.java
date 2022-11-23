package ifes.eric.organizze.helper;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class DateCustom {

    // Cria um formato de data, atual.
    public static String dataAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy hh:mm:ss");

        return simpleDateFormat.format(data);
    }

    public static String mesAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMyyyy");

        return simpleDateFormat.format(data);

    }

    public static String mesAnoDataEscolhida(String data){
        try {


            String retornoData[] = data.split("/");
            String retorno2[] = retornoData[2].split(" ");
            return retornoData[1] + retorno2[0] ;


        } catch (Exception ex) {
            return mesAtual();
        }


    }
}
