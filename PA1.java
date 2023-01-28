import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class PA1 {
    public static void main(String[] args) {

        int limit = 100_000_000;
        int numOfThreads = 8;
        long[] arrayOfPrimes = new long[12];
        boolean[] primes = new boolean[limit + 1];
        setTrue(primes);

        // execute the program
        long startTime = System.currentTimeMillis();
        multithreaded(numOfThreads, limit, primes);
        long endTime = System.currentTimeMillis();

        // time calculation and get results
        long duration = endTime - startTime;
        getResults(primes, arrayOfPrimes, limit);
        writeInfo(duration, arrayOfPrimes);

    }

    /* writes the results to the primes.txt file. */
    public static void writeInfo(long duration, long[] arrayOfPrimes) {
        try {
            FileWriter myWriter = new FileWriter("primes.txt");
            myWriter.write("Execution time: " + duration + "ms or " + (duration / 1000) + "s Total number of primes: "
                    + arrayOfPrimes[0] + " Sum of all primes: " + arrayOfPrimes[1] + " Top ten maximum primes: ");
            System.out.print("Execution time: " + duration + "ms or " + (duration / 1000) + "s Total number of primes: "
                    + arrayOfPrimes[0] + " Sum of all primes: " + arrayOfPrimes[1] + " Top ten maximum primes: ");
            for (int i = 2; i < 12; i++) {
                myWriter.write(arrayOfPrimes[i] + " ");
                System.out.print(arrayOfPrimes[i] + " ");
            }
            myWriter.close();
            System.out.println("\nSuccessfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*
     * Function will create of list of 8 threads which call calcPrimes with a
     * lower-bound and upper-bound to set weather it consider a prime number or not.
     */
    public static void multithreaded(int numOfThreads, int limit, boolean[] primes) {
        List<Thread> threads = new ArrayList<>();
        int last = (int) Math.sqrt(limit) / numOfThreads;

        for (int i = 1; i < numOfThreads + 1; i++) {
            final int threadMultiplier = i;
            Thread thread = new Thread(() -> {
                int start = (last * threadMultiplier) - last;
                calcPrimes(primes, limit, start, (start + last));

            });
            threads.add(thread);

        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        }
    }

    /* function set all the entries as true. */
    public static void setTrue(boolean[] primes) {
        for (int i = 2; i <= primes.length - 1; i++) {
            primes[i] = true;
        }
    }

    /*
     * Function will get the result for the count, sum, and last ten prime numbers.
     */
    public static void getResults(boolean[] primes, long[] arrayOfPrimes, int limit) {
        long sum = 0;
        long count = 0;
        int check = 11;
        for (int i = 2; i < limit; i++) {
            if (primes[i]) {
                count++;
                sum += i;
            }
        }
        arrayOfPrimes[0] = count;
        arrayOfPrimes[1] = sum;

        for (int j = limit - 1; check != 1; j--) {
            if (primes[j]) {
                arrayOfPrimes[check] = j;
                check--;
            }
        }

    }

    /*
     * Function to calculate all primes form start to last range of numbers using
     * Sieve of Eratosthenes
     */
    private static void calcPrimes(boolean[] primes, int limit, int start, int last) {
        if (2 > start)
            start = 2;
        for (int i = start; i < last; i++) {
            if (primes[i]) {
                for (int j = i * i; j < limit; j += i) {
                    primes[j] = false;
                }
            }
        }
    }

}