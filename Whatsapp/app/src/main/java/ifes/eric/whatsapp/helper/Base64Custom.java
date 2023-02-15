package ifes.eric.whatsapp.helper;

import android.util.Base64;

import java.nio.charset.StandardCharsets;


public class Base64Custom {

    public static String codificarString(String textoDecodificado){

        return Base64.encodeToString(textoDecodificado.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT).replaceAll("\\r|\\n","");

    }

    public static String decodificarString(String textoCodificado){

        return new String(Base64.decode(textoCodificado,Base64.DEFAULT));

    }

}
