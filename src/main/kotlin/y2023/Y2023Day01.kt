package y2023

import java.io.File

private fun main() {
    Y2023Day01().run()
}

class Y2023Day01 {

    private val year = "2023"
    private val day = "01"
    private val list = arrayListOf<String>()
    val mainPath = "src/main/resources/y$year/"

    fun run() {
        loadFile()
        part1()
        part2()
    }

    private fun loadFile() {
        File(mainPath + year + "day"+ day +".txt").forEachLine {
            list.add(it)
        }
    }

    private fun part1() {

        val allRowValues = list.map { row ->
            val rowsDigits = row.filter { it.isDigit() }
            "${rowsDigits.first()}${rowsDigits.last()}".toInt()
        }

        println("$year-$day part #1 => ${allRowValues.sum()} is the sum of all of the calibration values")
    }

    private fun part2() {

        val replacedList = list.map { row ->
            Numbers.values().fold(row) { acc, enumValue ->
                acc.replace(enumValue.stringValue, enumValue.value, ignoreCase = true)
            }
        }

        val allRowValues = replacedList.map { row ->
            val rowsDigits = row.filter { it.isDigit() }
            "${rowsDigits.first()}${rowsDigits.last()}".toInt()
        }

        println("$year-$day part #2 => ${allRowValues.sum()} is the sum of all of the calibration values")
    }

    enum class Numbers(val stringValue: String, val value: String) {
        ONE("one", "o1e"),
        TWO("two", "t2o"),
        THREE("three", "t3e"),
        FOUR("four", "t4e"),
        FIVE("five", "f5e"),
        SIX("six", "s6"),
        SEVEN("seven", "s7n"),
        EIGHT("eight", "e8t"),
        NINE("nine", "n9e")
    }

}