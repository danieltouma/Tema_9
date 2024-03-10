import java.util.Arrays;
import java.util.Random;


public class DataStructureComparison {
    private static int [] randomDataset;
    private static int [] sortedDataset;
    private static int [] reversedSortedDataset;
    public static void main(String[] args) {
        final int N = 100;
        randomDataset = generateRandomDataset(N, 10000);
        sortedDataset = generateSortedDataset();
        reversedSortedDataset = generateReversedSortedDataset();

        testPerformance("Random Dataset", new SplayTree<>(), new RedBlackTree<>(), new Treap<>(), randomDataset, N);
        testPerformance("Sorted Dataset", new SplayTree<>(), new RedBlackTree<>(), new Treap<>(), sortedDataset, N);
        testPerformance("Reversed Sorted Dataset", new SplayTree<>(), new RedBlackTree<>(), new Treap<>(), reversedSortedDataset, N);
    }

    private static void testPerformance(String datasetType, SplayTree<Integer> splayTree,
                                        RedBlackTree<Integer> redBlackTree, Treap<Integer> treap,
                                        int[] dataset, final int N) {
        int searchValue = dataset[50];
        testInsertion(datasetType, splayTree, redBlackTree, treap, dataset, N);
        testContains(datasetType, splayTree, redBlackTree, treap, searchValue, N);
        System.out.println("*************************************************************************");
    }

    private static void testInsertion(String datasetType, SplayTree<Integer> splayTree, RedBlackTree<Integer> redBlackTree, Treap<Integer> treap, int[] dataset, int N) {
        System.out.println("Inserting " + datasetType + " [size: " + N + "]");
        for (int value : dataset) {
            splayTree.insert(value);
            redBlackTree.insert(value);
            treap.insert(value);
        }

        int splayRotations = splayTree.getRotationCount();
        int redBlackRotations = redBlackTree.getRotationCount();
        int treapRotations = treap.getRotationCount();

        System.out.println("Number of rotations for insert:" +
                "\nTDST:  " + splayRotations +
                "\nRBT:   " + redBlackRotations +
                "\nTREAP: " + treapRotations + "\n");
    }

    private static void testContains(String datasetType, SplayTree<Integer> splayTree,
                                     RedBlackTree<Integer> redBlackTree, Treap<Integer> treap,
                                     int searchValue, final int N) {
        //to clear comparison count.
        splayTree.getComparisonCount();
        redBlackTree.getComparisonCount();
        treap.getComparisonCount();
        System.out.println("Searching for [value: " + searchValue + "]");

        for (int i = 0; i < 2; i++) {
            splayTree.contains(searchValue);
            redBlackTree.contains(searchValue);
            treap.contains(searchValue);

            System.out.println("SplayTree comparisons:" + splayTree.getComparisonCount());
            System.out.println("redBlackTree comparisons:" + redBlackTree.getComparisonCount());
            System.out.println("treap comparisons:" + treap.getComparisonCount() + "\n");
            if (i == 0) {
                System.out.println("Searching for [value: " + searchValue + "] again");
            }
        }
    }

    private static int[] generateRandomDataset(int size, int bound) {
        int[] dataset = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dataset[i] = random.nextInt(bound);
        }
        randomDataset = dataset;
        //System.out.println(Arrays.toString(randomDataset));
        return dataset;
    }

    private static int[] generateSortedDataset() {
        sortedDataset = Arrays.copyOf(randomDataset, randomDataset.length);
        Arrays.sort(sortedDataset);
        //System.out.println(Arrays.toString(sortedDataset));
        return sortedDataset;
    }


    private static int[] generateReversedSortedDataset() {
        reversedSortedDataset = Arrays.copyOf(sortedDataset, sortedDataset.length);
        return reverseArray(reversedSortedDataset);
    }

    private static int[] reverseArray(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - 1 - i];
        }
        //System.out.println(Arrays.toString(reversedArray));
        return reversedArray;
    }
}