# Simulação de Algoritmos de Escalonamento

Este projeto é uma simulação de diferentes algoritmos de escalonamento de processos em sistemas operacionais. A simulação permite aos usuários escolher entre vários algoritmos e observar como cada um deles gerencia a execução de processos.

<br>

## Conceitos usados

**Programação Orientada a Objeto**

**Algoritimos de Escalonamento**

<br>

## Funcionalidades

### Algoritmos de Escalonamento Implementados:

<br>

**FCFS (First-Come, First-Served)**

**SJF (Shortest Job First) Preemptivo e Não-Preemptivo**

**Prioridade Preemptivo e Não-Preemptivo**

**Round Robin**

<br>

### Modo de Popular Tarefas:

**Aleatoriamente**

**Manualmente**

<br>

### Impressão de Estatísticas:

**Tempo de Espera para cada tarefa**

**Tempo Médio de Espera**

<br>

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

Main.java: Este arquivo contém a lógica principal do programa, incluindo a interação com o usuário, escolha de algoritmos de escalonamento, e gerenciamento das tarefas.

Task.java: Esta classe representa uma tarefa. Cada tarefa possui atributos como tempo de execução (bt), tempo de chegada (at) e prioridade (prio). A classe também contém métodos para manipular e acessar esses atributos.
<br>
## Exemplo do Menu

1. **FCFS**
2. **SJF Preemptivo**
3. **SJF Não-Preemptivo**
4. **Prioridade Preemptivo**
5. **Prioridade Não-Preemptivo**
6. **Round Robin**
7. **Imprimir Lista de Tarefas**
8. **Popular Tarefas Novamente**
9. **Sair**
