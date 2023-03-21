package ifes.eric.whatsapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ifes.eric.whatsapp.Model.Mensagem;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.UserFacilities;

public class MensagensAdapter extends RecyclerView.Adapter<MensagensAdapter.MyviewHolder> {

    private List<Mensagem> mensagems;
    private Context context;
    private static final int TIPO_REMETENTE = 0;
    private static final int TIPO_DESTINATARIO = 1;


    public MensagensAdapter(List<Mensagem> lista, Context c) {
        this.mensagems = lista;
        this.context = c;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        if (viewType == TIPO_REMETENTE){

            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout_remetente,parent,false);

        }else if (viewType == TIPO_DESTINATARIO){

            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout_destinatario,parent,false);

        }

        return new MyviewHolder(item);



    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        Mensagem mensagem = mensagems.get(position);
        String msg = mensagem.getMensagem();
        String imagem = mensagem.getImagem();

        if (imagem != null){
            Uri uri = Uri.parse(imagem);
            Glide.with(context)
                    .load(uri)
                    .into(holder.imagem);
            holder.textoMensagem.setVisibility(View.GONE);
        }else
        {
            holder.textoMensagem.setText(msg);
            holder.imagem.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mensagems.size();
    }

    @Override
    public int getItemViewType(int position) {

        Mensagem mensagem = mensagems.get(position);
        String idUsuario = UserFacilities.UsuarioEmailB64();

        if (mensagem.getIdUsuario().equals(idUsuario)){
            return TIPO_REMETENTE;
        }else
            return TIPO_DESTINATARIO;


    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        private TextView textoMensagem;
        private ImageView imagem;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            textoMensagem = itemView.findViewById(R.id.textview_adapterRemetente_mensagem);
            imagem = itemView.findViewById(R.id.imageView_adapterRemetente_foto);


        }
    }


}
