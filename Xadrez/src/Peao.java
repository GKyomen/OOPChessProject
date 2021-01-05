public class Peao extends Peca{
    
    public Peao(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        String representacao = "";
        if (this.getStatus()) {
            if (this.getCor() == "preto")
                representacao = "P-";
            else 
                representacao = "P+";
        }
        return representacao;
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if(diffColuna == 0) {
            if(this.getCor() == "preto") {
                if((diffLinha == -1) || (diffLinha == -2 && linhaOrigem == 7)) 
                    ehAdequado = true;
            } else {
                if((diffLinha == 1) || (diffLinha == 2 && linhaOrigem == 2))
                    ehAdequado = true;
            }
        } else if(diffColuna == 1 || diffColuna == -1) {
            if(this.getCor() == "preto" && diffLinha == -1)
                ehAdequado = true;
            else if(this.getCor() == "branco" && diffLinha == 1)
                ehAdequado = true;
        }
        return ehAdequado;
    }
}
