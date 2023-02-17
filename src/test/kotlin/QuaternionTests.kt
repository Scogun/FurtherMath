import io.kotest.matchers.shouldBe
import kotlin.test.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class QuaternionTests {

    @ParameterizedTest
    @CsvSource("2, 3, 4, 5, 1, 1, 1, 1, 3+4i+5j+6k", "2, 3, 4, 5, 6, 7, 8, 9, 8+10i+12j+14k", "2, 1, 4, 0, 6, 7, 0, 9, 8+8i+4j+9k")
    fun sum(firstReal: Double, firstIImaginary: Double, firstJImaginary: Double, firstKImaginary: Double, secondReal: Double, secondIImaginary: Double, secondJImaginary: Double, secondKImaginary: Double, result: String) {
        val first = Quaternion(firstReal, firstIImaginary, firstJImaginary, firstKImaginary)
        val second = Quaternion(secondReal, secondIImaginary, secondJImaginary, secondKImaginary)
        val third = first + second
        third.toString().shouldBe(result)
    }

    @ParameterizedTest
    @CsvSource("2, 3, 4, 5, 1, 1, 1, 1, 1+2i+3j+4k", "2, 3, 4, 5, 6, 7, 8, 9, -4-4i-4j-4k", "2, 1, 4, 0, 6, 7, 0, 9, -4-6i+4j-9k")
    fun subtraction(firstReal: Double, firstIImaginary: Double, firstJImaginary: Double, firstKImaginary: Double, secondReal: Double, secondIImaginary: Double, secondJImaginary: Double, secondKImaginary: Double, result: String) {
        val first = Quaternion(firstReal, firstIImaginary, firstJImaginary, firstKImaginary)
        val second = Quaternion(secondReal, secondIImaginary, secondJImaginary, secondKImaginary)
        val third = first - second
        third.toString().shouldBe(result)
    }

    @ParameterizedTest
    @CsvSource("2, 3, 4, 5, 1, 1, 1, 1, -10+4i+8j+6k", "2, 3, 4, 5, 6, 7, 8, 9, -86+28i+48j+44k", "2, 1, 4, 0, 6, 7, 0, 9, 5+56i+15j-10k")
    fun multiplication(firstReal: Double, firstIImaginary: Double, firstJImaginary: Double, firstKImaginary: Double, secondReal: Double, secondIImaginary: Double, secondJImaginary: Double, secondKImaginary: Double, result: String) {
        val first = Quaternion(firstReal, firstIImaginary, firstJImaginary, firstKImaginary)
        val second = Quaternion(secondReal, secondIImaginary, secondJImaginary, secondKImaginary)
        val third = first*second
        third.toString().shouldBe(result)
        //Assertions.assertEquals(result, third.toString())
    }

    @Test
    fun toMatrix()
    {
        val quaternion = Quaternion(1.0, 2.0, 3.0, 4.0)
        quaternion.toMatrix().toString().shouldBe("1 -2 -3 -4\r\n2 1 -4 3\r\n3 4 1 -2\r\n4 -3 2 1")
    }

    @Test
    fun norm()
    {
        val quaternion = Quaternion(2.0, 2.0, 2.0, 2.0)
        quaternion.norm().shouldBe(16.0)
    }

    @Test
    fun abs()
    {
        val quaternion = Quaternion(2.0, 2.0, 2.0, 2.0)
        quaternion.abs().shouldBe(4.0)
    }

    @Test
    fun operations()
    {
        val first = Quaternion(2.0, 3.0, 4.0, 5.0)
        val second = Quaternion(6.0, 7.0, 8.0, 9.0)
        (first * second).conjugate().shouldBe(second.conjugate() * first.conjugate())
    }
}