package y2021
/*
import java.io.File

private fun main() {
    Y2021Day03().run()
}

class Y2021Day03 {

    private val year = "2021"
    private val day = "03"
    private val list = arrayListOf<String>()
    val mainPath = "src/main/resources/y$year/"

    /*
    What is the power consumption of the submarine?
    Part 1 => 4174964
    What is the life support rating of the submarine?
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
                    "${Integer.parseInt(gammaRate.toString(), 2) * Integer.parseInt(epsilonRate.toString(), 2)} " +
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

            oxygenGenRating = if (onesAtColumn >= zerosAtColumn) filterKeepVal(oxygenGenRating, col, 1)
            else filterKeepVal(oxygenGenRating, col, 0)
        }

        var scrubberRating = list.toMutableList()

        for (col in 0 until scrubberRating[0].length) {
            val onesAtColumn = timesValueExistInCol(scrubberRating, col, 1)
            val zerosAtColumn = timesValueExistInCol(scrubberRating, col, 0)

            if (onesAtColumn.toInt() + zerosAtColumn.toInt() == 1) continue // if none left, end function

            scrubberRating = if (zerosAtColumn <= onesAtColumn) filterKeepVal(scrubberRating, col, 0)
            else filterKeepVal(scrubberRating, col, 1)
        }

        println(
            "$year-$day part #2 => ${Integer.parseInt(oxygenGenRating[0], 2) * Integer.parseInt(scrubberRating[0], 2)} " +
                    "<= Mission: Binary Diagnostic (life support rating)"
        )
    }

    private fun filterKeepVal(inputList: MutableList<String>, chosenColumn: Int, keepThisNr: Int): MutableList<String> {
        return inputList.stream().filter { it[chosenColumn].digitToInt() == keepThisNr }.toList()
    }

    private fun timesValueExistInCol(inputList: List<String>, chosenColumn: Int, nr: Int) =
        inputList.stream().filter { it[chosenColumn].digitToInt() == nr }.count()
}

 */