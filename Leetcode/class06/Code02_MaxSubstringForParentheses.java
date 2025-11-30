package class06;

public class Code02_MaxSubstringForParentheses {
    public static int maxSubstringLength(String s){
        if(s == null || s.length() < 2) return 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int max = 0;
        int pre = 0;
        for(int i=1; i< chars.length;i++){
            if(chars[i]==')'){
                pre = i - dp[i-1]-1;
                if((pre > 0) && (chars[pre] == '(')){
                    dp[i] = dp[i-1] + 2 + (pre > 0?dp[pre-1]:0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
