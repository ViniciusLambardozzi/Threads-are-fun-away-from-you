package viniciuslambardozzi.threadsarefunawayfromyou.core.util;

public class Timer
{
    private long startTime;
    private long stopTime;

    public void start()
    {
        startTime = System.currentTimeMillis();
    }

    public void stop()
    {
        stopTime = System.currentTimeMillis();
    }

    public long getElapsedTime()
    {
        return stopTime - startTime;
    }
}
