package ifes.eric.whatsapp.Fragmants;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ifes.eric.whatsapp.Activity.AdicionarUser;
import ifes.eric.whatsapp.Activity.ChatActivity;
import ifes.eric.whatsapp.Activity.MainActivity;
import ifes.eric.whatsapp.Adapter.ContatosAdapter;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.RecyclerItemClickListener;
import ifes.eric.whatsapp.helper.UserFacilities;


public class ContatosFragments extends Fragment {

    private RecyclerView recyclerViewContatos;
    private ContatosAdapter contatosAdapter;
    private ArrayList<Usuario> listaContatos = new ArrayList<>();
    private DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
    private ValueEventListener valueEventListenerContatos;
    private  FirebaseUser firebaseUser;


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

        // Configurar evento de clique
        recyclerViewContatos.addOnItemTouchListener(new RecyclerItemClickListener(
                getContext(),
                recyclerViewContatos,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startActivity(new Intent(getContext(), ChatActivity.class));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getContext(), "C", Toast.LENGTH_SHORT).show();

                    }
                }

        ));



        recuperarContatos();
        return view;
    }

    public void recuperarContatos () {

        valueEventListenerContatos = usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dados: snapshot.getChildren()){

                    firebaseUser = UserFacilities.UsuarioGetUser();

                    Usuario usuario = dados.getValue(Usuario.class);


                    if (!usuario.getEmail().equals(firebaseUser.getEmail()) )
                    {
                        listaContatos.add(usuario);

                    }

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