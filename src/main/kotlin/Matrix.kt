import kotlin.math.abs
import kotlin.math.pow

class Matrix {

    private val structure: Array<DoubleArray>

    constructor(rowNumber: Int, colNumber: Int) {
        structure = Array(rowNumber) { _ -> DoubleArray(colNumber) }
    }

    constructor(rows: List<DoubleArray>) : this(rows.size, rows[0].size) {
        rows.forEachIndexed  { i, row -> row.forEachIndexed { j, item ->  structure[i][j] = item } }
    }

    val rowCount: Int
        get() = structure.size

    val colCount : Int
        get() = structure[0].size

    private fun rows() = structure.toList()

    operator fun get(rowIndex: Int, colIndex: Int) = structure[rowIndex][colIndex]
    private operator fun set(rowNumber: Int, colNumber: Int, value: Double) {
        structure[rowNumber][colNumber] = value
    }

    operator fun plus(other: Matrix) : Matrix {
        if (rowCount != other.rowCount || colCount != other.colCount) {
            throw Exception("For sum operation both matrix must have the same dimensions!")
        }

        val data = ArrayList<DoubleArray>(rowCount)
        structure.forEachIndexed { i, row -> data.add(row.mapIndexed { j, item -> item + other[i, j] }.toDoubleArray()) }
        return Matrix(data)
    }

    operator fun times(other: Double) = Matrix(structure.map { row -> row.map {item -> item * other}.toDoubleArray() })
    operator fun times(other: Int) = Matrix(structure.map { row -> row.map {item -> item * other}.toDoubleArray() })
    operator fun minus(other: Matrix) = try { this + (other * -1) } catch (_: Exception) { throw Exception("For minus operation both matrix must have the same dimensions!") }

    operator fun times(other: Matrix) : Matrix {
        if (colCount != other.rowCount) {
            throw Exception("For multiplication first matrix column count must be equal to second matrix row count!")
        }

        val result = Matrix(rowCount, other.colCount)
        for (i in 0 until result.rowCount) {
            for (j in 0 until result.colCount) {
                var sum = 0.0
                for (k in 0 until colCount) {
                    sum += this[i, k] * other[k, j]
                }

                result[i, j] = sum
            }
        }

        return result
    }

    fun transposition() = transposition(this)

    fun minor(rowIndex: Int, colIndex: Int) = minor(this, rowIndex, colIndex)

    fun determinant() = determinant(this)

    fun adjugate() = adjugate(this)

    fun inverted() = inverted(this)

    override fun toString(): String {
        return structure.joinToString("\r\n") { row -> row.joinToString(" ") { item -> item.toString().trimEnd('0').trimEnd('.') } }
    }

    override fun equals(other: Any?): Boolean {
        val otherMatrix = other as Matrix
        if (rowCount != otherMatrix.rowCount || colCount != otherMatrix.colCount) {
            return false
        }

        rows().forEachIndexed { i, row -> row.forEachIndexed { j, _ -> if (abs(this[i, j] - otherMatrix[i, j]) > 1*10.0.pow(-15.0)) { return false } } }
        return true
    }

    override fun hashCode(): Int {
        return structure.contentDeepHashCode()
    }

    companion object {

        fun transposition(matrix: Matrix) : Matrix {
            val rows = ArrayList<DoubleArray>(matrix.colCount)
            for (j in 0 until matrix.colCount) {
                val row = DoubleArray(matrix.rowCount)
                for (i in 0 until matrix.rowCount) {
                    row[i] = matrix[i, j]
                }
                rows.add(row)
            }

            return Matrix(rows)
        }

        fun minor(matrix: Matrix, rowIndex: Int, colIndex: Int) : Matrix {
            if (matrix.rowCount < 2 || matrix.colCount < 2 ){
                throw Exception("For take a minor matrix must have size 2x2 or bigger!")
            }

            val rows = ArrayList<DoubleArray>(matrix.rowCount - 1)
            for (i in 0 until matrix.rowCount) {
                if (i == rowIndex) {
                    continue
                }

                val row = DoubleArray(matrix.colCount - 1)
                var k = 0
                for (j in 0 until matrix.colCount) {
                    if (j == colIndex) {
                        continue
                    }
                    row[k++] = matrix[i, j]
                }
                rows.add(row)
            }

            return Matrix(rows)
        }

        fun determinant(matrix: Matrix) : Double {
            if (matrix.rowCount != matrix.colCount) {
                throw Exception("Determinant is available only for square matrix!")
            }

            if (matrix.rowCount == 2) {
                return matrix[0, 0]*matrix[1, 1] - matrix[0, 1]*matrix[1, 0]
            }

            var result = 0.0
            for (i in 0 until matrix.colCount) {
                result += (-1.0).pow(i.toDouble()) *matrix[0, i]*matrix.minor(0, i).determinant()
            }
            return result
        }

        fun adjugate(matrix: Matrix) : Matrix {
            if (matrix.rowCount != matrix.colCount) {
                throw Exception("Adjugate matrix is available only for square matrix!")
            }

            val result = Matrix(matrix.rowCount, matrix.colCount)
            if (matrix.rowCount == 2) {
                result[0, 0] = matrix[1, 1]
                result[0, 1] = -matrix[0, 1]
                result[1, 0] = -matrix[1, 0]
                result[1, 1] = matrix[0, 0]
            } else {
                for (i in 0 until matrix.rowCount) {
                    for (j in 0 until matrix.colCount) {
                        result[i, j] = (-1.0).pow((i + j).toDouble()) *matrix.minor(i, j).determinant()
                    }
                }
            }

            return result
        }

        fun inverted(matrix: Matrix) : Matrix {
            val determinant: Double
            try {
                determinant = matrix.determinant()
            } catch (_: Exception) {
                throw Exception("Only for square matrix possible to find inverse matrix!")
            }
            if (determinant == 0.0) {
                throw Exception("There is not inverted matrix for degenerate matrix!")
            }

            return matrix.adjugate().transposition() * (1/determinant)
        }

        fun makeIdentity(size: Int) : Matrix {
            val result = Matrix(size, size)
            for (i in 0 until size) {
                for (j in 0 until size) {
                    result[i, j] = if (i == j) 1.0 else 0.0
                }
            }

            return result
        }
    }
}