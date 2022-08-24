package com.ifes.eric.atmconsultoria.ui.sobre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifes.eric.atmconsultoria.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SobreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SobreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SobreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SobreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SobreFragment newInstance(String param1, String param2) {
        SobreFragment fragment = new SobreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String descricao = "Aqui, você trabalhador pode:" +
                "consultar as informações da sua Carteira de Trabalho Digital;" +
                "consultar a situação do seu Benefício TAC-Taxista;" +
                "consultar a situação do requerimento de Seguro-Desemprego" +
                "e informações sobre o benefício;" +
                "consultar a situação do seu Benefício Emergencial;" +
                "buscar emprego de acordo com o seu perfil profissional;" +
                "obter informações sobre o Abono Salarial";

        Element versao = new Element();
        versao.setTitle("Versão 1.0");

        return  new AboutPage(getActivity())


                .setImage(R.drawable.logo)
                .setDescription(descricao)

                .addGroup("Redes Sociais")
                .addFacebook("eric.micaela")
                .addInstagram("ericgiobini")
                .addTwitter("ericgiobini")
                .addGitHub("ericgiobini")
                .addYoutube("ericgiobini")
                .addPlayStore("com.hbrz.wodan", "download app")

                .addGroup("Entre em contato")
                .addEmail("ericgmicaela@gmail.com","Envie um e-mail")
                .addWebsite("www.google.com.br", "Acesse nosso site")
                .addItem(versao)

                .create();

    }
}