import java.util.Random;

public class ArrayUtil {

    // Generates array with random numbers and inserts it into the array of length size
    public static int[] randomNumberGeneratedArray(int size, int max) {
        Random generator = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = generator.nextInt(max);
        }
        return array;
    }
}
