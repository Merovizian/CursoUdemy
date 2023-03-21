package ifes.eric.whatsapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ifes.eric.whatsapp.Model.Mensagem;

public class MensagensAdapter extends RecyclerView.Adapter<MensagensAdapter.MyviewHolder> {

    private List<Mensagem> mensagems;
    private Context context;

    public MensagensAdapter(List<Mensagem> lista, Context c) {
        this.mensagems = lista;
        this.context = c;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
