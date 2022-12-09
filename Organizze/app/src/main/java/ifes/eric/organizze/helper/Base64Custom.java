package ifes.eric.organizze.helper;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Custom {

    public static String codificarBase64(String texto){

        byte[] data;

        data =texto.getBytes(StandardCharsets.UTF_8);

        return Base64.encodeToString(data,Base64.NO_WRAP);

    }

}
