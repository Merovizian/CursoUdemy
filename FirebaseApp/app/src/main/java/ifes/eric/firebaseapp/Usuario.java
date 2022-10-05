package ifes.eric.firebaseapp;

public class Usuario {
    private String Nome;
    private String Sobrenome;
    private int Idade;
    private boolean Trabalha;

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    public int getIdade() {
        return Idade;
    }

    public void setIdade(int Idade) {
        this.Idade = Idade;
    }

    public boolean isTrabalha() {
        return Trabalha;
    }

    public void setTrabalha(boolean Trabalha) {
        this.Trabalha = Trabalha;
    }
}
