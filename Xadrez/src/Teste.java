public class Teste {
    public static void main(String[] args) {
        /* Jogo jogo1 = new Jogo(true);
        Jogo jogo2 = new Jogo(false);
        System.out.println("O jogo 1 está na vez do jogador 1? " + jogo1.getEhVezDoJogador1());
        System.out.println("O jogo 2 está na vez do jogador 1? " + jogo2.getEhVezDoJogador1());
        jogo2.iniciaJogo();
        System.out.println("O jogo 2 está na vez do jogador 1? " + jogo2.getEhVezDoJogador1()); */

    }

    /* public void todosTestes() {
        //----- teste 1 -----
        Peao p1 = new Peao("preto");
        Peao p2 = new Peao("branco");
        int movPossiveis = 0;

        System.out.println("PEÃO PRETO");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(p1.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        movPossiveis = 0;
        System.out.println("PEÃO BRANCO");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(p2.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis); 

        //----- teste 2 -----
        Torre t1 = new Torre("preto");
        Torre t2 = new Torre("branco");

        movPossiveis = 0;
        System.out.println("TORRE PRETA");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(t1.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        movPossiveis = 0;
        System.out.println("TORRE BRANCA");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(t2.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        //----- teste 3 -----
        Cavalo c = new Cavalo("preto");

        movPossiveis = 0;
        System.out.println("CAVALO");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(c.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        //----- teste 4 -----
        Bispo b1 = new Bispo("preto");
        Bispo b2 = new Bispo("branco");

        movPossiveis = 0;
        System.out.println("BISPO PRETO");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(b1.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        movPossiveis = 0;
        System.out.println("BISPO BRANCO");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(b2.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        //----- teste 5 -----
        Dama d = new Dama("preto");

        movPossiveis = 0;
        System.out.println("DAMA");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(d.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        //----- teste 6 -----
        Rei r = new Rei("preto");
        movPossiveis = 0;
        System.out.println("REI");
        System.out.println("Movimentos possíveis:");
        for (int i = -1; i <= 8; i++) {
            for (int j = -1; j <= 8; j++) {
                for (int k = -1; k <= 8; k++) {
                    for (int l = -1; l <=8; l++) {
                        if(r.checaMovimento(i, j, k, l)) {
                            System.out.println("De (" + i + ", " + j + ") Para (" + k + ", " + l + ").");
                            movPossiveis++;
                        }
                    }
                }
            }
        }
        System.out.println("Total: " + movPossiveis);

        //----- teste 7 -----
        Peao pp = new Peao("preto");
        Peao pb = new Peao("branco");
        Torre tp = new Torre("preto");
        Torre tb = new Torre("branco");
        Cavalo cp = new Cavalo("preto");
        Cavalo cb = new Cavalo("branco");
        Bispo bp = new Bispo("preto");
        Bispo bb = new Bispo("branco");
        Dama dp = new Dama("preto");
        Dama db = new Dama("branco");
        Rei rp = new Rei("preto");
        Rei rb = new Rei("branco");

        System.out.println(pp.desenho());
        System.out.println(pb.desenho());
        System.out.println(tp.desenho());
        System.out.println(tb.desenho());
        System.out.println(cp.desenho());
        System.out.println(cb.desenho());
        System.out.println(bp.desenho());
        System.out.println(bb.desenho());
        System.out.println(dp.desenho());
        System.out.println(db.desenho());
        System.out.println(rp.desenho());
        System.out.println(rb.desenho());

        pp.setStatus(false);
        pb.setStatus(false);
        tp.setStatus(false);
        tb.setStatus(false);
        cp.setStatus(false);
        cb.setStatus(false);
        bp.setStatus(false);
        bb.setStatus(false);
        dp.setStatus(false);
        db.setStatus(false);
        rp.setStatus(false);
        rb.setStatus(false);

        System.out.println("Tentativa de imprimir peças fora de jogo:");

        System.out.println(pp.desenho());
        System.out.println(pb.desenho());
        System.out.println(tp.desenho());
        System.out.println(tb.desenho());
        System.out.println(cp.desenho());
        System.out.println(cb.desenho());
        System.out.println(bp.desenho());
        System.out.println(bb.desenho());
        System.out.println(dp.desenho());
        System.out.println(db.desenho());
        System.out.println(rp.desenho());
        System.out.println(rb.desenho());

        System.out.println("Fim da tentativa");

        //----- teste 8 -----
        Posicao[][] posicoes = new Posicao[8][8];
        char coluna;
        int linha;
        boolean ocupada = false;
        for (int i = 0; i < 8; i++) {
            linha = 7 - i;
            for (int j = 0; j < 8; j++) {
                coluna = (char) (j + 'a'); //usando ASCII para saber a letra da coluna
                posicoes[i][j] = new Posicao(linha, coluna);
                if(ocupada) {
                    posicoes[i][j].setOcupada(ocupada);
                }
                ocupada = !ocupada;
                System.out.println("POSICAO:");
                System.out.println("Coordenadas: " + posicoes[i][j].getLinha() + "ª linha, coluna " + posicoes[i][j].getColuna());
                System.out.println("Cor: " + posicoes[i][j].getCor());
                System.out.println("Está ocupada? " + posicoes[i][j].getOcupada());
            }
        }

        //----- teste 9 -----
        Tabuleiro tab = new Tabuleiro();
        tab.desenhaTabuleiro();
        for (int i = -1; i < 9; i++) {
            for (int j = -1; j < 9; j++) {
                if(!tab.checaDestino(i, (char) (j + 'a'))) {
                    System.out.println("(" + i + ", " + (char) (j + 'a') + ") não está nos limites.");
                }                
            }
        }
    } */
}
