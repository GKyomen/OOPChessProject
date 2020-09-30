public class Dama {
    private String cor;
    private boolean status;
    
    public Dama(String cor) {
        this.setCor(cor);
        this.setStatus(true);
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
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
                representacao = "D+";
            else 
                representacao = "D-";
        }
        return representacao;
    }

    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if (diffColuna == 0 || diffLinha == 0)
            ehAdequado = true;
        else if ( diffColuna == diffLinha || diffColuna == diffLinha*(-1) )
            ehAdequado = true;
        return ehAdequado;
    }     
}
