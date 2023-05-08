package sh.sinux.musicmanager;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;
import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class BenchmarkMyLinkedList {
    private static final int SIZE = 1000;
    private MyLinkedList<Integer> myLinkedList;

    @Setup
    public void setup() {
        myLinkedList = new MyLinkedList<>();
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            myLinkedList.add(random.nextInt());
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void addBenchmark() {
        Random random = new Random();
        for (int i = SIZE; i < 2 * SIZE; i++) {
            myLinkedList.add(random.nextInt());
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void getBenchmark() {
        for (int i = 0; i < SIZE; i++) {
            myLinkedList.get(i);
        }
    }

    public static void main(String[] args) throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(args);
    }
}

