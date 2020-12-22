import java.util.Scanner;

public class Jogo {
    private int estadoDoJogo;
    private Peca pecas[] = new Peca[32];
    private Tabuleiro tabuleiro;
    private Jogador jogadores[] = new Jogador[2];
    private boolean ehVezDoJogador1;

    public Jogo(String jBrancas, String jPretas) {
        this.iniciaJogo(jBrancas, jPretas);
        this.jogar();
    }

    //inicializa os atributos necessários para o jogo
    public void iniciaJogo(String jBrancas, String jPretas) {
        Peca brancas[] = new Peca[16];
        Peca pretas[] = new Peca[16];
        
        //inicia o jogo e coloca como vez do primeiro jogador
        this.estadoDoJogo = 0;
        this.ehVezDoJogador1 = true;

        //inicializa as peças do jogo
        this.pecas = new Peca[32];
        this.pecas[0] = new Torre("preto");
        this.pecas[1] = new Cavalo("preto");
        this.pecas[2] = new Bispo("preto");
        this.pecas[3] = new Rei("preto");
        this.pecas[4] = new Dama("preto");
        this.pecas[5] = new Bispo("preto");
        this.pecas[6] = new Cavalo("preto");
        this.pecas[7] = new Torre("preto");
        for (int i = 8; i <= 15; i++)
            this.pecas[i] = new Peao("preto");
        for (int i = 16; i <= 23; i++)
            this.pecas[i] = new Peao("branco");
        this.pecas[24] = new Torre("branco");
        this.pecas[25] = new Cavalo("branco");
        this.pecas[26] = new Bispo("branco");
        this.pecas[27] = new Rei("branco");
        this.pecas[28] = new Dama("branco");
        this.pecas[29] = new Bispo("branco");
        this.pecas[30] = new Cavalo("branco");
        this.pecas[31] = new Torre("branco");
        
        //inicializa o tabuleiro com referencia para as peças criadas, evitando duplicar peças
        this.tabuleiro = new Tabuleiro(this.pecas);

        //inicializa os jogadores
        for (int i = 0; i < 16; i++)
            brancas[i] = this.pecas[i];
        this.jogadores[0] = new Jogador(jBrancas, 1, brancas);

        for (int i = 16; i < 32; i++)
            pretas[i - 16] = this.pecas[i];
        this.jogadores[1] = new Jogador(jPretas, 2, pretas);
    }

    private void jogar() {
        Scanner teclado = new Scanner(System.in);
        char colunaOrigem, colunaDestino;
        int linhaOrigem, linhaDestino;
        String corPeca, jogador;
        int jogada;

        jogo:
        while(true) {
            System.out.println("-------------Tabuleiro atual-------------\n");
            this.tabuleiro.desenhaTabuleiro();
            System.out.println("");

            if(this.ehVezDoJogador1) {
                corPeca = this.jogadores[0].getCor();
                jogador = this.jogadores[0].getNome();
            } else {
                corPeca = this.jogadores[1].getCor();
                jogador = this.jogadores[1].getNome();
            }

            System.out.println(jogador + ", por favor, informe as coordenas da sua jogada: (informe q 0 q 0 para desistir)");

            do{ //pede uma jogada até que ela seja válida
                colunaOrigem = teclado.next().charAt(0);
                linhaOrigem = teclado.nextInt();
                colunaDestino = teclado.next().charAt(0);
                linhaDestino = teclado.nextInt();

                if(colunaOrigem == 'q' && colunaDestino == 'q' && linhaOrigem == 0 && linhaDestino == 0) {
                    System.out.println(jogador + " desistiu. Fim de jogo");
                    break jogo;
                }

                //imprime algo caso ocorra uma mudança significativa ou seja uma jogada inválida
                jogada = this.tabuleiro.movimentar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, corPeca, this.estadoDoJogo, this.pecas);
                if(jogada == -1) {
                    System.out.println("Jogada inválida. Insira outras coordenadas: ");
                } else if(jogada == 0) {
                    this.ehVezDoJogador1 = !this.ehVezDoJogador1;
                    this.estadoDoJogo = 0;
                } else if(jogada == 1) {
                    System.out.println("Agora o jogo está em xeque! Tire o rei do perigo");
                    this.ehVezDoJogador1 = !this.ehVezDoJogador1;
                    this.estadoDoJogo = 1;
                } else if(jogada == 2) {
                    System.out.println("Xeque-mate! Fim de jogo, parabéns " + jogador + " pela vitória");
                    this.estadoDoJogo = 2;
                    break jogo;
                } else if(jogada == 3) {
                    System.out.println("Fim de jogo por insuficiência material.");
                    this.estadoDoJogo = 3;
                    break jogo;
                }
            } while(jogada == -1);
        }

        teclado.close();
    }

    public boolean getEhVezDoJogador1() {
        return this.ehVezDoJogador1;
    }
}