/**
 * @author chenhang
 * @date 2018/8/12 下午9:35
 * @description
 */
public class Calculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand : expression.split("\\+")) {
            sum += Integer.valueOf(summand);
        }
        return sum;
    }
}
