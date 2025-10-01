// Arquivo: Cliente.java

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            // Procura pelo objeto remoto no servidor
            // IMPORTANTE: Se o servidor estiver em outra máquina, troque "localhost" pelo IP do servidor.
            ControleRemoto controle = (ControleRemoto) Naming.lookup("//localhost/ControleRemotoService");

            Scanner scanner = new Scanner(System.in);
            int escolha = -1;

            System.out.println("Cliente conectado ao servidor de Controle Remoto!");

            while (escolha != 0) {
                // Exibe o menu de opções
                System.out.println("\n--- MENU DO CONTROLE REMOTO ---");
                System.out.println("1. Ligar a TV");
                System.out.println("2. Desligar a TV");
                System.out.println("3. Aumentar Volume");
                System.out.println("4. Diminuir Volume");
                System.out.println("5. Avançar Canal");
                System.out.println("6. Voltar Canal");
                System.out.println("7. Ativar/Desativar Mudo");
                System.out.println("8. Mostrar Informações da TV");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                try {
                    escolha = scanner.nextInt();

                    switch (escolha) {
                        case 1:
                            controle.ligar();
                            System.out.println("Comando [LIGAR] enviado.");
                            break;
                        case 2:
                            controle.desligar();
                            System.out.println("Comando [DESLIGAR] enviado.");
                            break;
                        case 3:
                            controle.aumentarVolume();
                            System.out.println("Comando [AUMENTAR VOLUME] enviado.");
                            break;
                        case 4:
                            controle.diminuirVolume();
                            System.out.println("Comando [DIMINUIR VOLUME] enviado.");
                            break;
                        case 5:
                            controle.avancarCanal();
                            System.out.println("Comando [AVANÇAR CANAL] enviado.");
                            break;
                        case 6:
                            controle.voltarCanal();
                             System.out.println("Comando [VOLTAR CANAL] enviado.");
                            break;
                        case 7:
                            controle.alternarMudo();
                            System.out.println("Comando [MUDO] enviado.");
                            break;
                        case 8:
                            // Não faz nada aqui, pois o status será exibido após o switch
                            break;
                        case 0:
                            System.out.println("Saindo... Obrigado por usar o controle remoto RMI!");
                            continue; // Pula a exibição do status final
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            continue;
                    }

                    // Após cada ação, exibe o status atual da TV
                    System.out.println("\n" + controle.obterStatus());

                } catch (java.util.InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    scanner.next(); // Limpa o buffer do scanner
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}