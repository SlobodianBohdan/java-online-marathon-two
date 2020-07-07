import java.util.function.BinaryOperator;


class ParallelCalculator implements Runnable{
    BinaryOperator<Integer> binaryOperator;
    int operand1;
    int operand2;
    int result;

    public ParallelCalculator(BinaryOperator<Integer> binaryOperator, int operand1, int operand2) {
        this.binaryOperator = binaryOperator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void run() {
        result = binaryOperator.apply(operand1, operand2);
    }
}
