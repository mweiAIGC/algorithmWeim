package class03;

public class Code03LongSumSubarrayLength {
    public static int[][] MostRightIndexAndLeastSum(int[] arr){
        int N =  arr.length;
        int[] LeastSum = new int[N];
        int[] RightIndex =  new int[N];
        LeastSum[N-1] = arr[N-1];
        RightIndex[N-1] = N-1;
        for(int i = N-2; i>=0; i--){
            if(LeastSum[i+1]<=0){
                RightIndex[i] = RightIndex[i+1];
                LeastSum[i] = LeastSum[i+1] +arr[i];
            }else {
                RightIndex[i] = i;
                LeastSum[i] = arr[i];
            }
        }
        int[][] res = new int[2][N];
        res[0] = RightIndex;
        res[1] = LeastSum;
        return res;
    }

    public static int LongestSubarrayLength(int[] arr, int K){
        if(arr.length ==0||arr == null) return 0;
        int len = 0;
        int end = 0;
        int sum = 0;
        int [][] res = MostRightIndexAndLeastSum(arr);
        int[] RightIndex = res[0];
        int[] LeastSum = res[1];
        for(int i = 0; i < arr.length;i++){
            while(sum+ LeastSum[end]<=K&&end<arr.length){
                sum += LeastSum[end];
                end = RightIndex[end]+1;
            }
            len = Math.max(len, end-i);
            sum -= arr[i];
//            if(end>i){
//                sum-=arr[i];
//            }else {
//                end=i+1;
//            }

        }
        return len;
    }

}
