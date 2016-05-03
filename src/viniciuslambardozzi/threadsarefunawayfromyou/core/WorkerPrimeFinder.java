package viniciuslambardozzi.threadsarefunawayfromyou.core;

import viniciuslambardozzi.threadsarefunawayfromyou.core.util.Timer;
import viniciuslambardozzi.threadsarefunawayfromyou.gui.GuiPrimeFinder;

import java.math.BigInteger;
import java.util.LinkedList;

public class WorkerPrimeFinder  implements Runnable
{
    /*Controla o tempo de execução da thread*/
    Timer timer = new Timer();

    /*Salva os valores que serão testados */
    private LinkedList<BigInteger> values;

    /*Salva os primos encontrados*/
    private LinkedList<BigInteger> primesFound;

    /*Identificador da thread (Para informar o usuário)*/
    int id;

    public WorkerPrimeFinder(LinkedList<BigInteger> values, int id)
    {
        primesFound = new LinkedList<>();

        this.id = id;
        this.values = values;
    }

    /*Retorna a lista de números primos encontrados pela thread
    * Tem que ser chamado após término da thread */
    public LinkedList<BigInteger> getPrimesFound()
    {
        return primesFound;
    }

    /*Método run: Esse método é executado quando a thread é iniciada*/
    @Override
    public void run()
    {
        /*Inicia o timer*/
        timer.start();
        /*Checa se os números são primos. Matematicamente ineficiente para teste de performance
        * O crivo de eratóstenes envolveria comunicação entre threads */
        while (!values.isEmpty())
        {
            /*Escolhe o primeiro número da lista*/
            BigInteger i = values.pollFirst();
            /*Até provar que não é primo, o número é primo*/
            boolean isPrime = true;
            /*Se o número é 0 ou 1, ele não é primo*/
            if (i.compareTo(BigInteger.valueOf(0)) == 0)
            {
                isPrime = false;
            } else if (i.compareTo(BigInteger.valueOf(1)) == 0)
            {
                isPrime = false;
            } else
            {
                /*Se o número é divisível por qualquer número num intervalo de 2 até n/2, ele não é primo
                * O intervalo ótimo é 2 até raiz de n, mas a BigIntegers em Java não possuem um método para raiz*/
                for (BigInteger j = BigInteger.valueOf(2); j.compareTo(i.divide(BigInteger.valueOf(2)).add(BigInteger.ONE)) < 0; j = j.add(BigInteger.ONE))
                {
                    if (i.mod(j) == BigInteger.ZERO)
                    {
                        isPrime = false;
                        break;
                    }
                }
            }
            /*Se o número é primo, adicione à lista de primos encontrados*/
            if (isPrime)
            {
                primesFound.add(i);
            }
        }
        /*Para o timer no final da execução */
        timer.stop();
        /*Informa o usuário que a thread id terminou de executar em t milissegundos*/
        GuiPrimeFinder.frame.logToOutput("Thread " + id + " finished running after " + timer.getElapsedTime() + " milliseconds.");
    }
}
