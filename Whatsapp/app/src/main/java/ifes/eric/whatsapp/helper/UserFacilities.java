package ifes.eric.whatsapp.helper;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class UserFacilities {

    public static FirebaseAuth usuario = FirebaseAuth.getInstance();


    public static String codificarString(String textoDecodificado){

        return Base64.encodeToString(textoDecodificado.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT).replaceAll("\\r|\\n","");

    }

    public static String decodificarString(String textoCodificado){

        return new String(Base64.decode(textoCodificado,Base64.DEFAULT));

    }

    public static String UsuarioEmailB64() {
        return UserFacilities.codificarString(Objects.requireNonNull(Objects.requireNonNull(usuario
                .getCurrentUser()).getEmail()));
    }

    public static String UsuarioEmail() {
        return usuario.getCurrentUser().getEmail();
    }

    public static FirebaseUser UsuarioGetUser(){
        return usuario.getCurrentUser();
    }

//   ---------------------- Metodo que Atualiza a foto do Usuario no Firebase ----------------------
    public static boolean atualizarFotoUsuario (Uri url){
        try {
            FirebaseUser user = UsuarioGetUser();
            // Objeto que faz requerimento de alteração dos dados do usuario.
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(url)
                    .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()){
                        Log.d("Perfil", "Erro ao atualizar foto");
                    }
                }
            });
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
//   *******************  Metodo que Atualiza a foto do Usuario no Firebase   **********************


//   ---------------------- Metodo que Atualiza o nome do Usuario no Firebase ----------------------
    public static boolean AtualizarNomeUsuario (String nome){
        try {
            FirebaseUser user = UsuarioGetUser();
            // Objeto que faz requerimento de alteração dos dados do usuario.
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nome)
                    .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()){
                        Log.d("Perfil", "Erro ao atualizar Nome do Perfil");
                    }
                }
            });
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
//   *******************  Metodo que Atualiza o nome do Usuario no Firebase   **********************


}
//   --------------------------- Metodo que retorna a receitaTotal  --------------------------------

//   **************************  Metodo que retorna a receitaTotal   *******************************