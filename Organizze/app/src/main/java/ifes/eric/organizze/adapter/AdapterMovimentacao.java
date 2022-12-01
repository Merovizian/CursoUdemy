package ifes.eric.organizze.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ifes.eric.organizze.R;
import ifes.eric.organizze.model.Movimentacao;

public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    List<Movimentacao> movimentacoes;
    Context contexto;

    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context contexto) {
        this.movimentacoes = movimentacoes;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movimentacao movimentacao = movimentacoes.get(position);

        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText((int) movimentacao.getValor());
        holder.categoria.setText(movimentacao.getCategoria());

        if (movimentacao.getTipo() == "d" || movimentacao.getTipo().equals("d")){
            holder.valor.setText(contexto.getResources().getColor(R.color.purple_500));
            holder.valor.setText("-" + movimentacao.getValor());
        }
    }

    @Override
    public int getItemCount() {

        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, valor, categoria;

        public MyViewHolder (View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
        }
    }
}
