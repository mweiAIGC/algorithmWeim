package class01;

public class Code06_KMP {
    public static int kmp(String str, String subStr){
        char[] ch = str.toCharArray();
        char[] sub = subStr.toCharArray();
        if(ch.length == 0|| sub.length == 0 || ch == null) return 0;

        int[] next = next(sub);
        int i = 0, j = 0;
        while(i < ch.length && j < sub.length){
            if(ch[i] == sub[j]){
                i++;
                j++;
            }else if(next[j] != -1){
                j = next[j];
            }else {
                i++;
            }
        }
        return j== sub.length?i-j:-1;

    }

    public static int[] next(char[] sub){
        if(sub.length == 1) return new int[]{-1};
        int cnt = 0;
        int[] next = new int[sub.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        while(i < sub.length){
            if(sub[i-1] == sub[cnt]){
                next[i++] = ++cnt;
            }else if(next[cnt] > 0){
                cnt = next[cnt];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
