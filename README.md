# 💬 Sistema de Chat em Java (Cliente–Servidor com Threads)

![Java](https://img.shields.io/badge/Java-21-blue)
![Status](https://img.shields.io/badge/Status-Concluído-success)
![License](https://img.shields.io/badge/Licença-MIT-green)
![IDE](https://img.shields.io/badge/IDE-VSCode-blue)

---

## 📖 Sobre o Projeto

Este projeto implementa um **sistema de chat em tempo real** desenvolvido em **Java**, utilizando o modelo **cliente-servidor** e o conceito de **programação concorrente com threads**.  
O principal objetivo é permitir que múltiplos usuários se comuniquem simultaneamente por meio de **sockets TCP/IP**, simulando um chat funcional no terminal.

O sistema foi construído com **arquitetura modular**, garantindo separação de responsabilidades entre as classes e comunicação eficiente entre os componentes.  
Todo o desenvolvimento foi realizado no **Visual Studio Code**, com testes feitos em terminais independentes para o servidor e múltiplos clientes.

---

## 🧠 Arquitetura do Sistema

A aplicação segue o padrão **Cliente–Servidor Multithreaded**, com o seguinte fluxo de comunicação:

```
Cliente 1 ⇄ ClientHandler ⇄ Servidor ⇄ ClientHandler ⇄ Cliente 2 ... Cliente N
```

### 🔹 Componentes Principais

- **ChatServer** → Classe responsável por iniciar o servidor, escutar conexões e gerenciar os clientes conectados.  
- **ClientHandler** → Classe que gerencia a comunicação de cada cliente, rodando em uma thread independente.  
- **ChatClient** → Aplicação cliente usada pelo usuário para enviar e receber mensagens no chat.

Cada cliente conectado ao servidor possui sua própria thread, o que permite comunicação simultânea entre todos os usuários, sem travamentos.

---

## 🏗️ Estrutura do Projeto

```
📁 ProjetoChatJava/
│
├── ChatServer.java      # Classe principal do servidor
├── ChatClient.java      # Aplicação cliente (usuário)
├── ClientHandler.java   # Controla a comunicação de cada cliente
│
├── ChatServer.class
├── ChatClient.class
└── ClientHandler.class
```

---

## ⚙️ O Que Foi Feito

Durante o desenvolvimento do sistema, foram implementados os seguintes recursos e melhorias:

- Comunicação **bidirecional** entre cliente e servidor via **sockets TCP/IP**.  
- **Gerenciamento de múltiplos clientes simultaneamente** usando threads.  
- Estrutura modular com **3 classes principais**, cada uma com responsabilidade clara.  
- Implementação de um **protocolo simples de mensagens** (`NICK` para apelido e mensagens de texto).  
- Tratamento de **desconexões inesperadas** e liberação segura de recursos.  
- Testes realizados em **ambiente local com terminais simultâneos**, simulando diversos usuários.  
- Produção de um relatório técnico completo e documentação detalhada.

---

## 🚀 Como Executar o Projeto

### 📦 Pré-requisitos
- ☕ **Java 11+** instalado no computador  
- 💻 Qualquer terminal (CMD, PowerShell, Linux ou macOS)  
- 🧰 Editor de código (recomendado: **Visual Studio Code**)

---

### 🧱 1️⃣ Compilar o Projeto

Abra o terminal na pasta do projeto e execute:
```bash
javac *.java
```

Isso vai compilar todas as classes `.java`, gerando os arquivos `.class`.

---

### ▶️ 2️⃣ Iniciar o Servidor

Em um terminal separado, execute:
```bash
java ChatServer
```
Saída esperada:
```
Servidor de Chat iniciando...
Servidor escutando na porta 55555
```

---

### 💬 3️⃣ Iniciar os Clientes

Abra novos terminais (quantos quiser) e execute:
```bash
java ChatClient
```
Você verá:
```
Escolha seu apelido: Luyz
```

Agora os clientes podem enviar e receber mensagens em tempo real:
```
Luyz: Olá pessoal!
Pimvolim: E aí! Tudo bem?
```

Cada cliente funciona de forma independente, e o servidor retransmite as mensagens para todos os outros.

---

## 🧩 Tecnologias Utilizadas

| Tecnologia | Descrição |
|-------------|------------|
| ☕ **Java SE 21+** | Linguagem de programação principal |
| 🔗 **Sockets TCP/IP** | Comunicação de rede entre cliente e servidor |
| 🧵 **Threads** | Controle de múltiplas conexões simultâneas |
| 🧮 **Collections.synchronizedList** | Evita conflitos entre threads |
| 💻 **Visual Studio Code** | Ambiente de desenvolvimento |
| 🖥️ **Terminal / CMD** | Interface de execução e testes |

---

## 🧠 Dificuldades e Soluções

| Desafio | Solução Implementada |
|----------|----------------------|
| Sincronização entre threads | Uso de `Collections.synchronizedList()` |
| Bloqueio de leitura | Criação de threads separadas para entrada e saída |
| Desconexões inesperadas | Método `removeClient()` que remove e fecha conexões |
| Testes simultâneos | Execução com múltiplos terminais para simular usuários reais |

---

## 📈 Resultados Obtidos

O sistema de chat atingiu todos os objetivos propostos:  
✅ Comunicação em tempo real entre múltiplos clientes.  
✅ Servidor robusto com suporte a conexões simultâneas.  
✅ Código modular, limpo e fácil de manter.  
✅ Testes bem-sucedidos com simulação de usuários reais.  

O resultado final é um **chat de terminal funcional e escalável**, servindo como base para aplicações futuras com interface gráfica ou integração em rede local.

---

## 🖼️ Exemplo de Funcionamento

Servidor e dois clientes trocando mensagens em tempo real:

![Servidor rodando](./470cb202-ef83-4e9d-b4a8-11e3f2dcf1e6.jpg)
![Clientes trocando mensagens](./c14e1574-2177-4909-b2f1-b60b2d087ce2.jpg)

---

## 🧭 Próximos Passos

- Criar interface gráfica com **JavaFX** ou **Swing**.  
- Adicionar autenticação de usuários.  
- Implementar criptografia ponta a ponta.  
- Salvar histórico de conversas em banco de dados.  

---
