package tests;

class Benchmark {

    interface Function {
        void execute(final Object[] args);
    }

    static long benchmark(final Function function, final Object[] args) {
        final long startTime = System.nanoTime();
        function.execute(args);
        final long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
