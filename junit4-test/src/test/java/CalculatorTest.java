import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author chenhang
 * @date 2018/8/12 下午9:38
 * @description
 */
public class CalculatorTest {

    @Test
    public void evaluatesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }
}
