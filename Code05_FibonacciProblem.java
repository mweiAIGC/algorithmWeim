package class01;

public class Code05_FibonacciProblem {
    public static int Fibonacci(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        int[][] base = {{1,1},{1,0}};
        int[][] ans = multiPow(base, n-2);
        return ans[1][0]+ans[0][0];
    }

    public static int[][] multiPow(int[][] m, int k){
        int[][] powMatrix = m;
        int[][] res = new int[m.length][m.length];
        for(int i = 0; i < m.length; i++){
            res[i][i] = 1;
        }
        for(;k!=0;k>>=1){
            if((k&1)==1){
                res = multiMatrix(res,powMatrix);
            }
            powMatrix  = multiMatrix(powMatrix ,powMatrix);
        }

        return res;
    }
    public static int[][] multiMatrix(int[][] matrix1, int[][] matrix2){
        int[][] res = new int[matrix1.length][matrix2[0].length];
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                for(int k = 0; k < matrix1[0].length; k++){
                     res[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return res;
    }

}
