/*
Feito por Gabriel da Silva Kyomen
771008
*/
public class Cavalo extends Peca {

    public Cavalo(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        String representacao = "";
        if (this.getStatus()) {
            if (this.getCor() == "preto")
                representacao = "C-";
            else 
                representacao = "C+";
        }
        return representacao;
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if((diffColuna == 1 || diffColuna == -1) && (diffLinha == 2 || diffLinha == -2)) 
            ehAdequado = true;
        else if((diffLinha == 1 || diffLinha == -1) && (diffColuna == 2 || diffColuna == -2))
            ehAdequado = true;
        return ehAdequado;
    }    
}
