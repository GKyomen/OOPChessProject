public class Tabuleiro {
    private final Posicao[][] posicoes = new Posicao[8][8];

    public Tabuleiro() {
        inicializaTabuleiro();
    }

    private void inicializaTabuleiro() {
        char coluna;
        int linha;
        for (int i = 0; i < 8; i++) {
            linha = 7 - i;
            for (int j = 0; j < 8; j++) {
                coluna = (char) (j + 'a'); //usando ASCII para saber a letra da coluna
                this.posicoes[i][j] = new Posicao(linha, coluna);
                if (i == 0 || i == 1 || i == 6 || i == 7) {
                    this.posicoes[i][j].setOcupada(true);
                }
            }
        }
    }

    public void desenhaTabuleiro() {
        for (Posicao[] linha : posicoes) {
            for (Posicao posicao : linha) {
                if(posicao.getOcupada())
                    System.out.print(" O");
                else
                    System.out.print(" X");
            }
            System.out.println("");
        }
    }
}