
class ArrayUtil {
    public static <T> T setAndReturn(T[] array, T value, int index){
        return array[index] = value;
    }
}
