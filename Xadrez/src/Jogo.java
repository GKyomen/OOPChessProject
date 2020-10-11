public class Jogo {
    private int estadoDoJogo;
    private Tabuleiro tabuleiro;
    private boolean ehVezDoJogador1;

    public Jogo() {
        this.estadoDoJogo = 0;
        this.tabuleiro = new Tabuleiro();
        this.ehVezDoJogador1 = true;
    }
}