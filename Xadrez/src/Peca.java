/*
Feito por Gabriel da Silva Kyomen
771008
*/
public abstract class Peca {
    private String cor;
    private boolean status;
    
    public Peca(String cor) {
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

    abstract public String desenho();

    abstract public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino);
}
