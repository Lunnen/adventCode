package y2019

import java.io.File

fun main() {
    Y2019Day02().run()
}


class Y2019Day02 {

    val year = "2019";
    val day = "02"
    val mainPath = "src/main/resources/y$year/"


    val list = File(mainPath + year + "day" + day + ".txt").useLines { it.toMutableList() }

    fun run() {
        println(partOne()) // Answer: 6_568_671
        println(partTwo()) // Answer: 3_951
    }

    private fun partOne(): Int {

        return runOperation(12, 2)
    }

    private fun runOperation(placeOne: Int, placeTwo: Int): Int {
        var firstNumber: Int = -1

        list.forEach { row ->
            val number = row.split(",").map { it.toInt() }.toMutableList()

            number[1] = placeOne
            number[2] = placeTwo

            for (i in number.indices step 4) {

                when (number[i]) {
                    1 -> number[number[i + 3]] = number[number[i + 1]] + number[number[i + 2]]
                    2 -> number[number[i + 3]] = number[number[i + 1]] * number[number[i + 2]]
                    99 -> break;
                    else -> println("Wrong OP-code at $i")
                }
            }
            firstNumber = number[0]
        }
        return firstNumber
    }

    private fun partTwo(): Int {

            for (nounCounter in 0 until 100) {
                for (verbCounter in 0 until 100) {
                    if ( runOperation(nounCounter, verbCounter) == 19690720 ) return (100 * nounCounter + verbCounter)
                }
            }
        return 0
    }

}




