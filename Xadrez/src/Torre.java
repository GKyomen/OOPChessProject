public class Torre {
    private String cor;
    private boolean status;
    
    public Torre(String cor) {
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
                representacao = "T+";
            else 
                representacao = "T-";
        }
        return representacao;
    }

    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        return false;
    }
}
