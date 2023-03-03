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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder> {

    private List<Usuario> contatos;
    private Context context;


    public ContatosAdapter(List<Usuario> listaContatos, Context c) {
        this.contatos = listaContatos;
        this.context = c;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from( parent.getContext() ).inflate(R.layout.adapter_contatos, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Usuario usuario = contatos.get(position);

        holder.nome.setText(usuario.getNome());
        holder.email.setText(usuario.getEmail());

        if (usuario.getFoto() != null ){
            Glide.with( context )
                    .load(Uri.parse(usuario.getFoto()))
                    .into(holder.fotoPerfil);
        }
        else{
            holder.fotoPerfil.setImageResource(R.drawable.padrao);
        }


    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView fotoPerfil;
        TextView nome, email;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoPerfil = itemView.findViewById(R.id.adapterContatos_image_photoUser);
            nome = itemView.findViewById(R.id.adapterContatos_text_NomeUser);
            email = itemView.findViewById(R.id.adapterContatos_text_EmailUser);


        }
    }

}
