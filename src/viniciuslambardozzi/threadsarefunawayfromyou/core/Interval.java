package viniciuslambardozzi.threadsarefunawayfromyou.core;

import java.math.BigInteger;
import java.util.LinkedList;

public class Interval
{
    private LinkedList<BigInteger> interval;
    private BigInteger start;
    private BigInteger size;

    public Interval(BigInteger start, BigInteger size, BigInteger step)
    {
        this.start = start;
        this.size = size;
        init(start, size, step);
    }

    private void init(BigInteger start, BigInteger size, BigInteger step)
    {
        interval = new LinkedList<BigInteger>();
        BigInteger currValue = start;

        for(BigInteger i = BigInteger.ZERO; i.compareTo(size) < 0; i = i.add(BigInteger.ONE))
        {
            interval.add(new BigInteger(start.add(i.multiply(step)).toByteArray()));
        }
    }

    public LinkedList<BigInteger>[] split(int number)
    {
        LinkedList<BigInteger>[] lists = new LinkedList[number];
        BigInteger intervalSize = size.divide(BigInteger.valueOf(number));

        for(int i = 0; i < number; i++)
        {
            //TODO
        }

        return lists;
    }

    public LinkedList<BigInteger> getInterval()
    {
        return interval;
    }

    public BigInteger getSize()
    {
        return size;
    }
}
