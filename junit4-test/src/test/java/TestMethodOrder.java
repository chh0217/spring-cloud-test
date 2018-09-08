import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author chenhang
 * @date 2018/8/14 下午3:57
 * @description
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMethodOrder {
    @Test
    public void testA() {
        System.out.println("first");
    }
    @Test
    public void testB() {
        System.out.println("second");
    }
    @Test
    public void testC() {
        System.out.println("third");
    }
}
