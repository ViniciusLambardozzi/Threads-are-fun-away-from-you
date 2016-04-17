package viniciuslambardozzi.threadsarefunawayfromyou.sandbox;

import viniciuslambardozzi.threadsarefunawayfromyou.core.Interval;
import viniciuslambardozzi.threadsarefunawayfromyou.core.PrimeFinder;
import viniciuslambardozzi.threadsarefunawayfromyou.core.WorkerPrimeFinder;

import java.math.BigInteger;
import java.util.LinkedList;

public class Main
{
    public static void main(String[] args)
    {
        Interval interval = new Interval(BigInteger.valueOf(1), BigInteger.valueOf(60000), BigInteger.valueOf(2));

        PrimeFinder finder = new PrimeFinder();

        finder.find(interval, WorkerPrimeFinder.FinderMethod.NORMAL);

        finder.joinWorkers();

        LinkedList<BigInteger> primes = finder.getPrimesFound();

        for(BigInteger i : primes)
        {
            System.out.println(i);
        }
    }
}
