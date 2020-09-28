package io.flyingnimbus.forkjoinpool;



import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * example taken from https://medium.com/swlh/the-unfairly-unknown-forkjoinpool-c262777def6a
 */
public class ForkJoinExample {


    static class Fibonacci extends RecursiveTask<Integer> {

        private final int number;

        public Fibonacci(int number) {
            this.number = number;
        }

        @Override
        protected Integer compute() {
            if (number <= 1) {
                return number;
            } else {
                Fibonacci fibonacciMinus1 = new Fibonacci(number - 1);
                Fibonacci fibonacciMinus2 = new Fibonacci(number - 2);
                fibonacciMinus1.fork();
                return fibonacciMinus2.compute() + fibonacciMinus1.join();
            }
        }
    }
}