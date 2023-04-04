package ifes.eric.whatsapp.Fragmants;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import ifes.eric.whatsapp.Activity.ChatActivity;
import ifes.eric.whatsapp.Adapter.ConversasAdapter;
import ifes.eric.whatsapp.Model.Conversa;
import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.RecyclerItemClickListener;
import ifes.eric.whatsapp.helper.UserFacilities;


public class ConversasFragment extends Fragment {

    private RecyclerView recyclerViewConversas;
    private List<Conversa> listaConversas = new ArrayList<>();
    private ConversasAdapter conversasAdapter;
    private DatabaseReference bancoDados = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference conversasREF;
    private ChildEventListener childEventListenerConversas;

    public ConversasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);

        recyclerViewConversas = view.findViewById(R.id.recycler_fragmentsConversas_recyclerContatos);

        // configurar adapter
        conversasAdapter = new ConversasAdapter(listaConversas, getActivity());



        // configurar o recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewConversas.setLayoutManager(layoutManager);
        recyclerViewConversas.setHasFixedSize(true);
        recyclerViewConversas.setAdapter(conversasAdapter);

        // Configurar evento de clique - Codigo desenvolvido pelo curso Udemy
        recyclerViewConversas.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerViewConversas,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            // Evento de click em item
                            public void onItemClick(View view, int position) {
                                // Resgatar a conversa clicada retornando da lista de conversa a posição que foi clicada
                                Conversa conversaSelecionada = listaConversas.get(position);
                                // Na conversa resgatada da posicao acima, extrai-se o usuario (salvo no Firebase)
                                Usuario usuarioConversa = conversaSelecionada.getUsuarioExibicao();
                                Intent intent = new Intent(getContext(), ChatActivity.class);
                                // O que vai no put tem que ser exatamente o que recebe na classe ChatActivity
                                intent.putExtra("chatContato", usuarioConversa);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            }
                        }

                )
        );

        // Cria um link de referencia para a raiz de conversas no Firebase
        String identificadorUsuario = UserFacilities.UsuarioEmailB64();
        conversasREF = bancoDados.child("conversas")
                .child(identificadorUsuario);

 
        return view;
    }

//   ----------------------- Função que recupera os valores do Firebase e aplica no recycler ---------------------------
    public void recuperarConversas (){

        childEventListenerConversas = conversasREF.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Acessa o banco de dados e recupera toda a classe de conversa.
                Conversa conversa = snapshot.getValue(Conversa.class);

                // Adiciona cada child existente em uma lista de Conversas.
                listaConversas.add(conversa);

                conversasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//   *********************  Função que recupera os valores do Firebase e aplica no recycler  ***************************

    @Override
    public void onStart() {
        super.onStart();
        recuperarConversas();
    }

    @Override
    public void onStop() {
        super.onStop();
        conversasREF.removeEventListener(childEventListenerConversas);
    }

    public void pesquisarConversas(String query ){

    }
}