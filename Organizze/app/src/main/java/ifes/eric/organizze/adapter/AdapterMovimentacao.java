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

import ifes.eric.organizze.model.Movimentacao;
import ifes.eric.organizze.R;


public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    List<Movimentacao> movimentacoes;
    Context context;

    // construtor, serve para gerar instanciar objetos
    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context context) {
        this.movimentacoes = movimentacoes;
        this.context = context;
    }


    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }


    @SuppressLint("SetTextI18n")
    @Override
    // Usa os itens criados no MyViewHolder para settar de acordo com o que há em movimentações
    // de acordo com a posição.
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movimentacao movimentacao = movimentacoes.get(position);

        holder.data.setText(movimentacao.getData());
        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.categoria.setText(movimentacao.getCategoria());
        holder.valor.setTextColor(context.getResources().getColor(R.color.R));

        if (movimentacao.getTipo().equals("D")) {
            holder.valor.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
            holder.valor.setText("-" + movimentacao.getValor());
        }
    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    // clase que cria os TextViews que serão anexados ao XLM
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria, data;

        public MyViewHolder(View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.text_adapter_data);
            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
        }

    }

}
