package viniciuslambardozzi.threadsarefunawayfromyou.core.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;

public class PrimeFileOutput
{
    public static void writePrimesToFile(LinkedList<BigInteger> primes, String fileName) throws IOException
    {
        File file = new File(System.getProperty("user.dir") + "\\" + fileName + ".txt");

        if(!file.exists())
        {
                 file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for(BigInteger prime : primes)
        {
            writer.write(prime.toString());
            writer.write(", ");
        }

        writer.close();
    }
}
