package viniciuslambardozzi.threadsarefunawayfromyou.core;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;

public class WorkerPrimeFinder extends Worker
{
    public enum FinderMethod
    {
        NORMAL,
        ERATHOSTENES,
    }

    private Interval interval;
    private FinderMethod method;

    private LinkedList<BigInteger> primesFound;

    public WorkerPrimeFinder(IWorkerManager manager, Interval interval, FinderMethod method)
    {
        super(manager);

        primesFound = new LinkedList<BigInteger>();

        this.interval = interval;
        this.method = method;
    }

    @Override
    protected void onWorkerStart()
    {

    }

    @Override
    protected void work()
    {
        switch (method)
        {
            case NORMAL:
                while(!interval.getInterval().isEmpty())
                {
                    BigInteger i = interval.getInterval().pollFirst();
                    boolean isPrime = true;
                    if(i.compareTo(BigInteger.valueOf(0)) == 0)
                    {
                        isPrime = false;
                    }else if(i.compareTo(BigInteger.valueOf(1)) == 0)
                    {
                        isPrime = false;
                    }else
                    {
                        for (BigInteger j = BigInteger.valueOf(2); j.compareTo(i.divide(BigInteger.valueOf(2)).add(BigInteger.ONE)) < 0; j = j.add(BigInteger.ONE))
                        {
                            if (i.mod(j) == BigInteger.ZERO)
                            {
                                isPrime = false;
                                break;
                            }
                        }
                    }
                    if(isPrime)
                    {
                        primesFound.add(i);
                    }
                }
                break;
            case ERATHOSTENES:
                break;
            default:
                // Something went horribly wrong
        }
    }

    @Override
    protected void onWorkerFinish()
    {
        manager.manageWorkerResult(this);
    }


    public LinkedList<BigInteger> getPrimesFound()
    {
        return primesFound;
    }

}