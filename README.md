#Simulação de Algoritmos de Escalonamento

Este projeto é uma simulação de diferentes algoritmos de escalonamento de processos em sistemas operacionais. A simulação permite aos usuários escolher entre vários algoritmos e observar como cada um deles gerencia a execução de processos.

##Conceitos usados

**Programação Orientada a Objeto**
**Algoritimos de Escalonamento**

##Funcionalidades

Algoritmos de Escalonamento Implementados:

**FCFS (First-Come, First-Served)**
**SJF (Shortest Job First) Preemptivo e Não-Preemptivo**
**Prioridade Preemptivo e Não-Preemptivo**
**Round Robin**

Modo de Popular Tarefas:

**Aleatoriamente**
**Manualmente**

Impressão de Estatísticas:

**Tempo de Espera para cada tarefa**
**Tempo Médio de Espera**

##Estrutura do Projeto

O projeto está organizado da seguinte forma:

Main.java: Este arquivo contém a lógica principal do programa, incluindo a interação com o usuário, escolha de algoritmos de escalonamento, e gerenciamento das tarefas.
Task.java: Esta classe representa uma tarefa. Cada tarefa possui atributos como tempo de execução (bt), tempo de chegada (at) e prioridade (prio). A classe também contém métodos para manipular e acessar esses atributos.

##Exemplo do Menu

**FCFS**
**SJF Preemptivo**
**SJF Não-Preemptivo**
**Prioridade Preemptivo**
**Prioridade Não-Preemptivo**
**Round Robin**
**Imprimir Lista de Tarefas**
**Popular Tarefas Novamente**
**Sair**
