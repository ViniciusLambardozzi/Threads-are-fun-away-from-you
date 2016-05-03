package viniciuslambardozzi.threadsarefunawayfromyou.core;

import viniciuslambardozzi.threadsarefunawayfromyou.gui.GuiPrimeFinder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeFinder
{
    /*Mantém um registro de todas as threads para gerenciamento*/
    private ArrayList<WorkerPrimeFinder> workerList;
    private ArrayList<Thread> threadList;

    /* Variável usada como resultado */
    private LinkedList<BigInteger> primesFound;

    public PrimeFinder()
    {
        workerList = new ArrayList<>();
        threadList = new ArrayList<>();
        primesFound = new LinkedList<>();
    }

    /*Procura números primos em interval, com threadNumber threads
    * O intervalo é dividido, e uma thread é criada, salva nas listas de gerenciamento e iniciada para cada parte do intervalo */
    public void find(Interval interval, int threadNumber)
    {
        LinkedList<BigInteger>[] lists = interval.split(threadNumber);
        for(int i = 0; i < lists.length; i++)
        {
            WorkerPrimeFinder worker = new WorkerPrimeFinder(lists[i], i);
            Thread thread = new Thread(worker);
            GuiPrimeFinder.frame.logToOutput("Thread " + i + " created.");
            workerList.add(worker);
            registerWorker(thread);
            thread.start();
        }
    }

    /*Aguarda o término da execução das threads e retorna a lista de primos encontrados */
    public LinkedList<BigInteger> getPrimesFound()
    {
        joinWorkers();

        for(WorkerPrimeFinder worker : workerList)
        {
            primesFound.addAll(worker.getPrimesFound());
        }

        workerList.clear();

        return primesFound;
    }

    /*Salva uma thread na lista de gerenciamento*/
    public void registerWorker(Thread thread)
    {
        threadList.add(thread);
    }

    /*Aguarda o término de uma thread */
    public void joinWorkers()
    {
        GuiPrimeFinder.frame.logToOutput("\n Waiting for threads to finish...\n");
        for(Thread thread : threadList)
        {
            try
            {
                thread.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        threadList.clear();
    }
}
