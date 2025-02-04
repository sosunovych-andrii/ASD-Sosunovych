package Lab2;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab2 {

    public static void sumEvenIndexAndCountBetweenZero() {
        System.out.println("\nTask 1");

        System.out.println("Enter the length of the array");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Array: " + Arrays.toString(arr));


        //task 1.1: Sum of elements with even indexes
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                sum += arr[i];
            }
        }
        System.out.println("Sum of elements with even indexes: " + sum);


        //task 1.2: Amount of elements between first and second zero elements
        int firstZero = -1;
        int secondZero = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (firstZero == -1) {
                    firstZero = i;
                }
                secondZero = i;
            }
        }

        if (secondZero != -1 && secondZero > firstZero) {
            int countBetweenZeros = secondZero - firstZero - 1;
            System.out.println("Amount of elements between first and second zero elements: " + countBetweenZeros);
        } else {
            System.out.println("No zeros found in the array.");
        }
    }


    public static void sortArrayWithPositiveNegativeZero() {
        System.out.println("\nTask 2");

        System.out.println("Enter the length of the array: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Unsorted array: " + Arrays.toString(arr));

        int positiveEndIndex = 0;

        // Sort positive elements
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] > arr[j] && arr[i] > 0 && arr[j] > 0) ||
                        (arr[i] <= 0 && arr[j] > 0)) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        // Find the end of positive elements
        while (positiveEndIndex < n && arr[positiveEndIndex] > 0) {
            positiveEndIndex++;
        }

        int startZeroIndex = positiveEndIndex;

        // Place zeros after positive elements
        for (int i = positiveEndIndex; i < n; i++) {
            if (arr[i] == 0) {
                int temp = arr[i];
                arr[i] = arr[startZeroIndex];
                arr[startZeroIndex] = temp;
                startZeroIndex++;
            }
        }

        // Sort negative elements
        int zeroEndIndex = startZeroIndex;
        int negativeStartIndex = zeroEndIndex;

        for (int i = negativeStartIndex; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }


    public static void countZeroRowsAndLongestSeries() {
        System.out.println("\nTask 3");

        System.out.println("Enter the rows and the columns of the matrix: ");
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        Random rand = new Random();

        // matrix filling
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(11);
            }
        }

        // show matrix
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            System.out.print("[ ");
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("]");
        }


        // task3.1: Amount of rows which have at least one zero element
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    count++;
                    break;
                }
            }
        }
        System.out.println("Number of rows with zero element: " + count);


        // task3.2: Row number with the longest series of identical elements
        int resultRow = 0;
        int maxLength = 0;
        for (int i = 0; i < rows; i++) {
            int currentLength = 1;
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == matrix[i][j - 1]) {
                    currentLength++;
                } else {
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        resultRow = i;
                    }
                    currentLength = 1;
                }
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    resultRow = i;
                }
            }
        }
        System.out.println("Row number with the longest series: " + resultRow);
        System.out.println("His length is: " + maxLength);
    }


    public static void fillMatrixWithUpperTriangleOnes() {
        System.out.println("\nTask 4");

        System.out.println("Enter the rows and columns of the matrix: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j && j < n - i - 1) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        // Fill the square matrix with ones in the upper triangle between the diagonals of the matrix, fill the rest with zeros.
        System.out.println("Filled matrix:");
        for (int i = 0; i < n; i++) {
            System.out.print("[ ");
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("]");
        }
    }


    public static void lab_demonstration() {
        sumEvenIndexAndCountBetweenZero();
        sortArrayWithPositiveNegativeZero();
        countZeroRowsAndLongestSeries();
        fillMatrixWithUpperTriangleOnes();
    }
}
