
public class Test1{

    static int calculateFactorial(int n){
        int r = 1;
        for (int i = 1; i <= n; i++){
            r = r * i;
        }
        return r;
    }

    public static void main(String[] args){
        System.out.println(calculateFactorial(4));
    }
}