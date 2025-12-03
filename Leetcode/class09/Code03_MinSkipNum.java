package class09;

public class Code03_MinSkipNum {
    /**
     * 给出一组正整数arr，你从第0个数想最后一个数，每个数的值表示你从这个位置可以向右跳跃的最大长度，计算
     * 如何以最少的跳跃次数跳到最后一个数
     */
    public static int minSkipNum(int[] num){
        int skip = 0;
        int curR = 0; // 表示最大右边界
        int next = -1; //表示下一步跳的位置
        for(int i = 0; i < num.length; i++){
            if(curR < i){
                skip ++;
                curR = next;
            }
            next = Math.max(next, num[i]);
        }

        return skip;
    }

}
