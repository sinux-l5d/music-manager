package sh.sinux.musicmanager;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sh.sinux.musicmanager.MyHashMap.MyHashMap;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class BenchmarkMyHashMap {
    private static final int SIZE = 1000;
    private MyHashMap<String, Integer> myHashMap;

    @Setup
    public void setup() {
        myHashMap = new MyHashMap<>(String.class, Integer.class);
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            myHashMap.put("key-" + i, random.nextInt());
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void putBenchmark() {
        Random random = new Random();
        for (int i = SIZE; i < 2*SIZE; i++) {
            myHashMap.put("key-" + i, random.nextInt());
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void getBenchmark() {
        for (int i = 0; i < SIZE; i++) {
            myHashMap.get("key-" + i);
        }
    }

    public static void main(String[] args) throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(args);
    }
}
