/*
Feito por Gabriel da Silva Kyomen
771008
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        this.iniciaJogo(jBrancas, jPretas); //inicializa os atributos do jogo
        this.jogar(); //inicia as jogadas. Para somente quando o jogo é encerrado ou salvo.
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
                coord = teclado.next();

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
                        if(solicitouEmpate == 1) { //se havia sido solicitado o empate, significa que o jogador anterior não tinha jogado.
                            this.ehVezDoJogador1 = !this.ehVezDoJogador1;
                        }
                        salvarJogo();
                        break jogo;
                    //se foi uma jogada normal e tinha sido solicitado empate antes, nega a solicitação
                    } else if(solicitouEmpate == 1) {
                        System.out.println("Empate não aceito por " + jogador);
                        solicitouEmpate = 0;
                    }

                    //imprime mensagem caso ocorra uma mudança significativa ou seja uma jogada inválida, passando a vez ou encerrando o jogo quando válido
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
                } catch(InputMismatchException | NumberFormatException | StringIndexOutOfBoundsException e) { //caso a linha inserida não seja um número, invalida a jogada e solicita novamente
                    System.out.println("Entrada inválida. Insira outras coordenadas com atenção ao formato e número de digitos: ");
                    jogada = -1;
                }
                
            } while(jogada == -1);
        }

        teclado.close();
    }

    //função que carrega o jogo a partir de um arquivo e retoma do ponto em que parou
	public void carregaJogo() {
        Scanner sc = new Scanner(System.in);
        String nomePartida, linha, posicao; //nome da partida, linha do arquivo e posicao do tabuleiro
        int nLinhas = 0, nPeca = 0, nPecaAtiva = 0; //linhas lidas, posicoes lidas e peças ativas encontradas
        Peca pecas[] = new Peca[64]; //vetor que o tabuleiro irá manipular para guardar as peças na posição que o jogo foi interrompido
        this.pecas = new Peca[32]; //aloca espaço pras peças
        this.jogadores = new Jogador[2]; //aloca espaço pros jogadores
        //pede um nome de arquivo até que consiga recuperar
        while(true) {
            System.out.println("Para carregar um jogo, informe o nome exato da partida (o mesmo digitado ao salvar)");
            nomePartida = sc.nextLine();
            try {
                //tenta criar o objeto do arquivo
                File partida = new File(nomePartida + ".txt");
                if(partida.exists()) { //se o arquivo existe
                    //cria o leitor
                    FileReader fr = new FileReader(partida);
                    BufferedReader br = new BufferedReader(fr);
                    while(br.ready()) { //até acabar o arquivo
                        linha = br.readLine(); //lê a próxima linha
                        nLinhas++; //salva o número de linhas lidas
                        switch(nLinhas) { //de acordo com o número da linha lida
                            //nas duas primeiras, estão os jogadores
                            case 1:
                            case 2:
                                this.jogadores[nLinhas - 1] = new Jogador(linha, nLinhas); //cria o jogador e salva, ainda sem as peças
                                break;

                            //na terceira linha está o estado do jogo
                            case 3:
                                this.estadoDoJogo = Integer.parseInt(linha); //salva o estado
                                break;

                            //na quarta linha está de quem é a vez
                            case 4:
                                if(linha == "jogadorb")
                                    this.ehVezDoJogador1 = true;
                                else
                                    this.ehVezDoJogador1 = false;
                                break;

                            //da quinta em diante está o tabuleiro
                            default:
                                //cada casa ocupa 2 caracteres, então percorre a linha de 2 em 2
                                for (int i = 0; i < linha.length(); i+= 2) {
                                    posicao = "" + linha.charAt(i) + linha.charAt(i + 1); //salva os dois caracteres
                                    switch(posicao) {
                                        case "  ": //se for uma casa vazia
                                            pecas[nPeca++] = null; //salva peça nula
                                            break;

                                        case "P+": //peao branco
                                            pecas[nPeca++] = new Peao("branco"); //cria e salva peao branco
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "P-": //peao preto
                                            pecas[nPeca++] = new Peao("preto"); //cria e salva peao preto 
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "T+": //torre branca
                                            pecas[nPeca++] = new Torre("branco"); //cria e salva torre branca
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "T-": //torre preta
                                            pecas[nPeca++] = new Torre("preto"); //cria e salva torre preta
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "C+": //cavalo branco
                                            pecas[nPeca++] = new Cavalo("branco"); //cria e salva cavalo branco
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "C-": //cavalo preto
                                            pecas[nPeca++] = new Cavalo("preto"); //cria e salva cavalo preto
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "B+": //bispo branco
                                            pecas[nPeca++] = new Bispo("branco"); //cria e salva bispo branco
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "B-": //bispo preto
                                            pecas[nPeca++] = new Bispo("preto"); //cria e salva bispo preto
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;
                                        
                                        case "D+": //dama branca
                                            pecas[nPeca++] = new Dama("branco"); //cria e salva dama branca
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "D-": //dama preta
                                            pecas[nPeca++] = new Dama("preto"); //cria e salva dama preta
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "R+": //rei branco
                                            pecas[nPeca++] = new Rei("branco"); //cria e salva rei branco 
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;

                                        case "R-": //rei preto
                                            pecas[nPeca++] = new Rei("preto"); //cria e salva rei preto
                                            this.pecas[nPecaAtiva++] = pecas[nPeca - 1]; //salva no vetor de peças do jogo
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    //depois de acabar o arquivo, já sabe todas as peças no tabuleiro, então instancia ele e salva as peças de cada jogador
                    this.tabuleiro = new Tabuleiro(pecas, this.jogadores);
                    //fecha os leitores
                    br.close();
                    fr.close();
                    break;
                }
            } catch(IOException e) { //trata possíveis erros de arquivo, pedindo por outro nome
                System.out.println("Ocorreu um erro. Tente novamente.");
            }
        }
        jogar();
        sc.close();
    }
    
    //função que salva o jogo em um determinado ponto em um arquivo de texto
    private void salvarJogo() {
        Scanner sc = new Scanner(System.in);
        String nomePartida;
        //pede um nome de arquivo até que consiga salvar
        while(true) {
            System.out.println("Para salvar a partida, digite um nome para o arquivo sem a extensão.");
            System.out.println("Lembre-se que este será o nome para voltar nessa partida, então anote-o.");
            nomePartida = sc.nextLine();
            try {
                //tenta criar o objeto do arquivo
                File partida = new File(nomePartida + ".txt");
                if(!partida.exists()) { //se o nome de arquivo estiver livre
                    partida.createNewFile(); //cria o arquivo com esse nome
                    FileWriter fw = new FileWriter(partida); //cria o escritor
                    //salva as informações (nome e cor) dos jogadores
                    fw.write(jogadores[0].getNome() + "\n");
                    fw.write(jogadores[1].getNome() + "\n");
                    //salva o estado do jogo
                    fw.write(String.valueOf(this.estadoDoJogo) + "\n");
                    //salva de quem é a vez (b para brancas e p para pretas)
                    if(this.ehVezDoJogador1)
                        fw.write("jogadorb\n");
                    else
                        fw.write("jogadorp\n");
                    //salva o tabuleiro em versão texto para recuperar as posições e peças posteriormente
                    fw.write(this.tabuleiro.toString());
                    //fecha os leitores/escritores e avisa que a partida foi salva com sucesso
                    fw.close();
                    System.out.println("Partida salva com êxito.");
                    break;
                } else { //caso o arquivo com esse nome já exista, pede por outro nome.
                    System.out.println("Arquivo já existe. Escolha outro nome.");
                }

            } catch(IOException e) { //trata possíveis erros de arquivo, pedindo por outro nome
                System.out.println("Ocorreu um erro. Tente novamente com outro nome");
            }
        }
        sc.close();
    }
}