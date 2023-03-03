package ifes.eric.whatsapp.Fragmants;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ifes.eric.whatsapp.Adapter.ContatosAdapter;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;


public class ContatosFragments extends Fragment {

    private RecyclerView recyclerViewContatos;
    private ContatosAdapter contatosAdapter;
    private ArrayList<Usuario> listaContatos = new ArrayList<>();
    private DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
    private ValueEventListener valueEventListenerContatos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

        recyclerViewContatos = view.findViewById(R.id.contatos_recycler_lista);

        // Configurar adapter
        contatosAdapter = new ContatosAdapter(listaContatos, getActivity());

        // Configurar o recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        recyclerViewContatos.setLayoutManager( layoutManager );
        recyclerViewContatos.setHasFixedSize( true );
        recyclerViewContatos.setAdapter( contatosAdapter );

        recuperarContatos();

        return view;
    }

    public void recuperarContatos () {

        valueEventListenerContatos = usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dados: snapshot.getChildren()){

                    Usuario usuario = dados.getValue(Usuario.class);
                    listaContatos.add(usuario);


                }

                contatosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        //recuperarContatos();
    }

    @Override
    public void onStop() {
        super.onStop();
        usuarioRef.removeEventListener(valueEventListenerContatos);
    }
}