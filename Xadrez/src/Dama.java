public class Dama extends Peca {
    
    public Dama(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        String representacao = "";
        if (this.getStatus()) {
            if (this.getCor() == "preto")
                representacao = "D-";
            else 
                representacao = "D+";
        }
        return representacao;
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if(diffColuna == 0 || diffLinha == 0)
            ehAdequado = true;
        else if(diffColuna == diffLinha || diffColuna == diffLinha*(-1))
            ehAdequado = true;
        return ehAdequado;
    }     
}
