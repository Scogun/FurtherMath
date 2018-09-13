class Quaternion(private val real: Double, private val iImaginary: Double, private val jImaginary: Double, private val kImaginary: Double) {

    operator fun plus(other: Quaternion): Quaternion {
        val newReal = real + other.real
        val newIImaginary = iImaginary + other.iImaginary
        val newJImaginary = jImaginary + other.jImaginary
        val newKImaginary = kImaginary + other.kImaginary
        return Quaternion(newReal, newIImaginary, newJImaginary, newKImaginary)
    }

    operator fun minus(other: Quaternion): Quaternion {
        val newReal = real - other.real
        val newIImaginary = iImaginary - other.iImaginary
        val newJImaginary = jImaginary - other.jImaginary
        val newKImaginary = kImaginary - other.kImaginary
        return Quaternion(newReal, newIImaginary, newJImaginary, newKImaginary)
    }

    operator fun times(other: Quaternion): Quaternion {
        val newReal = real*other.real - iImaginary*other.iImaginary - jImaginary*other.jImaginary - kImaginary*other.kImaginary
        val newIImaginary = real*other.iImaginary + iImaginary*other.real + jImaginary*other.kImaginary - kImaginary*other.jImaginary
        val newJImaginary = real*other.jImaginary - iImaginary*other.kImaginary + jImaginary*other.real + kImaginary*other.iImaginary
        val newKImaginary = real*other.kImaginary + iImaginary*other.jImaginary - jImaginary*other.iImaginary + kImaginary*other.real
        return Quaternion(newReal, newIImaginary, newJImaginary, newKImaginary)
    }

    fun toMatrix() = toMatrix(this)

    fun norm() = norm(this)

    fun abs() = abs(this)

    fun conjugate() = conjugate(this)

    override fun equals(other: Any?): Boolean {
        val otherQuaternion = other as Quaternion
        return real == otherQuaternion.real && iImaginary == otherQuaternion.iImaginary && jImaginary == otherQuaternion.jImaginary && kImaginary == otherQuaternion.kImaginary
    }

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + iImaginary.hashCode()
        result = 31 * result + jImaginary.hashCode()
        result = 31 * result + kImaginary.hashCode()
        return result
    }

    override fun toString(): String {
        val builder = StringBuilder()
        if (real != 0.0) {
            builder.append(real.toString().trimEnd('0').trimEnd('.'))
        }

        addImaginary(builder, iImaginary, 'i')
        addImaginary(builder, jImaginary, 'j')
        addImaginary(builder, kImaginary, 'k')

        return builder.toString()
    }

    private fun addImaginary(builder: StringBuilder, imaginary: Double, imaginaryName: Char) {
        if (imaginary != 0.0){
            if (builder.isNotEmpty() && imaginary > 0) {
                builder.append('+')
            }

            builder.append("${imaginary.toString().trimEnd('0').trimEnd('.')}$imaginaryName")
        }
    }

    companion object {

        fun toMatrix(quaternion: Quaternion) = Matrix(
                arrayListOf(
                        doubleArrayOf(quaternion.real, -quaternion.iImaginary, -quaternion.jImaginary, -quaternion.kImaginary),
                        doubleArrayOf(quaternion.iImaginary, quaternion.real, -quaternion.kImaginary, quaternion.jImaginary),
                        doubleArrayOf(quaternion.jImaginary, quaternion.kImaginary, quaternion.real, -quaternion.iImaginary),
                        doubleArrayOf(quaternion.kImaginary, -quaternion.jImaginary, quaternion.iImaginary, quaternion.real)
                )
        )

        fun norm(quaternion: Quaternion) = Math.pow(quaternion.real, 2.0) + Math.pow(quaternion.iImaginary, 2.0) + Math.pow(quaternion.jImaginary, 2.0) + Math.pow(quaternion.kImaginary, 2.0)

        fun abs(quaternion: Quaternion) = Math.sqrt(norm(quaternion))

        fun conjugate(quaternion: Quaternion) = Quaternion(quaternion.real, -quaternion.iImaginary, -quaternion.jImaginary, -quaternion.kImaginary)
    }
}