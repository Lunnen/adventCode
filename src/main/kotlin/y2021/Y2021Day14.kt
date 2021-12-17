package y2021

import java.io.File

fun main() {
    Y2021Day14().run()
}

class Y2021Day14 {

    val year = "2021";
    val day = "14"
    private val filePath = "src/main/resources/y$year/${year}day$day.txt"

    val list = File(filePath).useLines { it.toMutableList() }

    fun run() {
        partOne() // Answer: 992 is too high .. :(

    }

    private fun partOne() {
        println(runCalculation(1, 10))
        println(runCalculation(2, 40))
    }

    private fun runCalculation(part: Int, runTimes: Int): String {
        val template = list.takeWhile { it.isNotEmpty() }.first()
        val insertionRules =
            list.takeLastWhile { it.isNotEmpty() }.map { it.split(" -> ") }.map { it.first() to it.last() }.toHashSet()

        var workTemp = template

        var loopRound = 1
        repeat(runTimes) {
            println("Running new loop $loopRound")
            loopRound++

            while(workTemp.length > 0){

                println ( workTemp.take(2) )
            }
/*
            val buildupList = mutableListOf<String>()

            for (i in 1 until workTemp.length) {

                val whatToPutIn = insertionRules.first { it.first == (workTemp[i - 1] + workTemp[i].toString()) }.second

                if (whatToPutIn.isNotEmpty()) buildupList.add(workTemp[i - 1] + whatToPutIn)

            }
            buildupList.add(workTemp.last().toString())
            workTemp = buildupList.joinToString("")

 */
        }

        return "Part #$part result = > " +
                "Most ${workTemp.groupBy { it }.mapValues { it.value.size }.maxByOrNull { it.value }} " +
                "Least ${workTemp.groupBy { it }.mapValues { it.value.size }.minByOrNull { it.value }}"
    }

}




