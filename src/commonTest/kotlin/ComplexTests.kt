import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import kotlin.math.PI
import kotlin.test.Test

class ComplexTests{

    @Test
    fun sum() {
        val first = Complex(1.0, 1.0)
        val second = Complex(2.0, 2.0)
        val third = first + second
        third.toString().shouldBe("3+3i")
    }

    @Test
    fun subtraction()
    {
        val first = Complex(3.0, 4.0)
        val second = Complex(2.0, 2.0)
        val third = first - second
        third.toString().shouldBe("1+2i")
    }

    /*@ParameterizedTest
    @CsvSource("1, 1, 2, 2, 4i", "0, 1, 0, 1, -1")
    fun multiplication(firstReal: Double, firstImaginary: Double, secondReal: Double, secondImaginary: Double, result: String) {
        val first = Complex(firstReal, firstImaginary)
        val second = Complex(secondReal, secondImaginary)
        val third = first * second
        third.toString().shouldBe(result)
    }

    @ParameterizedTest
    @CsvSource("1, 1, 2, 2, 0.5", "2, 2, 1, 1, 2", "5, 10, 4, 2, 2+1.5i")
    fun division(firstReal: Double, firstImaginary: Double, secondReal: Double, secondImaginary: Double, result: String) {
        val first = Complex(firstReal, firstImaginary)
        val second = Complex(secondReal, secondImaginary)
        val third = first / second
        third.toString().shouldBe(result)
    }*/

    @Test
    fun conjugate() {
        val complex = Complex(5.0, 6.0)
        Complex.conjugate(complex).toString().shouldBe("5-6i")
        complex.conjugate().toString().shouldBe("5-6i")
    }

    @Test
    fun abs() {
        val complex = Complex(3.0, 4.0)
        complex.abs().shouldBe(5.0)
    }

    @Test
    fun arg() {
        val complex = Complex(1.0, 1.0)
        complex.arg().shouldBe(PI / 4)
    }

    @Test
    fun equals(){
        val first = Complex(1.0, 4.0)
        val second = Complex(1.0, 4.0)
        first.shouldBeSameInstanceAs(first)
        first.shouldBe(first)
        first.shouldBe(second)
        first.shouldNotBeSameInstanceAs(second)
        (first != second).shouldBeFalse()
    }

    @Test
    fun properties() {
        val a = Complex(1.0, 4.0)
        val b = Complex(2.0, 5.0)
        val c = a + b
        (a + b).shouldBe(b + a)
        (a*b).shouldBe(b*a)
        (a*(b + c)).shouldBe(a*b + a*c)
    }
}