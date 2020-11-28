public class Posicao {
    private String cor;
    private int linha;
    private char coluna;
    private boolean ocupada;

    public Posicao(int linha, char coluna) {
        this.setLinha(linha);
        this.setColuna(coluna);
        this.setCor(this.defineCor());
        this.setOcupada(false);
    }

    public String getCor() {
        return cor;
    }

    private void setCor(String cor) {
        this.cor = cor;
    }

    public int getLinha() {
        return (linha + 1);
    }

    private void setLinha(int linha) {
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    private void setColuna(char coluna) {
        this.coluna = coluna;
    }

    public boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    private String defineCor() {
        String cor;
        boolean auxiliar = false;
        int numColuna = this.getColuna() - 97; //utilizando a tabela ASCII para calcular o número da coluna
        loopLinha:
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                auxiliar = !auxiliar;
                if (i == (this.getLinha() - 1) && j == numColuna) {
                    break loopLinha;
                }
            }
            auxiliar = !auxiliar; //troca pra cada linha começar com uma cor diferente
        }
        if (auxiliar)
            cor = "branco";
        else
            cor = "preto";
        return cor;
    }
}
