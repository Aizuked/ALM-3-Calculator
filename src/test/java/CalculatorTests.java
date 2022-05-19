import org.example.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTests {
    @Test
    public void evaluateCheckFails() {
        Calculator calculator = new Calculator();

        String text = calculator.compute("a + (a * b)");
        Integer eval = calculator.calculate("2 2 2 * +");

        Assert.assertEquals(text, "aab*+");
        Assert.assertEquals(eval, (Integer) 6);
    }
}
