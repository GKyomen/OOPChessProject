import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jogo {
    private int estadoDoJogo;
    private Peca pecas[] = new Peca[32];
    private Tabuleiro tabuleiro;
    private Jogador jogadores[] = new Jogador[2];
    private boolean ehVezDoJogador1;

    public Jogo() {
        this.estadoDoJogo = -1;
        this.tabuleiro = null;
        this.ehVezDoJogador1 = false;
    }

    public Jogo(String jBrancas, String jPretas) {
        this.iniciaJogo(jBrancas, jPretas);
        this.jogar();
    }

    //inicializa os atributos necessários para o jogo
    public void iniciaJogo(String jBrancas, String jPretas) {
        Peca brancas[] = new Peca[16];
        Peca pretas[] = new Peca[16];
        
        //inicia o jogo e coloca como vez do primeiro jogador
        this.estadoDoJogo = 0;
        this.ehVezDoJogador1 = true;

        //inicializa as peças do jogo
        this.pecas = new Peca[32];
        this.pecas[0] = new Torre("preto");
        this.pecas[1] = new Cavalo("preto");
        this.pecas[2] = new Bispo("preto");
        this.pecas[3] = new Dama("preto");
        this.pecas[4] = new Rei("preto");
        this.pecas[5] = new Bispo("preto");
        this.pecas[6] = new Cavalo("preto");
        this.pecas[7] = new Torre("preto");
        for (int i = 8; i <= 15; i++)
            this.pecas[i] = new Peao("preto");
        for (int i = 16; i <= 23; i++)
            this.pecas[i] = new Peao("branco");
        this.pecas[24] = new Torre("branco");
        this.pecas[25] = new Cavalo("branco");
        this.pecas[26] = new Bispo("branco");
        this.pecas[27] = new Dama("branco");
        this.pecas[28] = new Rei("branco");
        this.pecas[29] = new Bispo("branco");
        this.pecas[30] = new Cavalo("branco");
        this.pecas[31] = new Torre("branco");
        
        //inicializa o tabuleiro com referencia para as peças criadas, evitando duplicar peças
        this.tabuleiro = new Tabuleiro(this.pecas);

        //inicializa os jogadores
        for (int i = 0; i < 16; i++) //as primeiras 16 peças são pretas
            pretas[i] = this.pecas[i];
        this.jogadores[1] = new Jogador(jPretas, 2, pretas);
            
        for (int i = 16; i < 32; i++)
            brancas[i - 16] = this.pecas[i]; //as 16 últimas peças são brancas
        this.jogadores[0] = new Jogador(jBrancas, 1, brancas);

        //mostra as informações dos jogadores
        System.out.println("Informações dos Jogadores:");
        for(Jogador j : jogadores)
            j.infoJogador();
    }

    private void jogar() {
        Scanner teclado = new Scanner(System.in);
        char colunaOrigem, colunaDestino;
        int linhaOrigem, linhaDestino;
        String coord, corPeca, jogador;
        int jogada, solicitouEmpate = 0;

        jogo:
        while(true) {
            //mostra o tabuleiro após a última jogada
            System.out.println("-------------Tabuleiro atual-------------\n");
            this.tabuleiro.desenhaTabuleiro();
            System.out.println("");

            //capta informações do jogador atual
            if(this.ehVezDoJogador1) {
                corPeca = this.jogadores[0].getCor();
                jogador = this.jogadores[0].getNome();
            } else {
                corPeca = this.jogadores[1].getCor();
                jogador = this.jogadores[1].getNome();
            }

            //exibe o menu
            System.out.println(jogador + ", por favor, informe as coordenas da sua jogada no formato abaixo");
            System.out.println("colunaLinhaColunaLinha\n(coluna é uma letra de 'a' até 'h' e Linha é um número de '1' até '8')");
            System.out.println("Comandos especiais:\ninforme q0q0 para desistir");
            if(solicitouEmpate == 0) 
                System.out.println("informe q1q1 para solicitar empate");
            else
                System.out.println("informe q1q1 para aceitar o empate proposto");
            System.out.println("informe q2q2 para salvar a partida no ponto atual e sair.");

            do{ //pede uma jogada até que ela seja válida
                coord = teclado.nextLine();

                try { //tenta transformar as coordenadas em colunas e linhas, e então realizar a jogada
                    colunaOrigem = coord.charAt(0);
                    linhaOrigem = Integer.parseInt(String.valueOf(coord.charAt(1)));
                    colunaDestino = coord.charAt(2);
                    linhaDestino = Integer.parseInt(String.valueOf(coord.charAt(3)));

                    //caso seja solicitado a desistencia, anuncia o fato e finaliza o jogo
                    if(colunaOrigem == 'q' && colunaDestino == 'q' && linhaOrigem == 0 && linhaDestino == 0) {
                        System.out.println(jogador + " desistiu. Fim de jogo");
                        break jogo;
                    //caso seja solicitado o empate
                    } else if(colunaOrigem == 'q' && colunaDestino == 'q' && linhaOrigem == 1 && linhaDestino == 1) {
                        if(solicitouEmpate == 0) { //se não tinha sido solicitado antes, marca e passa a vez
                            System.out.println(jogador + " acaba de solicitar empate");
                            this.ehVezDoJogador1 = !this.ehVezDoJogador1;
                            solicitouEmpate = 1;
                            continue jogo;
                        } else { //se já tinha sido solicitado, empata e finaliza
                            System.out.println("Partida empatada por decisão de ambos jogadores.");
                            System.out.println("-------------Tabuleiro Final-------------\n");
                            this.tabuleiro.desenhaTabuleiro();
                            System.out.println("");
                            break jogo;
                        }
                    //caso seja solicitado para salvar o jogo e sair, salva as informações e sai
                    } else if(colunaOrigem == 'q' && colunaDestino == 'q' && linhaOrigem == 2 && linhaDestino == 2) {
                        salvarJogo();
                        break jogo;
                    //se foi uma jogada normal e tinha sido solicitado empate antes, nega a solicitação
                    } else if(solicitouEmpate == 1) {
                        System.out.println("Empate não aceito por " + jogador);
                        solicitouEmpate = 0;
                    }

                    //imprime algo caso ocorra uma mudança significativa ou seja uma jogada inválida, passando a vez ou encerrando o jogo quando válido
                    jogada = this.tabuleiro.movimentar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, corPeca, this.estadoDoJogo, this.pecas);
                    if(jogada == -1) { 
                        System.out.println("Jogada inválida. Insira outras coordenadas: ");
                    } else if(jogada == 0) {
                        this.ehVezDoJogador1 = !this.ehVezDoJogador1;
                        this.estadoDoJogo = 0;
                    } else if(jogada == 1) {
                        System.out.println("Agora o jogo está em xeque! Tire o rei do perigo");
                        this.ehVezDoJogador1 = !this.ehVezDoJogador1;
                        this.estadoDoJogo = 1;
                    } else if(jogada == 2) {
                        System.out.println("Xeque-mate! Fim de jogo, parabéns " + jogador + " pela vitória");
                        System.out.println("-------------Tabuleiro Final-------------\n");
                        this.tabuleiro.desenhaTabuleiro();
                        System.out.println("");
                        this.estadoDoJogo = 2;
                        break jogo;
                    } else if(jogada == 3) {
                        System.out.println("Fim de jogo por insuficiência material.");
                        System.out.println("-------------Tabuleiro Final-------------\n");
                        this.tabuleiro.desenhaTabuleiro();
                        System.out.println("");
                        this.estadoDoJogo = 3;
                        break jogo;
                    }
                } catch(InputMismatchException | NumberFormatException e) { //caso a linha inserida não seja um número, invalida a jogada e solicita novamente
                    System.out.println("Entrada inválida. Insira outras coordenadas com atenção ao formato: ");
                    jogada = -1;
                }
                
            } while(jogada == -1);
        }

        teclado.close();
    }

    public boolean getEhVezDoJogador1() {
        return this.ehVezDoJogador1;
    }

	public void carregaJogo() {

    }
    
    private void salvarJogo() {
        Scanner sc = new Scanner(System.in);
        String nomePartida;
        while(true) {
            System.out.println("Para salvar a partida, digite um nome para o arquivo sem a extensão.");
            System.out.println("Lembre-se que este será o nome para voltar nessa partida, então anote-o.");
            nomePartida = sc.nextLine();
            try {
                File partida = new File(nomePartida + ".txt");
                if(!partida.exists()) {
                    partida.createNewFile();
                    FileWriter fw = new FileWriter(partida);
                    fw.write(jogadores[0].infoJogadorString());
                    fw.write(jogadores[1].infoJogadorString());
                    fw.write(String.valueOf(this.estadoDoJogo) + "\n");
                    if(this.ehVezDoJogador1)
                        fw.write("brancas\n");
                    else
                        fw.write("pretas\n");
                    fw.write(this.tabuleiro.toString());
                    fw.close();
                    System.out.println("Partida salva com êxito.");
                    break;
                } else {
                    System.out.println("Arquivo já existe. Escolha outro nome.");
                }

            } catch (IOException e) {
                System.out.println("Ocorreu um erro. Tente novamente com outro nome");
            }
        }
        sc.close();
    }
}