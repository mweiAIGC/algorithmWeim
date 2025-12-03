package class09;

public class Code04_pMinPart {
    // Str中最少切多少刀，全回文
    public static int pMinPart(String str) {
        char[] chars = str.toCharArray();
        int N = chars.length;
        int[] dp = new int[N+1];
        for(int i = 0; i < N; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        boolean[][] Part = new boolean[N][N];
        for(int i = 0; i < N; i++){
            Part[i][i] = true;
        }
        for(int i = 1; i < N-1; i++){
            if(chars[i-1] == chars[i]){
                Part[i][i] = true;
            }
        }
        for(int row = N-3; row >=0;row--){
            for(int col = row+2; col < N; row++){
                Part[row][col] = chars[row]==chars[col] && Part[row+1][col-1];
            }
        }
        for(int i = N-1; i>=0;i--){
            for(int end = i; end < N; end++){
                if(Part[i][end]){
                    dp[i] = Math.min(dp[i],1+dp[end+1]);
                }
            }
        }
        return dp[0];
    }
}
