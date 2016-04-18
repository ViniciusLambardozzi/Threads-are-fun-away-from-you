package viniciuslambardozzi.threadsarefunawayfromyou.core;

import viniciuslambardozzi.threadsarefunawayfromyou.core.lib.LibSettings;
import viniciuslambardozzi.threadsarefunawayfromyou.gui.GuiPrimeFinder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeFinder
{
    private ArrayList<WorkerPrimeFinder> workerList;
    private ArrayList<Thread> threadList;

    private LinkedList<BigInteger> primesFound;

    public PrimeFinder()
    {
        workerList = new ArrayList<>();
        threadList = new ArrayList<>();
        primesFound = new LinkedList<>();
    }

    public void find(Interval interval)
    {
        LinkedList<BigInteger>[] lists = interval.split(LibSettings.shouldMultiThread ? LibSettings.currThreadNumber : 1);
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

    public void registerWorker(Thread thread)
    {
        threadList.add(thread);
    }

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
