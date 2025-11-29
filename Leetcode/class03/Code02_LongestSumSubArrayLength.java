package class03;
import java.util.HashMap;
public class Code02_LongestSumSubArrayLength {
    public static int LongestSubArrayLength(int[] arr,int K){
        if(arr == null || arr.length == 0) return 0;
        HashMap<Integer,Integer> hm = new HashMap<> ();
        hm.put(0,-1);
        int len = 0;
        int sum = 0;
        for(int i = 0;i < arr.length;i++){
            sum += arr[i];
            if(hm.containsKey(sum-K)){
                len = Math.max(len, i-hm.get(sum-K));
            }
            if(!hm.containsKey(sum)){
                hm.put(sum,i);
            }
        }
        return len;
    }
}
