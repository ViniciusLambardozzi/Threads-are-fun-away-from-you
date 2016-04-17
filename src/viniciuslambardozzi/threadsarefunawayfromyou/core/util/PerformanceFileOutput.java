package viniciuslambardozzi.threadsarefunawayfromyou.core.util;

import viniciuslambardozzi.threadsarefunawayfromyou.core.Interval;

import java.io.*;

public class PerformanceFileOutput
{
    public static void writePerformanceToFile(Interval interval, long primesFound, long timeElapsed, String fileName) throws IOException
    {
        File file = new File(System.getProperty("user.dir") + fileName);

        if(!file.exists())
        {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(System.getProperty("user.dir") + fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        out.println("Processors: " + Runtime.getRuntime().availableProcessors());
        out.println("Free Memory: " + Runtime.getRuntime().freeMemory());
        out.println("Interval: { start: " + interval.start.toString() + ", size: " + interval.size + ", step: " + interval.step + " }");
        out.println("Primes found: " + primesFound);
        out.println("Time elapsed: " + timeElapsed);
        out.println();
        out.println();
    }
}
