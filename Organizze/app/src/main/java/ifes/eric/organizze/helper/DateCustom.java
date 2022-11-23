package ifes.eric.organizze.helper;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

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

        String retornoData[] = data.split("/");
        String retorno2[] = retornoData[2].split(" ");
        return retornoData[1] + retorno2[0] ;


    }
}
