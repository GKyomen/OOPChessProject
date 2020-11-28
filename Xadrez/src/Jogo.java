public class Jogo {
    private int estadoDoJogo;
    private Tabuleiro tabuleiro;
    private boolean ehVezDoJogador1;

    public Jogo(boolean iniciar) {
        if(iniciar) {
            this.iniciaJogo();
        }
    }

    public void iniciaJogo() {
        if(this.estadoDoJogo == 0) {
            this.estadoDoJogo = 1;
            this.tabuleiro = new Tabuleiro();
            this.ehVezDoJogador1 = true;
        }
    }

    public boolean getEhVezDoJogador1() {
        return this.ehVezDoJogador1;
    }
}