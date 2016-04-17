package viniciuslambardozzi.threadsarefunawayfromyou.sandbox;

import viniciuslambardozzi.threadsarefunawayfromyou.core.Interval;
import viniciuslambardozzi.threadsarefunawayfromyou.core.PrimeFinder;
import viniciuslambardozzi.threadsarefunawayfromyou.core.util.Timer;

import java.math.BigInteger;
import java.util.LinkedList;

public class Main
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();

        Interval interval = new Interval(BigInteger.valueOf(1), BigInteger.valueOf(10000), BigInteger.valueOf(2));

        PrimeFinder finder = new PrimeFinder();

        timer.start();

        finder.find(interval);
        LinkedList<BigInteger> primes = finder.getPrimesFound();

        timer.stop();


        for(BigInteger prime : primes)
        {
            System.out.println(prime);
        }

        System.out.println("Primes found: " + primes.size());
        System.out.println("Time: " + timer.getElapsedTime());
    }
}
