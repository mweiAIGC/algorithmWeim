package class09;

public class Code02_desired {
    /**
     * 给定一个只由0(假)，1(真)，&，|，和^五种字符组成的字符串express，再给定一个布尔值desired。返回express能有多少种组合方式，
     * 可以达到desired效果。
     * eg:
     * express="1^0|0|1"，desired=false
     * 只有1^((0|0)|1)和1^(0|(0|1))的组合可以得到false，返回2.express=“1”，desired=false。无组合则可以得到false，返回0
     */
    public static boolean isValid(char[] s){
        if((s.length&1)==0){
            return false;
        }
        for(int i=0;i<s.length;i+=2){
            if((s[i]!='1')&&s[i]!='0'){
                return false;
            }
        }
        for(int i = 1;i<s.length-1;i+=2 ){
            if((s[i]!='&')&&(s[i]!='^')&&(s[i+1]!='|')){
                return false;
            }
        }
        return true;
    }
    public static int process(String express, boolean desired){
        if (express==null||express.length()==0){
            return 0;
        }
        char[] exp = express.toCharArray();
        if(!isValid(exp)){
            return 0;
        }
        return f(exp,desired,0,exp.length-1);
    }
    public static int f(char[] str, boolean desired, int L, int R) {
        if (L == R) {
            if (desired) {
                return str[L] == 1 ? 1 : 0;
            } else {
                return str[L] == 1 ? 0 : 1;
            }
        }
        int res = 0;
        for (int i = L + 1; i < R; i += 2) {
            if (desired) {
                switch (str[i]) {
                    case '&':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                    case '|':
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                    case '^':
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        break;
                }
            } else {
                switch (str[i]) {
                    case '&':
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '|':
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '^':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                }
            }
        }
        return res;
    }
}
