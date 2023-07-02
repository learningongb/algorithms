import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[20];
        for (int i=0; i<array.length; i++)
            array[i] = new Random().nextInt(10);
        print(array);
        sort(array);
        print(array);
//        isSorted(array);
    }

    private static void sort(int[] array) {
        int startNode;
        int tmp;
        int startLength = array.length;
        while (startLength > 0) {
            if (startLength % 2 == 0)
                startNode = (startLength - 2) / 2;
            else
                startNode = (startLength - 3) / 2;

            for (int i = startNode; i >= 0; i--) {
                heapify(array, i, startLength);
            }
            tmp = array[0];
            array[0] = array[startLength-1];
            array[startLength-1] = tmp;
            startLength--;
        }
    }
    private static void heapify(int[] array, int node, int count) {
        int indexMaxChild = node*2 + 1;
        if ((count > node*2 + 2) && (array[indexMaxChild] < array[node*2 + 2]) )
            indexMaxChild = node*2 + 2;
        if (array[node] < array[indexMaxChild])
        {
            int tmp = array[node];
            array[node] = array[indexMaxChild];
            array[indexMaxChild] = tmp;
        }
    }

    private static void isSorted(int[] array) {
        boolean isSorted = true;
        for (int i = 1; i < array.length; i++)
            if (array[i-1]>array[i])
            {
                isSorted = false;
                break;
            }
        System.out.println(isSorted);
    }

    private static void print(int[] array) {
        for (int i=0; i < array.length; i++)
        {
            System.out.print(array[i]);
            if (i + 1 < array.length)
                System.out.print(" ");
            else
                System.out.println("");
        }
    }

}
