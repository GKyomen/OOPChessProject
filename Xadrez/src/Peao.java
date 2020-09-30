public class Peao {
    private String cor;
    private boolean status;
    
    public Peao(String cor) {
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
                representacao = "P-";
            else 
                representacao = "P+";
        }
        return representacao;
    }

    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        boolean ehAdequado = false;
        int diffColuna = colunaDestino - colunaOrigem;
        int diffLinha = linhaDestino - linhaOrigem;
        if (diffColuna == 0) {
            if (this.getCor() == "preto")
                if ( (diffLinha == -1) || (diffLinha == -2 && linhaOrigem == 6) ) 
                    ehAdequado = true;
            else
                if ( (diffLinha == 1) || (diffLinha == 2 && linhaOrigem == 1) )
                    ehAdequado = true;
        } else if (diffColuna == 1 || diffColuna == -1) {
            if (this.getCor() == "preto" && diffLinha == -1)
                ehAdequado = true;
            else if (this.getCor() == "branco" && diffLinha == 1)
                ehAdequado = true;
        }
        return ehAdequado;
    }
}
