import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MatrixTests {

    @Test
    fun string() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(11.0, 12.0), doubleArrayOf(21.0, 22.0)))
        Assertions.assertEquals("11 12\r\n21 22", matrix.toString())
    }

    @Test()
    fun sum() {
        val first = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0)))
        val second = Matrix(arrayListOf(doubleArrayOf(4.0, 3.0), doubleArrayOf(2.0, 1.0)))
        val sum = first + second
        Assertions.assertEquals("5 5\r\n5 5", sum.toString())
        val bigger = Matrix(2, 3)
        Assertions.assertThrows(Exception::class.java) {first + bigger}
    }

    @Test
    fun scalarMultiplication() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0)))
        val result = matrix * 2
        Assertions.assertEquals("2 4\r\n6 8", result.toString())
    }

    @Test()
    fun multiplication() {
        val first = Matrix(arrayListOf(doubleArrayOf(1.0, -1.0), doubleArrayOf(2.0, .0), doubleArrayOf(3.0, .0)))
        val second = Matrix(arrayListOf(doubleArrayOf(1.0, 1.0), doubleArrayOf(2.0, .0)))
        val result = first * second
        Assertions.assertEquals("-1 1\r\n2 2\r\n3 3", result.toString())
        val bigger = Matrix(3,2)
        Assertions.assertThrows(Exception::class.java) {first*bigger}
    }

    @Test()
    fun subtraction(){
        val first = Matrix(arrayListOf(doubleArrayOf(5.0, 6.0), doubleArrayOf(7.0, 8.0)))
        val second = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0)))
        val subtraction = first - second
        Assertions.assertEquals("4 4\r\n4 4", subtraction.toString())
        val bigger = Matrix(2, 3)
        Assertions.assertThrows(Exception::class.java) {first - bigger}
    }

    @Test
    fun transposition() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0, 3.0), doubleArrayOf(4.0, 5.0, 6.0)))
        Assertions.assertEquals("1 4\r\n2 5\r\n3 6", matrix.transposition().toString())
    }

    @Test()
    fun minor() {
        val matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 2.0, 3.0), doubleArrayOf(4.0, 5.0, 6.0)))
        val minor = matrix.minor(0, 0)
        Assertions.assertEquals("5 6", minor.toString())
        Assertions.assertThrows(Exception::class.java) { minor.minor(0, 0) }
    }

    @Test()
    fun determinant() {
        var matrix = Matrix(arrayListOf(doubleArrayOf(11.0, -2.0), doubleArrayOf(7.0, 5.0)))
        Assertions.assertEquals(69.0, matrix.determinant(), Double.MIN_VALUE)
        matrix = Matrix(arrayListOf(doubleArrayOf(3.0, 3.0, -1.0), doubleArrayOf(4.0, 1.0, 3.0), doubleArrayOf(1.0, -2.0, -2.0)))
        Assertions.assertEquals(54.0, matrix.determinant(), Double.MIN_VALUE)
        matrix = Matrix(arrayListOf(doubleArrayOf(-2.0, 1.0, 3.0, 2.0), doubleArrayOf(3.0, 0.0, -1.0, 2.0), doubleArrayOf(-5.0, 2.0, 3.0, 0.0), doubleArrayOf(4.0, -1.0, 2.0, -3.0)))
        Assertions.assertEquals(-80.0, matrix.determinant(), Double.MIN_VALUE)
        Assertions.assertThrows(Exception::class.java) { Matrix(2, 3).determinant() }
    }

    @Test
    fun adjugate() {
        var matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 2.0)))
        Assertions.assertEquals("2 -1\r\n-1 1", matrix.adjugate().toString())
        matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 0.0, 2.0), doubleArrayOf(2.0, -1.0, 1.0), doubleArrayOf(1.0, 3.0, -1.0)))
        Assertions.assertEquals("-2 3 7\r\n6 -3 -3\r\n2 3 -1", matrix.adjugate().toString())
    }

    @Test
    fun inverted() {
        var matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 2.0)))
        Assertions.assertEquals("2 -1\r\n-1 1", matrix.inverted().toString())
        matrix = Matrix(arrayListOf(doubleArrayOf(1.0, 0.0, 2.0), doubleArrayOf(2.0, -1.0, 1.0), doubleArrayOf(1.0, 3.0, -1.0)))
        Assertions.assertEquals(Matrix.makeIdentity(3), matrix * matrix.inverted())
    }

    @Test
    fun equals() {
        val first = Matrix(arrayListOf(doubleArrayOf(5.0, 6.0), doubleArrayOf(7.0, 8.0)))
        var second = Matrix(arrayListOf(doubleArrayOf(5.0, 6.0), doubleArrayOf(7.0, 8.0)))
        Assertions.assertEquals(first, second)
        second =  Matrix(arrayListOf(doubleArrayOf(10.0, 6.0), doubleArrayOf(7.0, 8.0)))
        Assertions.assertNotEquals(first, second)
    }

    @Test
    fun properies() {
        val a = Matrix(arrayListOf(doubleArrayOf(1.0, 4.0), doubleArrayOf(2.0, 3.0)))
        val b = Matrix(arrayListOf(doubleArrayOf(4.0, 4.0), doubleArrayOf(5.0, 2.0)))
        val c = a + b
        Assertions.assertEquals(a + b + c, a + (b + c))
        Assertions.assertEquals(a + b, b + a)
        Assertions.assertEquals(a*b*c, a*(b*c))
        Assertions.assertNotEquals(a*b, b*a)
        Assertions.assertEquals(a*(b + c), a*b + a*c)
        Assertions.assertEquals(a, a.transposition().transposition())
        Assertions.assertEquals((a*b).transposition(), b.transposition()*a.transposition())
        Assertions.assertEquals(a.inverted().transposition(), a.transposition().inverted())
        Assertions.assertEquals((a + b).transposition(), a.transposition() + b.transposition())
        Assertions.assertEquals(a.determinant(), a.transposition().determinant(), Double.MIN_VALUE)
    }
}