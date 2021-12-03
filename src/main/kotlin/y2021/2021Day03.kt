package y2021

import java.io.File
import kotlin.math.pow

class `2021Day03` {

    private val year = "2021"
    private val day = "03"
    private val list = arrayListOf<String>()
    private val mainPath = "src/main/kotlin/y$year/files/"

    /*
    Part 1 => 4174964
    Part 2 => 4474944
     */

    fun run() {
        loadFile()
        part1()
        part2()
    }

    private fun loadFile() {
        File(mainPath + year + "day" + day + ".txt").forEachLine {
            list.add(it)
        }
    }

    private fun part1() {

        val columnWidth = list[0].length // All are the same length/width
        val gammaRate = StringBuilder()

        for (currentCol in 0 until columnWidth) {
            val onesAtColumn = timesValueExistInCol(list, currentCol, 1)
            val zerosAtColumn = timesValueExistInCol(list, currentCol, 0)

            if (onesAtColumn > zerosAtColumn) gammaRate.append(1) else gammaRate.append(0)
        }
        val epsilonRate = StringBuilder()
        gammaRate.forEach { if (it.digitToInt() == 1) epsilonRate.append(0) else epsilonRate.append(1) }

        println(
            "$year-$day part #1 => " +
                    "${
                        binaryToDecimal(gammaRate.toString().toLong()) * binaryToDecimal(
                            epsilonRate.toString().toLong()
                        )
                    } " +
                    "<= Mission: Binary Diagnostic (In Binary: $gammaRate and $epsilonRate)"
        )
    }

    private fun part2() {

        val columnWidth = list[0].length // All are the same length/width

        var oxygenGenRating = list.toMutableList()

        for (col in 0 until columnWidth) {
            val onesAtColumn = timesValueExistInCol(oxygenGenRating, col, 1)
            val zerosAtColumn = timesValueExistInCol(oxygenGenRating, col, 0)

            if (onesAtColumn.toInt() + zerosAtColumn.toInt() == 1) continue // if none left, end function

            oxygenGenRating = if (onesAtColumn >= zerosAtColumn) filterAndKeepOnly(oxygenGenRating, col, 1)
            else filterAndKeepOnly(oxygenGenRating, col, 0)
        }

        var scrubberRating = list.toMutableList()

        for (col in 0 until scrubberRating[0].length) {
            val onesAtColumn = timesValueExistInCol(scrubberRating, col, 1)
            val zerosAtColumn = timesValueExistInCol(scrubberRating, col, 0)

            if (onesAtColumn.toInt() + zerosAtColumn.toInt() == 1) continue // if none left, end function

            scrubberRating = if (zerosAtColumn <= onesAtColumn) filterAndKeepOnly(scrubberRating, col, 0)
            else filterAndKeepOnly(scrubberRating, col, 1)
        }

        println(
            "$year-$day part #2 => " +
                    "${binaryToDecimal(oxygenGenRating[0].toLong()) * binaryToDecimal(scrubberRating[0].toLong())} " +
                    "<= Mission: Binary Diagnostic (life support rating)"
        )
    }

    private fun filterAndKeepOnly(
        inputList: MutableList<String>,
        chosenColumn: Int,
        keepThisNr: Int
    ): MutableList<String> {
        return inputList.stream().filter { it[chosenColumn].digitToInt() == keepThisNr }.toList()
    }

    private fun timesValueExistInCol(inputList: List<String>, chosenColumn: Int, nr: Int) =
        inputList.stream().filter { it[chosenColumn].digitToInt() == nr }.count()

    private fun binaryToDecimal(inputBinary: Long): Int {
        var binaryNumber = inputBinary
        var decimalNo = 0
        var power = 0

        while (binaryNumber > 0) {
            val r = binaryNumber % 10
            decimalNo = (decimalNo + r * 2.0.pow(power.toDouble())).toInt()
            binaryNumber /= 10
            power++
        }
        return decimalNo
    }

}