package class06;

public class Code03MaxOneBox {
    public static void setBoardMap(int[][] arr, int[][] right, int[][] down) {
        int n = arr.length;
        int m = arr[0].length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (arr[i][j] == 1) {
                    right[i][j] = 1 + (j + 1 < m ? right[i][j + 1] : 0);
                    down[i][j] = 1 + (i + 1 < n ? down[i + 1][j] : 0);
                } else {
                    right[i][j] = 0;
                    down[i][j] = 0;
                }
            }
        }
    }

    public static int getMaxSize(int[][] num){
        int[][] right = new int[num.length][num[0].length];
        int[][] down = new int[num.length][num[0].length];
        setBoardMap(right, down, num);
        for (int size =Math.min(num.length, num[0].length);size!=0; size--){
            if(hasSizeOfBorder(size,right,down)){
                return size;
            }
        }
        return 0;
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down){
        for(int i =0; i < right.length-size+1; i++){
            for(int j = 0; j< right[0].length-size+1;j++){
                if(right[i][j]-size>=0 && down[i][j]-size >=0 && right[i+size-1][j]-size >=0 &&down[i][j+size-1] >=0){
                    return true;
                }
            }
        }
        return false;
    }


}
