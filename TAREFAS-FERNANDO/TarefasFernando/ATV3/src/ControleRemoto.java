// Arquivo: ControleRemoto.java

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A interface remota que define o contrato entre o cliente e o servidor.
 * Contém todas as ações que um controle remoto pode executar.
 */
public interface ControleRemoto extends Remote {

    // Liga a TV
    void ligar() throws RemoteException;

    // Desliga a TV
    void desligar() throws RemoteException;

    // Aumenta o volume em 1 ponto
    void aumentarVolume() throws RemoteException;

    // Diminui o volume em 1 ponto
    void diminuirVolume() throws RemoteException;

    // Avança para o próximo canal
    void avancarCanal() throws RemoteException;

    // Volta para o canal anterior
    void voltarCanal() throws RemoteException;

    // Alterna o estado de mudo (se está mudo, tira o mudo; se não está, coloca no mudo)
    void alternarMudo() throws RemoteException;

    // Retorna uma String formatada com todas as informações atuais da TV
    String obterStatus() throws RemoteException;
}