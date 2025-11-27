package class01;

public class Code04_HorseJump {
    public static int horseJump(int row, int col, int k) {
        if (k == 0) return row == 0 && col == 0 ? 1 : 0;
        if (row < 0 || col < 0 || row > 9 || col > 8) return 0;
        return horseJump(row + 2, col - 1, k - 1) +
                horseJump(row + 2, col + 1, k - 1) +
                horseJump(row + 1, col + 2, k - 1) +
                horseJump(row - 1, col + 2, k - 1) +
                horseJump(row - 2, col - 1, k - 1) +
                horseJump(row - 2, col + 1, k - 1) +
                horseJump(row - 1, col - 2, k - 1) +
                horseJump(row + 1, col - 2, k - 1);
    }

    public static int horseJump2(int row, int col, int k) {
        int[][][] dp = new int[row + 1][col + 1][k + 1];
        dp[0][0][0] = 1;
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                for (int l = 0; l <= k; l++) {
                    dp[i][j][l] = getValue(dp, i, j, l);
                }
            }
        }
        return dp[row][col][k];
    }


    public static int getValue(int[][][] dp, int row, int col, int k) {
        if (row < 0 || col < 0 || row > 9 || col > 8) {
            return 0;
        } else {
            return dp[row + 2][col - 1][k - 1] +
                    dp[row + 2][col + 1][k - 1] +
                    dp[row + 1][col + 2][k - 1] +
                    dp[row - 1][col + 2][k - 1] +
                    dp[row - 2][col - 1][k - 1] +
                    dp[row - 2][col + 1][k - 1] +
                    dp[row - 1][col - 2][k - 1] +
                    dp[row + 1][col - 2][k - 1];
        }
    }
}
