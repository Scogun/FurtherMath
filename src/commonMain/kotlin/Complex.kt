import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt

class Complex(private val real: Double, private val imaginary: Double) {

    operator fun plus(other: Complex) = Complex(real + other.real, imaginary + other.imaginary)
    operator fun minus(other: Complex) = Complex(real - other.real, imaginary - other.imaginary)

    operator fun times(other: Complex): Complex {
        val realMultiplication = real * other.real
        val imaginaryTimes = imaginary * other.imaginary
        val firstCross = real * other.imaginary
        val secondCross = imaginary * other.real
        return Complex(realMultiplication - imaginaryTimes, firstCross + secondCross)
    }

    operator fun div(other: Complex): Complex {
        val realMultiplication = real * other.real
        val imaginaryMultiplication = imaginary * other.imaginary
        val realPartNumerator = realMultiplication + imaginaryMultiplication
        val realPartDenominator = other.real.pow(2.0) + other.imaginary.pow(2.0)
        val realPart = realPartNumerator / realPartDenominator
        val firstCross = imaginary * other.real
        val secondCross = real * other.imaginary
        val imaginaryPartNumerator = firstCross - secondCross
        val imaginaryPart = imaginaryPartNumerator / realPartDenominator
        return Complex(realPart, imaginaryPart)
    }

    fun conjugate() = conjugate(this)

    fun abs() = abs(this)

    fun arg() = arg(this)

    override fun equals(other: Any?): Boolean {
        if (other !is Complex) {
            return false
        }

        return real == other.real && imaginary == other.imaginary
    }

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + imaginary.hashCode()
        return result
    }

    override fun toString() : String {
        val builder = StringBuilder()
        if (real != 0.0)
        {
            builder.append(real.toString().trimEnd('0').trimEnd('.'))
        }

        if (real != 0.0 && imaginary != 0.0)
        {
            if (imaginary > 0)
            {
                builder.append("+")
            }
        }

        if (imaginary != 0.0)
        {
            builder.append("${imaginary.toString().trimEnd('0').trimEnd('.')}i")
        }

        return builder.toString()
    }

    companion object {

        fun conjugate(complex: Complex) = Complex(complex.real, -complex.imaginary)
        fun abs(complex: Complex) = sqrt(complex.real.pow(2.0) + complex.imaginary.pow(2.0))
        fun arg(complex: Complex) = atan(complex.imaginary / complex.real)
    }
}