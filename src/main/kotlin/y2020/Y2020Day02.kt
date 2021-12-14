package y2020

import java.io.File

private fun main() {
    Y2020Day02().run()
}

class Y2020Day02 {

    private val year = "2020"; private val day = "02"
    val mainPath = "src/main/resources/y$year/"

    private val list = mutableListOf<String>()
    private var result = 0

    fun run() {
        loadFile()
        part1()
        part2();
    }

    private fun loadFile() {
        File(mainPath + year + "day"+ day +".txt").forEachLine {
            list.add(it)
        }
    }

    private fun part1() {
        result = 0

        for (firstIndex in 0 until list.size) {
            val line: List<String> = list[firstIndex].split(" ")
            val between: List<Int> = line[0].split("-").map { it.toInt() }
            val minAmount: Int = between[0]
            val maxAmount: Int = between[1]

            val letter: String = line[1].split(":")[0].trim()
            val password: String = line[2].trim()

            val countNrOfCorrectLettersInPassWord = password.count{ letter.contains(it) }

            if(countNrOfCorrectLettersInPassWord in minAmount..maxAmount) result++
        }

        println("$year-$day part #1 result => $result valid passwords")
    }

    private fun part2() {
        result = 0;

        for (currentIndex in 0 until list.size) {
            var checkedPost = 0

            val line: List<String> = list[currentIndex].split(" ")

            val between: List<Int> = line[0].split("-").map { it.toInt().minus(1) }
            val letter: Char = line[1].split(":")[0].first()
            val password: CharArray = line[2].toCharArray()

            val firstPositionToMatch: Int = between[0]
            val secondPositionToMatch: Int =  between[1]
            //if (between[1] <= password.size) secondPositionToMatch = between[1]

            if(password[firstPositionToMatch] == letter)  checkedPost++
            if(secondPositionToMatch > 0 && password[secondPositionToMatch] == letter) checkedPost++

            if (checkedPost == 1) result++
        }

        println("$year-$day part #2 result => $result valid passwords")
    }
}

