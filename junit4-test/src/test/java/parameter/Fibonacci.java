package parameter;

/**
 * @author chenhang
 * @date 2018/8/14 上午9:12
 * @description
 */
public class Fibonacci {

    public static int compute(int n) {
        int result = 0;

        if (n <= 1) {
            result = n;
        } else {
            result = compute(n - 1) + compute(n - 2);
        }

        return result;
    }
}
