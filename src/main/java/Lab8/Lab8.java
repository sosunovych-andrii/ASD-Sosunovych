package Lab8;

import java.util.Arrays;
import java.util.Random;

public class Lab8 {

    private static void linearSearch(int[] arr, int target) {
        System.out.println("Linear search:");
        boolean targetFound = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println("Target " + target + " found at " + i + " index.\n");
                targetFound = true;
                break;
            }
        }

        if (!targetFound) {
            System.out.println("Target " + target + " is not found in array.\n");
        }
    }


    private static void linearSearchWithBarrier(int[] arr, int target) {
        System.out.println("Linear search with Barrier:");
        boolean targetFound = false;

        int last = arr[arr.length - 1];
        arr[arr.length - 1] = target;
        int i = 0;
        while (arr[i] != target) {
            i++;
        }
        arr[arr.length - 1] = last;
        if (i < arr.length - 1 || last == target) {
            System.out.println("Target " + target + " found at " + i + " index.\n");
            targetFound = true;
        }

        if (!targetFound) {
            System.out.println("Target " + target + " is not found in array.\n");
        }
    }


    private static void binarySearch(int[] arr, int target) {
        System.out.println("Binary search:");
        boolean targetFound = false;

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                System.out.println("Target " + target + " found at " + mid + " index.\n");
                targetFound = true;
                break;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (!targetFound) {
            System.out.println("Target " + target + " is not found in array.\n");
        }
    }


    private static void transpositionSearch(int[] arr, int target) {
        System.out.println("Transposition search:");
        boolean targetFound = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                if (i > 0) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    System.out.println("Target " + target + " found at " + i + " index.");
                    System.out.println("Target " + target + " swapped at " + (i - 1) + " index.");
                    System.out.println("Current array: " + Arrays.toString(arr) + "\n");
                } else {
                    System.out.println("Target " + target + " found at 0 index.\n");
                }
                targetFound = true;
                break;
            }
        }

        if (!targetFound) {
            System.out.println("Target " + target + " is not found in array.\n");
        }
    }


    private static void moveToFrontSearch(int[] arr, int target) {
        System.out.println("Move to front search:");
        boolean targetFound = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                if (i != 0) {
                    int temp = arr[i];
                    arr[i] = arr[0];
                    arr[0] = temp;
                }
                targetFound = true;
                break;
            }
        }
        System.out.println("Target " + target + " is moved at 0 index.");
        System.out.println("Current array: " + Arrays.toString(arr) + "\n");

        if (!targetFound) {
            System.out.println("Target " + target + " is not found in array.\n");
        }
    }


    private static void insertionSort(int[] arr) {
        System.out.println("Insertion Sort:");
        int[] clone = arr.clone();

        for (int i = 1; i < clone.length; i++) {
            int key = clone[i];
            int j;
            for (j = i - 1; j >= 0 && clone[j] > key; j--) {
                clone[j + 1] = clone[j];
            }
            clone[j + 1] = key;
        }

        System.out.println("Sorted array: " + Arrays.toString(clone) + "\n");
    }


    private static void selectionSort(int[] arr) {
        System.out.println("Selection Sort:");
        int[] clone = arr.clone();

        for (int i = 0; i < clone.length - 1; i++) {
            for (int j = i + 1; j < clone.length; j++) {
                if (clone[i] > clone[j]) {
                    int temp = clone[i];
                    clone[i] = clone[j];
                    clone[j] = temp;
                }
            }
        }

        System.out.println("Sorted array: " + Arrays.toString(clone) + "\n");
    }


    private static void bubbleSort(int[] arr) {
        System.out.println("Bubble Sort:");
        int[] clone = arr.clone();

        for (int i = 0; i < clone.length - 1; i++) {
            for (int j = clone.length - 1; j > i; j--) {
                if (clone[j] < clone[j - 1]) {
                    int temp = clone[j];
                    clone[j] = clone[j - 1];
                    clone[j - 1] = temp;
                }
            }
        }
        System.out.println("Sorted array: " + Arrays.toString(clone) + "\n");
    }


    private static void shellSort(int[] arr) {
        System.out.println("Shell Sort:");
        int[] clone = arr.clone();

        for (int gap = clone.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < clone.length; i++) {
                int temp = clone[i];
                int j;
                for (j = i; j >= gap && clone[j - gap] > temp; j -= gap) {
                    clone[j] = clone[j - gap];
                }
                clone[j] = temp;
            }
        }
        System.out.println("Sorted array: " + Arrays.toString(clone) + "\n");
    }


    private static void mergeSort(int[] arr) {
        // якщо довжина підмасиву <= 1, то масив уже відсортований
        int length = arr.length;
        if (length <= 1) {
            return;
        }

        // створюємо підмасиви
        int middle = length / 2;
        int[] leftArr = new int[middle];
        int[] rightArr = new int[length - middle];

        // копіюємо елементи у підмасиви
        int leftIndex = 0;
        int rightIndex = 0;

        for (; leftIndex < length; leftIndex++) {
            if (leftIndex < middle) {
                leftArr[leftIndex] = arr[leftIndex];
            } else {
                rightArr[rightIndex] = arr[leftIndex];
                rightIndex++;
            }
        }

        // рекурсивно розбиваємо масив на підмасиви
        mergeSort(leftArr);
        mergeSort(rightArr);

        // об'єднуємо відсортовані підмасиви
        merge(leftArr, rightArr, arr);
    }

    private static void merge(int[] leftArr, int[] rightArr, int[] arr) {
        int leftLength = arr.length / 2;
        int rightLength = arr.length - leftLength;
        int arrIndex = 0, leftIndex = 0, rightIndex = 0;

        // зливаємо відсортовані елементи в основний масив
        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (leftArr[leftIndex] < rightArr[rightIndex]) {
                arr[arrIndex] = leftArr[leftIndex];
                arrIndex++;
                leftIndex++;
            } else {
                arr[arrIndex] = rightArr[rightIndex];
                arrIndex++;
                rightIndex++;
            }
        }

        // додаємо залишки підмасивів за індексами
        while (leftIndex < leftLength) {
            arr[arrIndex] = leftArr[leftIndex];
            leftIndex++;
            arrIndex++;
        }
        while (rightIndex < rightLength) {
            arr[arrIndex] = rightArr[rightIndex];
            rightIndex++;
            arrIndex++;
        }
    }


    private static void heapSort(int[] arr) {
        System.out.println("Heap sort:");
        int n = arr.length;

        // будування макс-купи
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // вилучення елементів із піраміди
        for (int i = n - 1; i > 0; i--) {
            // переміщення кореня на кінець
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
        System.out.println("Sorted array: " + Arrays.toString(arr) + "\n");
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // якщо найбільший не є коренем
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // рекурсивно заповнюємо піддерево
            heapify(arr, n, largest);
        }
    }


    private static void quickSort(int[] arr, int firstIndex, int lastIndex) {
        int pivot = arr[(firstIndex + lastIndex) / 2];

        int i = firstIndex;
        int j = lastIndex;

        do {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }

        } while (i <= j);

        if (j > firstIndex) {
            quickSort(arr, firstIndex, j);
        }
        if (i < lastIndex) {
            quickSort(arr, i, lastIndex);
        }
    }


    public static void task3() {
        int[] A = {102, 101, 100, 4, 5, 6};
        int[] B = {4, 5, 6, 7, 8, 9};

        int[] result = new int[A.length];
        int k = 0;

        for (int i = 0; i < A.length; i++) {
            boolean isFound = false;
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                result[k] = A[i];
                k++;
            }
        }

        int[] finalResult = new int[k];
        for (int i = 0; i < k; i++) {
            finalResult[i] = result[i];
        }

        for (int i = 0; i < finalResult.length; i++) {
            for (int j = 0; j < finalResult.length - i - 1; j++) {
                if (finalResult[j] > finalResult[j + 1]) {
                    int temp = finalResult[j];
                    finalResult[j] = finalResult[j + 1];
                    finalResult[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted elements of array A, that are absent in array B: " +
                Arrays.toString(finalResult) + "\n");
    }


    public static void lab_demonstration() {
        // 1.Алгоритми пошуку
        int[] arr = {-100, -50, -20, -10, 0, 10, 18, 47, 98, 100};
        linearSearch(arr, 0);
        binarySearch(arr, 0);
        linearSearchWithBarrier(arr, 0);
        moveToFrontSearch(arr.clone(), 0);
        transpositionSearch(arr.clone(), 0);
        System.out.println();


        // 2.Алгоритми сортування
        Random random = new Random();
        int[] arr2 = new int[10];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = random.nextInt(10);
        }
        System.out.println("Unsorted array: " + Arrays.toString(arr2) + "\n");
        insertionSort(arr2);
        selectionSort(arr2);
        bubbleSort(arr2);
        shellSort(arr2);
        int[] arrMerge = arr2.clone();
        System.out.println("Merge sort:");
        mergeSort(arrMerge);
        System.out.println("Sorted array: " + Arrays.toString(arrMerge) + "\n");
        int[] arrQuick = arr2.clone();
        System.out.println("Quick sort:");
        quickSort(arrQuick, 0, arrQuick.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(arrQuick) + "\n");
        heapSort(arr2.clone());


        //3.Посортувати елементи котрі присутні в масиві A, але відсутні в масиві B
        task3();
    }
}
