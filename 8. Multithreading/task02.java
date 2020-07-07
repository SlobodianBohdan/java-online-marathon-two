
import java.util.function.BinaryOperator;

class Accountant {
    public static int sum(int x, int y) {
        BinaryOperator<Integer> binaryOperator =
                (integer, integer2) -> integer + integer2;
        ParallelCalculator parallelCalculator = new ParallelCalculator(binaryOperator, x, y);
        Thread thread = new Thread(parallelCalculator);
        thread.start();
        synchronized (thread){
            try {
                thread.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return parallelCalculator.result;
    }
}
