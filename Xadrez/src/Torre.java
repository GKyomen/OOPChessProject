public class Torre extends Peca {
    
    public Torre(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        String representacao = "";
        if (this.getStatus()) {
            if (this.getCor() == "preto")
                representacao = "T-";
            else 
                representacao = "T+";
        }
        return representacao;
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        if(linhaOrigem < 0 || colunaOrigem < 0 || linhaDestino < 0 || colunaDestino < 0 ||
            linhaOrigem > 7 || colunaOrigem > 7 || linhaDestino > 7 || colunaDestino > 7 ||
            (linhaOrigem == linhaDestino && colunaOrigem == colunaDestino)) {
            return false;
        }
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if (diffColuna == 0 || diffLinha == 0)
            ehAdequado = true;
        return ehAdequado;
    }
}
