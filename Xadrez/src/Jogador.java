public class Jogador {
    private String nome, cor;
    private Peca pecas[];

    public Jogador(String nome, int nJogador, Peca pecas[]) {
        this.nome = nome;
        this.pecas = pecas;
        if(nJogador == 1)
            this.cor = "branco";
        else
            this.cor = "preto";
    }

    public String getNome() {
        return this.nome;
    }

    public String getCor() {
        return this.cor;
    }
}
