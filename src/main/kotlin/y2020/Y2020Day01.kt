package y2020

import java.io.File

class Y2020Day01 {

    private val list = arrayListOf<Int>()
    private val mainPath = "src/main/kotlin/y2020/files/"
    private val year = "2020"
    private val day = "01"

    val nrToMatchTogether = 2020;

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

        var result = 0;

        for (firstIndex in 0 until list.size-1) {
            for (secondIndex in 1 until list.size) {
                if (list[firstIndex] + list[secondIndex] == nrToMatchTogether) result = ( list[firstIndex] * list[secondIndex] )
            }

        }

        println("$year-$day part #1 result => $result are the multiplied answer of the matching pair $nrToMatchTogether")
    }

    private fun part2() {

        var result = 0;

        for (firstIndex in 0 until list.size-1) {
            for (secondIndex in 1 until list.size) {
                for (thirdIndex in 2 until list.size) {
                    if (list[firstIndex] + list[secondIndex] + list[thirdIndex] == nrToMatchTogether) result = (list[firstIndex] * list[secondIndex] * list[thirdIndex])
                }
            }
        }

        println("$year-$day part #2 result => $result are the multiplied answer of the matching pair $nrToMatchTogether")
    }

}