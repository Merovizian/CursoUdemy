package ifes.eric.organizze.model;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

public class DateCustom {

    // Cria um formato de data, atual.
    public static String dataAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy hh:mm:ss");
        String dataString = simpleDateFormat.format(data);

        return dataString;
    }

}
