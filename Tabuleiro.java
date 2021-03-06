/*
Feito por Gabriel da Silva Kyomen
771008
*/
public class Tabuleiro {
    private final Posicao[][] posicoes = new Posicao[8][8]; 

    public Tabuleiro(Peca pecas[], Jogador jogadores[]) { //construtor usado para retomar um jogo, recebe 64 peças (mínimo 32 nulas) e salva as peças nos jogadores
        char coluna;
        int linha, nPeca = 0, nBrancas = 0, nPretas = 0;
        Peca brancas[] = new Peca[16], pretas[] = new Peca[16];  //cria os vetores de peça dos jogadores
        //cria as posicoes com as caracteristicas corretas
        for(int i = 0; i < 8; i++) {
            linha = 8 - i;
            for(int j = 0; j < 8; j++) {
                coluna = (char) (j + 'a'); //usando ASCII para saber a letra da coluna
                this.posicoes[i][j] = new Posicao(linha, coluna); //cria a posicao
                if(pecas[nPeca] == null) { //se a peça existir, marca posição ocupada e guarda a peça, senão guarda nulo mesmo
                    this.posicoes[i][j].setOcupada(false);
                    this.posicoes[i][j].setPeca(pecas[nPeca++]);
                } else {
                    this.posicoes[i][j].setOcupada(true);
                    this.posicoes[i][j].setPeca(pecas[nPeca++]);
                }
            }
        }
        //separa as pecas brancas e pretas
        for(Peca peca : pecas) {
            if(peca != null) { //se tem uma peça
                if(peca.getCor() == "branco") //se é branca
                    brancas[nBrancas++] = peca; //salva nas brancas
                else //se é preta
                    pretas[nPretas++] = peca; //salva nas pretas
            }
        }
        //passa para os jogadores
        jogadores[0].setPecas(brancas);
        jogadores[1].setPecas(pretas);
    }

    public Tabuleiro(Peca pecas[]) { //construtor usado no caso de jogo do 0
        inicializaTabuleiro(pecas); //inicializa as posicoes com as peças nos locais iniciais
    }

    //inicia as posições do tabuleiro com as peças no devido lugar
    private void inicializaTabuleiro(Peca pecas[]) {
        char coluna;
        int linha, nPeca = 0;
        for(int i = 0; i < 8; i++) {
            linha = 8 - i;
            for(int j = 0; j < 8; j++) {
                coluna = (char) (j + 'a'); //usando ASCII para saber a letra da coluna
                this.posicoes[i][j] = new Posicao(linha, coluna);
                if(i == 0 || i == 1 || i == 6 || i == 7) { //marca ocupada nas 1as e ultimas linhas, colocando a peça
                    this.posicoes[i][j].setOcupada(true);
                    this.posicoes[i][j].setPeca(pecas[nPeca++]);
                }
            }
        }
    }
    
    //desenha as posições e as peças ainda em jogo
    public void desenhaTabuleiro() {
        int i = 8;
        System.out.println(" a   b   c   d   e   f   g   h\n");
        for(Posicao[] linha : this.posicoes) {
            for(Posicao posicao : linha) {
                if(posicao.getOcupada()) 
                    System.out.print(" " + posicao.getPeca().desenho() + " ");
                else
                    System.out.print("    ");
            }
            System.out.println(" " + i + "\n");
            i--;    
        }
    }

    //sobrescreve o toString para poder salvar o jogo numa determinada jogada
    @Override
    public String toString() {
        String tabuleiro = "";
        for(Posicao[] linha : this.posicoes) {
            for(Posicao posicao : linha) {
                if(posicao.getOcupada())
                    tabuleiro = tabuleiro + posicao.getPeca().desenho();
                else
                    tabuleiro = tabuleiro + "  ";
            }
            tabuleiro = tabuleiro + "\n";
        }
        return tabuleiro;
    }

    //verifica se uma posição está dentro do tabuleiro
    public boolean checaPosicao(int linha, int coluna) {
        return (linha >= 0 && linha <= 7 && coluna >= 0 && coluna <= 7);
    }
  
    //função que vai efetivamente fazer o movimento da peça
    private void movePeca(Posicao inicio, Posicao fim, String corPeca) {
        //Vê se o destino está vazio ou se está ocupado por uma peça do adversário e o realiza
        if(!fim.getOcupada()) {
            fim.setOcupada(true);
        } else if(fim.getOcupada() && fim.getPeca().getCor() != corPeca) {
            fim.getPeca().setStatus(false);
        }
        fim.setPeca(inicio.getPeca());
        inicio.setOcupada(false);
        inicio.setPeca(null);
    }

    //retorna, a partir de 2 posições: "n" quando não há caminho,
    // "hd" ou "he" para horizontal esq/dir,
    // "vs" ou "vd" para vertical subindo/descendo,
    // "ds" ou "dd" ou "es" ou "ed" para diagonais dir/sub, dir/desc, esq/sub, esq/desc
    // "c" para caminho que o cavalo pode fazer
    private String qualCaminho(Posicao inicio, Posicao fim) {
        //transforma as coordenadas em número
        int lInicio = inicio.getLinha(), cInicio = (int) (inicio.getColuna() - 'a'),
            lFim = fim.getLinha(), cFim = (int) (fim.getColuna() - 'a');

        //calcula as variações de linha e coluna
        int deltaL = lFim - lInicio, deltaC = cFim - cInicio;

        if(deltaC == 0 && deltaL == 0)
            return "n";

        if(deltaC == 0 && deltaL != 0) { //caso vertical
            if(deltaL < 0) //para baixo
                return "vd";
            else //para cima
                return "vs";
        } else if(deltaC != 0 && deltaL == 0) { //caso horizontal
            if(deltaC < 0) //para esq
                return "he";
            else //para dir
                return "hd";
        } else if(deltaC ==  deltaL || deltaC == -deltaL) { //caso diagonal
            if(deltaL < 0) { //para baixo
                if(deltaC < 0) //para esq
                    return "ed";
                else //para dir
                    return "dd";
            } else { //para cima
                if(deltaC < 0) //para esq
                    return "es";
                else //para dir
                    return "ds";
            }
        } else if((Math.abs(deltaC) == 2 && Math.abs(deltaL) == 1) || (Math.abs(deltaL) == 2 && Math.abs(deltaC) == 1)) { 
            return "c"; //movimentos de cavalo
        }
        return "n"; //não há caminho
    }

    //verifica se o caminho entre duas posições está livre
    private boolean caminhoLivre(Posicao inicio, Posicao fim, Posicao[][] posicoes) {
        String c = qualCaminho(inicio, fim); //descobre o caminho

        if(c == "c") //caminho sempre está livre para o cavalo
            return true;

        //transforma as coordenadas em número que serão usados na matriz
        int lInicio = 8 - inicio.getLinha(), cInicio = (int) (inicio.getColuna() - 'a'),
            lFim = 8 - fim.getLinha(), cFim = (int) (fim.getColuna() - 'a');

        switch(c) {
            //percorre o caminho se ele for um dos eixos, retornando falso se alguma posicao no meio está ocupada
            case "hd":
                for(int i = cInicio + 1; i < cFim; i++) {
                    if(posicoes[lFim][i].getOcupada())
                        return false;
                }
                break;
            case "he":
                for(int i = cInicio - 1; i > cFim; i--) {
                    if(posicoes[lFim][i].getOcupada())
                        return false;
                }   
                break;
            case "vd":
                for(int i = lInicio + 1; i < lFim; i++) {
                    if(posicoes[i][cFim].getOcupada())
                        return false;
                }
                break;
            case "vs":
                for(int i = lInicio - 1; i > lFim; i--) {
                    if(posicoes[i][cFim].getOcupada())
                        return false;
                }
                break;
            case "ds":
                for(int i = lInicio - 1, j = cInicio + 1; i > lFim && j < cFim; i--, j++) {
                    if(posicoes[i][j].getOcupada())
                        return false;
                }
                break;
            case "dd":
                for(int i = lInicio + 1, j = cInicio + 1; i < lFim && j < cFim; i++, j++) {
                    if(posicoes[i][j].getOcupada())
                        return false;
                }
                break;
            case "es":
                for(int i = lInicio - 1, j = cInicio - 1; i > lFim && j > cFim; i--, j--) {
                    if(posicoes[i][j].getOcupada())
                        return false;
                }
                break;
            case "ed":
                for(int i = lInicio + 1, j = cInicio - 1; i < lFim && j > cFim; i++, j--) {
                    if(posicoes[i][j].getOcupada())
                        return false;
                }
                break;
            default: //falso para sem caminho
                return false;
        }
        return true;
    }

    //verifica se uma determinada peça ataca em uma determinada direção
    private boolean atacaNaDirecao(Peca p, String direcao) {
        if(direcao == "n") //sem direcao, não tem como atacar
            return false;

        if(p instanceof Peao && p.getCor() == "preto" && (direcao == "dd" || direcao == "ed")) {
            return true; //peao preto diagonais pra baixo
        } else if(p instanceof Peao && p.getCor() == "branco" && (direcao == "ds" || direcao == "es")) {
            return true; //peao branco diagonais pra cima
        }else if(p instanceof Torre && (direcao == "hd" || direcao == "he" || direcao == "vs" || direcao == "vd")) {
            return true; //torre horizontais e verticais
        }else if(p instanceof Cavalo && direcao == "c") { 
            return true; //cavalo nas posições específicas
        } else if(p instanceof Bispo && (direcao == "dd" || direcao == "ed" || direcao == "ds" || direcao == "es")) {
            return true; //bispo diagonais
        } else if(p instanceof Dama && direcao != "c") {
            return true; //dama tudo exceto cavalo
        } else if(p instanceof Rei && direcao != "c") {
            return true; //rei tudo exceto cavalo
        }
        return false;
    }

    //verifica se alguma peça de uma cor está atacando uma determinada posição
    private boolean estaAtacada(Posicao p, String corAtacante, Posicao[][] posicoes) {
        //para cada posicao no tabuleiro
        for(Posicao[] linhas : posicoes) {
            for(Posicao pos : linhas) {
                int dLinha = p.getLinha() - pos.getLinha(); //salva a mudança de linha por conta do peao e do rei, que só atacam uma casa para frente
                //salva a mudança de coluna por conta do rei, que só ataca uma casa para o lado
                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                int dColuna = colP - colPos;
                //se esta ocupada por uma peca da cor atacante
                if(pos.getOcupada())
                    if(pos.getPeca().getCor() == corAtacante)
                        //se a peça ataca na direção entre as posicoes
                        if(atacaNaDirecao(pos.getPeca(), qualCaminho(pos, p)))
                            //se é um peão e é uma linha de diferença ou se não é um peão
                            if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei))
                                    //se o caminho está livre, sim, a posição está atacada
                                    if(caminhoLivre(pos, p, posicoes))
                                        return true;
            }
        }
        //falso para qualquer outro caso
        return false;
    }

    //retorna as posições das peças encontradas que atacam uma posição
    private Posicao[] posicaoAtacante(Posicao p, String corAtacante, Posicao[][] posicoes) {
        int nAtacantes = 0;
        Posicao[] aux = new Posicao[16];
        //para cada posicao no tabuleiro
        for(Posicao[] linhas : posicoes) {
            for(Posicao pos : linhas) {
                int dLinha = p.getLinha() - pos.getLinha(); //salva a mudança de linha por conta do peao e do rei, que só atacam uma casa para frente
                //salva a mudança de coluna por conta do rei, que só ataca uma casa para o lado
                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                int dColuna = colP - colPos;
                //se esta ocupada por uma peca da cor atacante
                if(pos.getOcupada())
                    if(pos.getPeca().getCor() == corAtacante)
                        //se a peça ataca na direção entre as posicoes
                        if(atacaNaDirecao(pos.getPeca(), qualCaminho(pos, p)))
                            //se é um peão e é uma linha de diferença ou se não é um peão
                            if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                            //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei))
                                    //se o caminho está livre, sim, a posição está atacada
                                    if(caminhoLivre(pos, p, posicoes))
                                        aux[nAtacantes++] = pos;
            }
        }
        
        //cria um array do tamanho exato do número de atacantes, salva os atacantes e retorna
        Posicao[] atacantes = new Posicao[nAtacantes];
        for (int i = 0; i < atacantes.length; i++)
            atacantes[i] = aux[i];
        return atacantes;
    }

    //verifica se uma posicao está atacada a partir de uma certa direção
    private boolean estaAtacadaNaDirecao(Posicao p, String corAtacante, String direcao, Posicao[][] posicoes) {
        //transforma as coordenadas em número que serão usados na matriz
        int lin = 8 - p.getLinha(), col = (int) (p.getColuna() - 'a');

        switch(direcao) {
            case "hd":
                for(int i = col + 1; i < 8; i++) {
                    Posicao pos = posicoes[lin][i];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            case "he":
                for(int i = col - 1; i >= 0; i--) {
                    Posicao pos = posicoes[lin][i];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            case "vd":
                for(int i = lin + 1; i < 8; i++) {
                    Posicao pos = posicoes[i][col];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            case "vs":
                for(int i = lin - 1; i >= 0; i--) {
                    Posicao pos = posicoes[i][col];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            case "ds":
                for(int i = lin - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
                    Posicao pos = posicoes[i][j];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            case "dd":
                for(int i = lin + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
                    Posicao pos = posicoes[i][j];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            case "es":
                for(int i = lin - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                    Posicao pos = posicoes[i][j];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            case "ed":
                for(int i = lin + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
                    Posicao pos = posicoes[i][j];
                    if(pos.getOcupada()) {
                        if(pos.getPeca().getCor() == corAtacante) {
                            if(atacaNaDirecao(pos.getPeca(), direcao)) {
                                int dLinha = p.getLinha() - pos.getLinha();
                                int colP = (int) (p.getColuna() - 'a'), colPos = (int) (pos.getColuna() - 'a');
                                int dColuna = colP - colPos;
                                //se é um peão e é uma linha de diferença ou se não é um peão
                                if((pos.getPeca() instanceof Peao && (dLinha == 1 || dLinha == -1)) || !(pos.getPeca() instanceof Peao))
                                    //se é um rei e tem no máximo uma casa de distância ou se não é um rei
                                    if((pos.getPeca() instanceof Rei && ((dLinha == 1 || dLinha == 0 || dLinha == -1) && (dColuna == 1 || dColuna == 0 || dColuna == -1))) || !(pos.getPeca() instanceof Rei)) 
                                        return true;
                            }
                        }
                        break;
                    }
                }
                return false;
            default: //falso para sem caminho e cavalo (não tem xeque descoberto de cavalo)
                return false;
        }
    }

    //função que retorna a posição do rei de uma determinada cor
    private Posicao procuraRei(String cor, Posicao[][] posicoes) {
        for(Posicao[] linhas : posicoes)
            for(Posicao p : linhas)
                if(p.getOcupada())
                    if(p.getPeca() instanceof Rei && p.getPeca().getCor() == cor)
                        return p;
        return null;
    }

    // verifica se o jogo está em xeque (Se quem moveu foram as brancas, verifica se
    // as pretas estão em xeque e vice-versa)
    private boolean verificaXeque(String corAtacante, Posicao pRei, Posicao[][] posicoes) {
        //vê se a casa do rei atacado está atacada
        return estaAtacada(pRei, corAtacante, posicoes);
    }

    //verifica se a posição do rei de uma determinada cor fica desprotegida ao realizar um movimento para uma posição objetivo
    //retorna falso se pelo menos um movimento não desprotege a casa do rei
    private boolean xequeDescoberto(Posicao posRei, Posicao objetivo, String corPeca, Posicao[][] posicoes) {
        String corAtacante = corPeca == "branco" ? "preto" : "branco";
        //pega referência para todas as posições que possuem uma peça da cor do rei que conseguem ir até o objetivo
        Posicao[] atacaObjetivo = posicaoAtacante(objetivo, corPeca, posicoes);
        for (Posicao pos : atacaObjetivo) { //para cada uma dessas posições
            if(pos.getPeca() instanceof Peao && objetivo.getOcupada()) { //se for um peão e no destino estiver um adversário
                if(objetivo.getPeca().getCor() == corAtacante) 
                    //se o rei não está atacado na direção entre ele e essa posição, o movimento não desprotege o rei
                    if(!estaAtacadaNaDirecao(pos, corAtacante, qualCaminho(posRei, pos), posicoes)) 
                        return false;
            } else if(!(pos.getPeca() instanceof Peao) && !(pos.getPeca() instanceof Rei)) { //se não for o peão nem o próprio rei (rei indo em direção ao ataque com certeza é xeque)
                //se o rei não está atacado na direção entre ele e essa posição, o movimento não desprotege o rei
                if(!estaAtacadaNaDirecao(pos, corAtacante, qualCaminho(posRei, pos), posicoes)) 
                    return false;
            }
        }
        return true; //se nenhum movimento é capaz de ser feito sem desproteger o rei, retorna verdadeiro
    }
    
    //verifica se o jogo está em xeque-mate (Se quem moveu foram as brancas, verifica se as pretas estão em xeque-mate e vice-versa)
    private boolean verificaXequeMate(String corAtacante, Posicao[][] posicoes) {
        //salva a cor de quem está sendo atacado, a posição do rei e os atacantes do rei
        String corAtacado = corAtacante == "branco" ? "preto" : "branco";
        Posicao pRei = procuraRei(corAtacado, posicoes);
        Posicao posAtacante[] = posicaoAtacante(pRei, corAtacante, posicoes);

        //vê se o rei tem casa de fuga (se tiver fuga não é xeque-mate)
        int lin = 8 - pRei.getLinha(), col = (int) pRei.getColuna() - 'a';
        for(int i = lin - 1; i <= (lin + 1); i++) 
            for(int j = col - 1; j <= (col + 1); j++) 
                if(checaPosicao(i, j)) //posicao existe
                    if(!posicoes[i][j].getOcupada() && !estaAtacada(posicoes[i][j], corAtacante, posicoes)) //está livre e não atacada
                        if(qualCaminho(pRei, posicoes[i][j]) != qualCaminho(posAtacante[0], pRei)) //não está na mesma direção em que o rei sofre o ataque
                            return false;
        
        //verifica se alguma peça pode capturar o atacante sem descobrir o rei
        if(posAtacante.length == 1 && estaAtacada(posAtacante[0], corAtacado, posicoes)) { //só é possível se só tiver um atacante
            //verifica se ao atacar não desprotege o rei
            if(xequeDescoberto(pRei, posAtacante[0], corAtacado, posicoes) == false)
                return false; //se pelo menos um ataque não causa xeque descoberto, não é mate
        }

        //verifica se alguma peça pode cobrir o ataque
        if(posAtacante.length == 1 && !(posAtacante[0].getPeca() instanceof Cavalo)) { //só dá se não for um cavalo e se for só um
            String c = qualCaminho(pRei, posAtacante[0]); //pega o tipo de caminho
            int limLin = 8 - posAtacante[0].getLinha(), limCol = (int) (posAtacante[0].getColuna() - 'a'); //pega as coordenadas da última casa do caminho
            //percorre e para cada posição, vê se alguma peça pode ocupá-la sem descobrir o rei
            switch(c) {
                case "hd":
                    for(int i = col + 1; i <= limCol; i++) 
                        if(xequeDescoberto(pRei, posicoes[lin][i], corAtacado, posicoes) == false)
                            return false;
                    break;
                case "he":
                    for(int i = col - 1; i >= limCol; i--) 
                        if(xequeDescoberto(pRei, posicoes[lin][i], corAtacado, posicoes) == false)
                            return false;
                    break;
                case "vd":
                    for(int i = lin + 1; i < limLin; i++) 
                        if(xequeDescoberto(pRei, posicoes[i][col], corAtacado, posicoes) == false)
                            return false;
                    break;
                case "vs":
                    for(int i = lin - 1; i >= limLin; i--) 
                        if(xequeDescoberto(pRei, posicoes[i][col], corAtacado, posicoes) == false)
                            return false;
                    break;
                case "ds":
                    for(int i = lin - 1, j = col + 1; i >= limLin && j < limCol; i--, j++) 
                        if(xequeDescoberto(pRei, posicoes[i][j], corAtacado, posicoes) == false)
                            return false;
                    break;
                case "dd":
                    for(int i = lin + 1, j = col + 1; i < limLin && j < limCol; i++, j++) 
                        if(xequeDescoberto(pRei, posicoes[i][j], corAtacado, posicoes) == false)
                            return false;
                    break;
                case "es":
                    for(int i = lin - 1, j = col - 1; i >= limLin && j >= limCol; i--, j--) 
                        if(xequeDescoberto(pRei, posicoes[i][j], corAtacado, posicoes) == false)
                            return false;
                    break;
                case "ed":
                    for(int i = lin + 1, j = col - 1; i < limLin && j >= limCol; i++, j--) 
                        if(xequeDescoberto(pRei, posicoes[i][j], corAtacado, posicoes) == false)
                            return false;
                    break;
                default: //falso para sem caminho 
                    return false;
            }
        }
        //se não deu falso em nada, é xeque-mate
        return true;
    }

    //verifica se o jogo não pode acabar devido a insuficiencia de peças
    private boolean verificaInsuficiencia(Peca[] pecas) {
        Peca pecasAtivas[] = new Peca[3];
        int nPAtivas = 0;

        //coloca todas as peças ativas em um vetor
        for(int i = 0; i < pecas.length; i++) {
            if(pecas[i].getStatus()) {
                if(nPAtivas < 3)
                    pecasAtivas[nPAtivas++] = pecas[i];
                else
                    return false; //só há insuficiencia material para situações de 3 peças
            }
        }

        //se estiver nas situações abaixo, é insuficiência:
        //1. rei vs rei e cavalo
        //2. rei vs rei e bispo
        //3. rei vs rei e peão já que não foi implementada a promoção do peão
        for(Peca peca : pecasAtivas) {
            if(peca instanceof Torre || peca instanceof Dama)
                return false;
        }
        return true;
    }

    //essa função não é muito eficiente... tentar pensar em uma maneira melhor
    //simula uma jogada e verifica se o estado do jogo não é mais xeque (nem xeque-mate)
    private boolean saiuDoXeque(Posicao origem, Posicao destino, String corPeca, String corAtacante) {
        //copia o tabuleiro inteiro (faz copias das peças também) no estado atual
        Posicao[][] tabuleiroAux = new Posicao[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiroAux[i][j] = new Posicao(this.posicoes[i][j].getLinha(), this.posicoes[i][j].getColuna());
                if(this.posicoes[i][j].getOcupada()) {
                    tabuleiroAux[i][j].setOcupada(true);
                    if(this.posicoes[i][j].getPeca() instanceof Peao) {
                        tabuleiroAux[i][j].setPeca(new Peao(this.posicoes[i][j].getPeca().getCor()));
                    }else if(this.posicoes[i][j].getPeca() instanceof Torre) {
                        tabuleiroAux[i][j].setPeca(new Torre(this.posicoes[i][j].getPeca().getCor()));
                    }else if(this.posicoes[i][j].getPeca() instanceof Cavalo) {
                        tabuleiroAux[i][j].setPeca(new Cavalo(this.posicoes[i][j].getPeca().getCor()));
                    }else if(this.posicoes[i][j].getPeca() instanceof Bispo) {
                        tabuleiroAux[i][j].setPeca(new Bispo(this.posicoes[i][j].getPeca().getCor()));
                    }else if(this.posicoes[i][j].getPeca() instanceof Dama) {
                        tabuleiroAux[i][j].setPeca(new Dama(this.posicoes[i][j].getPeca().getCor()));
                    }else {
                        tabuleiroAux[i][j].setPeca(new Rei(this.posicoes[i][j].getPeca().getCor()));
                    }
                } else {
                    tabuleiroAux[i][j].setOcupada(false);
                    tabuleiroAux[i][j].setPeca(null);
                }
            }
        }
        //salva as posições recebidas só que no tabuleiro auxiliar
        Posicao simOrigem = tabuleiroAux[8 - origem.getLinha()][(int) (origem.getColuna() - 'a')];
        Posicao simDestino = tabuleiroAux[8 - destino.getLinha()][(int) (destino.getColuna() - 'a')];
        
        //faz o movimento desejado
        movePeca(simOrigem, simDestino, corPeca);
        
        //retorna se o jogo saiu do xeque
        return verificaXeque(corAtacante, procuraRei(corPeca, tabuleiroAux), tabuleiroAux) == false;
    }

    //realiza um movimento caso seja possível
    public int movimentar(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino, String corPeca, int estadoDoJogo, Peca pecas[]) {
        //transforma as coordenadas em número e salva as diferenças
        int lOrigem = 8 - linhaOrigem, cOrigem = (int) (colunaOrigem - 'a'),
            lDestino = 8 - linhaDestino, cDestino = (int) (colunaDestino - 'a'),
            deltaL = lDestino - lOrigem, deltaC = cDestino - cOrigem;

        //salva a cor do atacante
        String corAtacante = corPeca == "branco" ? "preto" : "branco";

        //verifica a existência das casas de origem e de destino
        boolean posicoesValidas = checaPosicao(lOrigem, cOrigem) && checaPosicao(lDestino, cDestino);
        if(posicoesValidas) {
            //pega referencia às posições daquelas coordenadas
            Posicao origem = this.posicoes[lOrigem][cOrigem],
                    destino = this.posicoes[lDestino][cDestino];

            //movimenta se for possível, e retorna o novo estado do jogo
            if(verificaMovimento(origem, destino, deltaL, deltaC, corPeca, corAtacante, estadoDoJogo)){
                movePeca(origem, destino, corPeca);
                if(verificaXeque(corPeca, procuraRei(corAtacante, this.posicoes), this.posicoes)) {
                    if(verificaXequeMate(corPeca, this.posicoes)) {
                        return 2;
                    }
                    return 1;
                } else if(verificaInsuficiencia(pecas)) {
                    return 3;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        }
        return -1;
    }

    // verifica se um movimento é possivel
    private boolean verificaMovimento(Posicao origem, Posicao destino, int deltaL, int deltaC, String corPeca, String corAtacante, int estadoDoJogo) {
        //transforma as coordenadas em número
        int lOrigem = origem.getLinha(), cOrigem = (int) (origem.getColuna() - 'a'),
            lDestino = destino.getLinha(), cDestino = (int) (destino.getColuna() - 'a');

        //retorna falso se for a mesma posicao
        if(deltaL == 0 && deltaC == 0)
            return false;

        //verifica se a posicao inicial esta ocupada por uma peça da cor recebida
        if(origem.getOcupada() && origem.getPeca().getCor() == corPeca) {
            //checa o movimento da peça
            if(origem.getPeca().checaMovimento(lOrigem, cOrigem, lDestino, cDestino)) {
                //verifica se o caminho está livre
                if(caminhoLivre(origem, destino, this.posicoes)) {
                    //Se for o Rei: verificar se a casa destino não está atacada
                    if(origem.getPeca() instanceof Rei) {
                        if(!estaAtacada(destino, corAtacante, this.posicoes)) {
                            //verifica se a casa destino está livre ou ocupada por uma atacante
                            if(!destino.getOcupada() || (destino.getOcupada() && destino.getPeca().getCor() == corAtacante))
                                return true;
                        }
                    //Se for outras: verificar se não desprotege o rei
                    } else {
                        boolean xequeDescoberto = estaAtacadaNaDirecao(origem, corAtacante, qualCaminho(procuraRei(corPeca, this.posicoes), origem), this.posicoes);
                        if(xequeDescoberto) {
                            return false;
                        } else {
                            //OBS Peão: checar se o movimento é de andar ou capturar
                            if(origem.getPeca() instanceof Peao) {
                                //andar e destino vazio
                                if(!destino.getOcupada() && deltaC == 0) {
                                    //só deixa jogar se não está em xeque ou se saiu do xeque que estava
                                    if((estadoDoJogo == 1 && saiuDoXeque(origem, destino, corPeca, corAtacante)) || estadoDoJogo != 1)
                                        return true;
                                //capturar e destino tem um adversário
                                } else if(destino.getOcupada() && destino.getPeca().getCor() == corAtacante && deltaC != 0) {
                                    //só deixa jogar se não está em xeque ou se saiu do xeque que estava
                                    if((estadoDoJogo == 1 && saiuDoXeque(origem, destino, corPeca, corAtacante)) || estadoDoJogo != 1)
                                        return true;
                                }
                            } else {
                                //verifica se a casa destino está livre ou ocupada por uma atacante
                                if(!destino.getOcupada() || (destino.getOcupada() && destino.getPeca().getCor() == corAtacante))
                                    //só deixa jogar se não está em xeque ou se saiu do xeque que estava
                                    if((estadoDoJogo == 1 && saiuDoXeque(origem, destino, corPeca, corAtacante)) || estadoDoJogo != 1)
                                        return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
