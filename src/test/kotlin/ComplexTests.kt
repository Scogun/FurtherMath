import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ComplexTests{

    @Test
    fun sum() {
        val first = Complex(1.0, 1.0)
        val second = Complex(2.0, 2.0)
        val third = first + second
        Assertions.assertEquals("3+3i", third.toString())
    }

    @Test
    fun subtraction()
    {
        val first = Complex(3.0, 4.0)
        val second = Complex(2.0, 2.0)
        val third = first - second
        Assertions.assertEquals("1+2i", third.toString())
    }

    @ParameterizedTest
    @CsvSource("1, 1, 2, 2, 4i", "0, 1, 0, 1, -1")
    fun multiplication(firstReal: Double, firstImaginary: Double, secondReal: Double, secondImaginary: Double, result: String) {
        val first = Complex(firstReal, firstImaginary)
        val second = Complex(secondReal, secondImaginary)
        val third = first * second
        Assertions.assertEquals(result, third.toString())
    }

    @ParameterizedTest
    @CsvSource("1, 1, 2, 2, 0.5", "2, 2, 1, 1, 2", "5, 10, 4, 2, 2+1.5i")
    fun division(firstReal: Double, firstImaginary: Double, secondReal: Double, secondImaginary: Double, result: String) {
        val first = Complex(firstReal, firstImaginary)
        val second = Complex(secondReal, secondImaginary)
        val third = first / second
        Assertions.assertEquals(result, third.toString())
    }

    @Test
    fun conjugate() {
        val complex = Complex(5.0, 6.0)
        Assertions.assertEquals("5-6i", Complex.conjugate(complex).toString())
        Assertions.assertEquals("5-6i", complex.conjugate().toString())
    }

    @Test
    fun abs() {
        val complex = Complex(3.0, 4.0)
        Assertions.assertEquals(5.0, complex.abs(), Double.MIN_VALUE)
    }

    @Test
    fun arg() {
        val complex = Complex(1.0, 1.0)
        Assertions.assertEquals(Math.PI / 4, complex.arg(), Double.MIN_VALUE)
    }

    @Test
    fun equals(){
        val first = Complex(1.0, 4.0)
        val second = Complex(1.0, 4.0)
        Assertions.assertTrue(first === first)
        Assertions.assertTrue(first == first)
        Assertions.assertTrue(first == second)
        Assertions.assertFalse(first === second)
        Assertions.assertFalse(first != second)
    }

    @Test
    fun properties() {
        val a = Complex(1.0, 4.0)
        val b = Complex(2.0, 5.0)
        val c = a + b
        Assertions.assertEquals(a + b, b + a)
        Assertions.assertEquals(a*b, b*a)
        Assertions.assertEquals(a*(b + c), a*b + a*c)
    }
}