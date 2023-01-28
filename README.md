# Multithreading Prime Numbers with Sieve of Eratosthenes

## How to run the program
1. go into the directory where the source code is copy the path to command line.
2. type the following command:
```bash
javac PA1.java
```
3. press enter then write this in the terminal:
```bash
java PA1
```
4. press enter and it should print something.

## Proof of Correctness
The stratigie is to partition and run multiple threads in parallel within a range of 2 to 10^8. The ideas is to slip up the work for each thread by sqrt(10^8)/8 = 1,250. A thread will be given a range for example the first 2-1250 the total number of primes 952688 found then moves on to the next range 1250-2500. With each itteration within the loop creates a thread with a new runnable task to be exticted by purticlar thread then when completed will be joined.

## Experimental Evaluation

I first started with a Naive Approach. When I ran the code the for the first time without multithreading I new that it wasn't going to work because of how slow the first 4 minites took to reach the first 100k of prime number. The naive solution is to iterate through all numbers from 2 to sqrt(n) and for every number check if it divides n. If we find any number that divides, we return false. The approach given if the size of the given number is too large then its square root will be also very large, so to deal with large size input will make the proccess slower.


My second approach was Sieve of eratosthenes. I started without any multithreading to test how fast the algorithm would provided me the number of prime numbers within 10^8. When I ran the code the reslut came around 3s and thought it was good enough algothim to use multithreading which I was correct because it brought the time down to 883s compared to 3s.

## Efficiency
```txt
Execution time: 883ms or 0s Total number of primes: 5761455 Sum of all primes: 279209790387276 Top ten maximum primes: 99999787 99999821 99999827 99999839 99999847 99999931 99999941 99999959 99999971 99999989 
```