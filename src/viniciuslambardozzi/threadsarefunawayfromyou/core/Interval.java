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
/*
* Cria um intervalo que inicia em start, contém size números e avança de step em step
* Exemplo: start = 1, size = 5, step = 2
*          1, 2, 3, 5, 7
**/
    public Interval(BigInteger start, BigInteger size, BigInteger step)
    {
        this.start = start;
        this.size = size;
        this.step = step;
        init(start, size, step);
    }

    /*
    * Salva os valores do intervalo criado em uma lista */
    private void init(BigInteger start, BigInteger size, BigInteger step)
    {
        interval = new LinkedList<>();

        for(BigInteger i = BigInteger.ZERO; i.compareTo(size) < 0; i = i.add(BigInteger.ONE))
        {
            interval.add(new BigInteger(start.add(i.multiply(step)).toByteArray()));
        }
        initialized = true;
    }

    /*Divide o intervalo criado em number listas de tamanhos iguais, exceto pela útima, que recebe os valores extras do resto da divisão.
    * Usada para dividir o intervalo entre threads*/
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
