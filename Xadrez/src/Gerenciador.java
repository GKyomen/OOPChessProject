import java.util.Scanner;

public class Gerenciador {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String op, jB, jN;
        System.out.println("Bem vindo ao Xadrez em Java");
        gerenciador:
        while(true) {
            System.out.println("As opções são:\n1 - Novo jogo\n2 - Fechar o jogo");
            op = teclado.nextLine();
            switch(op) {
                case "1":
                    System.out.println("Informe o nome do jogador de peças brancas:");
                    jB = teclado.nextLine();
                    System.out.println("Informe o nome do jogador de peças pretas:");
                    jN = teclado.nextLine();
                    new Jogo(jB, jN);
                    System.out.println("Obrigado por jogar!");
                    break gerenciador;
                case "2":
                    System.out.println("Obrigado por utilizar o gerenciador");
                    break gerenciador;
                default:
                    System.out.println("Opção inválida, por favor digite sua Opção novamente.");
                    break;
            }
        }
        teclado.close();
    }
}
