package viniciuslambardozzi.threadsarefunawayfromyou.core;

import sun.awt.image.ImageWatched;
import viniciuslambardozzi.threadsarefunawayfromyou.core.lib.LibSettings;

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

    public void find(Interval interval)
    {
        LinkedList<BigInteger>[] lists = interval.split(LibSettings.shouldMultiThread ? LibSettings.currThreadNumber : 1);
        for(int i = 0; i < lists.length; i++)
        {
            WorkerPrimeFinder worker = new WorkerPrimeFinder(lists[i]);
            Thread thread = new Thread(worker);
            workerList.add(worker);
            registerWorker(thread);
            thread.start();
        }
    }

    public LinkedList<BigInteger> getPrimesFound()
    {
        joinWorkers();

        long totalTime = 0;

        for(WorkerPrimeFinder worker : workerList)
        {
            primesFound.addAll(worker.getPrimesFound());
            totalTime += worker.timer.getElapsedTime();
            System.out.println("Worker time: " + worker.timer.getElapsedTime());
        }
        System.out.println("Total time: " + totalTime);
        workerList.clear();

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
    }
}
