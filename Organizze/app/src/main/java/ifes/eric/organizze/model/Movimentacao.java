package ifes.eric.organizze.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.helper.DateCustom;

public class Movimentacao {

    private String data, categoria, descricao, tipo;
    private double valor;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Movimentacao() {
    }

    public void salvar(String dataEscolhida){

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        DatabaseReference reference = ConfiguracaoFirebase.getFirebaseDatabase();
        reference.child("movimentacao")
                .child(idUsuario)
                .child(DateCustom.mesAnoDataEscolhida(dataEscolhida))
                .push()
                .setValue(this);
    }
}
