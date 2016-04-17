package viniciuslambardozzi.threadsarefunawayfromyou.core;

import sun.awt.image.ImageWatched;
import viniciuslambardozzi.threadsarefunawayfromyou.core.lib.LibSettings;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeFinder implements IWorkerManager
{
    private ArrayList<WorkerPrimeFinder> workerList;
    private ArrayList<Thread> threadList;

    private LinkedList<BigInteger> primesFound;

    public PrimeFinder()
    {
        /*
        Worker time:7230
        Worker time:15779
        Worker time:21305
        Worker time:25606

        Worker time:50437
        */
        workerList = new ArrayList<>();
        threadList = new ArrayList<>();
        primesFound = new LinkedList<>();
    }

    public void find(Interval interval, WorkerPrimeFinder.FinderMethod method)
    {
        LinkedList<BigInteger>[] lists = interval.split(LibSettings.shouldMultiThread ? LibSettings.currThreadNumber : 1);
        for(int i = 0; i < lists.length; i++)
        {
            WorkerPrimeFinder worker = new WorkerPrimeFinder(this, lists[i], method);
            Thread thread = new Thread(worker);
            workerList.add(worker);
            registerWorker(thread);
            thread.start();
        }
    }

    public LinkedList<BigInteger> getPrimesFound()
    {
        joinWorkers();

        return primesFound;
    }

    public void registerWorker(Thread thread)
    {
        threadList.add(thread);
    }

    public void joinWorkers()
    {
        System.out.println(threadList.size());
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
        threadList.clear();

        for(WorkerPrimeFinder worker : workerList)
        {
            System.out.println("Worker time:" + worker.timer.getElapsedTime());
        }
        workerList.clear();
    }

    @Override
    public void manageWorkerResult(Worker worker)
    {
        WorkerPrimeFinder finder = (WorkerPrimeFinder)worker;
        primesFound.addAll(finder.getPrimesFound());
    }
}
