/*
Feito por Gabriel da Silva Kyomen
771008
*/
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
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if((diffColuna == 1 || diffColuna == 0 || diffColuna == -1) && (diffLinha == 1 || diffLinha == 0 || diffLinha == -1))
            ehAdequado = true;
        return ehAdequado;
    }         
}
