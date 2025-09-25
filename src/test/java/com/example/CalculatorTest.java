import com.example.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    // --- Tests for sqrt ---
    @Test
    void testSqrtPositive() {
        assertEquals(4.0, Calculator.sqrt(16), 1e-6);
        assertEquals(2.2360679, Calculator.sqrt(5), 1e-6);
    }

    @Test
    void testSqrtZero() {
        assertEquals(0.0, Calculator.sqrt(0), 1e-6);
    }

    @Test
    void testSqrtNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.sqrt(-3);
        });
        assertEquals("Square root of negative number is not possible", exception.getMessage());
    }

    // --- Tests for fact ---
    @Test
    void testFactPositive() {
        assertEquals(6, Calculator.fact(3));
        assertEquals(24, Calculator.fact(4));
    }

    @Test
    void testFactZero() {
        assertEquals(1, Calculator.fact(0));
    }

    @Test
    void testFactNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.fact(-2);
        });
        assertEquals("Factorial of negative number is not possible", exception.getMessage());
    }

    // --- Tests for log ---
    @Test
    void testLogPositive() {
        assertEquals(0.0, Calculator.log(1), 1e-6);
        assertEquals(Math.log(15), Calculator.log(15), 1e-6);
    }

    @Test
    void testLogZeroOrNegative() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.log(0);
        });
        assertEquals("Natural log of negative and zero not possible", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.log(-5);
        });
        assertEquals("Natural log of negative and zero not possible", exception2.getMessage());
    }

    // --- Tests for pow ---
    @Test
    void testPow() {
        assertEquals(1.0, Calculator.pow(2, 0), 1e-6);
        assertEquals(9.0, Calculator.pow(3, 2), 1e-6);
        assertEquals(6.25, Calculator.pow(2.5, 2), 1e-6);
    }
}
