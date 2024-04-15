import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //static int MAXIMO_TEMPO_EXECUCAO = 65535;
    static ArrayList<Task> tasks = new ArrayList<>();
    static int n_tasks = 3;
    static Scanner input = new Scanner(System.in);
    static int bt;
    static int at;
    static int prio;

    public static void main(String[] args) throws InterruptedException {
        list_tasks();
        print_tasks();

        while (true) {

            System.out.println("""
                    \n
                    Choose the algorithm?:
                    1 = FCFS
                    2 = Preemptive SJF
                    3 = Non-Preemptive SJF
                    4 = Preemptive Priority
                    5 = Non-preemptive priority
                    6 = Round Robin
                    7 = Prints task list
                    8 = Popular task again
                    9 = exit:\s""");

            String alg = input.nextLine();

            switch (alg) {

                case "1"://FCFS
                    FCFS();
                    break;

                case "2"://SJF PREEMPTIVO
                    SJF(true);
                    break;

                case "3":// SJF NÃO PREEMPTIVO
                    SJF(false);
                    break;

                case "4"://PRIORIDADE PREEMPTIVO
                    Priority(true);
                    break;

                case "5"://PRIORIDADE NÃO PREEMPTIVO
                    Priority(false);
                    break;

                case "6"://Round Robin  
                    RR();
                    break;

                case "7":
                    print_tasks();
                    break;

                case "8":
                    list_tasks();
                    print_tasks();
                    break;

                case "9":
                    return;

            }
        }
    }
    public static void list_tasks(){
        //Cria os processos
        tasks.clear();
        Random random = new Random();
        int aleatory;

        System.out.print("""
                1) Random popular
                2) Manually populate
                Option:\s""");
        aleatory =  input.nextInt();

        for (int i = 0; i < n_tasks; i++) {
            //Cria Processos Aleatorio
            if (aleatory == 1){
                bt = random.nextInt(10)+1;
                at = random.nextInt(10)+1;
                prio = random.nextInt(15)+1;
            }
            //Cria Processos Manualmente
            else {
                System.out.print("Digite o tempo de execução do processo["+i+"]:  ");
                bt = input.nextInt();
                System.out.print("Digite o tempo de chegada do processo["+i+"]:  ");
                at = input.nextInt();
                System.out.print("Digite a prioridade do processo["+i+"]:  ");
                prio = input.nextInt();
            }
            //Adiciona os processos na lista
            tasks.add(new Task(i,bt,at,prio));
        }
        System.out.println();
        input.nextLine();
    }
    public static void print_tasks() throws InterruptedException{
        //Imprime os processos
        for (Task task : tasks){
            task.info();
            Thread.sleep(600);
        }
    }
    public static void print_stats () throws InterruptedException {
        //Implementar o calculo e impressão de estatisticas
        double tempo_espera_total = 0;

        for(Task task : tasks){
            Thread.sleep(200);
            System.out.printf("\nTask[%d]: Wait Time= %d\n",task.getNumber(),task.getWt());
            tempo_espera_total += task.getWt();
        }
        Thread.sleep(200);
        System.out.printf("\nAverage wait time: %.2f \n",(tempo_espera_total/ n_tasks));
    }
    public static void reset_stats(){
        //Método para resetar a lista a seu formato original
        for (Task task : tasks){
            task.setTl(task.getBt());
            task.setWt(0);
        }
    }
    public static void FCFS() throws InterruptedException{
        //implementar código do FCFS
        int time = 1;
        for (Task task : tasks) {//loop principal
            task.setWt(time-1);//seta o valor do tempo de espera
            while (task.getTl() != 0) {//enquanto não terminar a tarefa não termina o loop
                task.setTl(task.getTl() - 1);
                Thread.sleep(200);
                System.out.printf("Time[%d]: Task[%d] Timeleft= %d\n", time, task.getNumber(), task.getTl());
                time++;
            }
        }
        print_stats();
        reset_stats();
    }
    public static void SJF(boolean preempt) throws InterruptedException{
        //implementar código SJF
        int time = 1;
        boolean new_arrival = true;
        int last_task = -1;
        boolean has_task;

        ArrayList<Task> running_tasks = new ArrayList<>();

        //Loop que roda enquanto existem tarefas comtempo restante diferente de 0
        do {//Verifica se algum processo ja chegou e se já não foi add em running_tasks
            // se atender as condições add a tarefa em running_tasks
            for (Task task : tasks) {
                if (task.getAt() <= time && !running_tasks.contains(task) && task.getTl() != 0) {
                    running_tasks.add(task);
                }
            }
            //váriavel para pegar a tarefa com o menor tempo de execuçãp
            int small_bt = 0;
            if (preempt) {//se for preemptivo pega a terefa com menor tempo de execução
                for (int i = 0; i < running_tasks.size(); i++) {
                    if (running_tasks.get(i).getTl() < running_tasks.get(small_bt).getTl()) {
                        small_bt = i;
                    }
                }
            } else {// se for não preemptivo apenas organiza em ordem de chegada
                running_tasks.sort(Comparator.comparingInt(Task::getAt));

                for (int i = 0; i < running_tasks.size(); i++) {
                    for (int j = 1; j < running_tasks.size(); j++) {
                        Task task1 = running_tasks.get(i);
                        Task task2 = running_tasks.get(j);
                        if (task1.getAt() == task2.getAt() && task1.getBt() > task2.getBt()) {
                            // Trocar tarefas se o tempo de chegada for igual e o burst time de task2 for menor
                            running_tasks.set(i, task2);
                            running_tasks.set(j, task1);
                        }
                    }
                }
            }
            if (!running_tasks.isEmpty()) {// se a lista não estiver vazia entra
                //verifica se chegou um novo processo, se não chegou seta o tempo de espera
                if (new_arrival || last_task != running_tasks.get(small_bt).getNumber()) {
                    running_tasks.get(small_bt).setWt(time - running_tasks.get(small_bt).getAt()-(running_tasks.get(small_bt).getBt() - running_tasks.get(small_bt).getTl()));
                    new_arrival = false;
                }
                //se o tempo restante for diferente de 0 diminui 1 do tempo e imprime, depois atribui o número da task a last_task
                if (running_tasks.get(small_bt).getTl() != 0) {
                    running_tasks.get(small_bt).setTl(running_tasks.get(small_bt).getTl() - 1);
                    Thread.sleep(200);
                    System.out.printf("Time[%d]: Task[%d] Timeleft= %d\n", time, running_tasks.get(small_bt).getNumber(), running_tasks.get(small_bt).getTl());
                    last_task = running_tasks.get(small_bt).getNumber();
                    //se for == 0 remove da lista
                    if (running_tasks.get(small_bt).getTl() == 0) {
                        new_arrival = true;
                        running_tasks.remove(running_tasks.get(small_bt));
                    }
                }
            } else {//caso ainda não tenham chegado algum processo
                Thread.sleep(200);
                System.out.printf("Time [%d]: No task ready\n", time);
            }
            time++;
            has_task = false;
            //verifica se todas as tasks foram concluidas, se sim encerra o loop
            for (Task task : tasks) {
                if (task.getTl() > 0) {
                    has_task = true;
                    break;
                }
            }

        } while (has_task);
        print_stats();
        reset_stats();
    }
    public static void Priority(boolean preempt) throws InterruptedException{
        //implementar código Prioridade
        int time = 1;
        boolean new_arrival = true;
        int last_task = -1;
        boolean has_task;

        ArrayList<Task> running_tasks = new ArrayList<>();

        //Loop que roda enquanto existem tarefas com tempo restante diferente de 0
        do {//Verifica se algum processo ja chegou e se já não foi add em running_tasks
            // se atender as condições add a tarefa em running_tasks
            for (Task task : tasks) {
                if (task.getAt() <= time && !running_tasks.contains(task) && task.getTl() != 0) {
                    running_tasks.add(task);
                }
            }
            //váriavel para pegar a tarefa com a maior prioridade
            int high_prio = 0;
            if (preempt) {//se for preemptivo pega a terefa com maior prioridade
                for (int i = 0; i < running_tasks.size(); i++) {
                    if (running_tasks.get(i).getPrio() > running_tasks.get(high_prio).getPrio()) {
                        high_prio = i;
                    }
                }
            } else {// se for não preemptivo organiza em ordem de chegada
                running_tasks.sort(Comparator.comparingInt(Task::getAt));

                for (int i = 0; i < running_tasks.size(); i++) {
                    for (int j = 1; j < running_tasks.size(); j++) {
                        Task task1 = running_tasks.get(i);
                        Task task2 = running_tasks.get(j);
                        if (task1.getAt() == task2.getAt() && task1.getPrio() < task2.getPrio()) {
                            // Trocar tarefas se o tempo de chegada for igual e a prioridade de task2 for menor
                            running_tasks.set(i, task2);
                            running_tasks.set(j, task1);
                        }
                    }
                }
            }
            if (!running_tasks.isEmpty()) {// se a lista não estiver vazia entra
                //verifica se chegou um novo processo, se não chegou seta o tempo de espera
                if (new_arrival || last_task != running_tasks.get(high_prio).getNumber()) {
                    running_tasks.get(high_prio).setWt(time - running_tasks.get(high_prio).getAt()-(running_tasks.get(high_prio).getBt() - running_tasks.get(high_prio).getTl()));
                    new_arrival = false;
                }
                //se o tempo restante for diferente de 0 diminui 1 do tempo e imprime, depois atribui o número da task a last_task
                if (running_tasks.get(high_prio).getTl() != 0) {
                    running_tasks.get(high_prio).setTl(running_tasks.get(high_prio).getTl() - 1);
                    Thread.sleep(200);
                    System.out.printf("Time[%d]: Task[%d] Timeleft= %d\n", time, running_tasks.get(high_prio).getNumber(), running_tasks.get(high_prio).getTl());
                    last_task = running_tasks.get(high_prio).getNumber();
                    //se for == 0 remove da lista
                    if (running_tasks.get(high_prio).getTl() == 0) {
                        new_arrival = true;
                        running_tasks.remove(running_tasks.get(high_prio));
                    }
                }
            } else {//caso ainda não tenham chegado algum processo
                Thread.sleep(200);
                System.out.printf("Time [%d]: No task ready\n", time);
            }
            time++;
            has_task = false;
            //verifica se todas as tasks foram concluidas, se sim encerra o loop
            for (Task task : tasks) {
                if (task.getTl() > 0) {
                    has_task = true;
                    break;
                }
            }

        } while (has_task);
        print_stats();
        reset_stats();
    }
    public static void RR() throws InterruptedException{
         //implementar código do Round Robin
         int time = 1;
         boolean has_task;

         System.out.println("Defina o time slice: ");
         int time_slice = input.nextInt();
         
         do{//loop principal
            for (Task task : tasks) {
                if (task.getTl() == 0) {
                    continue;
                }
                for (int i = 0; i<time_slice; i++) {//loop para o time slice
                    if (task.getTl() == 0) {
                        break;
                    }
                    task.setTl(task.getTl() - 1);
                    Thread.sleep(200);
                    System.out.printf("Time[%d]: Task[%d] Timeleft= %d\n", time, task.getNumber(), task.getTl());
                    time++;
                }
            }
            has_task = false;
            //verifica se todas as tasks foram concluidas, se sim encerra o loop
            for (Task task : tasks) {
                if (task.getTl() > 0) {
                    has_task = true;
                    break;
                }
            }
        }while(has_task);
         reset_stats();
    }
}