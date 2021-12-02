package y2021

import java.io.File

class `2021Day01` {

    private val list = arrayListOf<Int>()
    private val mainPath = "src/main/kotlin/y2021/files/"
    private val year = "2021"
    private val day = "01"

    fun run() {
        loadFile()
        part1()
        part2();
    }

    private fun loadFile() {
        File(mainPath + year + "day"+ day +".txt").forEachLine {
            list.add(it.toInt())
        }
    }

    private fun part1() {

        var counter = 0;

        for (currentIndex in 1 until list.size) {
            if (list[currentIndex] > list[currentIndex - 1]) counter++
        }

        println("$year-$day part #1 => $counter <= Mission: Single Sonar-sweep")
    }

    private fun part2() {

        var counterTwo = 0;

        for (currentIndex in 3 until list.size) {

            if (list[currentIndex] > list[currentIndex - 3]) counterTwo++
        }

        println("$year-$day part #2 => $counterTwo <= Mission: Sonar-sweep in triples")
    }

}