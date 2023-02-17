import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.lang.Exception
import kotlin.test.Test

class MatrixTests {

    @Test
    fun string() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(11.0, 12.0), doubleArrayOf(21.0, 22.0)))
        matrix.toString().shouldBe("11 12\r\n21 22")
    }

    @Test
    fun sum() {
        val first = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0)))
        val second = Matrix(arrayListOf(doubleArrayOf(4.0, 3.0), doubleArrayOf(2.0, 1.0)))
        val sum = first + second
        sum.toString().shouldBe("5 5\r\n5 5")
        val bigger = Matrix(2, 3)
        shouldThrowWithMessage<Exception>("For sum operation both matrix must have the same dimensions!") { first + bigger }
    }

    @Test
    fun scalarMultiplication() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0)))
        val result = matrix * 2
        result.toString().shouldBe("2 4\r\n6 8")
    }

    @Test
    fun multiplication() {
        val first = Matrix(arrayListOf(doubleArrayOf(1.0, -1.0), doubleArrayOf(2.0, .0), doubleArrayOf(3.0, .0)))
        val second = Matrix(arrayListOf(doubleArrayOf(1.0, 1.0), doubleArrayOf(2.0, .0)))
        val result = first * second
        result.toString().shouldBe("-1 1\r\n2 2\r\n3 3")
        val bigger = Matrix(3,2)
        shouldThrowWithMessage<Exception>("For multiplication first matrix column count must be equal to second matrix row count!") { first*bigger }
    }

    @Test
    fun subtraction(){
        val first = Matrix(arrayListOf(doubleArrayOf(5.0, 6.0), doubleArrayOf(7.0, 8.0)))
        val second = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0)))
        val subtraction = first - second
        subtraction.toString().shouldBe("4 4\r\n4 4")
        val bigger = Matrix(2, 3)
        shouldThrowWithMessage<Exception>("For minus operation both matrix must have the same dimensions!") { first - bigger }
    }

    @Test
    fun transposition() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0, 3.0), doubleArrayOf(4.0, 5.0, 6.0)))
        matrix.transposition().toString().shouldBe("1 4\r\n2 5\r\n3 6")
    }

    @Test()
    fun minor() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0, 3.0), doubleArrayOf(4.0, 5.0, 6.0)))
        val minor = matrix.minor(0, 0)
        minor.toString().shouldBe("5 6")
        shouldThrowWithMessage<Exception>("For take a minor matrix must have size 2x2 or bigger!") { minor.minor(0, 0) }
    }

    @Test()
    fun determinant() {
        var matrix = Matrix(arrayListOf(doubleArrayOf(11.0, -2.0), doubleArrayOf(7.0, 5.0)))
        matrix.determinant().shouldBe(69.0)
        matrix = Matrix(arrayListOf(doubleArrayOf(3.0, 3.0, -1.0), doubleArrayOf(4.0, 1.0, 3.0), doubleArrayOf(1.0, -2.0, -2.0)))
        matrix.determinant().shouldBe(54.0)
        matrix = Matrix(arrayListOf(doubleArrayOf(-2.0, 1.0, 3.0, 2.0), doubleArrayOf(3.0, 0.0, -1.0, 2.0), doubleArrayOf(-5.0, 2.0, 3.0, 0.0), doubleArrayOf(4.0, -1.0, 2.0, -3.0)))
        matrix.determinant().shouldBe(-80.0)
        shouldThrowWithMessage<Exception>("Determinant is available only for square matrix!") { Matrix(2, 3).determinant() }
    }

    @Test
    fun adjugate() {
        var matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 2.0)))
        matrix.adjugate().toString().shouldBe("2 -1\r\n-1 1")
        matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 0.0, 2.0), doubleArrayOf(2.0, -1.0, 1.0), doubleArrayOf(1.0, 3.0, -1.0)))
        matrix.adjugate().toString().shouldBe("-2 3 7\r\n6 -3 -3\r\n2 3 -1")
        matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 0.0, 2.0), doubleArrayOf(2.0, -1.0, 1.0)))
        shouldThrowWithMessage<Exception>("Adjugate matrix is available only for square matrix!") { matrix.adjugate() }
    }

    @Test
    fun inverted() {
        var matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 2.0)))
        matrix.inverted().toString().shouldBe("2 -1\r\n-1 1")
        matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 0.0, 2.0), doubleArrayOf(2.0, -1.0, 1.0), doubleArrayOf(1.0, 3.0, -1.0)))
        (matrix * matrix.inverted()).shouldBe(Matrix.makeIdentity(3))
        shouldThrowWithMessage<Exception>("Only for square matrix possible to find inverse matrix!") { Matrix(2, 3).inverted() }
        shouldThrowWithMessage<Exception>("There is not inverted matrix for degenerate matrix!") { Matrix(arrayListOf(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 1.0))).inverted() }
    }

    @Test
    fun equals() {
        val first = Matrix(arrayListOf(doubleArrayOf(5.0, 6.0), doubleArrayOf(7.0, 8.0)))
        var second = Matrix(arrayListOf(doubleArrayOf(5.0, 6.0), doubleArrayOf(7.0, 8.0)))
        first.shouldBe(second)
        second =  Matrix(arrayListOf(doubleArrayOf(10.0, 6.0), doubleArrayOf(7.0, 8.0)))
        first.shouldNotBe(second)
        second =  Matrix(arrayListOf(doubleArrayOf(5.0, 6.0, 7.0), doubleArrayOf(7.0, 8.0, 9.0)))
        first.shouldNotBe(second)
    }

    @Test
    fun properies() {
        val a = Matrix(arrayListOf(doubleArrayOf(1.0, 4.0), doubleArrayOf(2.0, 3.0)))
        val b = Matrix(arrayListOf(doubleArrayOf(4.0, 4.0), doubleArrayOf(5.0, 2.0)))
        val c = a + b
        (a + b + c).shouldBe(a + (b + c))
        (a + b).shouldBe(b + a)
        (a*b*c).shouldBe(a*(b*c))
        (a*b).shouldNotBe(b*a)
        (a*(b + c)).shouldBe(a*b + a*c)
        a.shouldBe(a.transposition().transposition())
        (a*b).transposition().shouldBe(b.transposition()*a.transposition())
        a.inverted().transposition().shouldBe(a.transposition().inverted())
        (a + b).transposition().shouldBe(a.transposition() + b.transposition())
        a.determinant().shouldBe(a.transposition().determinant())
    }
}