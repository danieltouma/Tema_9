import java.util.Random;

public class DataStructureComparison {
    public static void main(String[] args) {
        final int N = 10000; // Number of elements in the dataset
        final int[] sortedDataset = generateSortedDataset(N);
        final int[] randomDataset = generateRandomDataset(N);
        final int[] reversedSortedDataset = generateReversedSortedDataset(N);

        // Create instances of each data structure
        SplayTree<Integer> splayTree = new SplayTree<>();
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        Treap<Integer> treap = new Treap<>();

        // Test and compare performance with sorted dataset
        testPerformance("Sorted Dataset", splayTree, redBlackTree, treap, sortedDataset);

        // Test and compare performance with reversed sorted dataset
        testPerformance("Reversed Sorted Dataset", splayTree, redBlackTree, treap, reversedSortedDataset);

        // Test and compare performance with random dataset
        testPerformance("Random Dataset", splayTree, redBlackTree, treap, randomDataset);
    }

    private static void testPerformance(String datasetType, SplayTree<Integer> splayTree,
                                        RedBlackTree<Integer> redBlackTree, Treap<Integer> treap,
                                        int[] dataset) {
        System.out.println("Testing performance with " + datasetType);

        // Insertion of dataset into each data structure
        long startTime = System.nanoTime();
        int splayInsertionOps = 0;
        for (int value : dataset) {
            splayTree.insert(value);
            splayInsertionOps++;
        }
        long splayInsertionTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int redBlackInsertionOps = 0;
        for (int value : dataset) {
            redBlackTree.insert(value);
            redBlackInsertionOps++;
        }
        long redBlackInsertionTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int treapInsertionOps = 0;
        for (int value : dataset) {
            treap.insert(value);
            treapInsertionOps++;
        }
        long treapInsertionTime = System.nanoTime() - startTime;

        // Output results
        System.out.println("Insertion Operations: ");
        System.out.println("Top-Down Splay Tree: " + splayInsertionOps);
        System.out.println("Red-Black Tree: " + redBlackInsertionOps);
        System.out.println("Treap: " + treapInsertionOps);

        System.out.println("Insertion Time (ns): ");
        System.out.println("Top-Down Splay Tree: " + splayInsertionTime);
        System.out.println("Red-Black Tree: " + redBlackInsertionTime);
        System.out.println("Treap: " + treapInsertionTime);
        System.out.println();
    }

    private static int[] generateSortedDataset(int size) {
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++) {
            dataset[i] = i + 1;
        }
        return dataset;
    }

    private static int[] generateRandomDataset(int size) {
        int[] dataset = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dataset[i] = random.nextInt(size) + 1;
        }
        return dataset;
    }

    private static int[] generateReversedSortedDataset(int size) {
        int[] dataset = generateSortedDataset(size);
        return reverseArray(dataset);
    }

    private static int[] reverseArray(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - 1 - i];
        }
        return reversedArray;
    }
}
