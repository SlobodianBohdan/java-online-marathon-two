
import java.util.*;
import java.util.stream.DoubleStream;

public class Task05{
    public static void main(String[] args) {
        Array<Integer> set = new Array<>(new Integer[]{2, 4});
        Array<Double> set2 = new Array<>(new Double[]{2.0, 4.0});

        System.out.println(ArrayUtil.averageValue(set));
    }
}

class Array<T> {
    private T[] array;

    public Array(T[] array) {
        this.array = array;
    }

    public T get(int index) {
        return array[index];
    }

    public int length() {
        return array.length;
    }
}


class ArrayUtil {
    public static  <T extends Number> double averageValue(Array<T> array) {
        double sumElements = 0;
        for (int i = 0; i < array.length(); i++) {
            sumElements += array.get(i).doubleValue();
        }
        return sumElements / array.length();
    }
}
