package class03;
//牛和羊吃草，先手和后手吃草的顺序是固定的，他们每轮吃的草的数量是pow(4,n);
public class Code01_EatGrass {
    public static String winner1(int n){
        if(n<5){
            return (n==0||n==2) ?"后手":"先手";
        }
        int base = 1; // 当前先手决定吃的草数
        while(base <= n){
            if(winner1(n-base).equals("后手")){
                return "先手";
            }
            if(base > n / 4){  // 为了防止base * 4 之后溢出
                break;
            }
            base *= 4;
        }
        return "后手";
    }
}
