package viniciuslambardozzi.threadsarefunawayfromyou.core;

import java.math.BigInteger;
import java.util.LinkedList;

public class Interval
{
    private LinkedList<BigInteger> interval;
    public BigInteger start;
    public BigInteger size;
    public BigInteger step;

    private boolean initialized;

    public Interval(BigInteger start, BigInteger size, BigInteger step)
    {
        this.start = start;
        this.size = size;
        this.step = step;
        init(start, size, step);
    }

    private void init(BigInteger start, BigInteger size, BigInteger step)
    {
        interval = new LinkedList<>();

        for(BigInteger i = BigInteger.ZERO; i.compareTo(size) < 0; i = i.add(BigInteger.ONE))
        {
            interval.add(new BigInteger(start.add(i.multiply(step)).toByteArray()));
        }
        initialized = true;
    }

    public LinkedList<BigInteger>[] split(int number)
    {
        if(!initialized)
            init(start, size, step);
        LinkedList<BigInteger>[] lists = new LinkedList[number];
        BigInteger intervalSize = size.divide(BigInteger.valueOf(number));

        for(int i = 0; i < number; i++)
        {
            LinkedList<BigInteger> currList = new LinkedList<BigInteger>();

            for(BigInteger j = BigInteger.ZERO; j.compareTo(intervalSize) < 0; j = j.add(BigInteger.ONE))
            {
                currList.add(interval.pollFirst());
            }
            lists[i] = currList;
        }

        lists[number-1].addAll(interval);

        initialized = false;

        return lists;
    }
}
