package viniciuslambardozzi.threadsarefunawayfromyou.core;

import java.math.BigInteger;
import java.util.LinkedList;

public class WorkerPrimeFinder  implements Runnable
{

    private LinkedList<BigInteger> values;

    private LinkedList<BigInteger> primesFound;

    public WorkerPrimeFinder(LinkedList<BigInteger> values)
    {
        primesFound = new LinkedList<BigInteger>();

        this.values = values;
    }

    public LinkedList<BigInteger> getPrimesFound()
    {
        return primesFound;
    }

    @Override
    public void run()
    {
        while (!values.isEmpty())
        {
            BigInteger i = values.pollFirst();
            boolean isPrime = true;
            if (i.compareTo(BigInteger.valueOf(0)) == 0)
            {
                isPrime = false;
            } else if (i.compareTo(BigInteger.valueOf(1)) == 0)
            {
                isPrime = false;
            } else
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
            if (isPrime)
            {
                primesFound.add(i);
            }
        }
    }
}
