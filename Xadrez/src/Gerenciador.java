/*
Feito por Gabriel da Silva Kyomen
771008
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gerenciador {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int op;
        String opcao, jB, jN;
        System.out.println("Bem vindo ao Xadrez em Java");
        gerenciador:
        while(true) {
            System.out.println("As opções são:\n1 - Novo jogo\n2 - Carregar partida anterior\n3 - Fechar o jogo");
            opcao = teclado.nextLine();
            try {
                op = Integer.parseInt(opcao);
                switch(op) {
                    case 1: //opcao de iniciar um jogo do zero
                        //recebe o nome dos jogadores
                        System.out.println("Informe o nome do jogador de peças brancas:");
                        jB = teclado.nextLine();
                        System.out.println("Informe o nome do jogador de peças pretas:");
                        jN = teclado.nextLine();
                        //inicia o jogo
                        new Jogo(jB, jN);
                        System.out.println("Obrigado por jogar!");
                        break gerenciador;
                    case 2: //opcao de continuar partida
                        Jogo jogo = new Jogo();
                        jogo.carregaJogo();
                        System.out.println("Obrigado por utilizar o gerenciador");
                        break gerenciador;
                    case 3: //opcao de sair
                        System.out.println("Obrigado por utilizar o gerenciador");
                        break gerenciador;
                    default: //opcao fora do menu
                        System.out.println("Opção inválida, por favor digite sua Opção novamente.");
                        break;
                }
            } catch(InputMismatchException | NumberFormatException | StringIndexOutOfBoundsException e) { //caso tenha sido inserido algo que não é um inteiro
                System.out.println("Entrada inválida. Insira um número inteiro, por favor."); //avisa e tenta novamente
            }
            
        }
        teclado.close(); //fecha o teclado após o uso
    }
}
