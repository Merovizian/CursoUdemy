package ifes.eric.whatsapp.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.Model.Conversa;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;

public class ConversasAdapter extends RecyclerView.Adapter<ConversasAdapter.MyViewHolder> {

    private Context contexto;
    private List<Conversa> listaConversas ;


    public ConversasAdapter(List<Conversa> conversas, Context c) {
        this.listaConversas = conversas;
        this.contexto = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_contatos, parent, false);
        return new MyViewHolder(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Conversa conversa = listaConversas.get(position);
        holder.ultimaMensagem.setText(conversa.getUltimaMensagem());

        Usuario usuario = conversa.getUsuarioExibicao();
        holder.nome.setText(usuario.getNome());

        if (usuario.getFoto() != null){
            Uri urlFoto = Uri.parse(usuario.getFoto());
            Glide.with(contexto).load(urlFoto).into(holder.foto);

        }else{
            holder.foto.setImageResource(R.drawable.padrao);
        }


    }

    @Override
    public int getItemCount() {

        return listaConversas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView foto;
        TextView nome, ultimaMensagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.adapterContatos_image_photoUser);
            nome = itemView.findViewById(R.id.adapterContatos_text_NomeUser);
            ultimaMensagem = itemView.findViewById(R.id.adapterContatos_text_EmailUser);
        }


    }

}
