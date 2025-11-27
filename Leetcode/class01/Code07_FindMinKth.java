package class01;

public class Code07_FindMinKth {
    //    第一种方法，通过快排来解决荷兰国旗的问题
    public static int process(int[] arr, int L, int R, int index) {
        if (L == R) return arr[L];
        int pivot = (int) (L + Math.random() * (R - L));
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, R, index);
        }

    }

    public static int[] partition(int[] arr, int L, int R, int index) {
        int i = L;
        int j = R;
        int ll = L - 1;
        swap(arr, index, R);
        while (i < j) {
            if (arr[i] < arr[index]) {
                swap(arr, i++, ++ll);
            } else if (arr[i] > arr[index]) {
                swap(arr, i, --j);
            } else {
                i++;
            }
        }
        swap(arr, j, R);
        return new int[]{ll + 1, j};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) return arr[L];
        int pivot = medianOfList(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }

    }

    public static int medianOfList(int[] arr, int L, int R) {
        int size = (L - R + 1) / 5;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] temp = new int[size + offset];
        for (int i = 0; i < temp.length; i++) {
            int LeftTemp = i * 5 + L;
            temp[i] = medianOfMedian(arr, LeftTemp, Math.min(LeftTemp + 4, R));
        }
        return bfprt(temp, 0, temp.length - 1, temp.length / 2);
    }

    private static int medianOfMedian(int[] arr, int left, int right) {
        medianSort(arr, left, right);
        return arr[arr.length / 2];
    }

    private static void medianSort(int[] arr, int left, int right) {
        for (int i = left + 1; i < right; i++) {
            for (int j = i - 1; j >= left && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
}
