package class08;

public class Code03_DeleteMinCost {
    public static int minCost(String S1, String S2){
        if(S1.length()== 0 || S2.length()==0) return 0;
        int ans  = Integer.MAX_VALUE;
        char [] str2 = S2.toCharArray();
        for(int start =0;start<S1.length(); start++){
            for(int end = start+1; end< S1.length()+1;end++){
                ans = Math.min(ans, distance(str2, S1.substring(start, end).toCharArray()));
            }
        }
        return ans == Integer.MAX_VALUE ? Integer.MAX_VALUE:ans;
    }
    public static int distance(char[]str1, char[] str2){
        int row = str2.length;
        int col = str1.length;
        int[][] dp = new int[row][col];
        dp[0][0] = str1[0] == str2[0] ? 0 : Integer.MAX_VALUE;
        for(int j = 1; j< col;j++){
            dp[0][j] = Integer.MAX_VALUE;
        }
        for(int i = 1; i< row; i++){
            dp[i][0]= (str1[i]==str2[0]||dp[i-1][0]!=Integer.MAX_VALUE) ? i: Integer.MAX_VALUE;
        }
        for(int i =1; i< row; i++){
            for(int j = 1; j< col; j++){
                dp[i][j] = Integer.MAX_VALUE;
                if(dp[i-1][j] != Integer.MAX_VALUE){
                    dp[i][j] = dp[i-1][j] + 1;
                }
                if(str2[i] == str1[j] && dp[i-1][j-1] != Integer.MAX_VALUE){
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-1]);
                }
            }
        }
        return dp[row-1][col-1];
    }

//    解法2的优化
    public static int minCost2(String s1, String s2){
//        s2表示要删字符后是s1的子串
        if(s1.length() == 0 || s2.length()==0) return 0;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[M][N];
        int ans = M;
        for(int start = 0; start < N; start++){
            dp[start][0] = str1[start] == str2[start] ? 0 : M;
            for(int i = 1; i < M ;i++){
                dp[start][i] = (str2[i] == str1[0] || dp[i][0]!= M)? i:M;
            }
            for(int end = start+1; end < N && (end-start)<M; end++){
                int first = end - start;
                dp[first][end] = (str1[end] == str2[first] || str1[end-1] == str2[first-1]) ? 0 : M;
                for(int row = first+1; row < M;row++){
                    if(dp[row-1][end] != M){
                        dp[row][end] = dp[row-1][end] + 1;
                    }
                    if(str2[row]==str1[end] && dp[row-1][end-1]!= M){
                        dp[row][end] = Math.min(dp[row][end],dp[row-1][end-1]);
                    }
                }

                ans = Math.min(ans, dp[M-1][end]);
            }
        }
        return ans;
    }
}
