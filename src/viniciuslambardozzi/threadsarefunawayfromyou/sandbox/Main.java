package viniciuslambardozzi.threadsarefunawayfromyou.sandbox;

import viniciuslambardozzi.threadsarefunawayfromyou.core.Interval;
import viniciuslambardozzi.threadsarefunawayfromyou.core.PrimeFinder;
import viniciuslambardozzi.threadsarefunawayfromyou.core.WorkerPrimeFinder;
import viniciuslambardozzi.threadsarefunawayfromyou.core.util.Timer;

import java.math.BigInteger;
import java.util.LinkedList;

public class Main
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();

        Interval interval = new Interval(BigInteger.valueOf(1), BigInteger.valueOf(500000), BigInteger.valueOf(2));

        PrimeFinder finder = new PrimeFinder();

        timer.start();
        finder.find(interval, WorkerPrimeFinder.FinderMethod.NORMAL);
        timer.stop();

        LinkedList<BigInteger> primes = finder.getPrimesFound();

        for(BigInteger prime : primes)
        {
            System.out.println(prime);
        }

        System.out.println("Primes found: " + primes.size());
        System.out.println("Time: " + timer.getElapsedTime());
    }
}
