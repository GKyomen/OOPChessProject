public class Rei extends Peca {
    
    public Rei(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        String representacao = "";
        if (this.getStatus()) {
            if (this.getCor() == "preto")
                representacao = "R-";
            else 
                representacao = "R+";
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
        if ( (diffColuna == 0 && (diffLinha == 1 || diffLinha == -1)) || (diffLinha == 0 && (diffColuna == 1 || diffColuna == -1)) )
            ehAdequado = true;
        else if ( (diffColuna == diffLinha || diffColuna == diffLinha*(-1)) && (diffLinha == 1 || diffLinha == -1) )
            ehAdequado = true;
        return ehAdequado;
    }         
}
