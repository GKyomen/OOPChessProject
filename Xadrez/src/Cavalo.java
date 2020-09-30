public class Cavalo {
    private String cor;
    private boolean status;
    
    public Cavalo(String cor) {
        this.setCor(cor);
        this.setStatus(true);
    }

    public String getCor() {
        return cor;
    }

    private void setCor(String cor) {
        this.cor = cor;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String desenho() {
        String representacao = "";
        if (this.getStatus()) {
            if (this.getCor() == "preto")
                representacao = "C+";
            else 
                representacao = "C-";
        }
        return representacao;
    }

    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if ( (diffColuna == 1 || diffColuna == -1) && (diffLinha == 2 || diffLinha == -2) ) 
            ehAdequado = true;
        else if ( (diffLinha == 1 || diffLinha == -1) && (diffColuna == 2 || diffColuna == -2) )
            ehAdequado = true;
        return ehAdequado;
    }    
}
