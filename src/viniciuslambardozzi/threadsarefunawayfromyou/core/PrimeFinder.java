package viniciuslambardozzi.threadsarefunawayfromyou.core;

import sun.awt.image.ImageWatched;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeFinder implements IWorkerManager
{
    private ArrayList<Thread> threadList;

    private LinkedList<BigInteger> primesFound;

    public PrimeFinder()
    {
        threadList = new ArrayList<Thread>();
        primesFound = new LinkedList<BigInteger>();
    }

    public void find(Interval interval, WorkerPrimeFinder.FinderMethod method)
    {
        WorkerPrimeFinder workerPrimeFinder = new WorkerPrimeFinder(this, interval, method);
        Thread thread = new Thread(workerPrimeFinder);
        threadList.add(thread);
        thread.start();
    }

    public LinkedList<BigInteger> getPrimesFound()
    {
        return primesFound;
    }

    @Override
    public void registerWorker(Thread thread)
    {
        threadList.add(thread);
    }

    @Override
    public void joinWorkers()
    {
        for(Thread thread : threadList)
        {
            try
            {
                thread.join();
            } catch (InterruptedException e)
            {
                // Because this is what everyone does
                e.printStackTrace();
            }
        }
    }

    @Override
    public void manageWorkerResult(Worker worker)
    {
        WorkerPrimeFinder finder = (WorkerPrimeFinder)worker;
        primesFound.addAll(finder.getPrimesFound());
    }
}
