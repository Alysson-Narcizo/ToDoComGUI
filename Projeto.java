import java.io.Serializable;

public class Projeto implements Serializable {
    private String nome;
    private String descricao;
    private boolean sorteado;

    public Projeto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.sorteado = false;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isSorteado() {
        return sorteado;
    }

    public void setSorteado(boolean sorteado) {
        this.sorteado = sorteado;
    }

    @Override
    public String toString() {
        return (sorteado ? "[SORTEADO] " : "") + nome + ": " + descricao;
    }
}