package y2023

import utils.Utils
import java.util.regex.Pattern

private fun main() {
    Y2023Day03().part1()
    Y2023Day03().part2()
}

class Y2023Day03 {

    private val list = Utils.loadFile<String>("2023", "03")

    fun part1() {

        val numbersAdded = list.flatMapIndexed { rowIndex, row ->
            getRowNumbers(row).filter { (number, range) ->
                checkAround(list, range, rowIndex)
            }.map { it.first }
        }.sum()

        println("Total $numbersAdded")
    }

    fun part2() {
        val eachNumbersSymbolIndices = mutableListOf<Pair<Int, List<Pair<Int?, List<Int>>>>>()

        list.forEachIndexed() { rowIndex, row ->
                getRowNumbers(row).forEach { (number, range) ->
                    if (getIndicesOfSymbols(list, range, rowIndex).isNotEmpty()) { // Number have to have a neighboring symbol to be added
                        eachNumbersSymbolIndices.add(Pair(number, getIndicesOfSymbols(list, range, rowIndex)))
                    }
                }
            }

        val commonLastPartPairs = eachNumbersSymbolIndices
            .groupBy { it.second }
            .values
            .filter { it.size > 1 }
            .flatten()

        val alignedPairs = commonLastPartPairs.groupBy { it.second }
            .values.filter { it.size > 1 }
            .flatMap { group -> group.zipWithNext() }

        var totalCounter = 0

        alignedPairs.forEach { numberGroup ->
            val multiply = (numberGroup.first.first.toString().toInt() * numberGroup.second.first.toString().toInt())
            totalCounter += multiply
        }

        println("$totalCounter is the sum of all of the gear ratios in my engine schematic")

    }

    /* Both */
    private fun getRowNumbers(line: String): List<Pair<Int, IntRange>> {
        val p = Pattern.compile("\\d+")
        val m = p.matcher(line)
        val resultList = mutableListOf<Pair<Int, IntRange>>()

        while (m.find()) {
            val group = m.group()
            if (group.isNotEmpty()) {
                val startIndex = m.start()
                val endIndexExclusive = m.end()
                resultList.add(group.toInt() to (startIndex until endIndexExclusive))
            }
        }

        return resultList
    }

    private fun isAsymbol (inputChar: Char): Boolean {
        return  !inputChar.isDigit() && inputChar != '.' // All others are numbers in this rawData input
    }

    /* Part 1 */
    private fun checkAround(list: List<String>, range: IntRange, rowIndex: Int): Boolean {
        val above = if (rowIndex > 0)  checkOwnLine(list[rowIndex - 1], range) else false
        val ownLine = if (rowIndex >= 0)  checkOwnLine(list[rowIndex], range) else false
        val below = if (rowIndex >= 0 && rowIndex < list.size -1)  checkOwnLine(list[rowIndex + 1], range) else false

        return above || below || ownLine
    }

    /* Part 1 */
    private fun checkOwnLine(list: String, range: IntRange): Boolean {
        val charBefore = range.first - 1
        val charAfter = range.last + 1

        for (index in charBefore..charAfter) {
            if (index >= 0 && index < list.length && isAsymbol(list[index])) {
                return true
            }
        }

        return false
    }

    /* Part 2 */
    private fun getIndicesOfSymbols(list: List<String>, range: IntRange, rowIndex: Int): List<Pair<Int?, List<Int>>> {
        fun specificLineSymbols(list: String, range: IntRange): List<Int> {
            val charBefore = range.first - 1
            val charAfter = range.last + 1

            val returningIndices = arrayListOf<Int>()

            for (index in charBefore..charAfter) {
                if (index >= 0 && index < list.length && isAsymbol(list[index])) {
                    returningIndices += index
                }
            }

            return returningIndices
        }

        val above = if (rowIndex > 0) specificLineSymbols(list[rowIndex - 1], range) else listOf()
        val ownLine = if (rowIndex >= 0) specificLineSymbols(list[rowIndex], range) else listOf()
        val below =
            if (rowIndex >= 0 && rowIndex < list.size - 1) specificLineSymbols(list[rowIndex + 1], range) else listOf()

        // Return a list of pairs where number has symbols and their indices
        val pairs =  listOf(
            Pair(if (rowIndex > 0) rowIndex - 1 else null, above),
            Pair(rowIndex, ownLine),
            Pair(if (rowIndex >= 0 && rowIndex < list.size - 1) rowIndex + 1 else null, below)
        )

        return pairs.filter { it.first != null && it.second.isNotEmpty() }.distinctBy { it.toList() }
    }

}